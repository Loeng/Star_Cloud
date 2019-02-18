package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.ChannelInfo;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

/**
 * 频道接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/26 15:00
 */
@Repository
@FeignClient( "sce-data-access" )
public interface ChannelInfoDao {
    /**
     * 添加频道
     *
     * @param channelInfo 添加频道
     * @return 是否添加成功
     */
    @RequestMapping( value = "/channels", method = RequestMethod.POST )
    public RestRecord insert( ChannelInfo channelInfo );

    /**
     * 通过id删除
     *
     * @param id id
     * @return 删除是否成功
     */
    @RequestMapping( value = "/channels/{id}", method = RequestMethod.DELETE )
    public RestRecord deleteById( @PathVariable( "id" ) String id );

    /**
     * 更新频道
     *
     * @param channelInfo 频道
     * @return channelInfo
     */
    @RequestMapping( value = "/channels", method = RequestMethod.PUT )
    public RestRecord updateInfo( ChannelInfo channelInfo );

    /**
     * 获取所有频道数据
     *
     * @return 频道数据list
     */
    @RequestMapping( value = "/channels", method = RequestMethod.GET )
    public RestRecord getAll();
}
