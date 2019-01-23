package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.MarketAppVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query( nativeQuery = true, value = "SELECT APP_NAME AS appName FROM \"STARCLOUDMARKET\".\"SCE_MARKET_APP_INFO\" WHERE APP_ID=:appId" )
    Map< String, Object > getAppName( @Param( "appId" ) String appId );

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
            value = "UPDATE AppInfoEntity SET APP_LINK=:appLink,UPDATE_TIME=sysdate,UPDATE_USER_ID=:userId WHERE APP_ID=:appId ",nativeQuery = true)
    int updateAppLink( @Param( "appId" ) String appId, @Param( "userId" ) String userId, @Param( "appLink" ) String appLink );


}
