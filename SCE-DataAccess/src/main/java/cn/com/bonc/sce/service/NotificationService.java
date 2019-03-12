package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.NotificationDao;
import cn.com.bonc.sce.entity.Notification;
import cn.com.bonc.sce.rest.RestRecord;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

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

    private final String ASC = "ASC";
    private final String DESC = "DESC";
    private final String SORT_STR = "createTime";

    /**
     * 新增通知公告
     *
     * @param notification 通知公告
     * @return 添加通知公告是否成功
     */
    public RestRecord insertNotification( Notification notification ) {
        notification.setId( null );
        notification.setColumnId( 0 );
        notification.setContentStatus( "1" );
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
     * 查询通知公告列表（包含地址，先通过地址查询有哪些用户。）
     *
     * @param auditStatus 通知公告审核状态
     * @param content     内容
     * @param startDate   查询起始日期
     * @param endDate     查询结束日期
     * @param pageNum     分页页码
     * @param pageSize    分页每页条数
     * @param type        通知公告类型
     * @param province     省
     * @param city         市
     * @param district     区
     * @return 分页后的通知公告列表
     */
    public RestRecord getNotificationList( Integer type, String content, String auditStatus, String startDate, String endDate, String province, String city, String district, Integer pageNum, Integer pageSize ) {
        List< Object > userIdList = new ArrayList<>();
        if ( !StringUtils.isEmpty( district ) ) {
            userIdList = notificationDao.selectUserIdByAddress( province, city, district );
        } else if ( !StringUtils.isEmpty( city ) ) {
            userIdList = notificationDao.selectUserIdByAddress( province, city );
        } else if ( !StringUtils.isEmpty( province ) ) {
            userIdList = notificationDao.selectUserIdByAddress( province );
        }
        if ( userIdList.size() == 0 ) {
            Map< String, Object > info = new HashMap<>();
            info.put( "total", 0 );
            info.put( "info", new ArrayList<Notification>(  ) );
            //根据地址没有匹配到user
            return new RestRecord( 200, info );
        } else {
            pageNum--;
            Sort sort = Sort.by( Sort.Direction.fromString( DESC ), SORT_STR );
            Pageable pageable = PageRequest.of( pageNum, pageSize, sort );
            Page< Notification > page;
            if ( StringUtils.isEmpty( content ) ) {
                content = "%%";
            } else {
                content = "%" + content + "%";
            }
            String all = "3";
            if ( all.equals( auditStatus ) ) {
                if ( StringUtils.isEmpty( type ) ) {
                    if ( StringUtils.isEmpty( startDate ) ) {
                        page = notificationDao.findByIsDeleteAndContentLikeAndColumnIdAndCreateUserIdIn( 1, content, 0, userIdList, pageable );
                    } else {
                        page = notificationDao.findByIsDeleteAndContentLikeAndColumnIdAndUpdateTimeBetweenAndCreateUserIdIn( 1, content, 0, new Date( Long.parseLong( startDate ) ), new Date( Long.parseLong( endDate ) ), userIdList, pageable );
                    }
                } else {
                    if ( StringUtils.isEmpty( startDate ) ) {
                        page = notificationDao.findByIsDeleteAndContentLikeAndColumnIdAndContentTypeAndCreateUserIdIn( 1, content, 0, type, userIdList, pageable );
                    } else {
                        page = notificationDao.findByIsDeleteAndContentLikeAndColumnIdAndContentTypeAndUpdateTimeBetweenAndCreateUserIdIn( 1, content, 0, type, new Date( Long.parseLong( startDate ) ), new Date( Long.parseLong( endDate ) ), userIdList, pageable );
                    }
                }
            } else {
                if ( StringUtils.isEmpty( type ) ) {
                    if ( StringUtils.isEmpty( startDate ) ) {
                        page = notificationDao.findByIsDeleteAndContentLikeAndColumnIdAndContentStatusAndCreateUserIdIn( 1, content, 0, auditStatus, userIdList, pageable );
                    } else {
                        page = notificationDao.findByIsDeleteAndContentLikeAndColumnIdAndContentStatusAndUpdateTimeBetweenAndCreateUserIdIn( 1, content, 0, auditStatus, new Date( Long.parseLong( startDate ) ), new Date( Long.parseLong( endDate ) ), userIdList, pageable );
                    }
                } else {
                    if ( StringUtils.isEmpty( startDate ) ) {
                        page = notificationDao.findByIsDeleteAndContentLikeAndColumnIdAndContentTypeAndContentStatusAndCreateUserIdIn( 1, content, 0, type, auditStatus, userIdList, pageable );
                    } else {
                        page = notificationDao.findByIsDeleteAndContentLikeAndColumnIdAndContentTypeAndContentStatusAndUpdateTimeBetweenAndCreateUserIdIn( 1, content, 0, type, auditStatus, new Date( Long.parseLong( startDate ) ), new Date( Long.parseLong( endDate ) ), userIdList, pageable );
                    }
                }
            }
            List< Notification > list = page.getContent();
            Map< String, Object > info = new HashMap<>();
            info.put( "total", page.getTotalElements() );
            info.put( "info", list );
            return new RestRecord( 200, info );
        }
    }

    /**
     * 查询通知公告详情
     *
     * @param notificationId 通知公告id
     * @return 分页后的通知公告列表
     */
    public Notification getNotification( Integer notificationId ) {
        Notification notification = notificationDao.findByIdAndIsDelete( notificationId, 1 );
        return notification;
    }


    /**
     * 查询通知公告列表 （地址参数为空）
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
        pageNum--;
        Sort sort = Sort.by( Sort.Direction.fromString( DESC ), SORT_STR );
        Pageable pageable = PageRequest.of( pageNum, pageSize, sort );
        Page< Notification > page;
        if ( StringUtils.isEmpty( content ) ) {
            content = "%%";
        } else {
            content = "%" + content + "%";
        }
        String all = "3";
        if ( all.equals( auditStatus ) ) {
            if ( StringUtils.isEmpty( type ) ) {
                if ( StringUtils.isEmpty( startDate ) ) {
                    page = notificationDao.findByIsDeleteAndContentLikeAndColumnId( 1, content, 0, pageable );
                } else {
                    page = notificationDao.findByIsDeleteAndContentLikeAndColumnIdAndUpdateTimeBetween( 1, content, 0, new Date( Long.parseLong( startDate ) ), new Date( Long.parseLong( endDate ) ), pageable );
                }
            } else {
                if ( StringUtils.isEmpty( startDate ) ) {
                    page = notificationDao.findByIsDeleteAndContentLikeAndColumnIdAndContentType( 1, content, 0, type, pageable );
                } else {
                    page = notificationDao.findByIsDeleteAndContentLikeAndColumnIdAndContentTypeAndUpdateTimeBetween( 1, content, 0, type, new Date( Long.parseLong( startDate ) ), new Date( Long.parseLong( endDate ) ), pageable );
                }
            }
        } else {
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
        }
        List< Notification > list = page.getContent();
        Map< String, Object > info = new HashMap<>();
        info.put( "total", page.getTotalElements() );
        info.put( "info", list );
        return new RestRecord( 200, info );
    }
}
