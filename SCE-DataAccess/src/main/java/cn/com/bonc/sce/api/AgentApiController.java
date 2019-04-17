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
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    @PersistenceContext
    private EntityManager entityManager;

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
                    "", new Date(), saveAgentInfo.getAgentId(), "代理管理员", Secret.ES256GenerateSecret() );
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

    @GetMapping( "/getCompanyList/{AGENT_NAME}/{PROPERTY}/{AUDIT_STATUS}/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getCompanyList(@PathVariable("AGENT_NAME") String AGENT_NAME,@PathVariable("PROPERTY") String PROPERTY, @PathVariable("AUDIT_STATUS") Integer AUDIT_STATUS
            , @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        try {
            RestRecord restRecord = new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200);
            Page<List<Map<String, Object>>> page;
            StringBuilder sql = new StringBuilder( "SELECT\n" +
                    "\tsma.ID,\n" +
                    "\tsma.AGENT_NAME,\n" +
                    "\tsma.PROPERTY,\n" +
                    "\tscu.USER_NAME,\n" +
                    "\tscu.LOGIN_NAME,\n" +
                    "\tsua.AUDIT_STATUS,\n" +
                    "\tsua.AUDIT_TIME \n" +
                    "FROM\n" +
                    "\tSTARCLOUDPORTAL.SCE_ENTITY_AGENT sma\n" +
                    "\tLEFT JOIN STARCLOUDPORTAL.SCE_USER_AUDIT sua ON sma.ID = to_char(sua.ENTITY_ID)\n" +
                    "\tLEFT JOIN STARCLOUDPORTAL.SCE_COMMON_USER scu ON sua.USER_ID = scu.USER_ID \n" +
                    "WHERE\n" +
                    "\tsma.IS_DELETE = 1  " );
            if(!StringUtils.isEmpty(AGENT_NAME) && !"null".equals(AGENT_NAME)){
                sql.append("and sma.AGENT_NAME like '%"+AGENT_NAME+"%' ");
            }
            if(!StringUtils.isEmpty(PROPERTY) && !"null".equals(PROPERTY)){
                sql.append("and smc.PROPERTY = "+PROPERTY+" ");
            }
            if(StringUtils.isEmpty(AUDIT_STATUS) || "null".equals(AUDIT_STATUS) ){
                sql.append("AND ( sua.AUDIT_STATUS = 0  OR sua.AUDIT_STATUS = 2 ) ");
            }
            if(!StringUtils.isEmpty(AUDIT_STATUS) && "0".equals(AUDIT_STATUS) ){
                sql.append("AND  sua.AUDIT_STATUS = 0 ");
            }
            if(!StringUtils.isEmpty(AUDIT_STATUS) && "2".equals(AUDIT_STATUS) ){
                sql.append("AND  sua.AUDIT_STATUS = 2 ");
            }

            Session session = entityManager.unwrap( org.hibernate.Session.class );
            NativeQuery query = session.createNativeQuery( sql.toString() );
            query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
            int start = ( pageNum - 1 ) * pageSize;
            int total = query.getResultList().size();
            //判断分页
            if ( start < total && pageSize > 0 ) {
                query.setFirstResult( start );
                query.setMaxResults( pageSize );
            }
            Map< String, Object > temp = new HashMap<>( 16 );
            temp.put( "data", query.getResultList() );
            temp.put( "totalPage", ( total + pageSize - 1 ) / pageSize );
            temp.put( "totalCount", total );
            restRecord.setData( temp );
            return restRecord;
        } catch ( Exception e ) {
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420, e );
        }
    }

}
