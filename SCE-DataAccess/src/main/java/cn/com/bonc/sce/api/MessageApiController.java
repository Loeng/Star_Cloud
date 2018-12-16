package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.MessageDao;
import cn.com.bonc.sce.model.Message;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/messages" )
public class MessageApiController {
    private MessageDao messageDao;

    @Autowired
    public MessageApiController( MessageDao messageDao ) {
        this.messageDao = messageDao;
    }

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
            return new RestRecord( messageDao.insertMessage( message ) );
        } catch ( Exception e ) {
            return new RestRecord( 500, "", e );
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
            return new RestRecord( messageDao.insertMessage( message ) );
        } catch ( Exception e ) {
            return new RestRecord( 500, "", e );
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
    public RestRecord deleteMessageById( @PathVariable( "messageId" ) String messageId ) {
        try {
            return new RestRecord( 200, messageDao.deleteMessageById( messageId ) );
        } catch ( Exception e ) {
            return new RestRecord( 500, "", e );
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
    public RestRecord deleteAnnouncementById( @PathVariable( "announcementId" ) String announcementId ) {
        try {
            int totals = messageDao.deleteMessageById( announcementId );
            totals += messageDao.deleteAnnouncementById( announcementId );
            return new RestRecord( 200, totals );
        } catch ( Exception e ) {
            return new RestRecord( 500, "", e );
        }
    }

    /**
     * 修改message阅读状态
     *
     * @param messageId id
     * @return 删除是否成功
     */
    @PutMapping( "/updateMessageRead" )
    @ResponseBody
    public RestRecord updateMessageReadStatusById( String messageId ) {
        try {
            return new RestRecord( 200, messageDao.updateMessageReadStatusById( messageId ) );
        } catch ( Exception e ) {
            return new RestRecord( 500, "", e );
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
            String newestMessageTime = messageDao.getNewestMessageTimeByUserId( userId );
            List< Message > list = messageDao.getNewestMessageFromCommonInformation( userId, newestMessageTime );
            if ( list.size() > 0 ) {
                int totals = messageDao.insertNewestMessageToCommonUserInfo( list );
                if ( totals > 0 ) {
                    return new RestRecord( 200, messageDao.getNewestMessage( userId ) );
                }
                return new RestRecord( 500, "error" );
            } else {
                return new RestRecord( 200, messageDao.getNewestMessage( userId ) );
            }
        } catch ( Exception e ) {
            return new RestRecord( 500, "", e );
        }
    }
}

