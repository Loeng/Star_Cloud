package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.MarketAppVersion;
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
            value = "SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_VERSION WHERE APP_ID=:appId AND APP_VERSION LIKE CONCAT('%',CONCAT(:appVersion,'%')) AND IS_DELETE=1" )
    Page< List< Map< String, Object > > > queryAppVersionInfo( @Param( "appId" ) String appId,
                                                               @Param( "appVersion" ) String appVersion,
                                                               Pageable pageable );

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


}
