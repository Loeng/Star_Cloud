package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.CompanyInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//CompanyInfoRepository extends
@Repository
public interface CompanyInfoRepository extends JpaRepository< CompanyInfo, Long >, JpaSpecificationExecutor< CompanyInfo > {
    @Modifying
    @Query( "update CompanyInfo set IS_DELETE=0 where COMPANY_ID=:companyId" )
    int deleteCompanyByCompanyId( @Param( "companyId" ) Long companyId );


    @Query( nativeQuery = true,
            value = "SELECT SMC.COMPANY_ID,SMC.COMPANY_NAME,SMC.COMPANY_ADDRESS,SMC.COMPANY_TAX_NUM, SIC.\"USER_ID\" AS USER_ID, SCU.\"LOGIN_PERMISSION_STATUS\" AS LOGIN_PERMISSION_STATUS, COUNT_TEMP.APP_COUNT AS APP_COUNT \n" +
                    "FROM \"STARCLOUDMARKET\".\"SCE_MARKET_COMPANY\" SMC LEFT JOIN \"STARCLOUDPORTAL\".\"SCE_INFO_COMPANY\" SIC ON SMC.\"COMPANY_ID\" = SIC.\"COMPANY_ID\"\n" +
                    "\tLEFT JOIN \"STARCLOUDPORTAL\".\"SCE_COMMON_USER\" SCU ON SIC.\"USER_ID\" = SCU.\"USER_ID\" LEFT JOIN (\n" +
                    "SELECT SMC2.\"COMPANY_ID\", COUNT( MAI2.\"COMPANY_ID\" ) AS APP_COUNT \n" +
                    "FROM \"STARCLOUDMARKET\".\"SCE_MARKET_COMPANY\" SMC2 LEFT JOIN \"STARCLOUDMARKET\".\"SCE_MARKET_APP_INFO\" MAI2 ON SMC2.\"COMPANY_ID\" = MAI2.\"COMPANY_ID\" \n" +
                    "\tAND SMC2.\"IS_DELETE\" = 1  AND MAI2.\"IS_DELETE\" = 1 \n" +
                    "GROUP BY SMC2.\"COMPANY_ID\"  ) COUNT_TEMP ON SMC.\"COMPANY_ID\" = COUNT_TEMP.\"COMPANY_ID\" \n" +
                    "WHERE SMC.COMPANY_ID=:companyId ",
            countQuery = "SELECT COUNT(*) FROM \"STARCLOUDMARKET\".\"SCE_MARKET_COMPANY\" SMC LEFT JOIN \"STARCLOUDPORTAL\".\"SCE_INFO_COMPANY\" SIC ON SMC.\"COMPANY_ID\" = SIC.\"COMPANY_ID\"\n" +
                    "\tLEFT JOIN \"STARCLOUDPORTAL\".\"SCE_COMMON_USER\" SCU ON SIC.\"USER_ID\" = SCU.\"USER_ID\" LEFT JOIN (\n" +
                    "SELECT SMC2.\"COMPANY_ID\", COUNT( MAI2.\"COMPANY_ID\" ) AS APP_COUNT \n" +
                    "FROM \"STARCLOUDMARKET\".\"SCE_MARKET_COMPANY\" SMC2 LEFT JOIN \"STARCLOUDMARKET\".\"SCE_MARKET_APP_INFO\" MAI2 ON SMC2.\"COMPANY_ID\" = MAI2.\"COMPANY_ID\" \n" +
                    "\tAND SMC2.\"IS_DELETE\" = 1  AND MAI2.\"IS_DELETE\" = 1 \n" +
                    "GROUP BY SMC2.\"COMPANY_ID\"  ) COUNT_TEMP ON SMC.\"COMPANY_ID\" = COUNT_TEMP.\"COMPANY_ID\" \n" +
                    "WHERE SMC.COMPANY_ID=:companyId " )
    Page< List< Map< String, Object > > > findCompanyInfoByCompanyId( @Param( "companyId" ) Long companyId,
                                                           Pageable pageable );


    @Query( nativeQuery = true,
            value = "SELECT SMC.COMPANY_ID,SMC.COMPANY_NAME,SMC.COMPANY_ADDRESS,SMC.COMPANY_TAX_NUM, SIC.\"USER_ID\" AS USER_ID, SCU.\"LOGIN_PERMISSION_STATUS\" AS LOGIN_PERMISSION_STATUS, COUNT_TEMP.APP_COUNT AS APP_COUNT \n" +
                    "FROM \"STARCLOUDMARKET\".\"SCE_MARKET_COMPANY\" SMC LEFT JOIN \"STARCLOUDPORTAL\".\"SCE_INFO_COMPANY\" SIC ON SMC.\"COMPANY_ID\" = SIC.\"COMPANY_ID\"\n" +
                    "\tLEFT JOIN \"STARCLOUDPORTAL\".\"SCE_COMMON_USER\" SCU ON SIC.\"USER_ID\" = SCU.\"USER_ID\" LEFT JOIN (\n" +
                    "SELECT SMC2.\"COMPANY_ID\", COUNT( MAI2.\"COMPANY_ID\" ) AS APP_COUNT \n" +
                    "FROM \"STARCLOUDMARKET\".\"SCE_MARKET_COMPANY\" SMC2 LEFT JOIN \"STARCLOUDMARKET\".\"SCE_MARKET_APP_INFO\" MAI2 ON SMC2.\"COMPANY_ID\" = MAI2.\"COMPANY_ID\" \n" +
                    "\tAND SMC2.\"IS_DELETE\" = 1  AND MAI2.\"IS_DELETE\" = 1 \n" +
                    "GROUP BY SMC2.\"COMPANY_ID\"  ) COUNT_TEMP ON SMC.\"COMPANY_ID\" = COUNT_TEMP.\"COMPANY_ID\" \n" +
                    "WHERE  SMC.COMPANY_NAME LIKE  CONCAT('%',CONCAT(:companyName,'%'))",
            countQuery = "SELECT COUNT(*) FROM \"STARCLOUDMARKET\".\"SCE_MARKET_COMPANY\" SMC LEFT JOIN \"STARCLOUDPORTAL\".\"SCE_INFO_COMPANY\" SIC ON SMC.\"COMPANY_ID\" = SIC.\"COMPANY_ID\"\n" +
                    "\tLEFT JOIN \"STARCLOUDPORTAL\".\"SCE_COMMON_USER\" SCU ON SIC.\"USER_ID\" = SCU.\"USER_ID\" LEFT JOIN (\n" +
                    "SELECT SMC2.\"COMPANY_ID\", COUNT( MAI2.\"COMPANY_ID\" ) AS APP_COUNT \n" +
                    "FROM \"STARCLOUDMARKET\".\"SCE_MARKET_COMPANY\" SMC2 LEFT JOIN \"STARCLOUDMARKET\".\"SCE_MARKET_APP_INFO\" MAI2 ON SMC2.\"COMPANY_ID\" = MAI2.\"COMPANY_ID\" \n" +
                    "\tAND SMC2.\"IS_DELETE\" = 1  AND MAI2.\"IS_DELETE\" = 1 \n" +
                    "GROUP BY SMC2.\"COMPANY_ID\"  ) COUNT_TEMP ON SMC.\"COMPANY_ID\" = COUNT_TEMP.\"COMPANY_ID\" \n" +
                    "WHERE SMC.COMPANY_NAME LIKE  CONCAT('%',CONCAT(:companyName,'%'))" )
    Page< List< Map< String, Object > > > findCompanyInfoByCompanyName( @Param( "companyName" ) String companyName,
                                                                      Pageable pageable );
}
