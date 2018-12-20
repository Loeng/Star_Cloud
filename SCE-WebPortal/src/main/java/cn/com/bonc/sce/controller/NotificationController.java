package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

public class NotificationController {

    /**
     * 查询通知公告列表接口
     * @param auditStatus 通知公告审核状态
     * @param startDate 查询起始日期
     * @param endDate 查询结束日期
     * @param pageNum 分页页码
     * @param pageSize 分页每页条数
     * @param notificationType 通知公告类型
     * @return 分页后的通知公告列表
     */
    @ApiOperation( value = "查询通知公告列表接口", notes = "查询通知公告列表接口", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "auditStatus", dataType = "String", value = "审核状态", paramType = "path", required = false ) ,
            @ApiImplicitParam( name = "startDate", dataType = "String", value = "查询起始日期", paramType = "path", required = false ) ,
            @ApiImplicitParam( name = "endDate", dataType = "String", value = "查询结束日期", paramType = "path", required = false ) ,
            @ApiImplicitParam( name = "pageNum", dataType = "Integer", value = "分页参数-页码", paramType = "path", required = true ) ,
            @ApiImplicitParam( name = "pageSize", dataType = "Integer", value = "分页参数-每页条数", paramType = "path", required = true ) ,
            @ApiImplicitParam( name = "notificationType", dataType = "String", value = "通知公告类型, 0:全部通知公告 1：通知 2：公告", paramType = "path", required = true, allowableValues = "0,1,2")
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )
    @GetMapping("/{pageNum}/{pageSize}/{auditingStatus}/{startDate}/{endDate}")
    @ResponseBody
    public RestRecord getNotificationList ( @PathVariable( "pageNum" ) Integer pageNum,
                                            @PathVariable( "pageSize" ) Integer pageSize,
                                            @PathVariable( "auditStatus" ) String auditStatus,
                                            @PathVariable( "startDate" ) String startDate,
                                            @PathVariable( "endDate" ) String endDate,
                                            @PathVariable( "notificationType" ) String notificationType) {
        // 在common_column_content表中查询所有栏目id为通知公告的数据，返回其标题，图片，内容和时间信息
        return null;
    }

    /**
     * 查询通知公告详情接口
     * @param notificationId 通知公告id
     * @return 分页后的通知公告列表
     */
    @ApiOperation( value = "查询通知公告详情接口", notes = "查询通知公告详情接口", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "notificationId", dataType = "String", value = "通知公告id", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )
    @GetMapping("/{notificationId}")
    @ResponseBody
    public RestRecord getNotificationDetail ( @PathVariable( "notificationId" )  String notificationId ) {
        // 在common_column_content表中查询对应id的通知公告数据，返回其标题，图片，内容详情和时间信息
        return null;
    }

    /**
     * 新增通知公告列表接口
     * @param notificationTitle 通知公告标题
     * @param notificationContent 通知公告内容
     * @param picId 图片信息
     * @param notificationType 通知公告类型
     * @return 添加通知公告是否成功
     */
    @ApiOperation( value = "新增通知公告接口", notes = "新增通知公告接口", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "notificationTitle", dataType = "String", value = "通知公告标题", paramType = "query", required = true ) ,
            @ApiImplicitParam( name = "notificationContent", dataType = "String", value = "通知公告内容", paramType = "query", required = true ) ,
            @ApiImplicitParam( name = "picId", dataType = "String", value = "对应图片信息", paramType = "query", required = true ) ,
            @ApiImplicitParam( name = "notificationType", dataType = "String", value = "通知公告类型, 1：通知 2：公告", paramType = "path", required = true, allowableValues = "1,2")
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord addNotification ( @RequestParam( "notificationTitle" ) String notificationTitle,
                                        @RequestParam( "notificationContent" ) String notificationContent,
                                        @RequestParam( "picId" ) String picId,
                                        @RequestParam( "notificationType" ) String notificationType ) {
        return null;
    }

    /**
     * 更改通知公告列表接口
     * @param notificationId 通知公告Id
     * @param notificationTitle 通知公告标题
     * @param notificationContent 通知公告内容
     * @param picId 图片信息
     * @return 更新通知公告是否成功
     */
    @ApiOperation( value = "更改通知公告接口", notes = "更改通知公告接口", httpMethod = "PATCH" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "notificationTitle", dataType = "String", value = "通知公告标题", paramType = "query", required = true ) ,
            @ApiImplicitParam( name = "notificationContent", dataType = "String", value = "通知公告内容", paramType = "query", required = true ) ,
            @ApiImplicitParam( name = "picId", dataType = "String", value = "对应图片信息", paramType = "query", required = true ) ,
            @ApiImplicitParam( name = "notificationId", dataType = "String", value = "通知公告ID", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )
    @PatchMapping
    @ResponseBody
    public RestRecord updateNotificationDetail ( @RequestParam( "notificationTitle" ) String notificationTitle,
                                                 @RequestParam( "notificationContent" ) String notificationContent,
                                                 @RequestParam( "picId" ) String picId,
                                                 @RequestParam( "notificationId" ) String notificationId ) {
        return null;
    }

    /**
     * 删除通知公告列表接口
     * @param notificationIdList 通知公告Id列表
     * @return 删除通知公告是否成功
     */
    @ApiOperation( value = "删除通知公告接口", notes = "删除通知公告接口", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "notificationIdList", dataType = "List", value = "通知公告Id列表", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )
    @DeleteMapping("/{notificationIdList}")
    @ResponseBody
    public RestRecord updateNotificationDetail ( @PathVariable( "notificationIdList" ) String notificationIdList ) {
        return null;
    }
}
