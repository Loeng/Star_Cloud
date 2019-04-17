package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.AgentModel;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping( value = "/agent/getAgentInfo/{AGENT_NAME}/{PROPERTY}/{AUDIT_STATUS}/{pageNum}/{pageSize}", method = RequestMethod.GET )
    public RestRecord getAgentInfo(@PathVariable("AGENT_NAME") String AGENT_NAME, @PathVariable("PROPERTY") String PROPERTY, @PathVariable("AUDIT_STATUS") Integer AUDIT_STATUS
            , @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize );

}
