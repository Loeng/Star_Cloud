package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用管理
 * author jc_D
 */
@Slf4j
@Api( value = "应用管理接口", tags = "应用管理接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping( "/marketApp" )
public class MarketAppController {

    /**
     * 新增应用
     *
     * @param appInfo 应用信息， value为json字符串
     * @return
     */
    @ApiOperation( value = "新增应用", notes = "新增应用", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appInfo", value = "app相关信息，json格式", paramType = "path", required = true )

    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PostMapping
    public RestRecord addAppInfo( @RequestParam String appInfo ) {

        return new RestRecord( 0, null );
    }

    /**
     * 删除应用
     *
     * @param appIdList appId数组
     * @return
     */
    @ApiOperation( value = "删除应用", notes = "删除应用", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appIdList", value = "appId", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @DeleteMapping( "/{appIdList}" )
    public RestRecord deleteApps( @PathVariable( "appIdList" ) List< String > appIdList ) {
        return new RestRecord( 0, null );
    }

    /**
     * 编辑应用
     *
     * @param updateInfo 应用需更新的字段与更新的值，json字符串
     * @param appId      所需更新的应用ID
     * @return
     */
    @ApiOperation( value = "编辑应用", notes = "编辑应用", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "appid", paramType = "query", required = true ),
            @ApiImplicitParam( name = "updateInfo", value = "app修改的信息", paramType = "path", required = true )

    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PutMapping( "/{appId}" )
    public RestRecord updateAppInfo( @RequestParam String updateInfo,
                                     @PathVariable String appId ) {
        return new RestRecord( 0, null );
    }

    /**
     * 查询全部应用
     *
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */
    @ApiOperation( value = "查询全部应用", notes = "查询全部应用", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "plantformType", value = "应用类型(平台应用pt|软件应用rj)", paramType = "query", required = true )

    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/getAllAppList/{plantformType}" )
    public RestRecord getAllAppList( @PathVariable String plantformType ) {
        return new RestRecord( 0, null );
    }

    /**
     * 查询应用分类信息
     *
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */
    @ApiOperation( value = "查询应用分类信息", notes = "查询应用分类信息", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "plantformType", value = "应用类型(平台应用pt|软件应用rj)", paramType = "query", required = true )

    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/getAllAppTypeList/{plantformType}" )
    public RestRecord getAllAppTypeList( @PathVariable String plantformType ) {
        return new RestRecord( 0, null );
    }


    /**
     * 查询指定类型应用信息
     *
     * @param appType       查询的应用类型
     * @param orderType     查询排序字段，可为多个字段
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */
    @ApiOperation( value = "查询指定类型应用信息", notes = "查询指定类型应用信息", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appType", value = "查询的应用类型（all,教学类,教辅类,管理类，其它...）", paramType = "path", required = false ),
            @ApiImplicitParam( name = "orderType", value = "查询排序字段，可为多个字段(上架时间，下载量...)", paramType = "path", required = false ),
            @ApiImplicitParam( name = "plantformType", value = "应用类型(平台应用pt|软件应用rj)", paramType = "path", required = true )

    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/selectAppListByType" )
    public RestRecord selectAppListByType( @RequestParam( value = "appType", defaultValue = "all", required = false ) String appType,
                                           @RequestParam( value = "orderType", required = false ) String orderType,
                                           @RequestParam String plantformType ) {
        return new RestRecord( 0, null );
    }

    /**
     * 根据输入名模糊查询应用
     *
     * @param appName       查询的名字
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */
    @ApiOperation( value = "根据输入名模糊查询应用", notes = "根据输入名模糊查询应用", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appName", value = "appName", paramType = "query", required = true ),
            @ApiImplicitParam( name = "plantformType", value = "应用类型(平台应用|软件应用)", paramType = "query", required = true )

    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/selectAppListByName" )
    public RestRecord selectAppListByName( @RequestParam String appName,
                                           @RequestParam String plantformType ) {
        return new RestRecord( 0, null );
    }

    /**
     * 根据输入名和选择类别查询应用
     *
     * @param appName
     * @param appType
     * @param orderType
     * @param plantformType
     * @return
     */
    @ApiOperation( value = "根据输入名和选择类别查询应用", notes = "根据输入名和选择类别查询应用", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appName", value = "appName", paramType = "query", required = true ),
            @ApiImplicitParam( name = "appType", value = "appType", paramType = "query", required = true ),
            @ApiImplicitParam( name = "orderType", value = "orderType", paramType = "query", required = false ),
            @ApiImplicitParam( name = "plantformType", value = "应用类型(平台应用|软件应用)", paramType = "query", required = true )

    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/selectAppListByNameAndType" )
    public RestRecord selectAppListByNameAndType( @RequestParam String appName,
                                                  @RequestParam String appType,
                                                  @RequestParam( required = false ) String orderType,
                                                  @RequestParam String plantformType ) {
        return new RestRecord( 0, null );
    }

    /**
     * 查询单个应用详情
     *
     * @param appId
     * @return
     */
    @ApiOperation( value = "查询单个应用详情", notes = "查询单个应用详情", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "appId", paramType = "query", required = true )

    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/selectAppById/{appId}" )
    public RestRecord selectAppById( @PathVariable String appId ) {
        return new RestRecord( 0 );
    }

    /**
     * 用户下载应用接口
     *
     * @param appId
     * @param userId
     * @return
     */
    @ApiOperation( value = "用户下载应用接口", notes = "用户下载应用接口", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "appId", paramType = "path", required = true ),
            @ApiImplicitParam( name = "userId", value = "userId", paramType = "path", required = true )

    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/downloadUserApp" )
    public RestRecord downloadUserApp( @RequestParam String appId,
                                       @RequestParam String userId ) {
        return new RestRecord( 0 );
    }

    /**
     * 用户开通应用接口
     *
     * @param appId
     * @param userId
     * @return
     */
    @ApiOperation( value = "用户开通应用接口", notes = "用户开通应用接口", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "appid", paramType = "path", required = true ),
            @ApiImplicitParam( name = "userId", value = "用户id", paramType = "path", required = true )

    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PostMapping( "/openUserApp" )
    public RestRecord openUserApp( @RequestParam String appId,
                                   @RequestParam String userId ) {
        return new RestRecord( 0 );
    }


}
