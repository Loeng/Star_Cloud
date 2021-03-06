package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.MessageDao;
import cn.com.bonc.sce.dao.UserDao;
import cn.com.bonc.sce.dao.UserMessageDao;
import cn.com.bonc.sce.entity.Message;
import cn.com.bonc.sce.entity.UserMessage;
import cn.com.bonc.sce.entity.user.User;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.*;

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
    @Autowired
    private UserDao userDao;

    private final String ASC = "ASC";
    private final String DESC = "DESC";
    private final String SORT_CREATE_TIME = "createTime";
    private final String SORT_IS_READ = "isRead";

    /**
     * 添加message
     *
     * @param message 信息
     * @return 是否添加成功
     */
    public RestRecord insertMessage( Message message ) {
        message.setId( null );
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
     * @param id       id
     * @param pageNum  页码
     * @param pageSize 每页条数
     * @return message数据
     */
    public RestRecord getMessageByUserId( String userId, Integer id, Integer pageNum, Integer pageSize ) {
        User user = userDao.findByUserId( userId );
        Integer roleId = user.getUserType();
        List< Integer > typeList = new ArrayList<>();
        if ( roleId != 0 ) {
            typeList.add( 0 );
        }
        typeList.add( roleId );
        if ( id == null ) {
            pageNum--;
            List< Sort.Order > orders = new ArrayList<>();
            orders.add( new Sort.Order( Sort.Direction.ASC, SORT_IS_READ ) );
            orders.add( new Sort.Order( Sort.Direction.DESC, SORT_CREATE_TIME ) );
            Pageable pageable = PageRequest.of( pageNum, pageSize, new Sort( orders ) );
            Timestamp time = messageDao.getNewestTimeByUserId( userId );
            if ( StringUtils.isEmpty( time ) ) {
                time = messageDao.getCreateTimeByUserId( userId );
            }
            List< Message > list;
            if ( !StringUtils.isEmpty( time ) ) {
                list = messageDao.findByTypeInAndCreateTimeAfterAndIsDeleteOrTargetIdAndCreateTimeAfterAndIsDelete( typeList, time, 1, userId, time, 1 );
            } else {
                list = messageDao.findByTypeInAndIsDeleteOrTargetIdAndIsDelete( typeList, 1, userId, 1 );
            }
            List< UserMessage > userMessageList = new ArrayList<>();
            if ( list.size() > 0 ) {
                for ( Message m : list ) {
                    UserMessage um = new UserMessage();
                    um.setIsRead( 0 );
                    um.setUserId( userId );
                    Timestamp createTime = m.getCreateTime();
                    um.setCreateTime( new Timestamp( createTime.getTime() ) );
                    um.setIsDelete( 1 );
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
            Date createTime = user.getCreateTime();
            Timestamp timestamp = new Timestamp( createTime.getTime() );
            Page< UserMessage > page = userMessageDao.findByUserIdAndIsDeleteAndCreateTimeAfter( userId, 1, timestamp, pageable );

            info.put( "total", page.getTotalElements() );
            info.put( "info", page.getContent() );
            return new RestRecord( 200, info );
        } else {
            return new RestRecord( 200, userMessageDao.findByIdAndIsDelete( id, 1 ) );
        }
    }

    /**
     * 获取未读信息数据
     *
     * @param userId userId
     * @return count
     */
    public RestRecord getIsNotReadCount( String userId ) {
        return new RestRecord( 200, userMessageDao.getIsNotReadCount( userId ) );
    }
}

