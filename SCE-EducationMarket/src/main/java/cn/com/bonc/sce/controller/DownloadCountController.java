package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.CountService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 应用下载统计接口
 */
@Slf4j
@Api( value = "应用下载统计接口" ,tags = "应用下载统计接口")
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping( "/count" )
public class DownloadCountController {
    private CountService countService;

    @Autowired
    public DownloadCountController( CountService countService ) {
        this.countService = countService;
    }

    /**
     * 单个应用下载统计接口
     * 统计指定应用的下载量
     *
     * @param appId 应用ID
     * @return
     */
    @ApiOperation( value = "单个应用下载统计接口", notes = "统计指定应用的下载量", httpMethod = "GET" )
    @ApiImplicitParam( name = "appId", value = "应用Id", paramType = "query", required = true )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping("/one")
    @ResponseBody
    public RestRecord countSingleAppDownload( @RequestParam( "appId" ) String appId ) {
        return new RestRecord( 0,countService.countSingleAppDownload( appId ));
    }

    /**
     * 指定种类应用下载量统计接口
     *
     * @param appType 应用类型
     * @return
     */
    @ApiOperation( value = "应用类型下载统计接口", notes = "指定种类应用下载量统计接口", httpMethod = "GET" )
    @ApiImplicitParam( name = "appType", value = "应用类型", paramType = "query", required = true )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping("/type")
    @ResponseBody
    public RestRecord countAppDownloadByType( @RequestParam( "appType" ) String appType ) {
        // 根据应用类型查找对应的应用，统计下载量
        return new RestRecord( 0,countService.countAppDownloadByType( appType ));
    }

    /**
     * 查询指定厂商的应用下载量
     * 根据厂商Id查找对应的应用，统计下载量
     *
     * @param companyId 厂商Id
     * @return
     */
    @ApiOperation( value = "查询指定厂商的应用下载量", notes = "根据厂商Id查找对应的应用，统计下载量", httpMethod = "GET" )
    @ApiImplicitParam( name = "companyId", value = "应用类型", paramType = "query", required = true )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping("/company")
    @ResponseBody
    public RestRecord countAppDownloadByCompany( @RequestParam( "companyId" ) String companyId ) {
        // 根据厂商名查找对应的应用，统计下载量
        return new RestRecord( 0,countService.countAppDownloadByCompany( companyId ));
    }

}
