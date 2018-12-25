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

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Repository
@Transactional( rollbackFor = Exception.class )
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


}
