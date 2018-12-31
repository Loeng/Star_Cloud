package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.AppDownloadDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppDownloadService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 应用下载相关操作接口
 *
 * @author BTW
 * @version 0.1
 * @since 2018/12/18 17:13
 */
@Slf4j
@Api( value = "应用下载相关操作接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping("/app-download")
public class AppDownloadController {
    AppDownloadService appDownloadService;

    @Autowired
    public AppDownloadController( AppDownloadService appDownloadService ) {
        this.appDownloadService= appDownloadService;
    }


    /**
     * 用户应用下载历史查询接口
     * @param userId 查询的用户Id
     * @return 用户下载应用历史记录
     */
    @ApiOperation( value = "我的应用(用户应用下载历史查询接口)", notes = "根据用户id查询应用下载历史（列表）", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "userId", dataType = "String", value = "用户Id", paramType = "path", required = true ),
            @ApiImplicitParam( name = "pageSize", dataType = "Integer", value = "分页条数", paramType = "path", required = true ),
            @ApiImplicitParam( name = "pageNumber", dataType = "Integer", value = "页数", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping("/{userId}/{pageSize}/{pageNumber}")
    @ResponseBody
    public RestRecord getUserAppDownloadList ( @PathVariable( "userId" ) String userId,
                                               @PathVariable( "pageSize" ) Integer pageSize,
                                               @PathVariable( "pageNumber" ) Integer pageNumber ) {

        return appDownloadService.getUserAppDownloadList( userId, pageSize, pageNumber );

    }

    /**
     * 用户下载应用接口
     * @param userId 用户Id
     * @param appId  下载的应用Id
     * @param version 版本号
     * @param platform 应用平台
     * @return 开通应用是否成功
     */
    @ApiOperation( value = "用户下载应用接口", notes = "用户下载选中的应用", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "userId", dataType = "String", value = "用户Id", paramType = "query", required = true ),
            @ApiImplicitParam( name = "appId", dataType = "String", value = "应用Id", paramType = "query", required = true ),
            @ApiImplicitParam( name = "version", dataType = "String", value = "应用Id", paramType = "query", required = true ),
            @ApiImplicitParam( name = "platform", dataType = "String", value = "应用Id", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord addUserAppOpen ( @RequestParam( "userId" ) String userId,
                                       @RequestParam( "appId" ) String appId,
                                       @RequestParam( "version" ) String version,
                                       @RequestParam( "platform" ) String platform ) {
        return appDownloadService.addUserDownloadInfo( userId, appId, version, platform );
    }

}
