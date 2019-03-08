package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Repository
@FeignClient( "sce-data-mybatis" )
public interface AppMarketDao {

    @RequestMapping(value = "/app-portal/appCount", method = RequestMethod.GET)
    RestRecord appCount();

    @RequestMapping(value = "app-portal/userToDo", method = RequestMethod.GET)
    RestRecord userToDo();

}
