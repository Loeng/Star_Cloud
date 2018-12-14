package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用推荐-热门应用接口
 * atuhor jc_D
 */
@Slf4j
@Api( value = "应用推荐-热门应用接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping( "/hotApp" )
public class HotAppController {


    /**
     * 添加热门应用
     *
     * @param appIdList app的id用","分割
     * @param userId
     * @return
     */
    @ApiOperation( value = "添加热门应用", notes = "添加热门应用", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appIdList", value = "appid用逗号分隔", paramType = "body", required = true, example = "1,2,3" ),
            @ApiImplicitParam( name = "userId", value = "用户id", paramType = "body", required = true )

    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PostMapping( "/{userId}" )
    public RestRecord addHotRecommendAppList( @RequestParam List< String > appIdList,
                                              @PathVariable( "userId" ) String userId ) {
        return new RestRecord( 0, null );
    }


    /**
     * 查询所有热门应用
     *
     * @param page 页码
     * @param size 每页数量
     * @return
     */
    @ApiOperation( value = "查询所有热门应用", notes = "查询所有热门应用", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "page", value = "页面", paramType = "body", required = false ),
            @ApiImplicitParam( name = "rows", value = "每页显示数量", paramType = "body", required = false )

    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping
    public RestRecord selectHotRecommendAppList( @RequestParam( value = "page", defaultValue = "1", required = false ) int page,
                                                 @RequestParam( value = "rows", defaultValue = "10", required = false ) int size ) {
        // 查询应用表中热门推荐状态为true的应用
        return new RestRecord( 0, "查询应用表中热门推荐状态为true的应用" );
    }


    /**
     * 删除热门应用
     *
     * @param appIdList app的id用","分割
     * @param userId
     * @return
     */
    @ApiOperation( value = "删除热门应用", notes = "删除热门应用", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "userId", value = "用户id", paramType = "header", required = true ),
            @ApiImplicitParam( name = "appIdList", value = "appid用逗号分隔", paramType = "header", required = true, example = "1,2,3" )

    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @DeleteMapping( value = "/{userId}/{appIdList}" )
    public RestRecord deleteHotRecommendApp( @PathVariable( "appIdList" ) List< String > appIdList,
                                             @PathVariable( "userId" ) String userId ) {
        //将指定应用的热门推荐状态改为false
        return new RestRecord( 0, null );
    }

}
