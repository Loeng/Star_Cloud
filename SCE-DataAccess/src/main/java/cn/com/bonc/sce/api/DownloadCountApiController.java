package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.repository.AppCountRepository;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 应用下载统计接口
 *
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
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
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420, e );
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
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420, e );
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
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420, e );
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
                                             @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
                                             @RequestParam( value = "userId" ) String userId ) {
        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        Page< List< Map< String, Object > > > downloadRankingList = appCountRepository.getAppDownloadRankingList( userId, pageable );
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
            @RequestParam( value = "appId", required = false ) String appId,
            @RequestParam( value = "startTime", required = false, defaultValue = "1970-01-01 00:00:00" ) String startTime,
            @RequestParam( value = "endTime", required = false, defaultValue = "2099-01-01 00:00:00" ) String endTime ) {
        log.trace( "Query downloadChange userId is :{}", userId );
        try {
            Map< String, Object > companyInfo = appCountRepository.getCompanyInfo( userId );
            if ( companyInfo.get( "COMPANY_ID" ) == null ) {
                return new RestRecord( 110, WebMessageConstants.SCE_PORTAL_MSG_110 );
            } else {
                if ( appId == null ) {
                    return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, appCountRepository.getDownloadChange( Long.parseLong( ( String ) companyInfo.get( "COMPANY_ID" ) ), startTime, endTime ) );
                } else {
                    return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, appCountRepository.getDownloadChangeAndApp( Long.parseLong( ( String ) companyInfo.get( "COMPANY_ID" ) ), appId, startTime, endTime ) );
                }
            }
        } catch ( Exception e ) {
            log.error( "Query DownloadChange fail {}", e );
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420, e );
        }

    }

    /**
     * 查询当月厂商所有应用类型下载数量
     * http://localhost:10210/count/download-type?userId=123
     *
     * @param userId 用户Id
     * @param startTime   查询的月份
     * @return
     */
    @GetMapping( "/download-type" )
    @ResponseBody
    public RestRecord getDownloadTypeByMonth(
            @RequestParam( "userId" ) String userId,
            @RequestParam( value = "startTime", required = false, defaultValue = "2018-12" ) String startTime ) {
        log.trace( "Query DownloadTypeByMonth userId is :{}", userId );
        try {
            Map< String, Object > companyInfo = appCountRepository.getCompanyInfo( userId );
            if ( companyInfo.get( "COMPANY_ID" ) == null ) {
                return new RestRecord( 110, WebMessageConstants.SCE_PORTAL_MSG_110 );
            } else {
                //获取当前月份拼接年月(前台入参传送)
                RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
                restRecord.setData( appCountRepository.getDownloadByType( Long.parseLong( ( String ) companyInfo.get( "COMPANY_ID" ) ), startTime ) );
                return restRecord;
            }
        } catch ( Exception e ) {
            log.error( "Query DownloadChange fail {}", e );
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420, e );
        }
    }

    /**
     * Desc 根据用户ID查询用户对应的厂家下面APP总数及各类型占比
     *
     * @param userId
     * @return
     * @Auther mkl
     */

    @GetMapping( "/app-type-percent" )
    @ResponseBody
    public RestRecord getAppTypePrecent( @RequestParam String userId ) {
        Map< String, Object > companyInfo = appCountRepository.getCompanyInfo( userId );
        if ( null == companyInfo || CollectionUtils.isEmpty( companyInfo ) ) {
            log.info( "没有与[{}]相关联的厂商", userId );
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_110 );
        }
        int companyId = Integer.parseInt(companyInfo.get( "COMPANY_ID" ).toString());
        log.info("用户[{}]关联的厂商为[{}]",userId,companyId);
        List< Map< String, Object > > appTypePrecent = appCountRepository.getAppTypePrecent( companyId );
        appTypePrecent.add( getAppCountByCompanyId( companyId ) );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, appTypePrecent );
    }

    /**
     * DESC: 统计厂商下面的APP总数
     *
     * @param companyId
     * @return
     */
    public Map< String, Object > getAppCountByCompanyId( int companyId ) {
        Map< String, Object > appCountByCompanyId = appCountRepository.getAppCountByCompanyId( companyId );
        return appCountByCompanyId;

    }

    @GetMapping( "/collection-change" )
    @ResponseBody
    public RestRecord getCollectionChange(
            @RequestParam( "userId" ) String userId,
            @RequestParam( value = "appId", required = false ) String appId,
            @RequestParam( value = "startTime", required = false, defaultValue = "1970-01-01 00:00:00" ) String startTime,
            @RequestParam( value = "endTime", required = false, defaultValue = "2099-01-01 00:00:00" ) String endTime ) {
        log.trace( "Query CollectionChange userId is :{}", userId );
        try {
            Map< String, Object > companyInfo = appCountRepository.getCompanyInfo( userId );
            if ( companyInfo.get( "COMPANY_ID" ) == null ) {
                return new RestRecord( 110, WebMessageConstants.SCE_PORTAL_MSG_110 );
            } else {
                if ( appId == null ) {
                    return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, appCountRepository.getCollectionChange( Long.parseLong( ( String ) companyInfo.get( "COMPANY_ID" ) ), startTime, endTime ) );
                } else {
                    return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, appCountRepository.getCollectionChangeAndApp( Long.parseLong( ( String ) companyInfo.get( "COMPANY_ID" ) ), appId, startTime, endTime ) );
                }
            }
        } catch ( Exception e ) {
            log.error( "Query CollectionChange fail {}", e );
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420 );
        }

    }

    @GetMapping( "/company-app" )
    @ResponseBody
    public RestRecord getCompanyAppList( @RequestParam( "userId" ) String userId ) {
        log.trace( "Query CompanyAppList userId is :{}", userId );
        try {
            Map< String, Object > companyInfo = appCountRepository.getCompanyInfo( userId );
            if ( companyInfo.get( "COMPANY_ID" ) == null ) {
                return new RestRecord( 110, WebMessageConstants.SCE_PORTAL_MSG_110 );
            }
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, appCountRepository.getCompanyAppList( Long.parseLong( ( String ) companyInfo.get( "COMPANY_ID" ) ) ) );
        } catch ( Exception e ) {
            log.error( "Query CompanyAppList fail {}", e );
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420 );
        }

    }


    @RequestMapping( "/list" )
    @ResponseBody
    public RestRecord getDownloadList( @RequestParam( "userId" ) String userId,
                                       @RequestParam( "appType" ) int appType,
                                       @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
                                       @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize ) {
        log.trace( "Query DownloadList userId is :{}", userId );
        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        try {
            Map< String, Object > companyInfo = appCountRepository.getCompanyInfo( userId );
            if ( companyInfo.get( "COMPANY_ID" ) == null ) {
                return new RestRecord( 110, WebMessageConstants.SCE_PORTAL_MSG_110 );
            }
            /*判断是否查询全部类型*/
            Page< List< Map< String, Object > > > page;
            if ( appType == 0 ) {
                page = appCountRepository.getDownloadAllList( Long.parseLong( ( String ) companyInfo.get( "COMPANY_ID" ) ), pageable );
            } else {
                page = appCountRepository.getDownloadList( Long.parseLong( ( String ) companyInfo.get( "COMPANY_ID" ) ), appType, pageable );
            }
            Map< String, Object > result = new HashMap<>( 16 );
            result.put( "data", page.getContent() );
            result.put( "totalPage", page.getTotalPages() );
            result.put( "totalCount", page.getTotalElements() );
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, result );
        } catch ( Exception e ) {
            log.error( "Query DownloadList fail {}", e );
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420 );
        }
    }
}
