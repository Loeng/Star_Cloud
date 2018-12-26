package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.MessageDao;
import cn.com.bonc.sce.dao.UserMessageDao;
import cn.com.bonc.sce.entity.Message;
import cn.com.bonc.sce.entity.UserMessage;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Slf4j
@Service
@Transactional( rollbackFor = Exception.class )
public class MessageService {
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private UserMessageDao userMessageDao;

    /**
     * 添加message
     *
     * @param message 信息
     * @return 是否添加成功
     */
    public RestRecord insertMessage( Message message ) {
        message.setId( null );
        message.setType( 1 );
        message.setStatus( "0" );
        message.setIsDelete( 1 );
        return new RestRecord( 200, messageDao.save( message ) );
    }

    /**
     * 添加公告
     *
     * @param message 信息
     * @return 是否添加成功
     */
    public RestRecord insertAnnouncement( Message message ) {
        message.setId( null );
        message.setType( 0 );
        message.setStatus( "0" );
        message.setIsDelete( 1 );
        return new RestRecord( 200, messageDao.save( message ) );
    }

    /**
     * 通过id删除message
     *
     * @param messageId id
     * @return 删除是否成功
     */
    public RestRecord deleteMessageById( Integer messageId ) {
        return new RestRecord( 200, userMessageDao.updateDeleteStatusById( messageId ) );
    }

    /**
     * 通过id删除公告
     *
     * @param announcementId id
     * @return 删除是否成功
     */
    public RestRecord deleteAnnouncementById( Integer announcementId ) {
        int totals = messageDao.updateDeleteStatusById( announcementId );
        totals += userMessageDao.updateDeleteStatusByMessageId( announcementId );
        return new RestRecord( 200, totals );
    }

    /**
     * 修改message阅读状态
     *
     * @param messageId id
     * @return 删除是否成功
     */
    public RestRecord updateMessageReadStatusById( Integer messageId ) {
        return new RestRecord( 200, userMessageDao.updateIsReadById( messageId ) );
    }

    /**
     * 获取message数据
     *
     * @param userId   userId
     * @param pageNum  页码
     * @param pageSize 每页条数
     * @return message数据
     */
    public RestRecord getMessageByUserId( String userId, Integer pageNum, Integer pageSize ) {
        pageNum--;
        Pageable pageable = PageRequest.of( pageNum, pageSize );
        Timestamp time = messageDao.getNewestTimeByUserId( userId );
        List< Message > list;
        if ( !StringUtils.isEmpty( time ) ) {
            list = messageDao.findByTargetIdAndCreateTimeAfterAndIsDelete( userId, time, 1 );
        } else {
            list = messageDao.findByTargetIdAndIsDelete( userId, 1 );
        }
        List< UserMessage > userMessageList = new ArrayList<>();
        if ( list.size() > 0 ) {
            for ( Message m : list ) {
                UserMessage um = new UserMessage();
                um.setIsRead( 0 );
                um.setUserId( userId );
                Timestamp createTime = m.getCreateTime();
                um.setCreateTime( new Timestamp( createTime.getTime() ) );
                um.setIsDelete( 0 );
                um.setMessageId( m.getId() );
                userMessageList.add( um );
                if ( userMessageList.size() >= 1000 ) {
                    userMessageDao.saveAll( userMessageList );
                    userMessageList.clear();
                }
            }
            if ( userMessageList.size() > 0 ) {
                userMessageDao.saveAll( userMessageList );
            }
        }
        Map< String, Object > info = new HashMap<>();
        Page< UserMessage > page = userMessageDao.findByUserIdAndIsDelete( userId, 1, pageable );
        info.put( "total", page.getTotalElements() );
        info.put( "info", page.getContent() );
        return new RestRecord( 200, info );
    }
}

