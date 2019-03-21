package cn.com.bonc.sce.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient( "sce-data-mybatis" )
public interface AppDaoClient {

    @RequestMapping( value = "/app-portal/app-token", method = RequestMethod.GET )
    String getAppToken( @RequestParam( "appId" ) String appId );
}
