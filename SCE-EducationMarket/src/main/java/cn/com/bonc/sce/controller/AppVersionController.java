package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.AppVersionModel;
import cn.com.bonc.sce.model.PlatformAddModel;
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
     * 应用版本更新申请接口（迭代接口）
     * 1. 在应用版本信息表中插入一条新的版本信息, 并将状态设置为待审核
     * 2. 创建一条待办消息，创建人为申请人，接收人为系统管理员
     *
     * @param appId            更新版本的应用Id
     * @param userId           提交版本更新申请的用户Id
     * @param platformAddModel 版本更新详情
     * @return
     */
    @ApiOperation( value = "应用版本迭代申请接口", notes = "添加一条版本更新申请（迭代接口）", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @PostMapping( "/apply/{appId}" )
    @ResponseBody
    public RestRecord appVersionUpdateApply(
            @PathVariable( "appId" ) @ApiParam( "应用Id" ) String appId,
            @CurrentUserId @ApiParam( hidden = true ) String userId,
            @RequestBody @ApiParam( "应用版本信息对象" ) PlatformAddModel platformAddModel ) {
        return appVersionService.createVersionInfo( appId, userId, platformAddModel );
//        messageService.createAppVersionUpdateApplyMessage( userId, appId );

    }

    /**
     * 应用上架审批，迭代审批通过接口
     * 1	将应用版本表中应用状态更新为通过审核
     * 2	创建一条消息，通知对应厂商用户
     *
     * @param userId      用户Id
     * @param approveList 提交审核的APP列表
     * @return
     */
    @ApiOperation( value = "应用上架审批，迭代审批通过接口", notes = "应用上架审批，迭代审批通过接口", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @PutMapping( "/approve" )
    @ResponseBody
    public RestRecord appVersionUpdateApprove(
            @CurrentUserId @ApiParam( hidden = true ) String userId,
            @RequestBody @ApiParam( "若果有currentVersion字段，说明是迭代申请[{\"appId\":\"101\",\"appVersion\":\"v1.1\",\"currentVersion\":\"v1.0\",\"indexUrl\":\"www.baidu.com\",\"platformRatio\":\"25\",\"companyRatio\":\"25\",\"agentRatio\":\"50\"},{略}]" ) List< Map< String, String > > approveList ) {
        return appAuditingService.appVersionUpdateApprove( userId, approveList );
    }

    @ApiOperation( value = "下架审批通过接口", notes = "下架审批通过接口", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @PutMapping( "/approve/down-shelf" )
    @ResponseBody
    public RestRecord appVersionDownShelfApprove(
            @CurrentUserId @ApiParam( hidden = true ) String userId,
            @RequestBody @ApiParam( "[{\"appId\":\"101\",\"appVersion\":\"v1.1\"},{略}]" ) List< Map< String, String > > approveList ) {
        return appAuditingService.appVersionDownShelfApprove( userId, approveList );
//        messageService.createAppVersionUpdateApproveMessage( appId, userId );
    }

    /**
     * 审批不通过接口
     * 1 将应用版本表中应用状态更新为不通过审核，并更新不通过原因
     * 2 创建一条消息，通知对应厂商用户
     *
     * @param userId       管理员用户Id
     * @param rejectReason 驳回请求原因
     * @return
     */
    @ApiOperation( value = "上架，下架，迭代 驳回接口", notes = "不通过审核，并更新不通过原因", httpMethod = "PUT" )
    @PutMapping( "/reject/{operateType}" )
    @ResponseBody
    public RestRecord appVersionUpdateReject(
            @PathVariable( "operateType" ) @ApiParam( "up为上架驳回，down为下架驳回，iterator为迭代驳回" ) String operateType,
            @CurrentUserId @ApiParam( hidden = true ) String userId,
            @RequestBody @ApiParam( "需要驳回的列表[{\"appId\":\"101\",\"appVersion\":\"v1.1\"},{略}]" ) List< Map< String, String > > approveList,
            @RequestParam( "rejectReason" ) String rejectReason ) {
        return appAuditingService.appVersionUpdateReject( operateType, userId, approveList, rejectReason );
//        messageService.createAppVersionUpdateRejectMessage( appId, userId, rejectReason );

    }


    /* *
     * @Description
     * @Date 17:45 2019/1/14
     * @Param [userId, marketAppVersion]
     * @return cn.com.bonc.sce.rest.RestRecord
     */
    @ApiOperation( value = "应用数据暂存接口", notes = "暂存应用数据", httpMethod = "POST" )
    @PostMapping( "/temp/save" )
    @ResponseBody
    public RestRecord appVersionTempSave(
            @CurrentUserId @ApiParam( hidden = true ) String userId,
            @RequestBody @ApiParam( "应用信息暂存对象" ) Map< String, String > tempData
    ) {
        return appVersionService.tempSaveVersionInfo( userId, tempData );
    }
}
