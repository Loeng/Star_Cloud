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

    public RestRecord addCompanyInfo( CompanyInfoModel companyInfo ) {
        return companyInfoDao.addCompanyInfo( companyInfo );
    }

    public RestRecord updateCompanyInfo( Long companyId, CompanyInfoModel companyInfo ) {
        return companyInfoDao.updateCompanyInfo( companyId, companyInfo );
    }

    public RestRecord deleteCompanyInfo( Long companyId ) {
        return companyInfoDao.deleteCompanyInfo( companyId );
    }


    public RestRecord getAllUserInfo( String loginName, String companyName, String enable, Integer pageNum, Integer pageSize ) {
        return companyInfoDao.getAllUserInfo( loginName, companyName, enable, pageNum, pageSize );
    }
}
