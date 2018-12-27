package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.MarketAppVersion;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional( rollbackFor = Exception.class )
public interface MarketAppVersionRepository extends
        JpaRepository< MarketAppVersion, Long >, JpaSpecificationExecutor< MarketAppVersion > {
    /**
     * 应用版本查询接口
     * 1.通过应用Id查询应用所有的版本信息
     * 2.通过应用Id和应用版本查询该版本的详细信息
     *
     * @param appId      应用Id
     * @param appVersion 模糊查询的应用版本
     * @param isDelete   是否有效
     * @return
     */
    List< MarketAppVersion > findAllByAppIdAndAppVersionContainingAndIsDelete(
            String appId, String appVersion, int isDelete );

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

//    @Modifying
//    @Query( "" )
//    MarketAppVersion applyVersion();

    //    @Query(   "SELECT *  FROM SCE_MARKET_APP_VERSION WHERE IS_DELETE=:isDelete" )
    List< MarketAppVersion > findAllByIsDelete( @Param( "isDelete" ) int isDelete );


    /**
     * app上下架
     *
     * @param applyType
     * @param appIdList
     * @param userId
     * @return
     */
    @Modifying
    @Query( value = "UPDATE MarketAppVersion  SET appStatus= :applyType,updateUserId= :userId  where  appId in (:appIdList)" )
    int applyAppOnShelfByUserId( @Param( "applyType" ) String applyType, @Param( "appIdList" ) List< String > appIdList, @Param( "userId" ) String userId );

}
