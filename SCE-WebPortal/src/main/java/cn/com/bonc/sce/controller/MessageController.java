package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.Message;
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
@Api( value = "消息通知接口", tags = "消息通知接口" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
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
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertMessage( @RequestBody @ApiParam( name = "message", value = "信息", required = true )Message message ) {
        if(message.getContent().length()>200)return new RestRecord(409,MessageConstants.SCE_MSG_409);
        return messageService.insertMessage( message );
    }

    /**
     * 添加公告
     *
     * @param message 信息
     * @return 是否添加成功
     */
    @ApiOperation( value = "添加公告", notes = "添加公告信息", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping( "/announcements" )
    @ResponseBody
    public RestRecord insertAnnouncement( @RequestBody @ApiParam( name = "message", value = "信息", required = true )Message message ) {
        return messageService.insertAnnouncement( message );
    }

    /**
     * 通过id删除message
     *
     * @param messageId id
     * @return 删除是否成功
     */
    @ApiOperation( value = "通过id删除message", notes = "通过id删除message", httpMethod = "DELETE" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 408, message = MessageConstants.SCE_MSG_408, response = RestRecord.class )
    } )
    @DeleteMapping( "/{messageId}" )
    @ResponseBody
    public RestRecord deleteMessageById( @PathVariable( "messageId" ) @ApiParam( name = "messageId", value = "信息id", required = true ) Integer messageId ) {
        return messageService.deleteMessageById( messageId );
    }

    /**
     * 通过id删除公告
     *
     * @param announcementId id
     * @return 删除是否成功
     */
    @ApiOperation( value = "通过id删除公告", notes = "通过id删除公告", httpMethod = "DELETE" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 408, message = MessageConstants.SCE_MSG_408, response = RestRecord.class )
    } )
    @DeleteMapping( "/announcements/{announcementId}" )
    @ResponseBody
    public RestRecord deleteAnnouncementById( @PathVariable( "announcementId" ) @ApiParam( name = "announcementId", value = "公告id", required = true )Integer announcementId ) {
        return messageService.deleteAnnouncementById( announcementId );
    }

    /**
     * 修改message阅读状态
     *
     * @param messageId id
     * @return 删除是否成功
     */
    @ApiOperation( value = "修改message阅读状态", notes = "修改message阅读状态", httpMethod = "PUT" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PutMapping( "/{messageId}" )
    @ResponseBody
    public RestRecord updateMessageReadStatusById( @PathVariable( "messageId" ) @ApiParam( name = "messageId", value = "信息id", required = true ) Integer messageId ) {
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
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping( "/{userId}" )
    @ResponseBody
    public RestRecord getMessageByUserId( @PathVariable( "userId" ) @ApiParam( name = "userId", value = "userId", required = true )String userId,
                                          @RequestParam( value = "pageNum", required = false, defaultValue = "1"  ) @ApiParam( name = "pageNum", value = "页码")Integer pageNum,
                                          @RequestParam( value = "pageSize", required = false, defaultValue = "10"  ) @ApiParam( name = "pageSize", value = "数量")Integer pageSize ) {
        return messageService.getMessageByUserId( userId,pageNum,pageSize );
    }
}

