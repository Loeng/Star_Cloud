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

    //查询平台应用图标，名字，id
    @Query( nativeQuery = true, value = "SELECT a.APP_ID,a.APP_NAME,a.APP_ICON,a.APP_NOTES FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO a WHERE a.APP_SOURCE = 'pt'" )
    Page< List< Map< String, Object > > > getPlatformlist( Pageable pageable );

    //根据类型id 查询 appid 列表
    @Query( nativeQuery = true, value = "SELECT APP_ID FROM STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL WHERE APP_TYPE_ID= :appTypeId" )
    List< Object > getAppIdByTypeId( @Param( "appTypeId" ) Integer appTypeId );

    //根据appid 查询APP_CONDITION_INFO_VIEW视图
    @Query( nativeQuery = true, value = "SELECT * FROM STARCLOUDMARKET.\"APP_CONDITION_INFO_VIEW\" WHERE APP_ID IN (:appIdList) AND APP_SOURCE = 'rj'" )
    Page< List< Map< String, Object > > > getAppListInfoByIds( @Param( "appIdList" ) List< Object > appIdList,
                                                               Pageable page );

    //查询全部类型 根据时间或下载量排序
    @Query( nativeQuery = true, value = "SELECT * FROM STARCLOUDMARKET.\"APP_CONDITION_INFO_VIEW\" WHERE APP_SOURCE='rj'" )
    Page< List< Map< String, Object > > > getSoftware( Pageable pageable );
}