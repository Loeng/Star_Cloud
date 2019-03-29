package cn.com.bonc.sce.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient( "sce-data-mybatis" )
public interface AppDaoClient {

    @RequestMapping( value = "/app-portal/app-token", method = RequestMethod.GET )
    String getAppToken(@RequestParam("appId") String appId);

    @RequestMapping( value = "/app-portal/appInfo", method = RequestMethod.GET )
    Map<String, Object> getAppInfoById(@RequestParam("appId") String appId);

    @RequestMapping( value = "/app-portal/getUserAppAuth/{userId}/{appId}", method = RequestMethod.GET )
    int getUserAppAuth( @PathVariable( "userId" ) String userId,
                        @PathVariable( "appId" ) String appId );

}
