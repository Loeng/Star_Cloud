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
     * @param userId userId
     * @return 用户待办
     */
    @RequestMapping(value = "/app-portal/backlog", method = RequestMethod.GET)
    RestRecord userToDo(@RequestParam(value = "userId") String userId);

    @RequestMapping(value = "/app-portal/backlog", method = RequestMethod.POST)
    RestRecord backlog(@RequestHeader(value = "appId") String appId,
                       @RequestHeader(value = "appToken") String appToken,
                       @RequestParam(value = "userId") String userId,
                       @RequestBody Map backlog);



}
