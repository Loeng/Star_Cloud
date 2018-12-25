package cn.com.bonc.sce.repository;


import cn.com.bonc.sce.entity.AppInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface AppInfoRepository extends JpaRepository< AppInfoEntity, String > {

    //根据平台类型查询有哪些软件
    Page< AppInfoEntity > findByAppSource( String platformType, Pageable pageable );

    //查应用详情
    AppInfoEntity findByAppId( String appId );

    //根据输入名模糊查询应用
    Page< AppInfoEntity > findByAppNameLike( String appName, Pageable pageable );

    //查询应用审核状态码表
    @Query( nativeQuery = true, value = "SELECT ID ,AUDIT_STATUS FROM STARCLOUDMARKET.SCE_AUDIT_STATUS_DIC ORDER BY ID ASC" )
    List< Map< String, Object > > getAllAuditStatus();

    @Query( nativeQuery = true, value = "SELECT * FROM STARCLOUDMARKET.\"APP_MANAGE_INFO_VIEW\"  WHERE APP_STATUS =:auditStatus AND  APP_TYPE_ID=:typeId " )
    Page< List< Map< String, Object > > > getInfoById( @Param( value = "auditStatus" ) String auditStatus,
                                                       @Param( value = "typeId" ) Integer typeId,
                                                       Pageable pageable );

    @Query( nativeQuery = true, value = "SELECT * FROM STARCLOUDMARKET.\"APP_MANAGE_INFO_VIEW\" WHERE APP_STATUS =:auditStatus" )
    Page< List< Map< String, Object > > > getInfo( @Param( value = "auditStatus" ) String auditStatus,
                                                   Pageable pageable );

    @Query( nativeQuery = true, value = "SELECT * FROM STARCLOUDMARKET.\"APP_MANAGE_INFO_VIEW\"  A WHERE  A.APP_STATUS =:auditStatus AND A.APP_NAME  LIKE  CONCAT('%',CONCAT(:keyword,'%'))" )
    Page< List< Map< String, Object > > > getInfoByKeyword( @Param( value = "auditStatus" ) String auditStatus,
                                                            @Param( value = "keyword" ) String keyword,
                                                            Pageable pageable );

    @Query( nativeQuery = true, value = "SELECT * FROM STARCLOUDMARKET.\"APP_MANAGE_INFO_VIEW\"  A  WHERE A.APP_TYPE_ID=:typeId AND A.APP_STATUS =:auditStatus AND A.APP_NAME LIKE CONCAT('%',CONCAT(:keyword,'%'))" )
    Page< List< Map< String, Object > > > getInfoByTypeIdAndKeyword( @Param( value = "auditStatus" ) String auditStatus,
                                                                     @Param( value = "typeId" ) Integer typeId,
                                                                     @Param( value = "keyword" ) String keyword,
                                                                     Pageable pageable );
}