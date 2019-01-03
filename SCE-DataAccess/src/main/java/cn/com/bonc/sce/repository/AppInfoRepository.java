package cn.com.bonc.sce.repository;


import cn.com.bonc.sce.entity.AppInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface AppInfoRepository extends JpaRepository< AppInfoEntity, String > {

    @Override
    < S extends AppInfoEntity > S saveAndFlush( S s );

    //根据平台类型查询有哪些软件
    Page< AppInfoEntity > findByAppSource( String platformType, Pageable pageable );

    //查应用详情
    AppInfoEntity findByAppId( String appId );

    //根据输入名模糊查询应用
    Page< AppInfoEntity > findByAppNameLike( String appName, Pageable pageable );

    //从应用信息表删除应用
    @Modifying
    @Query( nativeQuery = true, value = "UPDATE STARCLOUDMARKET.SCE_MARKET_APP_INFO SET IS_DELETE = 0 , UPDATE_TIME=sysdate,UPDATE_USER_ID = :uid WHERE APP_ID IN (:appidList)" )
    int deleteAppInfo( @Param( value = "appidList" ) List< String > ids,
                       @Param( value = "uid" ) String uid );

    //查询应用审核状态码表
    @Query( nativeQuery = true, value = "SELECT ID ,AUDIT_STATUS FROM STARCLOUDMARKET.SCE_AUDIT_STATUS_DIC ORDER BY ID ASC" )
    List< Map< String, Object > > getAllAuditStatus();

    //通过审核状态查询信息
    @Query( nativeQuery = true, value = "SELECT * FROM STARCLOUDMARKET.\"APP_MANAGE_INFO_VIEW\"  A WHERE  A.APP_STATUS =:auditStatus AND A.APP_NAME  LIKE  CONCAT('%',CONCAT(:keyword,'%'))" )
    Page< List< Map< String, Object > > > getInfoByKeyword( @Param( value = "auditStatus" ) String auditStatus,
                                                            @Param( value = "keyword" ) String keyword,
                                                            Pageable pageable );

    //通过审核状态查询信息
    @Query( nativeQuery = true, value = "SELECT * FROM STARCLOUDMARKET.\"APP_MANAGE_INFO_VIEW\"  A  WHERE A.APP_TYPE_ID=:typeId AND A.APP_STATUS =:auditStatus AND A.APP_NAME LIKE CONCAT('%',CONCAT(:keyword,'%'))" )
    Page< List< Map< String, Object > > > getInfoByTypeIdAndKeyword( @Param( value = "auditStatus" ) String auditStatus,
                                                                     @Param( value = "typeId" ) Integer typeId,
                                                                     @Param( value = "keyword" ) String keyword,
                                                                     Pageable pageable );

    //查询平台应用图标，名字，id,平台连接  是否开通
    @Query( nativeQuery = true, value = "SELECT\n" +
            "\ta.APP_ID,\n" +
            "\ta.APP_NAME,\n" +
            "\ta.APP_ICON,\n" +
            "\ta.APP_NOTES,\n" +
            "\ta.APP_LINK,\n" +
            "\ta.APP_SOURCE,\n" +
            "\tDECODE(o.USER_ID,:userId, '1','0') IS_OPEN,\n" +
            "\tDECODE(sac.USER_ID,:userId, '1', '0') IS_COLLECTION\n" +
            "\n" +
            "FROM\n" +
            "\tSTARCLOUDMARKET.SCE_MARKET_APP_INFO a\n" +
            "\tLEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_OPEN o ON a.APP_ID = o.APP_ID \n" +
            "\tAND o.IS_DELETE = 1\n" +
            "\tLEFT JOIN STARCLOUDMARKET.SCE_USER_APP_COLLECTION SAC ON a.APP_ID = SAC.APP_ID \n" +
            "\tAND SAC.USER_ID = :userId AND SAC.IS_DELETE=1\n" +
            "WHERE\n" +
            "\ta.APP_SOURCE = 'pt'" )
    Page< List< Map< String, Object > > > getPlatformlist( @Param( "userId" ) String userId, Pageable pageable );


    //分类 查询平台应用图标，名字，id,平台连接  是否开通
    @Query( nativeQuery = true, value = "SELECT\n" +
            "\ta.APP_ID,\n" +
            "\ta.APP_NAME,\n" +
            "\ta.APP_ICON,\n" +
            "\ta.APP_NOTES,\n" +
            "\ta.APP_LINK,\n" +
            "\ta.APP_SOURCE,\n" +
            "\tDECODE(o.USER_ID,:userId, '1','0') IS_OPEN,\n" +
            "\tDECODE(sac.USER_ID,:userId, '1', '0') IS_COLLECTION\n" +
            "\n" +
            "FROM\n" +
            "\tSTARCLOUDMARKET.SCE_MARKET_APP_INFO a\n" +
            "\tLEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_OPEN o ON a.APP_ID = o.APP_ID \n" +
            "\tAND o.IS_DELETE = 1\n" +
            "\tLEFT JOIN STARCLOUDMARKET.SCE_USER_APP_COLLECTION SAC ON a.APP_ID = SAC.APP_ID \n" +
            "\tAND SAC.USER_ID = :userId AND SAC.IS_DELETE=1\n" +
            "WHERE\n" +
            "\ta.APP_SOURCE = 'pt' AND a.APP_ID IN (:appIdList)" )
    Page< List< Map< String, Object > > > getPlatformlistByIds( @Param( "userId" ) String userId, @Param( "appIdList" ) List< Object > appIdList, Pageable pageable );

    //根据类型id 查询 appid 列表
    @Query( nativeQuery = true, value = "SELECT APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL WHERE APP_TYPE_ID= :appTypeId" )
    List< Object > getAppIdByTypeId( @Param( "appTypeId" ) Integer appTypeId );

    //根据分类 查 应用列表
    @Query( nativeQuery = true, value = "SELECT DISTINCT \n" +
            "\tAI.APP_ID,\n" +
            "\tAI.APP_NAME,\n" +
            "\tAI.APP_NOTES,\n" +
            "\tAI.APP_ICON,\n" +
            "\tAI.APP_SOURCE,\n" +
            "\tTEMPB.CREATE_TIME,\n" +
            "\tTEMPB.APP_STATUS,\n" +
            "\tAI.APP_LINK," +
            "\tDECODE( AO.USER_ID, :userId, '1', '0' ) IS_OPEN,\n" +
            "\tDECODE( SUAC.USER_ID, :userId, '1', '0' ) IS_COLLECTION,\n" +
            "\tNVL( AD.DOWNLOAD_COUNT, 0 ) DOWNLOAD_COUNT \n" +
            "FROM\n" +
            "\tSTARCLOUDMARKET.SCE_MARKET_APP_INFO AI \n" +
            "\tLEFT JOIN ( SELECT APP_ID, COUNT( APP_ID ) AS DOWNLOAD_COUNT FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD WHERE IS_DELETE = 1 GROUP BY APP_ID ) AD ON AI.APP_ID = AD.APP_ID\n" +
            "\tLEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_OPEN AO ON AI.APP_ID = AO.APP_ID AND AO.IS_DELETE = 1 \n" +
            "\tAND AO.IS_DELETE = 1 \n" +
            "\tAND AO.USER_ID = :userId\n" +
            "\tLEFT JOIN (\n" +
            "\tSELECT\n" +
            "\t\tAVC.APP_ID,\n" +
            "\t\tAVC.APP_VERSION,\n" +
            "\t\tAVC.APP_STATUS,\n" +
            "\t\tTEMPA.CREATE_TIME CREATE_TIME \n" +
            "\tFROM\n" +
            "\t\tSTARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC\n" +
            "\t\tINNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
            "\t\tAND TEMPA.CREATE_TIME = AVC.CREATE_TIME \n" +
            "\t) TEMPB ON AI.APP_ID = TEMPB.APP_ID\n" +
            "\tLEFT JOIN STARCLOUDMARKET.SCE_USER_APP_COLLECTION SUAC ON AI.APP_ID = SUAC.APP_ID AND SUAC.IS_DELETE = 1 \n" +
            "\tAND SUAC.USER_ID = :userId \n" +
            "WHERE\n" +
            "\tAPP_SOURCE = :platform AND AI.APP_ID in (SELECT ar.APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL ar WHERE ar.APP_TYPE_ID=:typeId)",
            countQuery = "SELECT count( DISTINCT AI.APP_ID) " +
                    "FROM\n" +
                    "\tSTARCLOUDMARKET.SCE_MARKET_APP_INFO AI \n" +
                    "\tLEFT JOIN ( SELECT APP_ID, COUNT( APP_ID ) AS DOWNLOAD_COUNT FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD WHERE IS_DELETE = 1 GROUP BY APP_ID ) AD ON AI.APP_ID = AD.APP_ID\n" +
                    "\tLEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_OPEN AO ON AI.APP_ID = AO.APP_ID AND AO.IS_DELETE = 1 \n" +
                    "\tAND AO.IS_DELETE = 1 \n" +
                    "\tAND AO.USER_ID = :userId\n" +
                    "\tLEFT JOIN (\n" +
                    "\tSELECT\n" +
                    "\t\tAVC.APP_ID,\n" +
                    "\t\tAVC.APP_VERSION,\n" +
                    "\t\tAVC.APP_STATUS,\n" +
                    "\t\tTEMPA.CREATE_TIME CREATE_TIME \n" +
                    "\tFROM\n" +
                    "\t\tSTARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC\n" +
                    "\t\tINNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
                    "\t\tAND TEMPA.CREATE_TIME = AVC.CREATE_TIME \n" +
                    "\t) TEMPB ON AI.APP_ID = TEMPB.APP_ID\n" +
                    "\tLEFT JOIN STARCLOUDMARKET.SCE_USER_APP_COLLECTION SUAC ON AI.APP_ID = SUAC.APP_ID AND SUAC.IS_DELETE = 1 \n" +
                    "\tAND SUAC.USER_ID = :userId \n" +
                    "WHERE\n" +
                    "\tAPP_SOURCE = :platform AND AI.APP_ID in (SELECT ar.APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL ar WHERE ar.APP_TYPE_ID=:typeId)"
    )
    Page< List< Map< String, Object > > > getAppListInfoByTypeIdandPlatform( @Param( "typeId" ) Integer typeId,
                                                               @Param( "userId" ) String userId,
                                                               @Param( "platform" ) String platform,
                                                               Pageable page );

    //查询全部类型 根据时间或下载量排序
    @Query( nativeQuery = true, value = "SELECT DISTINCT \n" +
            "\tAI.APP_ID,\n" +
            "\tAI.APP_NAME,\n" +
            "\tAI.APP_NOTES,\n" +
            "\tAI.APP_ICON,\n" +
            "\tAI.APP_SOURCE,\n" +
            "\tTEMPB.CREATE_TIME,\n" +
            "\tTEMPB.APP_STATUS,\n" +
            "\tAI.APP_LINK," +
            "\tDECODE( AO.USER_ID, :userId, '1', '0' ) IS_OPEN,\n" +
            "\tDECODE( SUAC.USER_ID, :userId, '1', '0' ) IS_COLLECTION,\n" +
            "\tNVL( AD.DOWNLOAD_COUNT, 0 ) DOWNLOAD_COUNT \n" +
            "FROM\n" +
            "\tSTARCLOUDMARKET.SCE_MARKET_APP_INFO AI \n" +
            "\tLEFT JOIN ( SELECT APP_ID, COUNT( APP_ID ) AS DOWNLOAD_COUNT FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD WHERE IS_DELETE = 1 GROUP BY APP_ID ) AD ON AI.APP_ID = AD.APP_ID\n" +
            "\tLEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_OPEN AO ON AI.APP_ID = AO.APP_ID AND AO.IS_DELETE = 1 \n" +
            "\tAND AO.IS_DELETE = 1 \n" +
            "\tAND AO.USER_ID = :userId\n" +
            "\tLEFT JOIN (\n" +
            "\tSELECT\n" +
            "\t\tAVC.APP_ID,\n" +
            "\t\tAVC.APP_VERSION,\n" +
            "\t\tAVC.APP_STATUS,\n" +
            "\t\tTEMPA.CREATE_TIME CREATE_TIME \n" +
            "\tFROM\n" +
            "\t\tSTARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC\n" +
            "\t\tINNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
            "\t\tAND TEMPA.CREATE_TIME = AVC.CREATE_TIME \n" +
            "\t) TEMPB ON AI.APP_ID = TEMPB.APP_ID\n" +
            "\tLEFT JOIN STARCLOUDMARKET.SCE_USER_APP_COLLECTION SUAC ON AI.APP_ID = SUAC.APP_ID AND SUAC.IS_DELETE = 1 \n" +
            "\tAND SUAC.USER_ID = :userId \n" +
            "WHERE\n" +
            "\tAPP_SOURCE = :platform ",
            countQuery = "SELECT count( DISTINCT AI.APP_ID) FROM " +
                    "\tSTARCLOUDMARKET.SCE_MARKET_APP_INFO AI \n" +
                    "\tLEFT JOIN ( SELECT APP_ID, COUNT( APP_ID ) AS DOWNLOAD_COUNT FROM STARCLOUDMARKET.SCE_MARKET_APP_DOWNLOAD WHERE IS_DELETE = 1 GROUP BY APP_ID ) AD ON AI.APP_ID = AD.APP_ID\n" +
                    "\tLEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_OPEN AO ON AI.APP_ID = AO.APP_ID \n" +
                    "\tAND AO.IS_DELETE = 1 \n" +
                    "\tAND AO.USER_ID = :userId\n" +
                    "\tLEFT JOIN (\n" +
                    "\tSELECT\n" +
                    "\t\tAVC.APP_ID,\n" +
                    "\t\tAVC.APP_VERSION,\n" +
                    "\t\tAVC.APP_STATUS,\n" +
                    "\t\tTEMPA.CREATE_TIME CREATE_TIME \n" +
                    "\tFROM\n" +
                    "\t\tSTARCLOUDMARKET.SCE_MARKET_APP_VERSION AVC\n" +
                    "\t\tINNER JOIN ( SELECT AVB.APP_ID, MAX( AVB.CREATE_TIME ) CREATE_TIME FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION AVB GROUP BY AVB.APP_ID ) TEMPA ON AVC.APP_ID = TEMPA.APP_ID \n" +
                    "\t\tAND TEMPA.CREATE_TIME = AVC.CREATE_TIME \n" +
                    "\t) TEMPB ON AI.APP_ID = TEMPB.APP_ID\n" +
                    "\tLEFT JOIN STARCLOUDMARKET.SCE_USER_APP_COLLECTION SUAC ON AI.APP_ID = SUAC.APP_ID AND SUAC.IS_DELETE = 1 \n" +
                    "\tAND SUAC.USER_ID = :userId \n" +
                    "WHERE\n" +
                    "\tAPP_SOURCE = :platform ")
    Page< List< Map< String, Object > > > getAppListInfoByPlatform( @Param( "userId" ) String userId,
                                                       @Param( "platform" ) String platform,
                                                       Pageable pageable );


    @Query( value = "SELECT * FROM STARCLOUDMARKET.V_APP_DETAIL_INFO WHERE APP_ID = ?1 ", nativeQuery = true )
    Page< Map< String, Object > > findAppDetailById( String appId, Pageable pageable );

    @Query( value = "SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_OPEN WHERE APP_ID = ?1 AND USER_ID = ?2", nativeQuery = true )
    Map< String, Object > findAppOpenInfo( String appId, String userId );


    @Query( nativeQuery = true, value = "SELECT count(*) FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO  A  " )
    int getAppCountInfo();

    @Query( nativeQuery = true, value = "SELECT  COUNT(a.APP_TYPE_ID) as num,b.APP_TYPE_NAME FROM  STARCLOUDMARKET.TEST_MARKET_APP_APPTYPE_REL a INNER  JOIN (SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_TYPE   WHERE IS_DELETE='1') b\n" +
            "ON  a.APP_TYPE_ID = b.APP_TYPE_ID  GROUP BY  b.APP_TYPE_NAME,a.APP_TYPE_ID " )
    List< Map > getAppInfo();
}