package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.model.AppClass;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppClassService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 应用分类
 *
 * @author wzm
 * @version 0.1
 * @since 2018/17/12 12:00
 */
@Slf4j
@RestController
@Api( value = "应用分类" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RequestMapping( "/appClass" )
public class AppClassController {

    @Autowired
    private AppClassService appClassService;

    /**
     * 添加应用分类
     *
     * @param appClass 信息
     * @return 是否添加成功
     */
    @ApiOperation( value = "添加应用分类", notes = "添加应用分类", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appClass", value = "应用分类信息", paramType = "body", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertAppClass( AppClass appClass ) {
        return appClassService.insertAppClass( appClass );
    }

    /**
     * 通过id删除应用分类
     *
     * @param appClassId id
     * @return 删除是否成功
     */
    @ApiOperation( value = "通过id删除应用分类", notes = "通过id删除应用分类", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appClassId", value = "appClassId", paramType = "header", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @DeleteMapping( "/{appClassId}" )
    @ResponseBody
    public RestRecord deleteAppClassById( @PathVariable( "appClassId" )Integer appClassId ) {
        return appClassService.deleteAppClassById( appClassId );
    }

    /**
     * 更新应用分类
     *
     * @param appClass 应用分类信息
     * @return appClass
     */
    @ApiOperation( value = "更新应用分类", notes = "更新应用分类", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appClass", value = "应用分类信息", paramType = "body", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PutMapping
    @ResponseBody
    public RestRecord updateAppClassInfo( AppClass appClass ) {
        return appClassService.updateAppClassInfo( appClass );
    }

    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    @ApiOperation( value = "获取所有应用分类数据", notes = "获取所有应用分类数据", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping
    @ResponseBody
    public RestRecord getAllAppClassInfo() {
        return appClassService.getAllAppClassInfo();
    }
}
