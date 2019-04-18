package cn.com.bonc.sce.api;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.AgentBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.bean.UserBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AgencyService;
import cn.com.bonc.sce.service.UserService;
import cn.com.bonc.sce.tool.IDUtil;
import cn.com.bonc.sce.tool.IdWorker;
import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/4.
 */
@Slf4j
@RestController
@RequestMapping( "/agent" )
public class AgencyController {

    @Autowired
    private AgencyService agencyService;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private UserService userService;

    @ApiOperation( value = "代理启用与禁用", notes = "通过当前代理活跃状态，改变代理状态", httpMethod = "PUT" )
    @PutMapping( "/editActivity" )
    @ResponseBody
    public RestRecord editActivity( @RequestParam( "id" ) long id, @RequestParam( "isActivate" ) Integer isActivate ) {
        int newStatus = ( isActivate == 0 ) ? 1 : 0;
        if ( agencyService.editActivity( id, newStatus ) == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, 1 );
        } else {
            return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
        }
    }

    @ApiOperation( value = "代理商信息编辑", notes = "通过代理商id修改代理商名称和省市区", httpMethod = "PUT" )
    @PutMapping( "/editInfo" )
    @ResponseBody
    public RestRecord editInfo( @RequestBody @ApiParam( example = "{\"id\": 1743,\"AGENT_NAME\": \"测试一下代理商名称\",\"PROVINCE\": \"四川\",\"CITY\": \"成都\",\"AREA\": \"青羊区\"}" ) String json ) {
        Map map = ( Map ) JSONUtils.parse( json );
        Long id = Long.parseLong( map.get( "id" ).toString() );
        String agentName = ( String ) map.get( "AGENT_NAME" );
        String province = ( String ) map.get( "PROVINCE" );
        String city = ( String ) map.get( "CITY" );
        String area = ( String ) map.get( "AREA" );

        if ( agencyService.editInfo( id, agentName, province, city, area ) == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, 1 );
        } else {
            return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
        }
    }

    @ApiOperation( value = "代理商代理学校列表查询", notes = "通过代理商id查询代理学校列表", httpMethod = "GET" )
    @GetMapping( "/getSchools/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getSchools( @RequestParam( "id" ) long id,
                                  @PathVariable( value = "pageNum" ) Integer pageNum,
                                  @PathVariable( value = "pageSize" ) Integer pageSize ) {

        PageHelper.startPage( pageNum, pageSize );
        List< SchoolBean > schoolList = agencyService.getSchools( id );
        PageInfo pageInfo = new PageInfo( schoolList );
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, pageInfo );
    }

    @ApiOperation( value = "代理商代理学校删除", notes = "通过代理商id和学校id删除代理商和学校的代理关系", httpMethod = "DELETE" )
    @DeleteMapping( "/delSchoolRel" )
    @ResponseBody
    public RestRecord delSchoolRel( @RequestParam( "agentId" ) long agentId,
                                    @RequestParam( "schoolId" ) long schoolId ) {
        int count = agencyService.delSchoolRel( agentId, schoolId );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, 1 );
        } else {
            return new RestRecord( 407, MessageConstants.SCE_MSG_408, 0 );
        }
    }

    @ApiOperation( value = "获取代理商列表", notes = "获取查询条件，返回代理商列表", httpMethod = "GET" )
    @GetMapping( "/getAgents/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getAgents( @RequestParam( value = "agentName", required = false ) String agentName,
                                 //@RequestParam(value = "isActivate",required = false) Integer isActivate,
                                 @RequestParam( value = "grade", required = false ) String grade,
                                 @RequestParam( value = "agentArea", required = false ) String agentArea,
                                 @PathVariable( value = "pageNum" ) Integer pageNum,
                                 @PathVariable( value = "pageSize" ) Integer pageSize ) {

        PageHelper.startPage( pageNum, pageSize );
        List< AgentBean > agentList = agencyService.getAgents( agentName, grade, agentArea );
        PageInfo pageInfo = new PageInfo( agentList );
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, pageInfo );
    }

    @Transactional
    @ApiOperation( value = "新增代理商信息", notes = "获取用户编辑数据，写入代理商信息，并在用户信息表和账号密码表插入对应数据", httpMethod = "POST" )
    @PostMapping( "/insertInfo" )
    @ResponseBody
    public RestRecord insertInfo( @RequestBody @ApiParam( example = "{\"AGENT_NAME\": \"测试一下代理商名称\",\"PROVINCE\": \"四川\",\"CITY\": \"成都\",\"AREA\": \"青羊区\"}" ) String json ) {
        Map map = ( Map ) JSONUtils.parse( json );
        long agentId = idWorker.nextId();
        AgentBean agentBean = new AgentBean();
        agentBean.setAgentName( ( String ) map.get( "AGENT_NAME" ) );
        agentBean.setProvince( ( String ) map.get( "PROVINCE" ) );
        agentBean.setCity( ( String ) map.get( "CITY" ) );
        agentBean.setArea( ( String ) map.get( "AREA" ) );
        agentBean.setIsDelete( 1 );
        agentBean.setId( agentId );

        UserBean user = new UserBean();
        long userId = idWorker.nextId();
        String secret = Secret.generateSecret();
        String loginName = IDUtil.createID( "dl_" );
        user.setSecret( secret );
        user.setLoginName( loginName );
        user.setUserType( 6 );
        user.setIsDelete( 1 );
        user.setUserId( userId );
        user.setOrganizationId( agentId );

        AccountBean account = new AccountBean();
        long accountId = idWorker.nextId();
        account.setId( accountId );
        account.setPassword( "star123!" );
        account.setIsDelete( 1 );
        account.setUserId( userId );

        if ( agencyService.saveAgent( agentBean ) == 1
                && userService.saveUser( user ) == 1
                && userService.saveAccount( account ) == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, 1 );
        } else {
            return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
        }
    }

    @ApiOperation( value = "个人中心代理商信息获取", notes = "通过代理商id代理商信息", httpMethod = "POST" )
    @PostMapping( "/getInfo" )
    @ResponseBody
    public RestRecord getInfo( @RequestParam( "id" ) long id ) {
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, agencyService.getInfo( id ) );
    }
}
