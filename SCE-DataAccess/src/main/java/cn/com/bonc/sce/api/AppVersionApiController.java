package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.dao.AppAuditingDao;
import cn.com.bonc.sce.dao.MarketAppVersionRepository;
import cn.com.bonc.sce.entity.MarketAppVersion;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 应用下载统计接口
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
     * 请求样例
     * http://localhost:10101/AppVersion/{appId}
     * (not required)  {"appVersion":"v1.0"}
     *
     * @param appId 查询的应用Id
     * @return
     */
    @GetMapping
    @ResponseBody
    public RestRecord queryAppVersion(
            @RequestParam( "appId" ) String appId,
            @RequestParam( value = "appVersion", required = false, defaultValue = "" ) String appVersion ) {
        // 通过应用ID查询该应用的历史版本信息列表
        log.info( "查询应用版本信息，查询条件为appId:{}  appVersion:{}",appId,appVersion );
        String queryAppVersion = appVersion;
        List< MarketAppVersion > result = marketAppVersionRepository.findAllByAppIdAndAppVersionContainingAndIsDelete( appId, queryAppVersion, 0 );
        RestRecord restRecord = new RestRecord();
        restRecord.setData( result );
        return restRecord;
    }

    /**
     * 通过应用ID修改该应用的版本信息
     *
     * @param appId          查询的应用Id
     * @param appVersionInfo 版本应用信息详情
     * @return
     */
    @PostMapping( "/{appId}" )
    @ResponseBody
    public RestRecord updateAppHistoryVersionInfo(
            @PathVariable( "appId" ) String appId,
            @RequestBody MarketAppVersion appVersionInfo ) {
        //通过应用ID修改该应用的版本信息
        log.info( "更新应用版本信息  appId:{}  appVersionInfo:{}",appId,appVersionInfo );
        appVersionInfo.setAppId( appId );
        MarketAppVersion result = marketAppVersionRepository.saveAndFlush( appVersionInfo );
        return new RestRecord( 0, result );
    }


    /**
     * http://loaclhost:10101/AppVersion/1
     * 删除指定的一条应用版本信息
     * 通过应用ID和版本信息删除该应用的部分版本信息(删除状态字段更改为已删除)
     *
     * @param appId     应用Id
     * @param queryInfo 版本应用信息详情
     * @return
     */
    @DeleteMapping( "/{appId}" )
    @ResponseBody
    @Transactional
    public RestRecord deleteAppHistoryVersionInfo(
            @PathVariable( "appId" ) String appId,
            @RequestBody Map< String, String > queryInfo ) {
        //判断是删除指定版本还是删除应用的所有版本
//        if ( queryInfo.get( "appVersion" ) == null ) {
//            return new RestRecord( 200, marketAppVersionRepository.deleteByAppIdAndAppVersion( appId, queryInfo.get( "appVersion" ) ) );
//        } else {
//            return new RestRecord( 200, marketAppVersionRepository.deleteByAppId( appId ) );
//        }
        return null;
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
        RestRecord restRecord = new RestRecord();
        if ( appVersionInfo.getAppVersion() == null ) {
            restRecord.setMsg( "#########缺少迭代需要的版本号###########" );
            return restRecord;
        }
        restRecord.setMsg( PortalMessageConstants.SCE_PORTAL_MSG_200 );
        restRecord.setData( marketAppVersionRepository.saveAndFlush( appVersionInfo ) );
        return restRecord;
    }
//
//    /**
//     * 应用版本审批接口
//     * 1	将应用版本表中应用状态更新为通过审核
//     * 2	创建一条消息，通知对应厂商用户
//     * //     * todo 添加消息通知
//     *
//     * @param appId  提交版本更新申请的应用Id
//     * @param userId 管理员用户Id
//     * @return
//     */
//    @PatchMapping( "/approve/{appId}" )
//    @ResponseBody
//    public RestRecord appVersionUpdateApprove(
//            @PathVariable( "appId" ) String appId,
//            @RequestParam( "userId" ) String userId ) {
//        appAuditingDao.appVersionUpdateApprove( appId, userId );
////        messageService.createAppVersionUpdateApproveMessage( appId, userId );
//        return null;
//    }
//
//    /**
//     * 审批不通过接口
//     * 1 将应用版本表中应用状态更新为不通过审核，并更新不通过原因
//     * 2 创建一条消息，通知对应厂商用户
//     * todo 添加消息通知
//     *
//     * @param appId        提交版本更新申请的应用Id
//     * @param userId       管理员用户Id
//     * @param rejectReason 驳回请求原因
//     * @return
//     */
//    @PatchMapping( "/reject/{appId}" )
//    @ResponseBody
//    public RestRecord appVersionUpdateReject(
//            @PathVariable( "appId" ) String appId,
//            @RequestParam( "userId" ) String userId,
//            @RequestParam( "rejectReason" ) String rejectReason ) {
//        appAuditingDao.appVersionUpdateReject( appId, userId, rejectReason );
////        messageService.createAppVersionUpdateRejectMessage( appId, userId, rejectReason );
//        return null;
//    }


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
