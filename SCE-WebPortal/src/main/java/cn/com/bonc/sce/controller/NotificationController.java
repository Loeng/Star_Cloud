package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.Notification;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.NotificationService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 通知增删改相关接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Slf4j
@Api( value = "通知增删改相关接口", tags = "通知增删改相关接口" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RestController
@RequestMapping( "/notifications" )
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 新增通知公告
     *
     * @param notification 通知公告
     * @return 添加通知公告是否成功
     */
    @ApiOperation( value = "新增通知公告接口", notes = "新增通知公告接口", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertNotification( @RequestBody @ApiParam( name = "notification", value = "公告信息", required = true ) Notification notification ) {
        return notificationService.insertNotification( notification );
    }

    /**
     * 删除通知公告
     *
     * @param list 通知公告Id列表
     * @return 删除通知公告是否成功
     */
    @ApiOperation( value = "删除通知公告接口", notes = "删除通知公告接口", httpMethod = "DELETE" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 408, message = MessageConstants.SCE_MSG_408, response = RestRecord.class )
    } )
    @DeleteMapping( "" )
    @ResponseBody
    public RestRecord deleteNotificationByIdList( @RequestParam(value = "list") @ApiParam( name = "list", value = "待删除的公告列表", required = true ) String list ) {
        return notificationService.deleteNotificationByIdList( list );
    }

    /**
     * 更改通知公告
     *
     * @param notification 通知信息
     * @return 更新通知公告是否成功
     */
    @ApiOperation( value = "更改通知公告接口", notes = "更改通知公告接口", httpMethod = "PUT" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PutMapping
    @ResponseBody
    public RestRecord updateNotification( @RequestBody @ApiParam( name = "news", value = "新闻信息", required = true ) Notification notification ) {
        return notificationService.updateNotification( notification );
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
    @ApiOperation( value = "查询通知公告列表接口", notes = "查询通知公告列表接口", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping( "/list/{type}/{auditStatus}" )
    @ResponseBody
    public RestRecord getNotificationList( @PathVariable( "type" ) @ApiParam( name = "type", value = "类型") Integer type,
                                           @PathVariable( "auditStatus" ) @ApiParam( name = "auditStatus", value = "状态") String auditStatus,
                                           @RequestParam( value = "startDate", required = false ) @ApiParam( name = "startDate", value = "开始时间") String startDate,
                                           @RequestParam( value = "endDate", required = false ) @ApiParam( name = "endDate", value = "结束时间") String endDate,
                                           @RequestParam( value = "pageNum", required = false, defaultValue = "0"  ) @ApiParam( name = "pageNum", value = "页码")Integer pageNum,
                                           @RequestParam( value = "pageSize", required = false, defaultValue = "10"  ) @ApiParam( name = "pageSize", value = "数量")Integer pageSize ) {
        return notificationService.getNotificationList( type, auditStatus, startDate, endDate, pageNum, pageSize );
    }

    /**
     * 查询通知公告详情
     *
     * @param notificationId 通知公告id
     * @return 分页后的通知公告列表
     */
    @ApiOperation( value = "查询通知公告详情接口", notes = "查询通知公告详情接口", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping( "/{notificationId}" )
    @ResponseBody
    public RestRecord getNotification( @PathVariable( "notificationId" ) @ApiParam( name = "notificationId", value = "公告id" ,required = true) Integer notificationId ) {
        return notificationService.getNotification( notificationId );
    }
}
