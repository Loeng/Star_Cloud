package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.entity.DownloadCount;
import cn.com.bonc.sce.repository.UserDownloadRepository;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppDownloadService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private UserDownloadRepository userDownloadRepository;
    private AppDownloadService appDownloadService;

    @Autowired
    public AppDownloadController ( UserDownloadRepository userDownloadRepository, AppDownloadService appDownloadService ) {
        this.userDownloadRepository = userDownloadRepository;
        this.appDownloadService = appDownloadService;
    }


    /**
     * 用户应用下载历史查询接口
     * @param userId 查询的用户Id
     * @param pageSize 分页条数
     * @param pageNumber 页数
     * @return 用户下载应用历史记录
     */
    @PostMapping
    @ResponseBody
    public RestRecord getUserAppDownloadList ( @RequestParam( "userId" ) String userId,
                                               @RequestParam( "pageSize" ) Integer pageSize,
                                               @RequestParam( "pageNumber" ) Integer pageNumber) {
        Pageable pageable = PageRequest.of( pageNumber-1, pageSize );
        Page< List< Map< String,Object> > > listPage = userDownloadRepository.getUserDownloadList( userId, pageable );
        return new RestRecord( 200, listPage );
    }

    /**
     * 用户应用下载接口
     * @param userId 用户Id
     * @param appId 应用Id
     * @param version
     * @param platform
     * @return
     */
    @PostMapping("/info")
    @ResponseBody
    public RestRecord getUserAppDownloadList ( @RequestParam( "userId" ) String userId,
                                               @RequestParam( "appId" ) String appId,
                                               @RequestParam( "version" ) String version,
                                               @RequestParam( "platform" ) String platform
                                               ) {
        return appDownloadService.getAppDownloadPath( userId, appId, version, platform );
    }

    /**
     *@Author : lyy
     *@Desc :用户分类的人数
     *@Date : 16:30 2018/12/27
     */
    @PostMapping("/userGroupCount")
    @ResponseBody
    public RestRecord getUserGroupCOunt(){
        List<Map<String,Object>> map = null;
        map = userDownloadRepository.getUserGroupCount();
        log.info( "app的数量",map.toString());
        return new RestRecord( 200, map );
    }
    /**
     *@Author : lyy
     *@Desc :所有app的下载量
     *@Date : 2018/12/27
     */
    @PostMapping("/appCount")
    @ResponseBody
    public RestRecord getAppCount(){
        Map<String,Object>  appCount = userDownloadRepository.getAppCount();
        return  new RestRecord( 200, appCount );
    }
}
