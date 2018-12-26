package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.NotificationDao;
import cn.com.bonc.sce.entity.Notification;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通知增删改
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Slf4j
@Service
@Transactional( rollbackFor = Exception.class )
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
        notification.setColumnId( 0 );
        notification.setIsDelete( 1 );
        return new RestRecord( 200, notificationDao.save( notification ) );
    }

    /**
     * 删除通知公告
     *
     * @param list 通知公告Id列表
     * @return 删除通知公告是否成功
     */
    public RestRecord deleteNotificationByIdList( String list ) {
        String[] idArr = list.split( "," );
        int total = 0;
        for ( String idStr : idArr ) {
            total += notificationDao.updateDeleteStatusById( Integer.parseInt( idStr ) );
        }
        return new RestRecord( 200, total );
    }

    /**
     * 更改通知公告
     *
     * @param notification 通知信息
     * @return 更新通知公告是否成功
     */
    public RestRecord updateNotification( Notification notification ) {
        notification.setIsDelete( 1 );
        return new RestRecord( 200, notificationDao.save( notification ) );
    }

    /**
     * 查询通知公告列表
     *
     * @param auditStatus 通知公告审核状态
     * @param content     内容
     * @param startDate   查询起始日期
     * @param endDate     查询结束日期
     * @param pageNum     分页页码
     * @param pageSize    分页每页条数
     * @param type        通知公告类型
     * @return 分页后的通知公告列表
     */
    public RestRecord getNotificationList( Integer type, String content, String auditStatus, String startDate, String endDate, Integer pageNum, Integer pageSize ) {
        Pageable pageable = PageRequest.of( pageNum, pageSize );
        Page< Notification > page;
        if ( StringUtils.isEmpty( content ) ) {
            content = "%%";
        } else {
            content = "%" + content + "%";
        }
        if ( StringUtils.isEmpty( type ) ) {
            if ( StringUtils.isEmpty( startDate ) ) {
                page = notificationDao.findByIsDeleteAndContentLikeAndColumnIdAndContentStatus( 1, content, 0, auditStatus, pageable );
            } else {
                page = notificationDao.findByIsDeleteAndContentLikeAndColumnIdAndContentStatusAndUpdateTimeBetween( 1, content, 0, auditStatus, new Date( Long.parseLong( startDate ) ), new Date( Long.parseLong( endDate ) ), pageable );
            }
        } else {
            if ( StringUtils.isEmpty( startDate ) ) {
                page = notificationDao.findByIsDeleteAndContentLikeAndColumnIdAndContentTypeAndContentStatus( 1, content, 0, type, auditStatus, pageable );
            } else {
                page = notificationDao.findByIsDeleteAndContentLikeAndColumnIdAndContentTypeAndContentStatusAndUpdateTimeBetween( 1, content, 0, type, auditStatus, new Date( Long.parseLong( startDate ) ), new Date( Long.parseLong( endDate ) ), pageable );
            }
        }
        List< Notification > list = page.getContent();
        for ( Notification notification : list ) {
            if ( notification.getUser() == null ) {
                continue;
            }
            notification.setUserName( notification.getUser().getUserName() );
            notification.setUser( null );
        }
        Map< String, Object > info = new HashMap<>();
        info.put( "total", page.getTotalElements() );
        info.put( "info", list );
        return new RestRecord( 200, info );
    }

    /**
     * 查询通知公告详情
     *
     * @param notificationId 通知公告id
     * @return 分页后的通知公告列表
     */
    public RestRecord getNotification( Integer notificationId ) {
        Notification notification = notificationDao.findByIdAndIsDelete( notificationId, 1 );
        if ( notification.getUser() != null ) {
            notification.setUserName( notification.getUser().getUserName() );
            notification.setUser( null );
        }
        return new RestRecord( 200, notification );
    }
}
