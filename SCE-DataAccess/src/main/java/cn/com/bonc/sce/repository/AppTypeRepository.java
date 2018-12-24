package cn.com.bonc.sce.repository;


import cn.com.bonc.sce.entity.AppTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author BTW
 */
@Transactional( rollbackFor = Exception.class )
public interface AppTypeRepository extends JpaRepository< AppTypeEntity, String > {
    /**
     * 根据id查询app类型名称
     *
     * @param appTypeId
     * @param pageable
     * @return
     */
    Page< AppTypeEntity > findByAppTypeId( Integer appTypeId, Pageable pageable );

    /**
     * 查询app类型名称列表
     *
     * @return 返回类型名称与ID列表
     */
    @Query( value = "SELECT APP_TYPE_ID, APP_TYPE_NAME FROM STARCLOUDMARKET.SCE_MARKET_APP_TYPE WHERE IS_DELETE = 1", nativeQuery = true )
    List< Map< String, Object > > getAllAppTypeList();

    /**
     * 更新类型名称信息
     *
     * @param appTypeId
     * @param appTypeName
     * @return
     */
    @Transactional( rollbackFor = Exception.class )
    @Modifying
    @Query( value = "UPDATE STARCLOUDMARKET.SCE_MARKET_APP_TYPE SET APP_TYPE_NAME = :appTypeName WHERE APP_TYPE_ID = :appTypeId", nativeQuery = true )
    int updateAppTypeInfo( @Param( value = "appTypeId" ) Integer appTypeId,
                           @Param( value = "appTypeName" ) String appTypeName );

    /**
     * 新增app分类
     *
     * @param appTypeName
     * @return
     */
    @Transactional( rollbackFor = Exception.class )
    @Modifying
    @Query( value = "INSERT INTO STARCLOUDMARKET.SCE_MARKET_APP_TYPE ( APP_TYPE_NAME ) VALUES (:appTypeName )", nativeQuery = true )
    int addAppTypeInfo( @Param( value = "appTypeName" ) String appTypeName );

    /**
     * 新增app分类
     *
     * @param s
     * @return
     */
    @Override
    < S extends AppTypeEntity > S save( S s );

    /**
     * 删除app分类
     *
     * @param appTypeId
     * @return
     */
    @Transactional( rollbackFor = Exception.class )
    @Modifying
    @Query( value = "UPDATE STARCLOUDMARKET.SCE_MARKET_APP_TYPE SET IS_DELETE = 0 WHERE APP_TYPE_ID = :appTypeId", nativeQuery = true )
    int deleteAppTypeInfo( @Param( value = "appTypeId" ) Integer appTypeId );


}