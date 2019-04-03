package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.MarketAppVersion;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Repository
@Transactional( rollbackFor = Exception.class )
public interface AppVersionRepository extends
        JpaRepository< MarketAppVersion, Long >, JpaSpecificationExecutor< MarketAppVersion > {

    @Query( nativeQuery = true,
            value = "SELECT * FROM STARCLOUDMARKET.APP_VERSION_DETAIL_VIEW WHERE APP_ID=:appId AND APP_VERSION=:appVersion" )
    Page< List< Map< String, Object > > > queryAppVersionInfo( @Param( "appId" ) String appId,
                                                               @Param( "appVersion" ) String appVersion,
                                                               Pageable pageable );

    //查询计费模式信息
    @Query( nativeQuery = true,
            value = "SELECT * FROM STARCLOUDMARKET.SCE_MARKET_PRICE_MODE WHERE APP_ID= :appId AND APP_VERSION = :appVersion ORDER BY SHOWING_ORDER ASC" )
    List< Map< String, Object > > queryAppPriceModeInfo( @Param( "appId" ) String appId,
                                                         @Param( "appVersion" ) String appVersion );

    /**
     * 通过AppId和AppVersion删除指定的版本信息
     *
     * @param appId      应用Id
     * @param appVersion 应用版本
     * @return 影响条数
     */
    @Modifying
    @Query( "UPDATE MarketAppVersion SET IS_DELETE=0 WHERE APP_ID=:appId and APP_VERSION=:appVersion" )
    int deleteByAppIdAndAppVersion( @Param( "appId" ) String appId,
                                    @Param( "appVersion" ) String appVersion );

    /**
     * 通过AppId删除指定应用的所有版本信息
     *
     * @param appId 应用Id
     * @return 影响条数
     */
    @Modifying
    @Query( "UPDATE MarketAppVersion SET IS_DELETE=0 WHERE APP_ID=:appId" )
    int deleteByAppId( @Param( "appId" ) String appId );

    /**
     * 查询应用下载地址
     *
     * @param appId
     * @param version
     * @param platform
     * @return
     */
    @Query( value = "SELECT APP_DOWNLOAD_ADDRESS FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION WHERE APP_ID = :appId AND APP_VERSION = :version AND RUNNING_PLATFORM = :platform", nativeQuery = true )
    String getDownloadAddressByIdAndVersionAndName( @Param( "appId" ) String appId,
                                                    @Param( "version" ) String version,
                                                    @Param( "platform" ) String platform );


    /* *
     * @Description APP信息暂存
     * @Date 15:28 2019/1/16
     * @Param [appNAME, createUserId, appVersion, downloadAddress, versionInfo, versionSsize, runningPlatform, newFeatures, packageName, authDetail, appPhonePic, appPcPic, appIcon, companyId, appType, tempAppId]
     * @return int
     */
    @Modifying
    @Query( nativeQuery = true,
            value = "INSERT INTO STARCLOUDMARKET.SCE_MARKET_TEMP_APP(APP_NAME,CREATE_USER_ID,APP_VERSION,DOWNLOAD_ADDRESS,VERSION_INFO,VERSION_SIZE,RUNNING_PLATFORM,NEW_FEATURES,PACKAGE_NAME,AUTH_DETAIL,APP_PHONE_PIC,APP_PC_PIC,APP_ICON,COMPANY_ID,APP_TYPE,TEMP_APP_ID) " +
                    " VALUES (:APP_NAME, :CREATE_USER_ID, :APP_VERSION, :DOWNLOAD_ADDRESS, :VERSION_INFO, :VERSION_SIZE, :RUNNING_PLATFORM, :NEW_FEATURES, :PACKAGE_NAME, :AUTH_DETAIL, :APP_PHONE_PIC, :APP_PC_PIC, :APP_ICON, :COMPANY_ID, :APP_TYPE, :TEMP_APP_ID)" )
    int insertTempAppInfo( @Param( "APP_NAME" ) String appNAME,
                           @Param( "CREATE_USER_ID" ) String createUserId,
                           @Param( "APP_VERSION" ) String appVersion,
                           @Param( "DOWNLOAD_ADDRESS" ) String downloadAddress,
                           @Param( "VERSION_INFO" ) String versionInfo,
                           @Param( "VERSION_SIZE" ) String versionSize,
                           @Param( "RUNNING_PLATFORM" ) String runningPlatform,
                           @Param( "NEW_FEATURES" ) String newFeatures,
                           @Param( "PACKAGE_NAME" ) String packageName,
                           @Param( "AUTH_DETAIL" ) String authDetail,
                           @Param( "APP_PHONE_PIC" ) String appPhonePic,
                           @Param( "APP_PC_PIC" ) String appPcPic,
                           @Param( "APP_ICON" ) String appIcon,
                           @Param( "COMPANY_ID" ) String companyId,
                           @Param( "APP_TYPE" ) String appType,
                           @Param( "TEMP_APP_ID" ) String tempAppId );


    /* *
     * @Description APP暂存信息修改
     * @Date 15:28 2019/1/16
     * @Param [appNAME, createUserId, appVersion, downloadAddress, versionInfo, versionSsize, runningPlatform, newFeatures, packageName, authDetail, appPhonePic, appPcPic, appIcon, companyId, appType, tempAppId]
     * @return int
     */
    @Modifying
    @Query( value = "UPDATE STARCLOUDMARKET.SCE_MARKET_TEMP_APP SET APP_NAME= :APP_NAME,CREATE_USER_ID= :CREATE_USER_ID,APP_VERSION= :APP_VERSION," +
            "DOWNLOAD_ADDRESS= :DOWNLOAD_ADDRESS,VERSION_INFO= :VERSION_INFO,VERSION_SIZE= :VERSION_SIZE,RUNNING_PLATFORM= :RUNNING_PLATFORM," +
            "NEW_FEATURES= :NEW_FEATURES,PACKAGE_NAME= :PACKAGE_NAME,AUTH_DETAIL= :AUTH_DETAIL,APP_PHONE_PIC= :APP_PHONE_PIC,APP_PC_PIC= :APP_PC_PIC," +
            "APP_ICON= :APP_ICON,COMPANY_ID= :COMPANY_ID,APP_TYPE= :APP_TYPE WHERE TEMP_APP_ID= :TEMP_APP_ID ", nativeQuery = true )
    int updateTempAppInfo( @Param( "APP_NAME" ) String appNAME,
                           @Param( "CREATE_USER_ID" ) String createUserId,
                           @Param( "APP_VERSION" ) String appVersion,
                           @Param( "DOWNLOAD_ADDRESS" ) String downloadAddress,
                           @Param( "VERSION_INFO" ) String versionInfo,
                           @Param( "VERSION_SIZE" ) String versionSize,
                           @Param( "RUNNING_PLATFORM" ) String runningPlatform,
                           @Param( "NEW_FEATURES" ) String newFeatures,
                           @Param( "PACKAGE_NAME" ) String packageName,
                           @Param( "AUTH_DETAIL" ) String authDetail,
                           @Param( "APP_PHONE_PIC" ) String appPhonePic,
                           @Param( "APP_PC_PIC" ) String appPcPic,
                           @Param( "APP_ICON" ) String appIcon,
                           @Param( "COMPANY_ID" ) String companyId,
                           @Param( "APP_TYPE" ) String appType,
                           @Param( "TEMP_APP_ID" ) String tempAppId );

    /**
     * 根据appId查所有版本号
     *
     * @param appId
     * @return
     */
    @Query( nativeQuery = true, value = "SELECT APP_VERSION FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION WHERE APP_ID=:appId" )
    List< String > getVersionByAppId( @Param( "appId" ) String appId );
}
