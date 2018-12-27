package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.MessageDao;
import cn.com.bonc.sce.model.Message;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息通知
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Slf4j
@Service
public class MessageService {
    private MessageDao messageDao;

    @Autowired
    public MessageService( MessageDao messageDao ) {
        this.messageDao = messageDao;
    }

    /**
     * 添加message
     *
     * @param message 用户信息
     * @return 是否添加成功
     */
    public RestRecord insertMessage( Message message ) {
        return messageDao.insertMessage( message );
    }

    /**
     * 添加公告
     *
     * @param message 公告信息
     * @return 是否添加成功
     */
    public RestRecord insertAnnouncement( Message message ) {
        return messageDao.insertAnnouncement( message );
    }

    /**
     * 修改message阅读状态
     *
     * @param messageId id
     * @return 删除是否成功
     */
    public RestRecord updateMessageReadStatusById( Integer messageId ) {
        return messageDao.updateMessageReadStatusById( messageId );
    }

    /**
     * 通过id删除message
     *
     * @param messageId id
     * @return 删除是否成功
     */
    public RestRecord deleteMessageById( Integer messageId ) {
        return messageDao.deleteMessageById( messageId );
    }

    /**
     * 通过id删除message
     *
     * @param announcementId id
     * @return 删除是否成功
     */
    public RestRecord deleteAnnouncementById( Integer announcementId ) {
        return messageDao.deleteAnnouncementById( announcementId );
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
        return messageDao.getMessageByUserId( userId, id, pageNum, pageSize );
    }
}

