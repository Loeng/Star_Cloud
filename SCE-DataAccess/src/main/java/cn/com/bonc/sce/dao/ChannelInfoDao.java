package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.ChannelInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 频道接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/26 15:00
 */
@Repository
@FeignClient( "sce-data-access" )
public interface ChannelInfoDao extends JpaRepository< ChannelInfo, Integer > {
    @Override
    ChannelInfo save( ChannelInfo channelInfo );

    @Modifying
    @Query( "UPDATE ChannelInfo c SET c.isDelete=0 WHERE c.id=?1" )
    Integer updateDeleteStatusById( Integer id );

    List<ChannelInfo> findByIsDelete( Integer isDelete );
}
