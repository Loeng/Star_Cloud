package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.ColumnInfo;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.ColumnInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 栏目接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/26 15:00
 */
@Slf4j
@RestController
@Api( value = "栏目接口", tags = "栏目接口"  )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RequestMapping( "/columns" )
public class ColumnInfoController {
    @Autowired
    private ColumnInfoService columnInfoService;

    /**
     * 添加栏目
     *
     * @param columnInfo 添加栏目
     * @return 是否添加成功
     */
    @ApiOperation( value = "添加栏目", notes = "添加栏目", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insert( @RequestBody @ApiParam( name = "栏目", value = "栏目信息", required = true ) ColumnInfo columnInfo ) {
        return columnInfoService.insert( columnInfo );
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
        return columnInfoService.deleteById( id );
    }

    /**
     * 更新栏目
     *
     * @param columnInfo 栏目
     * @return columnInfo
     */
    @ApiOperation( value = "更新栏目", notes = "更新栏目", httpMethod = "PUT" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PutMapping
    @ResponseBody
    public RestRecord updateInfo( @RequestBody @ApiParam( name = "栏目", value = "栏目信息", required = true ) ColumnInfo columnInfo ) {
        return columnInfoService.updateInfo( columnInfo );
    }

    /**
     * 获取所有栏目数据
     *
     * @return 栏目数据list
     */
    @ApiOperation( value = "获取所有栏目数据", notes = "获取所有栏目数据", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping
    @ResponseBody
    public RestRecord getAll() {
        return columnInfoService.getAll();
    }
}
