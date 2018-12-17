package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppListService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping("/{classId}/{keyword}")
    @ResponseBody
    public RestRecord getAppListInfo( @PathVariable( "appClassId" )Integer appClassId,
                                          @PathVariable( "keyword" )String keyword) {
        return appListService.getAppListInfo(appClassId,keyword);
    }
}
