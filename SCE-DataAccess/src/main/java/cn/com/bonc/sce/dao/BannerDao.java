package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * banner接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Repository
public interface BannerDao extends JpaRepository< Banner, Integer > {
    @Override
    Banner save( Banner banner );

    @Modifying
    @Query( "UPDATE Banner b SET b.isDelete=0 WHERE b.id=?1" )
    Integer updateDeleteStatusById( Integer id );

    @Modifying
    @Query( "UPDATE Banner b SET b.url=?2 WHERE b.id=?1" )
    Integer updateUrlById( Integer id, String url );

    @Modifying
    @Query( "UPDATE Banner b SET b.appId=?2 WHERE b.id=?1" )
    Integer updateAppIdById( Integer id, String appId );

    @Modifying
    @Query( "UPDATE Banner b SET b.order=?2 WHERE b.id=?1" )
    Integer updateOrderById( Integer id, Integer order );

    List< Banner > findByTypeAndIsDelete( Integer type,Integer isDelete );

    Banner findByTypeAndIdAndIsDelete( Integer type,Integer bannerId,Integer isDelete );

    List< Banner > findByIsDelete(Integer isDelete);
}
