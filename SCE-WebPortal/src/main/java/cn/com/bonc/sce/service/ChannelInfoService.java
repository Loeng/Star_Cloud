package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.ChannelInfoDao;
import cn.com.bonc.sce.model.ChannelInfo;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 频道接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/26 15:00
 */
@Slf4j
@Service
public class ChannelInfoService {

    @Autowired
    private ChannelInfoDao channelInfoDao;

    /**
     * 添加频道
     *
     * @param channelInfo 添加频道
     * @return 是否添加成功
     */
    public RestRecord insert( ChannelInfo channelInfo ) {
        return channelInfoDao.insert( channelInfo );
    }

    /**
     * 通过id删除
     *
     * @param id id
     * @return 删除是否成功
     */
    public RestRecord deleteById( String id ) {
        return channelInfoDao.deleteById( id );
    }

    /**
     * 更新频道
     *
     * @param channelInfo 频道
     * @return channelInfo
     */
    public RestRecord updateInfo( ChannelInfo channelInfo ) {
        return channelInfoDao.updateInfo( channelInfo );
    }

    /**
     * 获取所有频道数据
     *
     * @return 频道数据list
     */
    public RestRecord getAll() {
        return channelInfoDao.getAll();
    }
}
