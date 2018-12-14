package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.CompanyInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyInfoDao {
    public List< CompanyInfo > selectAllCompanyList() {
        return null;
    }

    public List< CompanyInfo > selectCompanyListByName( String companyName ) {
        return null;
    }

    public CompanyInfo selectCompanyById( String companyId ) {
        return null;
    }

    public boolean addCompanyInfo( CompanyInfo companyInfo ) {
        return false;
    }

    public boolean updateCompanyInfo( String companyId, CompanyInfo companyInfo ) {
        return false;
    }

    public boolean deleteCompanyInfo( String companyId ) {
        return false;
    }
}
