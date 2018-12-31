package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Repository
@FeignClient( "sce-data-access" )
public interface CountDao {
    @RequestMapping( value = "/count/one", method = RequestMethod.GET )
    public RestRecord countSingleAppDownload( @RequestParam( "appId" ) String appId,
                                              @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
                                              @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize );

    @RequestMapping( value = "/count/type", method = RequestMethod.GET )
    public RestRecord countAppDownloadByType( @RequestParam( "appType" ) String appType,
                                              @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
                                              @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize );

    @RequestMapping( value = "/count/company", method = RequestMethod.GET )
    public RestRecord countAppDownloadByCompanyId( @RequestParam( "companyId" ) Long companyId,
                                                   @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
                                                   @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize );

    @RequestMapping( value = "/count/download-ranking", method = RequestMethod.GET )
    public RestRecord getAppDownloadRanking( @RequestParam( "pageSize" ) Integer pageSize,
                                             @RequestParam( "pageNum" ) Integer pageNum,
                                             @RequestParam( "userId" ) String userId );

    @RequestMapping( value = "/count/download-change", method = RequestMethod.GET )
    RestRecord getDownloadChange( @RequestParam( "userId" ) String userId,
                                  @RequestParam( "appId" ) String appId,
                                  @RequestParam( value = "startTime", required = false, defaultValue = "1970-01-01 00:00:00" ) String startTime,
                                  @RequestParam( value = "endTime", required = false, defaultValue = "2099-01-01 00:00:00" ) String endTime );

    @RequestMapping( value = "/count/download-type" )
    RestRecord getDownloadByType( @RequestParam( "userId" ) String userId,
                                  @RequestParam( value = "startTime", required = false, defaultValue = "2018-12" ) String time );

    @RequestMapping( value = "/count/app-type-percent", method = RequestMethod.GET )
    RestRecord getAppTypePrecent( @RequestParam( "userId" ) String userId );

    @RequestMapping( value = "/count/collection-change", method = RequestMethod.GET )
    RestRecord getCollectionChange( @RequestParam( "userId" ) String userId,
                                    @RequestParam( "appId" ) String appId,
                                    @RequestParam( value = "startTime", required = false, defaultValue = "1970-01-01 00:00:00" ) String startTime,
                                    @RequestParam( value = "endTime", required = false, defaultValue = "2099-01-01 00:00:00" ) String endTime );

    @RequestMapping( value = "/count/company-app", method = RequestMethod.GET )
    RestRecord getCompanyAppList( @RequestParam( "userId" ) String userId );

    @RequestMapping( value = "/count/list", method = RequestMethod.GET )
    RestRecord getDownloadList( @RequestParam( "userId" ) String userId,
                                @RequestParam( "appType" ) int appType,
                                @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
                                @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize );
}
