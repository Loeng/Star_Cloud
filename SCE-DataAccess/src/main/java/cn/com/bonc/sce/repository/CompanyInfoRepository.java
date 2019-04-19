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
            value = "SELECT  \n" +
                    "SMC.COMPANY_ID,\n" +
                    "\tSMC.COMPANY_NAME,\n" +
                    "\tSMC.COMPANY_ADDRESS,\n" +
                    "\tSMC.COMPANY_TAX_NUM,\n" +
                    "\tSCU.USER_ID AS USER_ID,\n" +
                    "\tSCU.LOGIN_PERMISSION_STATUS AS LOGIN_PERMISSION_STATUS,\n" +
                    "\tSCU.ACCOUNT_STATUS\tAS ACCOUNT_STATUS,\n" +
                    "\tSCU.LOGIN_NAME AS LOGIN_NAME,\n" +
                    "\tNVL(COUNT_TEMP.APP_COUNT,0) AS APP_COUNT \n" +
                    "FROM STARCLOUDPORTAL.SCE_COMMON_USER scu \n" +
                    "LEFT JOIN STARCLOUDPORTAL.SCE_INFO_COMPANY sic ON  sic.USER_ID =scu.USER_ID AND sic.IS_DELETE=1\n" +
                    "LEFT JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY smc ON smc.COMPANY_ID =sic.COMPANY_ID AND smc.IS_DELETE=1\n" +
                    "LEFT JOIN (\n" +
                    "\tSELECT\n" +
                    "\t\tSMC2.COMPANY_ID,\n" +
                    "\t\tCOUNT( MAI2.\"COMPANY_ID\" ) AS APP_COUNT \n" +
                    "\tFROM\n" +
                    "\t\tSTARCLOUDMARKET.SCE_MARKET_COMPANY SMC2\n" +
                    "\t\tLEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_INFO MAI2 ON SMC2.COMPANY_ID = MAI2.COMPANY_ID \n" +
                    "\t\tAND SMC2.IS_DELETE = 1 \n" +
                    "\t\tAND MAI2.IS_DELETE = 1 \n" +
                    "\tGROUP BY\n" +
                    "\t\tSMC2.COMPANY_ID \n" +
                    "\t) COUNT_TEMP ON SMC.COMPANY_ID = COUNT_TEMP.COMPANY_ID \n" +
                    "WHERE \n" +
                    " scu.USER_TYPE='4' AND scu.IS_DELETE =1 \n" +
                    " AND SMC.COMPANY_ID=:companyId " +
                    "ORDER BY scu.CREATE_TIME desc",
            countQuery = "SELECT  \n" +
                    "\tCOUNT(*) \n" +
                    "FROM STARCLOUDPORTAL.SCE_COMMON_USER scu \n" +
                    "LEFT JOIN STARCLOUDPORTAL.SCE_INFO_COMPANY sic ON  sic.USER_ID =scu.USER_ID AND sic.IS_DELETE=1\n" +
                    "LEFT JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY smc ON smc.COMPANY_ID =sic.COMPANY_ID AND smc.IS_DELETE=1\n" +
                    "LEFT JOIN (\n" +
                    "\tSELECT\n" +
                    "\t\tSMC2.COMPANY_ID,\n" +
                    "\t\tCOUNT( MAI2.\"COMPANY_ID\" ) AS APP_COUNT \n" +
                    "\tFROM\n" +
                    "\t\tSTARCLOUDMARKET.SCE_MARKET_COMPANY SMC2\n" +
                    "\t\tLEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_INFO MAI2 ON SMC2.COMPANY_ID = MAI2.COMPANY_ID \n" +
                    "\t\tAND SMC2.IS_DELETE = 1 \n" +
                    "\t\tAND MAI2.IS_DELETE = 1 \n" +
                    "\tGROUP BY\n" +
                    "\t\tSMC2.COMPANY_ID \n" +
                    "\t) COUNT_TEMP ON SMC.COMPANY_ID = COUNT_TEMP.COMPANY_ID \n" +
                    "WHERE \n" +
                    " scu.USER_TYPE='4' AND scu.IS_DELETE =1 \n" +
                    " AND SMC.COMPANY_ID=:companyId " )
    Page< List< Map< String, Object > > > findCompanyInfoByCompanyId( @Param( "companyId" ) Long companyId,
                                                                      Pageable pageable );


    @Query( nativeQuery = true,
            value = "SELECT  \n" +
                    "SMC.COMPANY_ID,\n" +
                    "\tSMC.COMPANY_NAME,\n" +
                    "\tSMC.COMPANY_ADDRESS,\n" +
                    "\tSMC.COMPANY_TAX_NUM,\n" +
                    "\tSCU.USER_ID AS USER_ID,\n" +
                    "\tSCU.LOGIN_PERMISSION_STATUS AS LOGIN_PERMISSION_STATUS,\n" +
                    "\tSCU.ACCOUNT_STATUS\tAS ACCOUNT_STATUS,\n" +
                    "\tSCU.LOGIN_NAME AS LOGIN_NAME,\n" +
                    "\tNVL(COUNT_TEMP.APP_COUNT,0) AS APP_COUNT \n" +
                    "FROM STARCLOUDPORTAL.SCE_COMMON_USER scu \n" +
                    "LEFT JOIN STARCLOUDPORTAL.SCE_INFO_COMPANY sic ON  sic.USER_ID =scu.USER_ID AND sic.IS_DELETE=1\n" +
                    "LEFT JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY smc ON smc.COMPANY_ID =sic.COMPANY_ID AND smc.IS_DELETE=1\n" +
                    "LEFT JOIN (\n" +
                    "\tSELECT\n" +
                    "\t\tSMC2.COMPANY_ID,\n" +
                    "\t\tCOUNT( MAI2.\"COMPANY_ID\" ) AS APP_COUNT \n" +
                    "\tFROM\n" +
                    "\t\tSTARCLOUDMARKET.SCE_MARKET_COMPANY SMC2\n" +
                    "\t\tLEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_INFO MAI2 ON SMC2.COMPANY_ID = MAI2.COMPANY_ID \n" +
                    "\t\tAND SMC2.IS_DELETE = 1 \n" +
                    "\t\tAND MAI2.IS_DELETE = 1 \n" +
                    "\tGROUP BY\n" +
                    "\t\tSMC2.COMPANY_ID \n" +
                    "\t) COUNT_TEMP ON SMC.COMPANY_ID = COUNT_TEMP.COMPANY_ID \n" +
                    "WHERE \n" +
                    " scu.USER_TYPE='4' AND scu.IS_DELETE =1 \n" +
                    " AND nvl(smc.COMPANY_NAME,0) LIKE CONCAT('%',CONCAT(:companyName, '%' ))\n" +
                    "ORDER BY scu.CREATE_TIME desc",
            countQuery = "SELECT  \n" +
                    "\tCOUNT(*) \n" +
                    "FROM STARCLOUDPORTAL.SCE_COMMON_USER scu \n" +
                    "LEFT JOIN STARCLOUDPORTAL.SCE_INFO_COMPANY sic ON  sic.USER_ID =scu.USER_ID AND sic.IS_DELETE=1\n" +
                    "LEFT JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY smc ON smc.COMPANY_ID =sic.COMPANY_ID AND smc.IS_DELETE=1\n" +
                    "LEFT JOIN (\n" +
                    "\tSELECT\n" +
                    "\t\tSMC2.COMPANY_ID,\n" +
                    "\t\tCOUNT( MAI2.\"COMPANY_ID\" ) AS APP_COUNT \n" +
                    "\tFROM\n" +
                    "\t\tSTARCLOUDMARKET.SCE_MARKET_COMPANY SMC2\n" +
                    "\t\tLEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_INFO MAI2 ON SMC2.COMPANY_ID = MAI2.COMPANY_ID \n" +
                    "\t\tAND SMC2.IS_DELETE = 1 \n" +
                    "\t\tAND MAI2.IS_DELETE = 1 \n" +
                    "\tGROUP BY\n" +
                    "\t\tSMC2.COMPANY_ID \n" +
                    "\t) COUNT_TEMP ON SMC.COMPANY_ID = COUNT_TEMP.COMPANY_ID \n" +
                    "WHERE \n" +
                    " scu.USER_TYPE='4' AND scu.IS_DELETE =1 \n" +
                    " AND nvl(smc.COMPANY_NAME,0) LIKE CONCAT('%',CONCAT( :companyName, '%' ))" )
    Page< List< Map< String, Object > > > findCompanyInfoByCompanyName( @Param( "companyName" ) String companyName,
                                                                        Pageable pageable );

    /**
     * 根据用户id 查询厂家id
     *
     * @param uid
     * @return
     */
    @Query( nativeQuery = true, value = "SELECT COMPANY_ID FROM STARCLOUDPORTAL.SCE_INFO_COMPANY WHERE USER_ID = :uid" )
    Long getCompanyIdByUid( @Param( "uid" ) String uid );


    @Query( nativeQuery = true, value = "SELECT *  FROM \"STARCLOUDPORTAL\".\"V_COMPANY_INFO\" \n" +
            "WHERE COMPANY_NAME LIKE CONCAT( '%', CONCAT( :companyName, '%' ) ) \n" +
            "AND LOGIN_NAME LIKE CONCAT( '%', CONCAT( :loginName, '%' ) ) ",
    countQuery = "SELECT COUNT(*)  FROM \"STARCLOUDPORTAL\".\"V_COMPANY_INFO\" \n" +
            "WHERE COMPANY_NAME LIKE CONCAT( '%', CONCAT( :companyName, '%' ) ) \n" +
            "AND LOGIN_NAME LIKE CONCAT( '%', CONCAT( :loginName, '%' ) ) ")
    Page< Map< String, Object > > getAllCompanyUser(
            Pageable pageable,
            @Param( "loginName" ) String loginName,
            @Param( "companyName" ) String companyName );

    @Query( nativeQuery = true, value = "SELECT *  FROM \"STARCLOUDPORTAL\".\"V_COMPANY_INFO\" \n" +
            "WHERE COMPANY_NAME LIKE CONCAT( '%', CONCAT( :companyName, '%' ) ) \n" +
            "AND LOGIN_NAME LIKE CONCAT( '%', CONCAT( :loginName, '%' ) ) AND LOGIN_PERMISSION_STATUS = :enable ",
            countQuery = "SELECT COUNT(*)  FROM \"STARCLOUDPORTAL\".\"V_COMPANY_INFO\" \n" +
                    "WHERE COMPANY_NAME LIKE CONCAT( '%', CONCAT( :companyName, '%' ) ) \n" +
                    "AND LOGIN_NAME LIKE CONCAT( '%', CONCAT( :loginName, '%' ) ) AND LOGIN_PERMISSION_STATUS =:enable")
    Page< Map< String, Object > > getAllCompanyUserAndEnable(
            Pageable pageable,
            @Param( "loginName" ) String loginName,
            @Param( "companyName" ) String companyName,
            @Param( "enable" )String enable);
}
