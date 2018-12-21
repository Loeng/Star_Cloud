package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.NotificationDao;
import cn.com.bonc.sce.model.message.Notification;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 通知增删改
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Slf4j
@Service
public class NotificationService {

    @Autowired
    private NotificationDao notificationDao;

    /**
     * 新增通知公告
     *
     * @param notification 通知公告
     * @return 添加通知公告是否成功
     */
    public RestRecord insertNotification( Notification notification ) {
        return notificationDao.insertNotification( notification );
    }

    /**
     * 删除通知公告
     *
     * @param list 通知公告Id列表
     * @return 删除通知公告是否成功
     */
    public RestRecord deleteNotificationByIdList( String list ) {
        return notificationDao.deleteNotificationByIdList( list );
    }

    /**
     * 更改通知公告
     *
     * @param notification 通知信息
     * @return 更新通知公告是否成功
     */
    public RestRecord updateNotification( Notification notification ) {
        return notificationDao.updateNotification( notification );
    }

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
    public RestRecord getNotificationList( Integer type,String auditStatus,String startDate,String endDate,Integer pageNum,Integer pageSize ) {
        return notificationDao.getNotificationList( type, auditStatus, startDate, endDate, pageNum, pageSize );
    }

    /**
     * 查询通知公告详情
     *
     * @param notificationId 通知公告id
     * @return 分页后的通知公告列表
     */
    public RestRecord getNotification( Integer notificationId ) {
        return notificationDao.getNotification( notificationId );
    }
}
