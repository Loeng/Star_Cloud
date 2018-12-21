package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.model.message.Message;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.MessageService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 消息通知接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Slf4j
@RestController
@Api( value = "消息通知接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RequestMapping( "/messages" )
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 添加message
     *
     * @param message 信息
     * @return 是否添加成功
     */
    @ApiOperation( value = "添加message", notes = "添加message信息", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "message", value = "advice信息", paramType = "body", required = true),
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertMessage( Message message ) {
        //权限不足
        if(message.getType()==1)return new RestRecord(400,"error");
        return messageService.insertMessage( message );
    }

    /**
     * 添加公告
     *
     * @param message 信息
     * @return 是否添加成功
     */
    @ApiOperation( value = "添加公告", notes = "添加公告信息", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "message", value = "公告信息", paramType = "body", required = true),
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping( "/announcements" )
    @ResponseBody
    public RestRecord insertAnnouncement( Message message ) {
        return messageService.insertAnnouncement( message );
    }

    /**
     * 通过id删除message
     *
     * @param messageId id
     * @return 删除是否成功
     */
    @ApiOperation( value = "通过id删除message", notes = "通过id删除message", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "messageId", value = "消息id", paramType = "header", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 408, message = MessageConstants.SCE_MSG_408, response = RestRecord.class )
    } )
    @DeleteMapping( "/{messageId}" )
    @ResponseBody
    public RestRecord deleteMessageById( @PathVariable( "messageId" )Integer messageId ) {
        return messageService.deleteMessageById( messageId );
    }

    /**
     * 通过id删除公告
     *
     * @param announcementId id
     * @return 删除是否成功
     */
    @ApiOperation( value = "通过id删除公告", notes = "通过id删除公告", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "announcementId", value = "公告id", paramType = "header", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 408, message = MessageConstants.SCE_MSG_408, response = RestRecord.class )
    } )
    @DeleteMapping( "/announcements/{announcementId}" )
    @ResponseBody
    public RestRecord deleteAnnouncementById( @PathVariable( "announcementId" )Integer announcementId ) {
        return messageService.deleteAnnouncementById( announcementId );
    }

    /**
     * 修改message阅读状态
     *
     * @param messageId id
     * @return 删除是否成功
     */
    @ApiOperation( value = "修改message阅读状态", notes = "修改message阅读状态", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "messageId", value = "消息id", paramType = "body", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PutMapping( "/{messageId}" )
    @ResponseBody
    public RestRecord updateMessageReadStatusById( @PathVariable( "messageId" )Integer messageId ) {
        return messageService.updateMessageReadStatusById( messageId );
    }

    /**
     * 获取message数据
     *
     * @param userId userId
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return message数据
     */
    @ApiOperation( value = "获取message数据", notes = "获取message数据", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "userId", value = "用户id", paramType = "header", required = true ),
            @ApiImplicitParam( name = "pageNum", dataType = "Integer", value = "分页参数-页码", paramType = "path", required = true ),
            @ApiImplicitParam( name = "pageSize", dataType = "Integer", value = "分页参数-每页条数", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping( "/{userId}/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getMessageByUserId( @PathVariable( "userId" )String userId,
                                          @PathVariable( "pageNum" )Integer pageNum,
                                          @PathVariable( "pageSize" )Integer pageSize ) {
        return messageService.getMessageByUserId( userId,pageNum,pageSize );
    }
}

