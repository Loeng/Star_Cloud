package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.AgentModel;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author BTW
 */
@Repository
@FeignClient( "sce-data-access" )
public interface AgentDao {

    /**
     * 新增代理信息
     *
     * @param agentModel
     * @return
     */
    @RequestMapping( value = "/agent", method = RequestMethod.POST )
    public RestRecord saveAgentInfo(
            @RequestBody AgentModel agentModel );

    @RequestMapping( value = "/agent/addAgent/{roleId}", method = RequestMethod.POST )
    RestRecord addAgent(@RequestBody AgentModel agent,@RequestParam( "userId" ) String userId,@PathVariable( "roleId" ) Integer roleId );

    @RequestMapping( value = "/agent/updateAgentById", method = RequestMethod.PUT )
    RestRecord updateAgentById(@RequestBody AgentModel agent );

    @RequestMapping( value = "/agent/{id}",method = RequestMethod.GET )
    RestRecord getAgentById(@PathVariable( "id" ) String id );

    @RequestMapping( value = "/agent/updateAgent", method = RequestMethod.PUT )
    RestRecord updateAgent(@RequestBody @ApiParam( "厂商信息对象" ) AgentModel agent);

    /**
     * 查询所有代理用户信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping( value = "/agent/all-user-info", method = RequestMethod.GET )
    public RestRecord getAllAgentUserInfo(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize );

    @RequestMapping( value = "/agent/getAgentInfo/{pageNum}/{pageSize}", method = RequestMethod.GET )
    public RestRecord getAgentInfo(@RequestParam(value = "agentName",required = false) @ApiParam( name = "agentName", value = "代理商名称") String agentName,@RequestParam(value = "property",required = false) @ApiParam( name = "property", value = "公司性质") String property, @RequestParam(value = "auditStatus",required = false) @ApiParam( name = "auditStatus", value = "审核状态") Integer auditStatus
            , @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize );

    @RequestMapping( value = "/agent/getActingSchoolList/{school_name}", method = RequestMethod.GET )
    public RestRecord getActingSchoolList(@PathVariable("school_name") String school_name);

    @RequestMapping( value = "/agent/getHasBeenActingSchoolList/{ID}/{school_name}", method = RequestMethod.GET )
    public RestRecord getHasBeenActingSchoolList(@PathVariable("ID") String ID,@PathVariable("school_name") String school_name);

    @RequestMapping( value = "/agent/addActingSchool", method = RequestMethod.POST )
    public RestRecord addActingSchool(@RequestBody Map<String, String> info);

}
