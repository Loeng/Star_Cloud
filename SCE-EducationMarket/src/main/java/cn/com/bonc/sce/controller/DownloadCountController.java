package cn.com.bonc.sce.controller;

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
@Api( value = "应用下载统计接口", tags = "应用下载统计接口" )
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
    @GetMapping( "/one" )
    @ResponseBody
    public RestRecord countSingleAppDownload(
            @RequestParam( "appId" ) @ApiParam( "应用Id" ) String appId,
            @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
            @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize ) {

        return countService.countSingleAppDownload( appId, pageNum, pageSize );

    }

    /**
     * 指定种类应用下载量统计接口
     *
     * @param appType 应用类型
     * @return
     */
    @ApiOperation( value = "应用类型下载统计接口", notes = "指定种类应用下载量统计接口", httpMethod = "GET" )
    @GetMapping( "/type" )
    @ResponseBody
    public RestRecord countAppDownloadByType(
            @RequestParam( "appType" ) @ApiParam( "应用类型" ) String appType,
            @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
            @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize ) {
        // 根据应用类型查找对应的应用，统计下载量
        return countService.countAppDownloadByType( appType, pageNum, pageSize );
    }

    /**
     * 查询指定厂商的应用下载量
     * 根据厂商Id查找对应的应用，统计下载量
     *
     * @param companyId 厂商Id
     * @return
     */
    @ApiOperation( value = "查询指定厂商的应用下载量", notes = "根据厂商Id查找对应的应用，统计下载量", httpMethod = "GET" )
    @GetMapping( "/company" )
    @ResponseBody
    public RestRecord countAppDownloadByCompanyId(
            @RequestParam( "companyId" ) @ApiParam( "厂商Id" ) Long companyId,
            @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
            @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize ) {
        // 根据厂商名查找对应的应用，统计下载量
        return countService.countAppDownloadByCompanyId( companyId, pageNum, pageSize );
    }

    /**
     * 查询应用下载量排行列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation( value = "查询应用下载量排行列表", notes = "查询全部应用应用下载量排行", httpMethod = "GET" )
    @GetMapping( "/download-ranking/{pageSize}/{pageNum}" )
    @ResponseBody
    public RestRecord getAppDownloadRanking( @PathVariable( "pageSize" ) @ApiParam( "分页条数" ) Integer pageSize,
                                             @PathVariable( "pageNum" ) @ApiParam( "分页页数" ) Integer pageNum ) {
        // 根据厂商名查找对应的应用，统计下载量
        return countService.getAppDownloadRanking( pageSize, pageNum );
    }

}
