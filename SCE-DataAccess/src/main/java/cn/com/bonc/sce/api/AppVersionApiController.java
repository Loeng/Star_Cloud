package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.AppAuditingDao;
import cn.com.bonc.sce.dao.MarketAppVersionRepository;
import cn.com.bonc.sce.entity.MarketAppVersion;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    private MarketAppVersionRepository marketAppVersionRepository;
    private AppAuditingDao appAuditingDao;

    @Autowired
    public AppVersionApiController( MarketAppVersionRepository marketAppVersionRepository, AppAuditingDao appAuditingDao ) {
        this.marketAppVersionRepository = marketAppVersionRepository;
        this.appAuditingDao = appAuditingDao;
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
        log.info( "查询应用版本信息，查询条件为appId:{}  appVersion:{}", appId, appVersion );
//        String queryAppVersion = appVersion;
//        List< MarketAppVersion > result = marketAppVersionRepository.findAllByAppIdAndAppVersionContainingAndIsDelete( appId, queryAppVersion, 1 );
        RestRecord restRecord = new RestRecord( 200 );
        Map< String, Object > map = new HashMap<>( 16 );
        List< Map< String, Object > > data_detail = new ArrayList<>();
        Map< String, Object > map1 = new HashMap<>( 16 );
        map1.put( "appName", "皇室战争" );
        map1.put( "appType", "手机游戏" );
        map1.put( "companyName", "暴雪" );
        map1.put( "runningPlatform", "IOS" );
        map1.put( "currentVersion", "v2.1" );
        map1.put( "updateVersion", "v2.4" );
        map1.put( "createTime", "2018-08-08" );
        map1.put( "newFeatures", "这里是newFeatures" );
        map1.put( "packageName", "这里是packageName" );
        map1.put( "authDetail", "这里是authDetail" );
        data_detail.add( map1 );
        Map< String, Object > map2 = new HashMap<>( 16 );
        map2.put( "略", "略" );
        data_detail.add( map2 );
        map.put( "data", data_detail );
        map.put( "totalCount", 123 );
        map.put( "totalPage", 23 );
        restRecord.setData( map );
        restRecord.setMsg( WebMessageConstants.SCE_PORTAL_MSG_200 );
        return restRecord;
    }

    /**
     * 通过应用ID修改该应用的版本信息
     *
     * @param appId          查询的应用Id
     * @param appVersionInfo 版本应用信息详情
     * @return
     */
    @PutMapping( "/{appId}" )
    @ResponseBody
    public RestRecord updateAppHistoryVersionInfo(
            @PathVariable( "appId" ) String appId,
            @RequestBody MarketAppVersion appVersionInfo ) {
        //通过应用ID修改该应用的版本信息
        log.info( "更新应用版本信息  appId:{}  appVersionInfo:{}", appId, appVersionInfo );
        appVersionInfo.setAppId( appId );
//        MarketAppVersion result = marketAppVersionRepository.saveAndFlush( appVersionInfo );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
//        return new RestRecord( 0, result );
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
    @Transactional
    public RestRecord deleteAppHistoryVersionInfo(
            @RequestParam( "appId" ) String appId,
            @RequestParam( value = "appVersion", required = false, defaultValue = "" ) String appVersion ) {
        //判断是删除指定版本还是删除应用的所有版本
//        if ( queryInfo.get( "appVersion" ) == null ) {
//            return new RestRecord( 200, marketAppVersionRepository.deleteByAppIdAndAppVersion( appId, queryInfo.get( "appVersion" ) ) );
//        } else {
//            return new RestRecord( 200, marketAppVersionRepository.deleteByAppId( appId ) );
//        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    /**
     * 应用版本更新申请接口
     * 1. 在应用版本信息表中插入一条新的版本信息, 并将状态设置为待审核
     * 2. 创建一条待办消息，创建人为申请人，接收人为系统管理员
     * //     * todo 添加消息通知
     *
     * @param appId          更新版本的应用Id
     * @param userId         提交版本更新申请的用户Id
     * @param appVersionInfo 版本更新详情
     * @return
     */
    @PostMapping( "/apply/{appId}" )
    @ResponseBody
    public RestRecord appVersionUpdateApply(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "userId" ) String userId,
            @RequestBody MarketAppVersion appVersionInfo ) {

//        marketAppVersionRepository.createVersionInfo( appId, appVersionInfo );
//        messageService.createAppVersionUpdateApplyMessage( userId, appId );
//        RestRecord restRecord = new RestRecord();
//        if ( appVersionInfo.getAppVersion() == null ) {
//            restRecord.setMsg( "#########缺少迭代需要的版本号###########" );
//            return restRecord;
//        }
//        restRecord.setMsg( WebMessageConstants.SCE_PORTAL_MSG_200 );
//        restRecord.setData( marketAppVersionRepository.saveAndFlush( appVersionInfo ) );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }
//

    /**
     * 应用版本审批接口
     * 1	将应用版本表中应用状态更新为通过审核
     * 2	创建一条消息，通知对应厂商用户
     * //     * todo 添加消息通知
     *
     * @param appId  提交版本更新申请的应用Id
     * @param userId 管理员用户Id
     * @return
     */
    @PutMapping( "/approve/{appId}" )
    @ResponseBody
    public RestRecord appVersionUpdateApprove(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "userId" ) String userId ) {
//        appAuditingDao.appVersionUpdateApprove( appId, userId );
//        messageService.createAppVersionUpdateApproveMessage( appId, userId );
//        return null;
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    /**
     * 审批不通过接口
     * 1 将应用版本表中应用状态更新为不通过审核，并更新不通过原因
     * 2 创建一条消息，通知对应厂商用户
     * todo 添加消息通知
     *
     * @param appId        提交版本更新申请的应用Id
     * @param userId       管理员用户Id
     * @param rejectReason 驳回请求原因
     * @return
     */
    @PutMapping( "/reject/{appId}" )
    @ResponseBody
    public RestRecord appVersionUpdateReject(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "userId" ) String userId,
            @RequestParam( "rejectReason" ) String rejectReason ) {
//        appAuditingDao.appVersionUpdateReject( appId, userId, rejectReason );
//        messageService.createAppVersionUpdateRejectMessage( appId, userId, rejectReason );
//        return null;
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }


    /**
     * http://localhost:10101/AppVersion/test
     *
     * @return
     */
    @GetMapping( "/test" )
    @ResponseBody
    public RestRecord test() {
        List< MarketAppVersion > test = marketAppVersionRepository.findAllByIsDelete( 0 );
        RestRecord restRecord = new RestRecord();
        restRecord.setData( test );
        return restRecord;
    }
}
