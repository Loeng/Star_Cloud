package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.Notification;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

/**
 * 通知增删改相关接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Repository
@FeignClient( "sce-data-access" )
public interface NotificationDao {

    /**
     * 新增通知公告
     *
     * @param notification 通知公告
     * @return 添加通知公告是否成功
     */
    @RequestMapping( value = "/notifications", method = RequestMethod.POST )
    public RestRecord insertNotification( Notification notification ) ;

    /**
     * 删除通知公告
     *
     * @param list 通知公告Id列表
     * @return 删除通知公告是否成功
     */
    @RequestMapping( value = "/notifications/{list}", method = RequestMethod.DELETE )
    public RestRecord deleteNotificationByIdList( @PathVariable( "list" ) String list );

    /**
     * 更改通知公告
     *
     * @param notification 通知信息
     * @return 更新通知公告是否成功
     */
    @RequestMapping( value = "/notifications", method = RequestMethod.PUT )
    public RestRecord updateNotification( Notification notification );

    /**
     * 查询通知公告列表
     *
     * @param auditStatus 通知公告审核状态
     * @param startDate   查询起始日期
     * @param endDate     查询结束日期
     * @param pageNum     分页页码
     * @param pageSize    分页每页条数
     * @param type        通知公告类型
     * @return 分页后的通知公告列表
     */
    @RequestMapping( value = "/notifications/{type}/{auditStatus}/{startDate}/{endDate}/{pageNum}/{pageSize}", method = RequestMethod.GET )
    public RestRecord getNotificationList( @PathVariable( "type" ) Integer type,
                                           @PathVariable( "auditStatus" ) String auditStatus,
                                           @PathVariable( "startDate" ) String startDate,
                                           @PathVariable( "endDate" ) String endDate,
                                           @PathVariable( "pageNum" ) Integer pageNum,
                                           @PathVariable( "pageSize" ) Integer pageSize );

    /**
     * 查询通知公告详情
     *
     * @param notificationId 通知公告id
     * @return 分页后的通知公告列表
     */
    @RequestMapping( value = "/notifications/{notificationId}", method = RequestMethod.GET )
    public RestRecord getNotification( @PathVariable( "notificationId" ) Integer notificationId );
}
