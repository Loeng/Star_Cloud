package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppAuditingService;
import cn.com.bonc.sce.service.AppVersionService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Api( value = "应用版本更新、审核接口", tags = "应用版本更新/审核接口" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RestController
@RequestMapping( "/app-version" )
public class AppVersionController {
    private AppVersionService appVersionService;
    private AppAuditingService appAuditingService;
//    private AdviseService adviseService;

    @Autowired
    public AppVersionController( AppVersionService appVersionService,
                                 AppAuditingService appAuditingService

    ) {
        this.appVersionService = appVersionService;
        this.appAuditingService = appAuditingService;
//        this.adviseService = adviseService;
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
    @ApiOperation( value = "应用版本查询接口", notes = "通过应用ID查询该应用的历史版本信息列表", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "查询的应用Id", paramType = "path", required = true ),
            @ApiImplicitParam( name = "appVersion", value = "查询的应用版本若为空，则查询全部版本信息", paramType = "path", required = false )
    } )
    @GetMapping( "/query" )
    @ResponseBody
    public RestRecord selectAppHistoryVersionList(
            @RequestParam( "appId" ) String appId,
            @RequestParam( value = "appVersion", required = false, defaultValue = "" ) String appVersion ) {
        // 通过应用ID查询该应用的历史版本信息列表
        RestRecord restRecord = new RestRecord( 0 );
        restRecord.setData( appVersionService.selectAppHistoryVersionList( appId, appVersion ) );
        return restRecord;
    }

    /**
     * 通过应用ID修改该应用的版本信息
     *
     * @param appId            查询的应用Id
     * @param marketAppVersion 版本应用信息详情
     * @return
     */
    @ApiOperation( value = "修改应用的版本信息", notes = "通过应用ID修改该应用的版本信息", httpMethod = "PATCH" )
    @PostMapping( "/{appId}" )
    @ResponseBody
    public RestRecord updateAppHistoryVersionInfo(
            @PathVariable( "appId" ) String appId,
            @RequestBody Map<String,String> marketAppVersion ) {
        //通过应用ID修改该应用的版本信息
        return new RestRecord( 0, appVersionService.updateAppHistoryVersionInfo( appId, marketAppVersion ) );
    }

    /**
     * 删除指定的一条应用版本信息
     * 通过应用ID和版本信息删除该应用的部分版本信息(删除状态字段更改为已删除)
     *
     * @param appId            应用Id
     * @param marketAppVersion 版本应用信息详情
     * @return
     */
    @ApiOperation( value = "删除指定的一条应用版本信息", notes = "通过应用ID和版本信息删除该应用的部分版本信息", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "应用Id", paramType = "path", required = true ),
            @ApiImplicitParam( name = "marketAppVersion", value = "版本应用信息详情", paramType = "body", required = true )
    } )
    @DeleteMapping( "/{appId}" )
    @ResponseBody
    public RestRecord deleteAppHistoryVersionInfo(
            @PathVariable( "appId" ) String appId,
            @RequestBody Map<String,String> marketAppVersion ) {
        return new RestRecord( 0, appVersionService.deleteAppHistoryVersionInfo( appId, marketAppVersion ) );
    }

    /**
     * 删除指定的应用版本信息
     * 通过应用ID删除该应用的全部版本信息(删除状态字段更改为已删除)
     *
     * @param appId 应用Id
     * @return
     */
    @ApiOperation( value = "删除指定的应用版本信息", notes = "通过应用ID删除该应用的全部版本信息", httpMethod = "DELETE" )
    @ApiImplicitParam( name = "appId", value = "应用Id", paramType = "path", required = true )
    @DeleteMapping( "/all/{appId}" )
    @ResponseBody
    public RestRecord deleteAppAllVersionInfoById(
            @PathVariable( "appId" ) String appId ) {
        return new RestRecord( 0, appVersionService.deleteAppAllVersionInfoById( appId ) );
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
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "应用Id", paramType = "path", required = true ),
            @ApiImplicitParam( name = "userId", value = "用户Id", paramType = "query", required = true ),
            @ApiImplicitParam( name = "marketAppVersion", value = "版本更新详情", paramType = "body", required = true )
    } )
    @PostMapping( "apply/{appId}" )
    @ResponseBody
    public RestRecord appVersionUpdateApply(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "userId" ) String userId,
            @RequestBody Map<String,String> marketAppVersion ) {
        appVersionService.createVersionInfo( appId, marketAppVersion );
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
    @ApiOperation( value = "应用版本审批接口", notes = "将应用版本表中应用状态更新为通过审核", httpMethod = "PATCH" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "应用Id", paramType = "path", required = true ),
            @ApiImplicitParam( name = "userId", value = "用户Id", paramType = "query", required = true )
    } )
    @PatchMapping( "/approve/{appId}" )
    @ResponseBody
    public RestRecord appVersionUpdateApprove(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "userId" ) String userId ) {
        appAuditingService.appVersionUpdateApprove( appId, userId );
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
    @ApiOperation( value = "审批不通过接口", notes = "不通过审核，并更新不通过原因", httpMethod = "PATCH" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "应用Id", paramType = "path", required = true ),
            @ApiImplicitParam( name = "userId", value = "管理员用户Id", paramType = "query", required = true ),
            @ApiImplicitParam( name = "rejectReason", value = "驳回请求原因", paramType = "query", required = true )
    } )
    @PatchMapping( "/reject/{appId}" )
    @ResponseBody
    public RestRecord appVersionUpdateReject(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "userId" ) String userId,
            @RequestParam( "rejectReason" ) String rejectReason ) {
        appAuditingService.appVersionUpdateReject( appId, userId, rejectReason );
//        messageService.createAppVersionUpdateRejectMessage( appId, userId, rejectReason );
        return null;
    }
}
