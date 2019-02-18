package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.AgentModel;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

}
