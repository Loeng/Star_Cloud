package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.AppAuditingDao;
import cn.com.bonc.sce.dao.AppVersionDao;
import cn.com.bonc.sce.model.AppVersionInfo;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 应用下载统计接口
 */
@Slf4j
@RestController
@RequestMapping( "/AppVersion" )
public class AppVersionApiController {

    private AppVersionDao appVersionDao;
    private AppAuditingDao appAuditingDao;

    @Autowired
    public AppVersionApiController( AppVersionDao appVersionDao, AppAuditingDao appAuditingDao ) {
        this.appVersionDao = appVersionDao;
        this.appAuditingDao = appAuditingDao;
    }

    /**
     * 应用版本查询接口
     * 通过应用ID查询该应用的历史版本信息列表
     *
     * @param appId 查询的应用Id
     * @return
     */
    @GetMapping( "/{appId}" )
    @ResponseBody
    public RestRecord selectAppHistoryVersionList(
            @PathVariable( "appId" ) String appId ) {
        // 通过应用ID查询该应用的历史版本信息列表
        return new RestRecord( 0, appVersionDao.selectAppHistoryVersionList( appId ) );
    }

    /**
     * 通过应用ID修改该应用的版本信息
     *
     * @param appId          查询的应用Id
     * @param appVersionInfo 版本应用信息详情
     * @return
     */
    @PatchMapping( "/{appId}" )
    @ResponseBody
    public RestRecord updateAppHistoryVersionInfo(
            @PathVariable( "appId" ) String appId,
            @RequestBody AppVersionInfo appVersionInfo ) {
        //通过应用ID修改该应用的版本信息
        return new RestRecord( 0, appVersionDao.updateAppHistoryVersionInfo( appId, appVersionInfo ) );
    }

    /**
     * 删除指定的一条应用版本信息
     * 通过应用ID和版本信息删除该应用的部分版本信息(删除状态字段更改为已删除)
     *
     * @param appId          应用Id
     * @param appVersionInfo 版本应用信息详情
     * @return
     */
    @DeleteMapping( "/{appId}" )
    @ResponseBody
    public RestRecord deleteAppHistoryVersionInfo(
            @PathVariable( "appId" ) String appId,
            @RequestBody AppVersionInfo appVersionInfo ) {
        return new RestRecord( 0, appVersionDao.deleteAppHistoryVersionInfo( appId, appVersionInfo ) );
    }

    /**
     * 删除指定的应用版本信息
     * 通过应用ID删除该应用的全部版本信息(删除状态字段更改为已删除)
     *
     * @param appId 应用Id
     * @return
     */
    @DeleteMapping( "/all/{appId}" )
    @ResponseBody
    public RestRecord deleteAppAllVersionInfoById(
            @PathVariable( "appId" ) String appId ) {
        return new RestRecord( 0, appVersionDao.deleteAppAllVersionInfoById( appId ) );
    }

    /**
     * 应用版本更新申请接口
     * 1. 在应用版本信息表中插入一条新的版本信息, 并将状态设置为待审核
     * 2. 创建一条待办消息，创建人为申请人，接收人为系统管理员
     * todo 等待消息服务完成
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
            @RequestBody AppVersionInfo appVersionInfo ) {
        appVersionDao.createVersionInfo( appId, appVersionInfo );
//        messageService.createAppVersionUpdateApplyMessage( userId, appId );
        return null;
    }

    /**
     * 应用版本审批接口
     * 1	将应用版本表中应用状态更新为通过审核
     * 2	创建一条消息，通知对应厂商用户
     * todo 等待消息服务完成
     *
     * @param appId  提交版本更新申请的应用Id
     * @param userId 管理员用户Id
     * @return
     */
    @PatchMapping( "/approve/{appId}" )
    @ResponseBody
    public RestRecord appVersionUpdateApprove(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "userId" ) String userId ) {
        appAuditingDao.appVersionUpdateApprove( appId, userId );
//        messageService.createAppVersionUpdateApproveMessage( appId, userId );
        return null;
    }

    /**
     * 审批不通过接口
     * 1 将应用版本表中应用状态更新为不通过审核，并更新不通过原因
     * 2 创建一条消息，通知对应厂商用户
     * todo 等待消息服务完成
     *
     * @param appId        提交版本更新申请的应用Id
     * @param userId       管理员用户Id
     * @param rejectReason 驳回请求原因
     * @return
     */
    @PatchMapping( "/reject/{appId}" )
    @ResponseBody
    public RestRecord appVersionUpdateReject(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "userId" ) String userId,
            @RequestParam( "rejectReason" ) String rejectReason ) {
        appAuditingDao.appVersionUpdateReject( appId, userId, rejectReason );
//        messageService.createAppVersionUpdateRejectMessage( appId, userId, rejectReason );
        return null;
    }
}
