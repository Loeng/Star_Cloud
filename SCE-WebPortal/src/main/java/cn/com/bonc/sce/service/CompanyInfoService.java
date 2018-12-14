package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.CompanyInfoDao;
import cn.com.bonc.sce.model.CompanyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CompanyInfoService {
    private CompanyInfoDao companyInfoDao;

    @Autowired
    public CompanyInfoService( CompanyInfoDao companyInfoDao ) {
        this.companyInfoDao = companyInfoDao;
    }

    public List< CompanyInfo > selectAllCompanyList() {
        return companyInfoDao.selectAllCompanyList();
    }

    public List< CompanyInfo > selectCompanyListByName( String companyName ) {
        return companyInfoDao.selectCompanyListByName( companyName );
    }

    public CompanyInfo selectCompanyById( String companyId ) {
        return companyInfoDao.selectCompanyById( companyId );
    }

    public boolean addCompanyInfo( CompanyInfo companyInfo ) {
        return companyInfoDao.addCompanyInfo( companyInfo );
    }

    public boolean updateCompanyInfo( String companyId, CompanyInfo companyInfo ) {
        return companyInfoDao.updateCompanyInfo( companyId, companyInfo );
    }

    public boolean deleteCompanyInfo( String companyId ) {
        return companyInfoDao.deleteCompanyInfo( companyId );
    }
}
