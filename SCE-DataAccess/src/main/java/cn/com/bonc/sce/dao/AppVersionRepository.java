package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.MarketAppVersion;
import cn.com.bonc.sce.model.AppVersionQueryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Repository
public interface AppVersionRepository extends
        JpaRepository< MarketAppVersion, Long >, JpaSpecificationExecutor< MarketAppVersion > {
    /**
     * @param appId      应用Id
     * @param appVersion 应用版本
     * @param isDelete   是否删除
     * @param pageable   分页参数
     * @return
     */
    Page< MarketAppVersion > findAllByAppIdAndAppVersionContainingAndIsDelete(
            String appId, String appVersion, long isDelete, Pageable pageable );

    /**
     * 通过AppId和AppVersion删除指定的版本信息
     *
     * @param appId      应用Id
     * @param appVersion 应用版本
     * @return
     */
    @Modifying
    @Query( "UPDATE MarketAppVersion SET IS_DELETE=1 WHERE APP_ID=:appId and APP_VERSION=:appVersion" )
    int deleteByAppIdAndAppVersion( @Param( "appId" ) String appId, @Param( "appVersion" ) String appVersion );

    /**
     * 通过AppId删除指定应用的所有版本信息
     *
     * @param appId 应用Id
     * @return
     */
    @Modifying
    @Query( "UPDATE MarketAppVersion SET IS_DELETE=1 WHERE APP_ID=:appId" )
    int deleteByAppId( @Param( "appId" ) String appId );

    @Query( nativeQuery = true, value = "SELECT\n" +
            "SMAV.APP_ID,\n" +
            "SMAI.APP_NAME,\n" +
            "SMAI.COMPANY_ID,\n" +
            "SMAI.CATEGORY_ID,\n" +
            "SMAV.APP_VERSION,\n" +
            "TEST.CURRENT_VERSION,\n" +
            "SMAV.APP_DOWNLOAD_ADDRESS,\n" +
            "SMAV.CREATE_TIME,\n" +
            "SMAV.RUNNING_PLATFORM,\n" +
            "SMAV.NEW_FEATURES,\n" +
            "SMAV.PACKAGE_NAME,\n" +
            "SMAV.AUTH_DETAIL,\n" +
            "SMC.COMPANY_NAME \n" +
            "FROM\n" +
            "\"SCE_MARKET_APP_INFO\" SMAI,\n" +
            "\"SCE_MARKET_APP_VERSION\" SMAV,\n" +
            "\"SCE_MARKET_COMPANY\" SMC,\n" +
            "(\n" +
            "SELECT\n" +
            "SMAI.APP_ID AS TEMP_ID,\n" +
            "SMAV.APP_STATUS AS TEMP_STATUS,\n" +
            "SMAV.APP_VERSION AS CURRENT_VERSION \n" +
            "FROM\n" +
            "\"SCE_MARKET_APP_INFO\" SMAI,\n" +
            "\"SCE_MARKET_APP_VERSION\" SMAV \n" +
            "WHERE\n" +
            "SMAI.APP_ID = SMAV.APP_ID \n" +
            "AND SMAV.APP_STATUS = '审核通过' \n" +
            ") TEST \n" +
            "WHERE\n" +
            "SMAI.APP_ID = SMAV.APP_ID \n" +
            "AND SMAI.COMPANY_ID = SMC.COMPANY_ID \n" +
            "AND SMAV.APP_ID = TEST.TEMP_ID AND SMAV.IS_DELETE=:isDelete" )
    List< Object[] > findAllByIsDelete( @Param( "isDelete" ) Long isDelete );


    @Query( nativeQuery = true, value = "select * from MarketAppVersion av join AppInfoEntity ai where av.appId=ai.appId and av.isDelete=:isDelete" )
    Page< MarketAppVersion > findAllByIsDelete(
            @Param( "isDelete" ) long isDelete, Pageable pageable );


    @Query( value = "SELECT T1.APP_ID AS APP_ID,T2.APP_NAME AS APP_NAME,T1.APP_STATUS FROM SCE_MARKET_APP_VERSION T1 LEFT JOIN SCE_MARKET_APP_INFO T2 ON T1.APP_ID=T2.APP_ID ",
            countQuery = "SELECT COUNT(*) FROM SCE_MARKET_APP_VERSION T1 LEFT JOIN SCE_MARKET_APP_INFO T2 ON T1.APP_ID=T2.APP_ID "
            , nativeQuery = true )
    Page< AppVersionQueryModel > findAppAndName( Pageable pageable );
}
