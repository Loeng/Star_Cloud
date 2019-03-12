package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Repository
@FeignClient( "sce-data-mybatis" )
public interface AppMarketDao {

    /**
     * 查询用户类型
     * @return 类型列表
     */
    @RequestMapping(value = "/app-portal/appCount", method = RequestMethod.GET)
    RestRecord appCount();

    /**
     * 查询代办
     * @param userID 用户id
     * @return 用户待办
     */
    @RequestMapping(value = "/app-portal/userToDo", method = RequestMethod.GET)
    RestRecord userToDo(@CurrentUserId String userID);

    @RequestMapping(value = "/app-portal/backlog", method = RequestMethod.POST)
    RestRecord backlog(@RequestHeader(value = "appId") String appId,
                       @RequestHeader(value = "appToken") String appToken,
                       @RequestHeader(value = "authentication") String authentication,
                       @RequestBody Map backlog);



}
