package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient( "sce-data-access" )
public interface AppVersionDao {

    @RequestMapping( value = "/app-version", method = RequestMethod.GET )
    public RestRecord queryAppVersion(
            @RequestParam( "appId" ) String appId,
            @RequestParam( value = "appVersion", required = false, defaultValue = "" ) String appVersion );

    @RequestMapping( value = "/app-version/{appId}", method = RequestMethod.POST )
    public RestRecord updateAppHistoryVersionInfo(
            @PathVariable( "appId" ) String appId,
            @RequestBody Map< String, String > marketAppVersion );

    @RequestMapping( value = "/app-version/{appId}", method = RequestMethod.DELETE )
    public RestRecord deleteAppHistoryVersionInfo(
            @PathVariable( "appId" ) String appId,
            @RequestBody Map< String, String > marketAppVersion );

    @RequestMapping( value = "/app-version/all/{appId}", method = RequestMethod.DELETE )
    public RestRecord deleteAppAllVersionInfoById(
            @PathVariable( "appId" ) String appId );

    @RequestMapping( value = "/app-version/apply/{appId}", method = RequestMethod.POST )
    public boolean createVersionInfo(
            @PathVariable( "appId" ) String appId,
            @RequestBody Map< String, String > marketAppVersion );
}
