package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.ColumnInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 栏目接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/26 15:00
 */
@Repository
@FeignClient( "sce-data-access" )
public interface ColumnInfoDao extends JpaRepository< ColumnInfo, Integer > {
    @Override
    ColumnInfo save( ColumnInfo columnInfo );

    @Modifying
    @Query( "UPDATE ColumnInfo c SET c.isDelete=0 WHERE c.id=?1" )
    Integer updateDeleteStatusById( Integer id );

    List< ColumnInfo > findByIsDelete(Integer isDelete);
}
