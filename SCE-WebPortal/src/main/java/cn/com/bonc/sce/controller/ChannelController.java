package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.model.Channel;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * author jc_D
 */
@Slf4j
@RestController
@Api( value = "频道管理接口", tags = "频道管理接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RequestMapping( "/channel" )
public class ChannelController {

    /**
     * 频道查询
     * 通过父频道ID查询频道列表，打开页面默认传入-1，只查询顶层频道信息，依次点开频道树状结构，传入当前频道ID，即查询当前频道下的所有频道信息。
     *
     * @param pChannelId 频道id
     * @return 频道信息
     */
    @ApiOperation( value = "频道查询接口", notes = "频道查询接口", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "pChannelId", value = "频道id", paramType = "query", required = true ),
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/{pChannelId}" )
    @ResponseBody
    public RestRecord searchChannel( @PathVariable( "pChannelId" ) String pChannelId ) {
        return null;
    }

    /**
     * 添加频道
     * 包括频道名字，处于的节点位置，状态信息。
     *
     * @param channel 频道信息
     * @return
     */
    @ApiOperation( value = "添加频道", notes = "添加频道", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "channel", value = "频道信息", paramType = "path", required = true ),
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord addChannel( Channel channel ) {

        return new RestRecord( 0 );
    }

    /**
     * 修改频道
     * 包括频道名字，处于的节点位置，状态信息。
     *
     * @param channel 频道信息
     * @return
     */
    @ApiOperation( value = "修改频道", notes = "修改频道", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "channel", value = "频道信息", paramType = "path", required = true ),
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PutMapping
    public RestRecord updateChannel( Channel channel ) {

        return new RestRecord( 0 );
    }

    /**
     * 删除频道
     * 删除频道，本系统中所有删除均为标记删除，不做真实删除；频道删除后，需要把该频道下的所有子频道和内容，移动到跟频道下。
     *
     * @param channelId 频道信息
     * @return
     */
    @ApiOperation( value = "删除频道", notes = "删除频道", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "channelId", value = "频道id", paramType = "query", required = true ),
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @DeleteMapping( "/{channelId}" )
    public RestRecord deleteChannel( @PathVariable String channelId ) {

        return new RestRecord( 0 );
    }
}
