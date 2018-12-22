package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppTypeService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 应用类型查询管理相关
 * @author BTW
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Slf4j
@Api( value = "应用类型查询更改相关接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping("/app-type")
public class AppTypeController {
    private AppTypeService appTypeService;

    @Autowired
    public AppTypeController ( AppTypeService appTypeService ) {
        this.appTypeService = appTypeService;
    }

    /**
     * 应用类型查询接口
     * @return 所用应用类型列表
     */
    @ApiOperation( value = "应用类型查询接口", notes = "查询所有应用类型名称", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = MarketMessageConstants.SCE_MARKET_MSG_100, response = RestRecord.class )
    } )
    @GetMapping
    @ResponseBody
    public RestRecord getAllAppTypeList () {
        return appTypeService.getAllAppTypeList();
    }

    /**
     * 应用类型新增接口
     * @param appTypeName 新增应用类型名称
     * @return 添加类型是否成功
     */
    @ApiOperation( value = "应用类型添加接口", notes = "添加新的应用类型", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appTypeName", dataType = "String", value = "新增应用类型名称", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = MarketMessageConstants.SCE_MARKET_MSG_100, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord addAppType ( @RequestParam( "appTypeName" ) String appTypeName ) {
        return appTypeService.addAppTypeInfo( appTypeName );
    }

    /**
     * 应用类型更改接口
     * @param appTypeId 需更改的应用类型id
     * @param appTypeName 更改后的应用类型名称
     * @return 更改应用类型名称是否成功
     */
    @ApiOperation( value = "应用类型更改接口", notes = "更改应用类型名称", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appTypeId", dataType = "String", value = "更改应用类型ID", paramType = "query", required = true ),
            @ApiImplicitParam( name = "appTypeName", dataType = "String", value = "更改应用类型名称", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = MarketMessageConstants.SCE_MARKET_MSG_100, response = RestRecord.class )
    } )
    @PostMapping("/new-type-info")
    @ResponseBody
    public RestRecord updateAppTypeName ( @RequestParam( "appTypeId" ) String appTypeId,
                                          @RequestParam( "appTypeName" ) String appTypeName ) {
        return appTypeService.updateAppTypeInfo( appTypeId, appTypeName );
    }

    /**
     * 应用类型删除接口
     * @param appTypeId 需删除的应用类型id
     * @return 删除应用类型是否成功
     */
    @ApiOperation( value = "应用类型删除接口", notes = "根据Id删除对应应用类型", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appTypeId", dataType = "String", value = "删除的应用类型ID", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = MarketMessageConstants.SCE_MARKET_MSG_100, response = RestRecord.class )
    } )
    @DeleteMapping("/{appTypeId}")
    @ResponseBody
    public RestRecord deleteAppTypeById ( @PathVariable( "appTypeId" ) String appTypeId ) {
        return appTypeService.deleteAppTypeInfo( appTypeId );
    }
}
