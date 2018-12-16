package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.MessageDao;
import cn.com.bonc.sce.model.Message;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public RestRecord insertMessage(Message message){
        return messageDao.insertMessage(message);
    }

    /**
     * 添加公告
     *
     * @param message 公告信息
     * @return 是否添加成功
     */
    public RestRecord insertAnnouncement(Message message){
        return messageDao.insertAnnouncement(message);
    }

    /**
     * 修改message阅读状态
     *
     * @param messageId id
     * @return 删除是否成功
     */
    public RestRecord updateMessageReadStatusById(String messageId){
        return messageDao.updateMessageReadStatusById(messageId);
    }

    /**
     * 通过id删除message
     *
     * @param messageId  id
     * @return 删除是否成功
     */
    public RestRecord deleteMessageById(String messageId){
        return messageDao.deleteMessageById(messageId);
    }

    /**
     * 获取message数据
     *
     * @param userId userId
     * @return message数据
     */
    public RestRecord getMessageByUserId(String userId){
        return messageDao.getMessageByUserId(userId);
    }
}
