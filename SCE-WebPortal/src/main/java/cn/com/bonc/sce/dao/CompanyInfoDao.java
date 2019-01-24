package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.CompanyInfoModel;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Repository
@FeignClient( "sce-data-access" )
public interface CompanyInfoDao {

    @RequestMapping( value = "/company", method = RequestMethod.GET )
    public RestRecord queryCompanyInfo(
            @RequestParam( value = "companyId", required = false ) Long companyId,
            @RequestParam( value = "companyName", required = false, defaultValue = "" ) String companyName,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize
    );

    @RequestMapping( value = "/company/one/{companyId}" )
    RestRecord queryOneCompanyInfo(
            @PathVariable( "companyId" ) Long companyId );

    @RequestMapping( value = "/company", method = RequestMethod.POST )
    public RestRecord addCompanyInfo(
            @RequestBody CompanyInfoModel companyInfo );

    @RequestMapping( value = "/company/{companyId}", method = RequestMethod.PUT )
    public RestRecord updateCompanyInfo(
            @PathVariable( "companyId" ) Long companyId,
            @RequestBody CompanyInfoModel companyInfo );

    @RequestMapping( value = "/company/{companyId}", method = RequestMethod.DELETE )
    public RestRecord deleteCompanyInfo(
            @PathVariable( "companyId" ) Long companyId );

    @RequestMapping( value = "/company/user-info", method = RequestMethod.GET )
    RestRecord getAllUserInfo( @RequestParam( value = "loginName", required = false, defaultValue = "" ) String loginName,
                               @RequestParam( value = "companyName", required = false, defaultValue = "" ) String companyName,
                               @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                               @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize );
}
