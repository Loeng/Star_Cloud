package cn.com.bonc.sce.api;


import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.AppAuditingRepository;
import cn.com.bonc.sce.entity.MarketAppVersion;
import cn.com.bonc.sce.entity.Message;
import cn.com.bonc.sce.model.PlatformAddModel;
import cn.com.bonc.sce.repository.AppVersionRepository;
import cn.com.bonc.sce.repository.FileResourceRepository;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.MessageService;
import cn.com.bonc.sce.utils.ClassCopyUtil;
import cn.com.bonc.sce.utils.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Slf4j
@RestController
@RequestMapping( "/app-version" )
public class AppVersionApiController {
    private AppVersionRepository appVersionRepository;
    private AppAuditingRepository appAuditingRepository;

    @Autowired
    private FileResourceRepository fileResourceRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    public AppVersionApiController( AppVersionRepository appVersionRepository, AppAuditingRepository appAuditingRepository ) {
        this.appVersionRepository = appVersionRepository;
        this.appAuditingRepository = appAuditingRepository;
    }

    /**
     * 应用版本查询接口
     * 1.通过应用Id查询应用所有的版本信息
     * 2.通过应用Id和应用版本查询该版本的详细信息
     *
     * @param appId 查询的应用Id
     * @return
     */
    @GetMapping
    @ResponseBody
    public RestRecord queryAppVersion(
            @RequestParam( "appId" ) String appId,
            @RequestParam( value = "appVersion", required = false, defaultValue = "" ) String appVersion,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) int pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) int pageSize ) {
        // 通过应用ID查询该应用的历史版本信息列表
        log.trace( "Query AppVersion By appId:{} , appVersion:{}", appId, appVersion );
        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        try {
            //结算比例 厂家界面结算比例（厂家设置比例，平台比例=1-COMPANY_SET_RATIO，）和管理员界面（AGENT_RATIO,COMPANY_RATIO, PLATFORM_RATIO,）
            Page< List< Map< String, Object > > > temp = appVersionRepository.queryAppVersionInfo( appId, appVersion, pageable );

            //查询计费模式信息
            List< Map< String, Object > > price = appVersionRepository.queryAppPriceModeInfo( appId, appVersion );

            Map< String, Object > result = new HashMap<>( 16 );
            result.put( "data", temp.getContent() );
            result.put( "priceMode", price );
            result.put( "totalPage", temp.getTotalPages() );
            result.put( "totalCount", temp.getTotalElements() );
            RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
            restRecord.setData( result );
            return restRecord;
        } catch ( Exception e ) {
            log.error( "Query AppVersion Fail {}", e );
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420, e );
        }

    }

    /**
     * 通过应用ID修改该应用的版本信息（已废弃）
     *
     * @param appId          查询的应用Id
     * @param appVersionInfo 版本应用信息详情
     * @return
     */
    @PutMapping( "/{appId}" )
    @ResponseBody
    public RestRecord updateAppHistoryVersionInfo(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "appVersion" ) String appVersion,
            @RequestBody MarketAppVersion appVersionInfo ) {
        //通过应用ID修改该应用的版本信息
        log.trace( "更新应用版本信息  appId:{}  appVersionInfo:{}", appId, appVersionInfo );
        try {
            //根据id取pc图片链接
            String pcUrl = findRealUrl( appVersionInfo.getAppPcPic() );

            //根据id取phone图片链接
            String phoneUrl = findRealUrl( appVersionInfo.getAppPhonePic() );

            //根据addressId获取软件存储路径
            String addressId = findRealUrl( appVersionInfo.getAppDownloadAddress() );

            appVersionInfo.setAppDownloadAddress( addressId );
            appVersionInfo.setAppPcPic( pcUrl );
            appVersionInfo.setAppPhonePic( phoneUrl );
            appVersionInfo.setAppId( appId );
            appVersionInfo.setAppVersion( appVersion );
            appVersionInfo.setIsDelete( 1L );
            appVersionInfo.setUpdateTime( new Date() );
            appVersionRepository.save( appVersionInfo );
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        } catch ( Exception e ) {
            log.error( "Update appVersion fail {}", e );
            return new RestRecord( 421, WebMessageConstants.SCE_PORTAL_MSG_421 );
        }
    }

    /**
     * 删除应用版本
     *
     * @param appId      应用Id
     * @param appVersion 应用版本，如果为空则删除全部
     * @return
     */
    @DeleteMapping
    @ResponseBody
    public RestRecord deleteAppHistoryVersionInfo(
            @RequestParam( "appId" ) String appId,
            @RequestParam( value = "appVersion", required = false, defaultValue = "" ) String appVersion ) {
        //判断是删除指定版本还是删除应用的所有版本
        try {
            RestRecord restRecord = new RestRecord( 200 );
            restRecord.setMsg( WebMessageConstants.SCE_PORTAL_MSG_200 );
            if ( "".equals( appVersion ) ) {
                restRecord.setData( appVersionRepository.deleteByAppId( appId ) );
            } else {
                restRecord.setData( appVersionRepository.deleteByAppIdAndAppVersion( appId, appVersion ) );
            }
            return restRecord;
        } catch ( Exception e ) {
            log.error( "delete AppVersion fail {}", e );
            return new RestRecord( 422, WebMessageConstants.SCE_PORTAL_MSG_422 );
        }
    }

    /**
     * 应用版本更新 申请接口（迭代接口）
     * 1. 在应用版本信息表中插入一条新的版本信息, 并将状态设置为 2迭代待审核
     * 2. 创建一条待办消息，创建人为申请人，接收人为系统管理员
     *
     * @param appId            更新版本的应用Id
     * @param userId           提交版本更新申请的用户Id
     * @param platformAddModel 版本更新详情
     * @return
     */
    @PostMapping( "/apply/{appId}" )
    @ResponseBody
    public RestRecord appVersionUpdateApply(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "userId" ) String userId,
            @RequestBody PlatformAddModel platformAddModel ) {
        log.trace( "apply appVersion appId is {} , userId us {} , detail is {}", appId, userId, platformAddModel );
        try {
            //TODO  平台应用迭代接口 还没修改完成,上传参数还需重新封装（暂时被安排做其它事情了，后面再回来改接口）

            MarketAppVersion appVersionInfo = appAuditingRepository.findByAppIdAndAppVersion( appId, platformAddModel.getCurrentVersion() );
            //用户填写的版本号
            String appversion = appVersionInfo.getAppVersion();
            List< String > versionList = appVersionRepository.getVersionByAppId( appId );
            for ( String s : versionList ) {
                if ( s.equals( appversion ) ) {
                    return new RestRecord( 423, "版本号重复，请重新设置一个版本号" );
                }
            }
            //根据id取pc图片链接
            String pcUrl = findRealUrl( appVersionInfo.getAppPcPic() );
            //根据storeLocationId获取平台包的存储位置
            String storeLocation = findRealUrl( appVersionInfo.getStoreLocation() );
            appVersionInfo.setAppId( userId );
            appVersionInfo.setStoreLocation( storeLocation );
            appVersionInfo.setAppPcPic( pcUrl );
            appVersionInfo.setIsDelete( 1L );
            appVersionInfo.setAppStatus( "2" );
            appVersionInfo.setUpdateTime( new Date() );
            appVersionInfo.setUpdateUserId( userId );
            appVersionInfo.setAppId( appId );

            //将appVersionInfo为空的属性用当前版本的信息
            // ClassCopyUtil.Copy( currentVersion, appVersionInfo );

            RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
            restRecord.setData( appVersionRepository.save( appVersionInfo ) );
            //todo 添加消息通知,通知管理员进行审核
            return restRecord;
        } catch ( Exception e ) {
            log.error( "apply appVersion fail {}", e );
            return new RestRecord( 423, WebMessageConstants.SCE_PORTAL_MSG_423 );
        }
    }

    /**
     * 应用版本审批接口(上架审批，迭代审批)
     * 1	将应用版本表中应用状态更新为通过审核 运营中
     * 存入应用地址字段，结算比例（厂家，平台，代理商）相关字段。
     * 2	创建一条消息，通知对应厂商用户
     *
     * @param userId      管理员用户Id
     * @param approveList 需要审核通过的应用版本和应用Id列表
     * @return
     */
    @PutMapping( "/approve/{userId}" )
    @ResponseBody
    public RestRecord appVersionUpdateApprove(
            @PathVariable( "state" ) String state,
            @PathVariable( "userId" ) String userId,
            @RequestBody List< Map< String, String > > approveList ) {
        log.trace( "Approve {} AppVersion By {}", approveList.size(), userId );
        try {
            Date date = new Date();
            for ( Map< String, String > approve : approveList ) {
                /*更改目标版本号应用状态为上架*/
                MarketAppVersion appAbove = appAuditingRepository.findByAppIdAndAppVersion( approve.get( "appId" ), approve.get( "appVersion" ) );
                appAbove.setAppStatus( "4" );
                appAbove.setAuditTime( date );
                appAbove.setIndexUrl( approve.get( "indexUrl" ) );
                appAbove.setPlatformRatio( approve.get( "platformRatio" ) );
                appAbove.setCompanyRatio( approve.get( "companyRatio" ) );
                appAbove.setAgentRatio( approve.get( "agentRatio" ) );

                appAuditingRepository.saveAndFlush( appAbove );
                /*更改目标版本号应用状态为 7 已下架（运行中）*/
                if ( approve.get( "currentVersion" ) != null ) {
                    MarketAppVersion appUnder = appAuditingRepository.findByAppIdAndAppVersion( approve.get( "appId" ), approve.get( "currentVersion" ) );
                    appUnder.setAppStatus( "7" );
                    appUnder.setAuditTime( date );
                    appAuditingRepository.saveAndFlush( appUnder );
                }
                //平台审核会传url ,将url存入SCE_MARKET_APP_VERSION表的INDEX_URL字段
                if ( StringUtils.isNotEmpty( approve.get( "indexUrl" ) ) ) {
                    appAuditingRepository.updateIndexUrl( approve.get( "appId" ), approve.get( "appVersion" ), userId, approve.get( "indexUrl" ) );
                }
                /*给提交审核人员发送消息，通知其审核通过*/
                Message message = new Message();
                String appName = String.valueOf( appAuditingRepository.getAppName( appAbove.getAppId(), appAbove.getAppVersion() ).get( "appName" ) );
                message.setContent( String.format( WebMessageConstants.SCE_PORTAL_MSG_641, appName, appAbove.getAppVersion() ) );
                message.setTargetId( appAbove.getCreateUserId() );
                messageService.insertMessage( message );
            }
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        } catch ( Exception e ) {
            log.error( "Approve fail {}", e );
            return new RestRecord( 421, WebMessageConstants.SCE_PORTAL_MSG_421 );
        }

    }

    /**
     * 下架审批通过
     * 将应用状态设置为 7已下架（运行中）
     *
     * @param userId
     * @param approveList
     * @return
     */
    @PutMapping( "/approve/down-shelf/{userId}" )
    @ResponseBody
    public RestRecord appVersionUpdateApprove(
            @PathVariable( "userId" ) String userId,
            @RequestBody List< Map< String, String > > approveList ) {
        log.trace( "Approve {} AppVersion By {}", approveList.size(), userId );
        try {
            Date date = new Date();
            for ( Map< String, String > approve : approveList ) {
                /*更改目标版本号应用状态为下架（运行中）*/
                MarketAppVersion appAbove = appAuditingRepository.findByAppIdAndAppVersion( approve.get( "appId" ), approve.get( "appVersion" ) );
                appAbove.setAppStatus( "7" );
                appAbove.setAuditTime( date );
                appAuditingRepository.saveAndFlush( appAbove );

                /*给提交审核人员发送消息，通知其审核通过*/
                Message message = new Message();
                String appName = String.valueOf( appAuditingRepository.getAppName( appAbove.getAppId(), appAbove.getAppVersion() ).get( "appName" ) );
                message.setContent( String.format( WebMessageConstants.SCE_PORTAL_MSG_641, appName, appAbove.getAppVersion() ) );
                message.setTargetId( appAbove.getCreateUserId() );
                messageService.insertMessage( message );
            }
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        } catch ( Exception e ) {
            log.error( "down-shelf Approve fail {}", e );
            return new RestRecord( 421, WebMessageConstants.SCE_PORTAL_MSG_421 );
        }

    }


    /**
     * 审批不通过接口
     * 1 将应用版本表中应用状态 3上架审核（被驳回）,6迭代审核（被驳回），10下架审核（被驳回），更新驳回原因
     * 2 创建一条消息，通知对应厂商用户
     *
     * @param userId       提交版本更新申请的应用Id
     * @param approveList  需要审核通过的应用版本和应用Id列表
     * @param rejectReason 驳回请求原因
     * @return
     */
    @PutMapping( "/reject/{operateType}/{userId}" )
    @ResponseBody
    public RestRecord appVersionUpdateReject(
            @PathVariable( "userId" ) String userId,
            @PathVariable( "operateType" ) String operateType,
            @RequestBody List< Map< String, String > > approveList,
            @RequestParam( "rejectReason" ) String rejectReason ) {
        log.trace( "Reject {} AppVersion By {}", approveList.size(), userId );
        try {
            for ( Map< String, String > approve : approveList ) {
                // appAuditingRepository.appVersionReject( approve.get( "appId" ), approve.get( "appVersion" ), userId );
                MarketAppVersion appReject = appAuditingRepository.findByAppIdAndAppVersion( approve.get( "appId" ), approve.get( "appVersion" ) );
                if ( "up".equals( operateType ) ) {
                    appReject.setAppStatus( "3" );
                } else if ( "iterator".equals( operateType ) ) {
                    appReject.setAppStatus( "6" );
                } else if ( "down".equals( operateType ) ) {
                    appReject.setAppStatus( "10" );
                } else {
                    return new RestRecord( 421, WebMessageConstants.SCE_PORTAL_MSG_421 );
                }

                appReject.setAuditTime( new Date() );
                appAuditingRepository.saveAndFlush( appReject );
                /*给提交审核人员发送消息，通知其审核为通过，如果有原因则表明原因*/
                Message message = new Message();
                String appName = String.valueOf( appAuditingRepository.getAppName( appReject.getAppId(), appReject.getAppVersion() ).get( "appName" ) );
                if ( StringUtils.isBlank( rejectReason ) ) {
                    message.setContent( String.format( WebMessageConstants.SCE_PORTAL_MSG_642, appName, appReject.getAppVersion() ) );
                } else {
                    message.setContent( String.format( WebMessageConstants.SCE_PORTAL_MSG_643, appName, appReject.getAppVersion(), rejectReason ) );
                }
                message.setTargetId( appReject.getCreateUserId() );
                messageService.insertMessage( message );
            }
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        } catch ( Exception e ) {
            log.error( "Reject fail {}", e );
            return new RestRecord( 421, WebMessageConstants.SCE_PORTAL_MSG_421 );
        }
    }


    /* *
     * @Description 应用数据暂存
     * @Date 9:58 2019/1/15
     * @Param [userId, appVersionInfo]
     * @return cn.com.bonc.sce.rest.RestRecord
     */
    @PutMapping( "/temp/save" )
    @ResponseBody
    public RestRecord tempSaveVersionInfo(
            @RequestParam( "userId" ) String userId,
            @RequestBody Map< String, String > tempData ) {
        log.trace( "temp save {} AppInfo", userId );

        String appNAME = tempData.get( "appNAME" ) == null ? "" : tempData.get( "appNAME" );
//        String createUserId=userId;
        String appVersion = tempData.get( "appVersion" ) == null ? "" : tempData.get( "appVersion" );
        String downloadAddress = tempData.get( "downloadAddress" ) == null ? "" : tempData.get( "downloadAddress" );
        String versionInfo = tempData.get( "versionInfo" ) == null ? "" : tempData.get( "versionInfo" );
        String versionSize = tempData.get( "versionSize" ) == null ? "" : tempData.get( "versionSize" );
        String runningPlatform = tempData.get( "runningPlatform" ) == null ? "" : tempData.get( "runningPlatform" );
        String newFeatures = tempData.get( "newFeatures" ) == null ? "" : tempData.get( "newFeatures" );
        String packageName = tempData.get( "packageName" ) == null ? "" : tempData.get( "packageName" );
        String authDetail = tempData.get( "authDetail" ) == null ? "" : tempData.get( "authDetail" );
        String appPhonePic = tempData.get( "appPhonePic" ) == null ? "" : tempData.get( "appPhonePic" );
        String appPcPic = tempData.get( "appPcPic" ) == null ? "" : tempData.get( "appPcPic" );
        String appIcon = tempData.get( "appIcon" ) == null ? "" : tempData.get( "appIcon" );
        String companyId = tempData.get( "companyId" ) == null ? "" : tempData.get( "companyId" );
        String appType = tempData.get( "appType" ) == null ? "0" : tempData.get( "appType" );
//        String tempAppId=tempData.get("tempAppId")==null?"":tempData.get("tempAppId");

        if ( tempData.get( "tempAppId" ) == null ) {
            try {
                appVersionRepository.insertTempAppInfo(
                        appNAME,
                        userId,
                        appVersion,
                        downloadAddress,
                        versionInfo,
                        versionSize,
                        runningPlatform,
                        newFeatures,
                        packageName,
                        authDetail,
                        appPhonePic,
                        appPcPic,
                        appIcon,
                        companyId,
                        appType,
                        UUID.getUUID() );
                return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );

            } catch ( Exception e ) {
                log.error( "app tempsave fail {}", e );
                return new RestRecord( 423, WebMessageConstants.SCE_PORTAL_MSG_423 );
            }

        } else {

            try {
                appVersionRepository.updateTempAppInfo(
                        appNAME,
                        userId,
                        appVersion,
                        downloadAddress,
                        versionInfo,
                        versionSize,
                        runningPlatform,
                        newFeatures,
                        packageName,
                        authDetail,
                        appPhonePic,
                        appPcPic,
                        appIcon,
                        companyId,
                        appType,
                        tempData.get( "tempAppId" ) );
                return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );

            } catch ( Exception e ) {
                log.error( "app tempsave fail {}", e );
                return new RestRecord( 421, WebMessageConstants.SCE_PORTAL_MSG_421 );
            }

        }

    }

    /**
     * 通过接收的下载地址Id获取到真实地址
     *
     * @param query 需要查询的Id数组字符串
     * @return 地址字符串
     */
    public String findRealUrl( String query ) {
        if ( "".equals( query ) || query == null ) {
            return null;
        }
        String[] temp = query.split( "," );
        StringBuilder result = new StringBuilder();
        for ( String s : temp ) {
            Map< String, Object > fileStorePath = fileResourceRepository.getFileStorePathById( Integer.parseInt( s ) );
            String p = String.valueOf( fileStorePath.get( "FILE_STORE_PATH" ) );
            result.append( p ).append( "," );
        }
        return StringUtils.substring( result.toString(), 0, result.length() - 1 );
    }
}
