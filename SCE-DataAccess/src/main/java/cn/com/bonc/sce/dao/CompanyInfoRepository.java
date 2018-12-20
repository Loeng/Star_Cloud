package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.CompanyInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

//CompanyInfoRepository extends
@Repository
public interface CompanyInfoRepository extends JpaRepository< CompanyInfo, Long >, JpaSpecificationExecutor< CompanyInfo > {

//    public List< CompanyInfo > queryCompanyInfo( String companyId, String companyName, Pageable pageable );
//
//    public boolean addCompanyInfo( CompanyInfo companyInfo );
//
//    public boolean updateCompanyInfo( String companyId, CompanyInfo companyInfo );
//
//    public boolean deleteCompanyInfo( String companyId );
}
