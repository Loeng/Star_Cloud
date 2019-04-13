package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.MarketAppVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Repository
@Transactional( rollbackFor = Exception.class )
public interface AppAuditingRepository extends JpaRepository< MarketAppVersion, String > {

    /**
     * 根据应用Id和版本号查询应用版本详情
     *
     * @param appId      应用Id
     * @param appVersion 版本号
     * @return
     */
    MarketAppVersion findByAppIdAndAppVersion( String appId, String appVersion );

    @Query( nativeQuery = true, value = "SELECT APP_NAME AS appName FROM \"STARCLOUDMARKET\".\"SCE_MARKET_APP_VERSION\" WHERE APP_ID=:appId AND APP_VERSION=:appVersion" )
    Map< String, Object > getAppName( @Param( "appId" ) String appId, @Param( "appVersion" ) String appVersion );

    @Modifying
    @Query(
            value = "UPDATE MarketAppVersion SET APP_STATUS=4,UPDATE_TIME=sysdate,UPDATE_USER_ID=:userId WHERE APP_ID=:appId AND APP_VERSION=:appVersion " )
    int appVersionApprove( @Param( "appId" ) String appId, @Param( "appVersion" ) String appVersion, @Param( "userId" ) String userId );

    @Modifying
    @Query(
            value = "UPDATE MarketAppVersion SET APP_STATUS=3,UPDATE_TIME=sysdate,UPDATE_USER_ID=:userId WHERE APP_ID=:appId AND APP_VERSION=:appVersion " )
    int appVersionReject( @Param( "appId" ) String appId, @Param( "appVersion" ) String appVersion, @Param( "userId" ) String userId );

    @Modifying
    @Query(
            value = "UPDATE MarketAppVersion SET INDEX_URL=:indexUrl,AUDIT_TIME=sysdate,UPDATE_USER_ID=:userId WHERE APP_ID=:appId AND APP_VERSION = :appVersion", nativeQuery = false )
    int updateIndexUrl( @Param( "appId" ) String appId, @Param( "appVersion" ) String appVersion, @Param( "userId" ) String userId, @Param( "indexUrl" ) String indexUrl );


}
