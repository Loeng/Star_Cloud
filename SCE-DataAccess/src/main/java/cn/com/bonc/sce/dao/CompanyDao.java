package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2019/4/16.
 */
@Repository
public interface CompanyDao extends JpaRepository<CompanyInfo, Integer > {

    @Override
    CompanyInfo save( CompanyInfo company);

    @Modifying
    @Query(value = "UPDATE STARCLOUDMARKET.SCE_MARKET_COMPANY SET COMPANY_ADDRESS = ?2,POSTCODE = ?3,PHONE = ?4,COMPANY_EMAIL = ?5,COMPANY_WEBSITE = ?6 WHERE COMPANY_ID = ?1 ", nativeQuery = true)
    int updateCompanyByCompanyId(Long companyId,String companyAddress,String postcode,String phone,String companyEmail,String companyWebsite);

    CompanyInfo findByCompanyId(Long companyId);

}
