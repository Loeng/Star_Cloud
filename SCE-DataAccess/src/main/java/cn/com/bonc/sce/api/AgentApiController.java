package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.UserInfoRepository;
import cn.com.bonc.sce.dao.UserPasswordDao;
import cn.com.bonc.sce.entity.Agent;
import cn.com.bonc.sce.entity.UserPassword;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.repository.AgentRepository;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IDUtil;
import cn.com.bonc.sce.utils.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BTW
 */
@Slf4j
@RestController
@RequestMapping( "/agent" )
public class AgentApiController {

    private AgentRepository agentRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserPasswordDao passwordDao;

    @Autowired
    public AgentApiController( AgentRepository agentRepository ) {
        this.agentRepository = agentRepository;
    }

    /**
     * 添加单个代理信息
     *
     * @param agent 用户输入的代理信息
     * @return 返回是否添加成功
     */
    @PostMapping
    @ResponseBody
    public RestRecord saveAgentInfo(
            @RequestBody Agent agent ) {
        log.trace( "add agent :{}", agent );
        try {
            Agent saveAgentInfo = agentRepository.save( agent );
            //创建“管理员账号”
            String userId = UUID.getUUID();
            userInfoRepository.insertUser( userId, IDUtil.createID( "dl_" ), "", "", 6, "", 0, "", "",
                    "", new Date(), String.valueOf( saveAgentInfo.getAgentId() ), "代理管理员", Secret.generateSecret() );
            //创建密码
            UserPassword password = new UserPassword();
            password.setIsDelete( 1 );
            password.setUserId( userId );
            password.setPassword( "star123!" );
            passwordDao.save( password );
            RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
            restRecord.setData( agent );
            return restRecord;
        } catch ( Exception e ) {
            return new RestRecord( 409, WebMessageConstants.SCE_PORTAL_MSG_423 );
        }
    }


    /**
     * 查询所有代理用户
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping( "/all-user-info" )
    @ResponseBody
    public RestRecord getAllAgentUserInfo(
            @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
            @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize ) {
        try {
            Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
            Page page = agentRepository.getAllAgentUserInfo( pageable );
            Map< String, Object > temp = new HashMap<>( pageSize );
            if ( null != page ) {
                temp.put( "data", page.getContent() );
                temp.put( "totalPage", page.getTotalPages() );
                temp.put( "totalCount", page.getTotalElements() );
            }
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, temp );
        } catch ( Exception e ) {
            return new RestRecord( 409, WebMessageConstants.SCE_PORTAL_MSG_423 );
        }
    }
}
