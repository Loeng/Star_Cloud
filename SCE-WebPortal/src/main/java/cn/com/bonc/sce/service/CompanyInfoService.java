package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.CompanyInfoDao;
import cn.com.bonc.sce.model.CompanyInfoModel;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CompanyInfoService {
    private CompanyInfoDao companyInfoDao;

    @Autowired
    public CompanyInfoService( CompanyInfoDao companyInfoDao ) {
        this.companyInfoDao = companyInfoDao;
    }

    public RestRecord queryCompanyInfo( Long companyId, String companyName, int pageNum, int pageSize ) {
        return companyInfoDao.queryCompanyInfo( companyId, companyName, pageNum, pageSize );
    }

    public RestRecord queryOneCompanyInfo( Long companyId ) {
        return companyInfoDao.queryOneCompanyInfo( companyId );
    }

    public RestRecord getCompanyByCompanyId( Long companyId ) {
        return companyInfoDao.getCompanyByCompanyId( companyId );
    }

    public RestRecord addCompanyInfo( CompanyInfoModel companyInfo ) {
        return companyInfoDao.addCompanyInfo( companyInfo );
    }

    public RestRecord addCompany( CompanyInfoModel company,String userId,Integer roleId ) {
        return companyInfoDao.addCompany( company,userId,roleId );
    }

    public RestRecord updateCompanyInfo( Long companyId, CompanyInfoModel companyInfo ) {
        return companyInfoDao.updateCompanyInfo( companyId, companyInfo );
    }

    public RestRecord updateCompanyByCompanyId(CompanyInfoModel companyInfo ) {
        return companyInfoDao.updateCompanyByCompanyId(companyInfo );
    }

    public RestRecord deleteCompanyInfo( Long companyId ) {
        return companyInfoDao.deleteCompanyInfo( companyId );
    }


    public RestRecord getAllUserInfo( String loginName, String companyName, String enable, Integer pageNum, Integer pageSize ) {
        return companyInfoDao.getAllUserInfo( loginName, companyName, enable, pageNum, pageSize );
    }

    public RestRecord updateCompany(CompanyInfoModel companyInfo){
        return companyInfoDao.updateCompany(companyInfo);
    }

    public RestRecord getCompanyList( String COMPANY_NAME, String PROPERTY, Integer AUDIT_STATUS, Integer pageNum,Integer pageSize ) {
        return companyInfoDao.getCompanyList( COMPANY_NAME, PROPERTY, AUDIT_STATUS, pageNum, pageSize );
    }
}
