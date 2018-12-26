package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.AppVersionModel;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@FeignClient( "sce-data-access" )
public interface AppVersionDao {

    /**
     * 查询应用版本
     *
     * @param appId      应用Id
     * @param appVersion 应用版本，可空，可模糊查询
     * @return
     */
    @RequestMapping( value = "/app-version", method = RequestMethod.GET )
    public RestRecord queryAppVersion(
            @RequestParam( "appId" ) String appId,
            @RequestParam( value = "appVersion", required = false, defaultValue = "" ) String appVersion,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) int pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) int pageSize );

    @RequestMapping( value = "/app-version/{appId}", method = RequestMethod.PUT )
    public RestRecord updateAppHistoryVersionInfo(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "appVersion" ) String appVersion,
            @RequestBody AppVersionModel marketAppVersion );

    @RequestMapping( value = "/app-version", method = RequestMethod.DELETE )
    public RestRecord deleteAppHistoryVersionInfo(
            @RequestParam( "appId" ) String appId,
            @RequestParam( value = "appVersion", required = false, defaultValue = "" ) String appVersion );

    @RequestMapping( value = "/app-version/apply/{appId}", method = RequestMethod.POST )
    public RestRecord createVersionInfo(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "userId" ) String userId,
            @RequestBody AppVersionModel appVersionInfo );
}
