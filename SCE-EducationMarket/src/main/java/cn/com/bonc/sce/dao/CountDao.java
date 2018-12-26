package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * 查询全部应用下载量排名
     *
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping( value = "/count/download-ranking", method = RequestMethod.GET )
    public RestRecord getAppDownloadRanking( @RequestParam( "pageSize" ) Integer pageSize,
                                             @RequestParam( "pageNum" ) Integer pageNum );
}
