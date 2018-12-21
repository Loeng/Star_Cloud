package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.PortalMessageConstants;
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
@Api( value = "通知增删改相关接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
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
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "title", dataType = "String", value = "通知公告标题", paramType = "query", required = true ),
            @ApiImplicitParam( name = "content", dataType = "String", value = "通知公告内容", paramType = "query", required = true ),
            @ApiImplicitParam( name = "picId", dataType = "Integer", value = "对应图片信息", paramType = "query", required = true ),
            @ApiImplicitParam( name = "type", dataType = "Integer", value = "通知公告类型, 1：通知 2：公告", paramType = "path", required = true, allowableValues = "1,2" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertNotification( Notification notification ) {
        return notificationService.insertNotification( notification );
    }

    /**
     * 删除通知公告
     *
     * @param list 通知公告Id列表
     * @return 删除通知公告是否成功
     */
    @ApiOperation( value = "删除通知公告接口", notes = "删除通知公告接口", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "list", dataType = "String", value = "通知公告Id列表", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 408, message = MessageConstants.SCE_MSG_408, response = RestRecord.class )
    } )
    @DeleteMapping( "/{list}" )
    @ResponseBody
    public RestRecord deleteNotificationByIdList( @PathVariable( "list" ) String list ) {
        return notificationService.deleteNotificationByIdList( list );
    }

    /**
     * 更改通知公告
     *
     * @param notification 通知信息
     * @return 更新通知公告是否成功
     */
    @ApiOperation( value = "更改通知公告接口", notes = "更改通知公告接口", httpMethod = "PATCH" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "notificationId", dataType = "Integer", value = "通知公告标题", paramType = "query", required = true ),
            @ApiImplicitParam( name = "title", dataType = "String", value = "通知公告标题", paramType = "query", required = true ),
            @ApiImplicitParam( name = "content", dataType = "String", value = "通知公告内容", paramType = "query", required = true ),
            @ApiImplicitParam( name = "picId", dataType = "Integer", value = "对应图片信息", paramType = "query", required = true ),
            @ApiImplicitParam( name = "type", dataType = "Integer", value = "通知公告类型, 1：通知 2：公告", paramType = "path", required = true, allowableValues = "1,2" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PutMapping
    @ResponseBody
    public RestRecord updateNotification( Notification notification ) {
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
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "type", dataType = "Integer", value = "通知公告类型, 0:全部通知公告 1：通知 2：公告", paramType = "path", required = true, allowableValues = "0,1,2" ),
            @ApiImplicitParam( name = "auditStatus", dataType = "String", value = "审核状态", paramType = "path", required = false ),
            @ApiImplicitParam( name = "startDate", dataType = "String", value = "查询起始日期", paramType = "path", required = false ),
            @ApiImplicitParam( name = "endDate", dataType = "String", value = "查询结束日期", paramType = "path", required = false ),
            @ApiImplicitParam( name = "pageNum", dataType = "Integer", value = "分页参数-页码", paramType = "path", required = true ),
            @ApiImplicitParam( name = "pageSize", dataType = "Integer", value = "分页参数-每页条数", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping( "/{type}/{auditingStatus}/{startDate}/{endDate}/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getNotificationList( @PathVariable( "type" ) Integer type,
                                           @PathVariable( "auditStatus" ) String auditStatus,
                                           @PathVariable( "startDate" ) String startDate,
                                           @PathVariable( "endDate" ) String endDate,
                                           @PathVariable( "pageNum" ) Integer pageNum,
                                           @PathVariable( "pageSize" ) Integer pageSize ) {
        return notificationService.getNotificationList( type, auditStatus, startDate, endDate, pageNum, pageSize );
    }

    /**
     * 查询通知公告详情
     *
     * @param notificationId 通知公告id
     * @return 分页后的通知公告列表
     */
    @ApiOperation( value = "查询通知公告详情接口", notes = "查询通知公告详情接口", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "notificationId", dataType = "Integer", value = "通知公告id", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping( "/{notificationId}" )
    @ResponseBody
    public RestRecord getNotification( @PathVariable( "notificationId" ) Integer notificationId ) {
        return notificationService.getNotification( notificationId );
    }
}
