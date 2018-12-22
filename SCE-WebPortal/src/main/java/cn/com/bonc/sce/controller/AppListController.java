package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppListService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 应用列表
 *
 * @author wzm
 * @version 0.1
 * @since 2018/17/12 12:00
 */
@Slf4j
@RestController
@Api( value = "应用列表" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RequestMapping( "/appList" )
public class AppListController {

    @Autowired
    private AppListService appListService;

    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    @ApiOperation( value = "获取所有应用分类数据", notes = "获取所有应用分类数据", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appClassId", value = "appClassId", paramType = "header", required = true ),
            @ApiImplicitParam( name = "keyword", value = "关键字", paramType = "header", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping( "/{appClassId}/{keyword}" )
    @ResponseBody
    public RestRecord getAppListInfo( @PathVariable( "appClassId" ) Integer appClassId,
                                      @PathVariable( "keyword" ) String keyword ) {
        String undefined = "undefined";
        if ( StringUtils.isEmpty( appClassId ) || undefined.equals( appClassId ) ) {
            appClassId = null;
        }
        if ( StringUtils.isEmpty( keyword ) || undefined.equals( keyword ) ) {
            keyword = null;
        }
        return appListService.getAppListInfo( appClassId, keyword );
    }
}
