package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.Message;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 消息通知
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Repository
@FeignClient( "sce-data-access" )
public interface MessageDao {

    /**
     * 添加message
     *
     * @param message 信息
     * @return 是否添加成功
     */
    @RequestMapping( value = "/messages", method = RequestMethod.POST )
    public RestRecord insertMessage( Message message );

    /**
     * 添加公告
     *
     * @param message 信息
     * @return 是否添加成功
     */
    @RequestMapping( value = "/messages/announcements", method = RequestMethod.POST )
    public RestRecord insertAnnouncement( Message message );

    /**
     * 通过id删除message
     *
     * @param messageId  id
     * @return 删除是否成功
     */
    @RequestMapping( value = "/messages/{messageId}", method = RequestMethod.DELETE )
    public RestRecord deleteMessageById( @PathVariable( "messageId" ) Integer messageId );

    /**
     * 通过id删除公告
     *
     * @param announcementId  id
     * @return 删除是否成功
     */
    @RequestMapping( value = "/messages/announcements/{announcementId}", method = RequestMethod.DELETE )
    public RestRecord deleteAnnouncementById( @PathVariable( "announcementId" ) Integer announcementId );

    /**
     * 修改message阅读状态
     *
     * @param messageId id
     * @return 删除是否成功
     */
    @RequestMapping( value = "/messages/update-message-read/{messageId}", method = RequestMethod.PUT )
    public RestRecord updateMessageReadStatusById( @PathVariable( "messageId" )Integer messageId );

    /**
     * 获取message数据
     *
     * @param userId userId
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return message数据
     */
    @RequestMapping( value = "/messages/{userId}/{pageNum}/{pageSize}", method = RequestMethod.GET )
    public RestRecord getMessageByUserId( @PathVariable( "userId" ) String userId,
                                          @PathVariable( "pageNum" ) Integer pageNum,
                                          @PathVariable( "pageSize" ) Integer pageSize );
}

