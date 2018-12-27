package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.AppVersionModel;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppAuditingService;
import cn.com.bonc.sce.service.AppVersionService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Slf4j
@Api( value = "应用版本更新、审核接口", tags = "应用版本更新/审核接口" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
} )
@RestController
@RequestMapping( "/app-version" )
public class AppVersionController {
    private AppVersionService appVersionService;
    private AppAuditingService appAuditingService;

    @Autowired
    public AppVersionController( AppVersionService appVersionService,
                                 AppAuditingService appAuditingService ) {
        this.appVersionService = appVersionService;
        this.appAuditingService = appAuditingService;
    }


    /**
     * 应用版本查询接口
     * 1.通过应用Id查询应用所有的版本信息
     * 2.通过应用Id和应用版本查询该版本的详细信息
     * 请求样例
     * http://localhost:10200/query
     *
     * @param appId 查询的应用Id
     * @return
     */
    @ApiOperation( position = 1, value = "应用版本查询接口", notes = "通过应用ID查询该应用的历史版本信息列表", httpMethod = "GET" )
    @GetMapping
    @ResponseBody
    public RestRecord queryAppVersion(
            @RequestParam( "appId" ) @ApiParam( "应用Id" ) String appId,
            @RequestParam( value = "appVersion", required = false, defaultValue = "" ) @ApiParam( "应用版本" ) String appVersion,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) int pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) int pageSize ) {
        // 通过应用ID查询该应用的历史版本信息列表
        return appVersionService.queryAppVersion( appId, appVersion, pageNum, pageSize );
    }

    /**
     * 通过应用ID修改该应用的版本信息
     *
     * @param appId            查询的应用Id
     * @param marketAppVersion 版本应用信息详情
     * @return
     */
    @ApiOperation( value = "修改应用的版本信息", notes = "通过应用ID修改该应用的版本信息", httpMethod = "PUT" )
    @PutMapping( "/{appId}" )
    @ResponseBody
    public RestRecord updateAppHistoryVersionInfo(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "appVersion" ) String appVersion,
            @RequestBody @ApiParam( "应用版本信息对象" ) AppVersionModel marketAppVersion ) {
        //通过应用ID修改该应用的版本信息
        return appVersionService.updateAppHistoryVersionInfo( appId, appVersion, marketAppVersion );
    }

    /**
     * 删除应用版本
     *
     * @param appId      应用Id
     * @param appVersion 应用版本，如果为空则删除全部
     * @return
     */
    @ApiOperation( value = "删除指定的一条应用版本信息", notes = "通过应用ID和版本信息删除该应用的部分版本信息", httpMethod = "DELETE" )
    @DeleteMapping
    @ResponseBody
    public RestRecord deleteAppHistoryVersionInfo(
            @RequestParam( "appId" ) String appId,
            @RequestParam( value = "appVersion", required = false, defaultValue = "" ) String appVersion ) {
        return appVersionService.deleteAppHistoryVersionInfo( appId, appVersion );
    }

    /**
     * 应用版本更新申请接口
     * 1. 在应用版本信息表中插入一条新的版本信息, 并将状态设置为待审核
     * 2. 创建一条待办消息，创建人为申请人，接收人为系统管理员
     * todo 等待消息服务完成
     *
     * @param appId            更新版本的应用Id
     * @param userId           提交版本更新申请的用户Id
     * @param marketAppVersion 版本更新详情
     * @return
     */
    @ApiOperation( value = "应用版本更新申请接口", notes = "添加一条版本更新申请", httpMethod = "POST" )
    @PostMapping( "apply/{appId}" )
    @ResponseBody
    public RestRecord appVersionUpdateApply(
            @PathVariable( "appId" ) @ApiParam( "应用Id" ) String appId,
            @RequestParam( "userId" ) @ApiParam( "用户Id" ) String userId,
            @RequestBody @ApiParam( "应用版本信息对象" ) AppVersionModel marketAppVersion ) {
        return appVersionService.createVersionInfo( appId, userId, marketAppVersion );
//        messageService.createAppVersionUpdateApplyMessage( userId, appId );

    }

    /**
     * 应用版本审批接口
     * 1	将应用版本表中应用状态更新为通过审核
     * 2	创建一条消息，通知对应厂商用户
     * todo 等待消息服务完成
     *
     * @param userId      用户Id
     * @param approveList 提交审核的APP列表
     * @return
     */
    @ApiOperation( value = "应用版本审批接口", notes = "将应用版本表中应用状态更新为通过审核", httpMethod = "PUT" )
    @PutMapping( "/approve/{userId}" )
    @ResponseBody
    public RestRecord appVersionUpdateApprove(
            @PathVariable( "userId" ) String userId,
            @RequestBody @ApiParam( "需通过审核的列表[{\"appId\":\"101\",\"appVersion\":\"v1.1\"},{略}]" ) List< Map< String, String > > approveList ) {
        return appAuditingService.appVersionUpdateApprove( userId, approveList );
//        messageService.createAppVersionUpdateApproveMessage( appId, userId );
    }

    /**
     * 审批不通过接口
     * 1 将应用版本表中应用状态更新为不通过审核，并更新不通过原因
     * 2 创建一条消息，通知对应厂商用户
     * todo 等待消息服务完成
     *
     * @param userId       管理员用户Id
     * @param userId       管理员用户Id
     * @param rejectReason 驳回请求原因
     * @return
     */
    @ApiOperation( value = "审批不通过接口", notes = "不通过审核，并更新不通过原因", httpMethod = "PUT" )
    @PutMapping( "/reject/{userId}" )
    @ResponseBody
    public RestRecord appVersionUpdateReject(
            @PathVariable( "userId" ) String userId,
            @RequestBody @ApiParam( "需通过审核的列表[{\"appId\":\"101\",\"appVersion\":\"v1.1\"},{略}]" ) List< Map< String, String > > approveList,
            @RequestParam( "rejectReason" ) String rejectReason ) {
        return appAuditingService.appVersionUpdateReject( userId, approveList, rejectReason );
//        messageService.createAppVersionUpdateRejectMessage( appId, userId, rejectReason );

    }
}
