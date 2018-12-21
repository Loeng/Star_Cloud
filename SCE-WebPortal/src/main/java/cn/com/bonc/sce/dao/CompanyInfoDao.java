package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Repository
@FeignClient( "sce-data-access" )
public interface CompanyInfoDao {

    @RequestMapping( value = "/company", method = RequestMethod.GET )
    public RestRecord queryCompanyInfo(
            @RequestParam( value = "companyId", required = false ) String companyId,
            @RequestParam( value = "companyName", required = false, defaultValue = "" ) String companyName,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) String pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) String pageSize
    );

    @RequestMapping( value = "/company", method = RequestMethod.POST )
    public RestRecord addCompanyInfo(
            @RequestBody Map< String, Object > companyInfo );

    @RequestMapping( value = "/company/{companyId}", method = RequestMethod.PUT )
    public RestRecord updateCompanyInfo(
            @PathVariable( "companyId" ) String companyId,
            @RequestBody Map< String, Object > companyInfo );

    @RequestMapping( value = "/company/{companyId}", method = RequestMethod.DELETE )
    public RestRecord deleteCompanyInfo(
            @PathVariable( "companyId" ) String companyId );

}
