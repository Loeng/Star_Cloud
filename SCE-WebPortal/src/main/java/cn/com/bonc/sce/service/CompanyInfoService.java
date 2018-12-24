package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.CompanyInfoDao;
import cn.com.bonc.sce.model.CompanyInfoModel;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class CompanyInfoService {
    private CompanyInfoDao companyInfoDao;

    @Autowired
    public CompanyInfoService( CompanyInfoDao companyInfoDao ) {
        this.companyInfoDao = companyInfoDao;
    }

    //    public RestRecord selectAllCompanyList() {
//        return companyInfoDao.selectAllCompanyList();
//    }
//
//    public RestRecord selectCompanyListByName( String companyName ) {
//        return companyInfoDao.selectCompanyListByName( companyName );
//    }
//
//    public RestRecord selectCompanyById( String companyId ) {
//        return companyInfoDao.selectCompanyById( companyId );
//    }

    public RestRecord queryCompanyInfo( String companyId, String companyName, int pageNum, int pageSize ) {
        return companyInfoDao.queryCompanyInfo( companyId, companyName, pageNum, pageSize );
    }

    public RestRecord addCompanyInfo( CompanyInfoModel companyInfo ) {
        return companyInfoDao.addCompanyInfo( companyInfo );
    }

    public RestRecord updateCompanyInfo( String companyId, CompanyInfoModel companyInfo ) {
        return companyInfoDao.updateCompanyInfo( companyId, companyInfo );
    }

    public RestRecord deleteCompanyInfo( String companyId ) {
        return companyInfoDao.deleteCompanyInfo( companyId );
    }


}
