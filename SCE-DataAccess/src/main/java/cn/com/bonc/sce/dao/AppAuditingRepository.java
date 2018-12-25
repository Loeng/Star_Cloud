package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.MarketAppVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Repository
@Transactional( rollbackFor = Exception.class )
public interface AppAuditingRepository extends JpaRepository< MarketAppVersion, Long > {

    @Modifying
    @Query(
            value = "UPDATE MarketAppVersion SET APP_STATUS=4,UPDATE_TIME=sysdate,UPDATE_USER_ID=:userId WHERE APP_ID=:appId AND APP_VERSION=:appVersion " )
    int appVersionApprove( @Param( "appId" ) String appId, @Param( "appVersion" ) String appVersion, @Param( "userId" ) String userId );

    @Modifying
    @Query(
            value = "UPDATE MarketAppVersion SET APP_STATUS=3,UPDATE_TIME=sysdate,UPDATE_USER_ID=:userId WHERE APP_ID=:appId AND APP_VERSION=:appVersion " )
    int appVersionReject( @Param( "appId" ) String appId, @Param( "appVersion" ) String appVersion, @Param( "userId" ) String userId );
}
