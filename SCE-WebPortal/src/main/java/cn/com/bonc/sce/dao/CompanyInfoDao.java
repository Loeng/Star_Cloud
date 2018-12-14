package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.CompanyInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Repository
@FeignClient( "sce-data-access" )
public interface CompanyInfoDao {
    @RequestMapping( value = "/Company", method = RequestMethod.GET )
    public List< CompanyInfo > selectAllCompanyList(
    );

    @RequestMapping( value = "/Company/{companyName}", method = RequestMethod.GET )
    public List< CompanyInfo > selectCompanyListByName(
            @PathVariable( value = "companyName" ) String companyName
    );

    @RequestMapping( value = "/Company/{companyId}", method = RequestMethod.GET )
    public CompanyInfo selectCompanyById(
            @PathVariable( "companyId" ) String companyId);

    @RequestMapping( value = "/Company", method = RequestMethod.PUT )
    public boolean addCompanyInfo(
            @RequestBody CompanyInfo companyInfo );

    @RequestMapping( value = "/Company/{companyId}", method = RequestMethod.PATCH )
    public boolean updateCompanyInfo(
            @PathVariable( "companyId" ) String companyId,
            @RequestBody CompanyInfo companyInfo );

    @RequestMapping( value = "/Company/{companyId}", method = RequestMethod.DELETE )
    public boolean deleteCompanyInfo(
            @PathVariable( "companyId" ) String companyId );
}
