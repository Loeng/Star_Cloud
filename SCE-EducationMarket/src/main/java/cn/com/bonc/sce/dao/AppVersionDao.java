package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.AppVersionInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient( "sce-data-access" )
public interface AppVersionDao {

    @RequestMapping( value = "/AppVersion/{appId}", method = RequestMethod.GET )
    public List< AppVersionInfo > selectAppHistoryVersionList( @PathVariable( "appId" ) String appId );

    @RequestMapping( value = "/AppVersion/{appId}", method = RequestMethod.PATCH )
    public boolean updateAppHistoryVersionInfo( @PathVariable( "appId" ) String appId,
                                                @RequestBody AppVersionInfo appVersionInfo);

    @RequestMapping( value = "/AppVersion/{appId}", method = RequestMethod.DELETE )
    public boolean deleteAppHistoryVersionInfo( @PathVariable( "appId" ) String appId,
                                                @RequestBody AppVersionInfo appVersionInfo );

    @RequestMapping( value = "/AppVersion/all/{appId}", method = RequestMethod.DELETE )
    public boolean deleteAppAllVersionInfoById(  @PathVariable( "appId" ) String appId );

    @RequestMapping( value = "/AppVersion/apply/{appId}", method = RequestMethod.POST )
    public boolean createVersionInfo( @PathVariable( "appId" ) String appId,
                                      @RequestBody AppVersionInfo appVersionInfo );
}
