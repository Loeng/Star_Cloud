package cn.com.bonc.sce.controller;

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
@Api( value = "消息通知接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RequestMapping( "/message" )
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
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
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
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PostMapping
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
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @DeleteMapping( "/{messageId}" )
    @ResponseBody
    public RestRecord deleteMessageById( @PathVariable( "messageId" )String messageId ) {
        return messageService.deleteMessageById( messageId );
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
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PutMapping( "/{messageId}" )
    @ResponseBody
    public RestRecord updateMessageReadStatusById( @PathVariable( "messageId" )String messageId ) {
        return messageService.updateMessageReadStatusById( messageId );
    }

    /**
     * 获取message数据
     *
     * @param userId userId
     * @return message数据
     */
    @ApiOperation( value = "获取message数据", notes = "获取message数据", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "userId", value = "用户id", paramType = "header", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/{userId}" )
    @ResponseBody
    public RestRecord getMessageByUserId( @PathVariable( "userId" )String userId ) {
        return messageService.getMessageByUserId( userId );
    }
}
