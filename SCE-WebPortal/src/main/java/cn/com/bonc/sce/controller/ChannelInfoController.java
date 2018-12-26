package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.ChannelInfo;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.ChannelInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 频道接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/26 15:00
 */
@Slf4j
@RestController
@Api( value = "频道接口", tags = "频道接口"  )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RequestMapping( "/channels" )
public class ChannelInfoController {

    @Autowired
    private ChannelInfoService channelInfoService;

    /**
     * 添加频道
     *
     * @param channelInfo 添加频道
     * @return 是否添加成功
     */
    @ApiOperation( value = "添加频道", notes = "添加频道", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insert( @RequestBody @ApiParam( name = "频道", value = "频道信息", required = true ) ChannelInfo channelInfo ) {
        return channelInfoService.insert( channelInfo );
    }

    /**
     * 通过id删除
     *
     * @param id id
     * @return 删除是否成功
     */
    @ApiOperation( value = "通过id删除", notes = "通过id删除", httpMethod = "DELETE" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 408, message = MessageConstants.SCE_MSG_408, response = RestRecord.class )
    } )
    @DeleteMapping( "/{id}" )
    @ResponseBody
    public RestRecord deleteById( @PathVariable( "id" ) @ApiParam( name = "id", value = "id", required = true ) String id ) {
        return channelInfoService.deleteById( id );
    }

    /**
     * 更新频道
     *
     * @param channelInfo 频道
     * @return channelInfo
     */
    @ApiOperation( value = "更新频道", notes = "更新频道", httpMethod = "PUT" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PutMapping
    @ResponseBody
    public RestRecord updateInfo( @RequestBody @ApiParam( name = "频道", value = "频道信息", required = true ) ChannelInfo channelInfo ) {
        return channelInfoService.updateInfo( channelInfo );
    }

    /**
     * 获取所有频道数据
     *
     * @return 频道数据list
     */
    @ApiOperation( value = "获取所有频道数据", notes = "获取所有频道数据", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping
    @ResponseBody
    public RestRecord getAll() {
        return channelInfoService.getAll();
    }
}
