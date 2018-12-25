package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//CompanyInfoRepository extends
@Repository
public interface CompanyInfoRepository extends JpaRepository< CompanyInfo, Long >, JpaSpecificationExecutor< CompanyInfo > {
    @Modifying
    @Query( "update CompanyInfo set IS_DELETE=0 where COMPANY_ID=:companyId" )
    int deleteCompanyByCompanyId( @Param( "companyId" ) Long companyId );
}
