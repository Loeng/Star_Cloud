package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.ChannelInfoDao;
import cn.com.bonc.sce.entity.ChannelInfo;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 频道接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/26 15:00
 */
@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
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
        channelInfo.setId( null );
        channelInfo.setIsDelete( 1 );
        return new RestRecord( 200,channelInfoDao.save( channelInfo ));
    }

    /**
     * 通过id删除
     *
     * @param id id
     * @return 删除是否成功
     */
    public RestRecord deleteById( Integer id ) {
        return new RestRecord( 200,channelInfoDao.updateDeleteStatusById( id ));
    }

    /**
     * 更新频道
     *
     * @param channelInfo 频道
     * @return channelInfo
     */
    public RestRecord updateInfo( ChannelInfo channelInfo ) {
        return new RestRecord( 200,channelInfoDao.save( channelInfo ));
    }

    /**
     * 获取所有频道数据
     *
     * @return 频道数据list
     */
    public RestRecord getAll() {
        return new RestRecord( 200,channelInfoDao.findByIsDelete(1));
    }
}
