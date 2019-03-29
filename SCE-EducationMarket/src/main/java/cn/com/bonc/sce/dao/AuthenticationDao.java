package cn.com.bonc.sce.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@FeignClient( "sce-data-mybatis" )
public interface AuthenticationDao {

    @RequestMapping(value = "/app-portal/app-token", method = RequestMethod.GET)
    String getAppToken(@RequestParam("appId") String appId);

}
