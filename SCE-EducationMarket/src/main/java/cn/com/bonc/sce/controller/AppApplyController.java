package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MarketMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppApplyService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 厂商应用上架审核申请
 * @author BTW
 * @version 0.1
 * @since 2018/12/14 17:26
 */
@Slf4j
@Api( value = "厂商应用上下架审核申请相关接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping("/app-on-shelf-apply")
public class AppApplyController {
    private AppApplyService appApplyService;

    @Autowired
    public AppApplyController( AppApplyService appApplyService ) {
        this.appApplyService = appApplyService;
    }

    /**
     * 应用上架审核申请接口
     * @param applyType 请求业务类型（上架或下架,1为上架，0为下架）
     * @param appIdList 请求审核的appId，可为单个id或批量id
     * @param userId 发出上架审核请求的用户id
     * @return 上架审核申请是否发起
     */
    @ApiOperation( value = "应用上下架审核申请接口", notes = "根据选择的应用id向管理员发起上/下架审核申请", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "applyType", dataType = "Integer", value = "请求的业务类型（1：上架 0：下架）", paramType = "query", required = true, allowableValues = "0,1" ),
            @ApiImplicitParam( name = "appIdList", dataType = "String", value = "申请上/下架的应用ID", paramType = "query", required = true ),
            @ApiImplicitParam( name = "userId", dataType = "String", value = "提出上/下架申请的用户ID", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = MarketMessageConstants.SCE_MARKET_MSG_100, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord applyAppOnShelf ( @RequestParam( "applyType" ) Integer applyType,
                                        @RequestParam( "appIdList" ) String appIdList,
                                        @RequestParam( "userId" ) String userId ) {
        // 1. 将所有选择的应用状态更新为待审核
        // 2. 自动向管理员用户发送一条信息，内容为有xxx应用上架审核请求需处理
        return null;
    }
}
