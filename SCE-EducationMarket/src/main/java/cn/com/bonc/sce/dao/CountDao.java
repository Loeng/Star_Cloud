package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@FeignClient("sce-data-access")
public interface CountDao {
    @RequestMapping( value = "/count/one", method = RequestMethod.GET )
    public RestRecord countSingleAppDownload( @RequestParam( "appId" ) String appId );

    @RequestMapping( value = "/count/type", method = RequestMethod.GET )
    public RestRecord countAppDownloadByType( @RequestParam( "appType" ) String appType  );

    @RequestMapping( value = "/count/company", method = RequestMethod.GET )
    public RestRecord countAppDownloadByCompanyId( @RequestParam( "companyId" ) Long companyId  );
}
