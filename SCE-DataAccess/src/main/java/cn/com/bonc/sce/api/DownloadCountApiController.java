package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.CountDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 应用下载统计接口
 */
@Slf4j
@RestController
@RequestMapping( "/count" )
public class DownloadCountApiController {
    private CountDao countDao;

    @Autowired
    public DownloadCountApiController( CountDao countDao ) {
        this.countDao = countDao;
    }

    /**
     * 单个应用下载统计接口
     * 统计指定应用的下载量
     *
     * @param appId 应用ID
     * @return
     */
    @GetMapping( "/one" )
    @ResponseBody
    public RestRecord countSingleAppDownload( @RequestParam( "appId" ) String appId ) {
        RestRecord restRecord = new RestRecord();
        Map< String, Object > resultMap = new HashMap<>();
        restRecord.setMsg( "This is ApiController response message ! Method name countSingleAppDownload" );
        resultMap.put( "Param1", appId );
        restRecord.setData( resultMap );
        return restRecord;
    }

    /**
     * 指定种类应用下载量统计接口
     *
     * @param appType 应用类型
     * @return
     */
    @GetMapping( "/type" )
    @ResponseBody
    public RestRecord countAppDownloadByType( @RequestParam( "appType" ) String appType ) {
        // 根据应用类型查找对应的应用，统计下载量
        RestRecord restRecord = new RestRecord();
        Map< String, Object > resultMap = new HashMap<>();
        restRecord.setMsg( "This is ApiController response message ! Method name countAppDownloadByType" );
        resultMap.put( "Param1", appType );
        restRecord.setData( resultMap );
        return restRecord;
    }

    /**
     * 查询指定厂商的应用下载量
     * 根据厂商Id查找对应的应用，统计下载量
     *
     * @param companyId 厂商Id
     * @return
     */
    @GetMapping( "/company" )
    @ResponseBody
    public RestRecord countAppDownloadByCompanyId( @RequestParam( "companyId" ) String companyId ) {
        // 根据厂商名查找对应的应用，统计下载量
        RestRecord restRecord = new RestRecord();
        Map< String, Object > resultMap = new HashMap<>();
        restRecord.setMsg( "This is ApiController response message ! Method name countAppDownloadByCompanyId" );
        resultMap.put( "Param1", companyId );
        restRecord.setData( resultMap );
        return restRecord;
    }

}
