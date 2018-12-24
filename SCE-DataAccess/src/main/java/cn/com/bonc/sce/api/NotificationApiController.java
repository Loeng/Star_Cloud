package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.NotificationDao;
import cn.com.bonc.sce.entity.Notification;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 通知增删改
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Slf4j
@RestController
@RequestMapping( "/notifications" )
public class NotificationApiController {

    @Autowired
    private NotificationDao notificationDao;

    /**
     * 新增通知公告
     *
     * @param notification 通知公告
     * @return 添加通知公告是否成功
     */
    @PostMapping
    @ResponseBody
    public RestRecord insertNotification( @RequestBody Notification notification ) {
        notification.setIsDelete( 0 );
        try {
            return new RestRecord( 200, notificationDao.save( notification ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 409, MessageConstants.SCE_MSG_409, e );
        }
    }

    /**
     * 删除通知公告
     *
     * @param list 通知公告Id列表
     * @return 删除通知公告是否成功
     */
    @DeleteMapping( "/{list}" )
    @ResponseBody
    public RestRecord deleteNotificationByIdList( @PathVariable( "list" ) String list ) {
        try {
            String[] idArr = list.split( "," );
            int total = 0;
            for(String idStr:idArr){
                total+=notificationDao.updateDeleteStatusById( Integer.parseInt( idStr ));
            }
            return new RestRecord( 200, total );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 408, MessageConstants.SCE_MSG_408, e );
        }
    }

    /**
     * 更改通知公告
     *
     * @param notification 通知信息
     * @return 更新通知公告是否成功
     */
    @PutMapping
    @ResponseBody
    public RestRecord updateNotification( @RequestBody Notification notification ) {
        notification.setIsDelete( 0 );
        try {
            return new RestRecord( 200, notificationDao.save( notification ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 407, MessageConstants.SCE_MSG_407, e );
        }
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
    @GetMapping( "/{type}/{auditStatus}/{startDate}/{endDate}/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getNotificationList( @PathVariable( "type" ) Integer type,
                                           @PathVariable( "auditStatus" ) String auditStatus,
                                           @PathVariable( "startDate" ) String startDate,
                                           @PathVariable( "endDate" ) String endDate,
                                           @PathVariable( "pageNum" ) Integer pageNum,
                                           @PathVariable( "pageSize" ) Integer pageSize ) {
        try {
            Pageable pageable = PageRequest.of( pageNum, pageSize );
            Page< Notification > page = notificationDao.findByIsDeleteAndContentTypeAndContentStatusAndUpdateTimeBetween( 0,type,auditStatus,startDate,endDate, pageable );
            Map<String,Object> info = new HashMap<>();
            info.put( "total",page.getTotalElements() );
            info.put( "info",page.getContent() );
            return new RestRecord( 200, info );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }

    /**
     * 查询通知公告详情
     *
     * @param notificationId 通知公告id
     * @return 分页后的通知公告列表
     */
    @GetMapping( "/{notificationId}" )
    @ResponseBody
    public RestRecord getNotification( @PathVariable( "notificationId" ) Integer notificationId ) {
        try {
            return new RestRecord( 200, notificationDao.findByIdAndIsDelete( notificationId, 0 ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }
}
