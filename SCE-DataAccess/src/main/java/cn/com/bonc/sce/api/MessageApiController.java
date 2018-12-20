package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.MessageDao;
import cn.com.bonc.sce.dao.UserMessageDao;
import cn.com.bonc.sce.model.message.Message;
import cn.com.bonc.sce.model.message.UserMessage;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Slf4j
@RestController
@RequestMapping( "/messages" )
public class MessageApiController {
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
    @PostMapping( "" )
    @ResponseBody
    public RestRecord insertMessage( Message message ) {
        try {
            return new RestRecord( 200, messageDao.save( message ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(),e );
            return new RestRecord( 409, MessageConstants.SCE_MSG_409, e );
        }
    }

    /**
     * 添加公告
     *
     * @param message 信息
     * @return 是否添加成功
     */
    @PostMapping( "/announcements" )
    @ResponseBody
    public RestRecord insertAnnouncement( Message message ) {
        try {
            return new RestRecord( 200, messageDao.save( message ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(),e );
            return new RestRecord( 409, MessageConstants.SCE_MSG_409, e );
        }
    }

    /**
     * 通过id删除message
     *
     * @param messageId id
     * @return 删除是否成功
     */
    @DeleteMapping( "/{messageId}" )
    @ResponseBody
    public RestRecord deleteMessageById( @PathVariable( "messageId" ) Integer messageId ) {
        try {
            return new RestRecord( 200, userMessageDao.updateDeleteStatusById( messageId ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(),e );
            return new RestRecord( 408, MessageConstants.SCE_MSG_408, e );
        }
    }

    /**
     * 通过id删除公告
     *
     * @param announcementId id
     * @return 删除是否成功
     */
    @DeleteMapping( "/announcements/{announcementId}" )
    @ResponseBody
    public RestRecord deleteAnnouncementById( @PathVariable( "announcementId" ) Integer announcementId ) {
        try {
            int totals = messageDao.updateDeleteStatusById( announcementId );
            totals += userMessageDao.updateDeleteStatusByMessageId( announcementId );
            return new RestRecord( 200, totals );
        } catch ( Exception e ) {
            log.error( e.getMessage(),e );
            return new RestRecord( 408, MessageConstants.SCE_MSG_408, e );
        }
    }

    /**
     * 修改message阅读状态
     *
     * @param messageId id
     * @return 删除是否成功
     */
    @PatchMapping( "/updateMessageRead/{messageId}" )
    @ResponseBody
    public RestRecord updateMessageReadStatusById( @PathVariable( "messageId" )Integer messageId ) {
        try {
            return new RestRecord( 200, userMessageDao.updateIsReadById( messageId ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(),e );
            return new RestRecord( 407, MessageConstants.SCE_MSG_407, e );
        }
    }

    /**
     * 获取message数据
     *
     * @param userId userId
     * @return message数据
     */
    @GetMapping( "/{userId}" )
    @ResponseBody
    public RestRecord getMessageByUserId( @PathVariable( "userId" ) String userId ) {
        try {
            String time = messageDao.getNewestTimeByUserId( userId );
            if( StringUtils.isEmpty( time))time="1970-1-1 00:00:00.000000";
            List< Message > list = messageDao.findByTargetIdAndCreateTimeGreaterThanAndIsDelete( userId, time,0 );
            List< UserMessage > userMessageList = new ArrayList<>();
            if ( list.size() > 0 ) {
                for ( Message m : list ) {
                    UserMessage um = new UserMessage();
                    um.setIsRead( 0 );
                    um.setUserId( userId );
                    um.setCreateTime( m.getCreateTime() );
                    um.setIsDelete( 0 );
                    um.setMessageId( m.getId() );
                    userMessageList.add( um );
                }
                List<UserMessage> returns = userMessageDao.saveAll( userMessageList );
                if ( returns.size() > 0 ) {
                    return new RestRecord( 200, userMessageDao.findByUserIdAndIsDelete( userId,0 ) );
                }
                return new RestRecord( 500, "error" );
            } else {
                return new RestRecord( 200, userMessageDao.findByUserIdAndIsDelete( userId,0 ) );
            }
        } catch ( Exception e ) {
            log.error( e.getMessage(),e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }
}

