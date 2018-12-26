package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.repository.AppCountRepository;
import cn.com.bonc.sce.rest.RestRecord;
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
 * 应用下载统计接口
 */
@Slf4j
@RestController
@RequestMapping( "/count" )
public class DownloadCountApiController {
    private AppCountRepository appCountRepository;

    @Autowired
    public DownloadCountApiController( AppCountRepository appCountRepository ) {
        this.appCountRepository = appCountRepository;
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
    public RestRecord countSingleAppDownload(
            @RequestParam( "appId" ) String appId,
            @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
            @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize ) {
        log.trace( "Query AppDownload By AppId :{}", appId );
        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        try {
            RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
            Page< List< Map< String, Object > > > temp = appCountRepository.getDownloadCountByAppId( appId, pageable );
            Map< String, Object > result = new HashMap<>( 16 );
            result.put( "data", temp.getContent() );
            result.put( "totalPage", temp.getTotalPages() );
            result.put( "totalCount", temp.getTotalElements() );
            restRecord.setData( result );
            return restRecord;
        } catch ( Exception e ) {
            log.error( "Query AppDownload fail {}", e );
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420 );
        }
    }

    /**
     * 指定种类应用下载量统计接口
     *
     * @param appType 应用类型
     * @return
     */
    @GetMapping( "/type" )
    @ResponseBody
    public RestRecord countAppDownloadByType(
            @RequestParam( "appType" ) String appType,
            @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
            @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize ) {
        // 根据应用类型查找对应的应用，统计下载量
        log.trace( "Query AppDownload By AppType :{}", appType );
        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        try {
            RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
            Page< List< Map< String, Object > > > temp = appCountRepository.getDownloadCountByType( appType, pageable );
            Map< String, Object > result = new HashMap<>( 16 );
            result.put( "data", temp.getContent() );
            result.put( "totalPage", temp.getTotalPages() );
            result.put( "totalCount", temp.getTotalElements() );
            restRecord.setData( result );
            return restRecord;
        } catch ( Exception e ) {
            log.error( "Query AppDownload fail {}", e );
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420 );
        }
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
    public RestRecord countAppDownloadByCompanyId(
            @RequestParam( "companyId" ) Long companyId,
            @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
            @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize ) {
        // 根据厂商名查找对应的应用，统计下载量
        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        log.trace( "Query AppDownload By CompanyId :{}", companyId );
        try {
            RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
            Page< List< Map< String, Object > > > temp = appCountRepository.getDownloadCountByCompanyId( companyId, pageable );
            Map< String, Object > result = new HashMap<>( 16 );
            result.put( "data", temp.getContent() );
            result.put( "totalPage", temp.getTotalPages() );
            result.put( "totalCount", temp.getTotalElements() );
            restRecord.setData( result );
            return restRecord;
        } catch ( Exception e ) {
            log.error( "Query AppDownload fail {}", e );
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420 );
        }
    }

    /**
     * 查询全部应用下载排名
     *
     * @param pageSize 分页条数
     * @param pageNum  分页页码
     * @return
     */
    @GetMapping( "/download-ranking" )
    @ResponseBody
    public RestRecord getAppDownloadRanking( @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize,
                                             @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum ) {
        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        Page< List< Map< String, Object > > > downloadRankingList = appCountRepository.getAppDownloadRankingList( pageable );
        return new RestRecord( 200, downloadRankingList );
    }

    /**
     * 查询软件下载量变化
     * http://localhost:10210/count/download-change?userId=123
     *
     * @return
     */
    @GetMapping( "/download-change" )
    @ResponseBody
    public RestRecord getDownloadChange(
            @RequestParam( "userId" ) String userId,
            @RequestParam( value = "startTime", required = false, defaultValue = "1970-01-01 00:00:00" ) String startTime,
            @RequestParam( value = "endTime", required = false, defaultValue = "2099-01-01 00:00:00" ) String endTime ) {
        log.trace( "Query downloadChange userId is :{}", userId );
        try {
            Map< String, Object > companyInfo = appCountRepository.getCompanyInfo( userId );
            if ( companyInfo.get( "COMPANY_ID" ) == null ) {
                return new RestRecord( 110, WebMessageConstants.SCE_PORTAL_MSG_110 );
            } else {
                List< Map< String, Object > > downloadInfo = appCountRepository.getDownloadChange( Long.parseLong( ( String ) companyInfo.get( "COMPANY_ID" ) ), startTime, endTime );
                RestRecord restRecord = new RestRecord( 200 );
                restRecord.setData( downloadInfo );
                return restRecord;
            }
        } catch ( Exception e ) {
            log.error( "Query DownloadChange fail {}", e );
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420 );
        }

    }


}
