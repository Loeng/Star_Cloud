package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppApplyService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 厂商应用上下架审核申请
 * @author BTW
 * @version 0.1
 * @since 2018/12/14 17:26
 */
@Slf4j
@Api( value = "厂商应用上下架审核申请相关接口",tags = "厂商应用上下架审核申请相关接口")
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
     * 应用上下架审核申请接口
     * @param applyType 请求的业务类型 【8，撤销上架，9，下架（待审核），5,下架（停止服务）】
     * @param appIdList 请求审核的appId，可为单个id或批量id
     * @param userId 发出上架审核请求的用户id
     * @return 上架审核申请是否发起
     */
    @ApiOperation( value = "应用上下架及启停服务接口", notes = "应用上下架，停止服务审核申请接口", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord applyAppOnShelf ( @RequestBody @ApiParam( name = "appIdList", value = "[{\"appId\":\"101\",\"appVersion\":\"v1.1\"}]", required = true )  List<Map>  appIdList,
                                        @RequestParam( "applyType" ) @ApiParam( name = "applyType", value = "请求的业务类型 【8，撤销上架，9，下架（待审核），5,下架（停止服务）】", required = true, allowableValues = "5,7,8" ) Integer applyType,
                                        @CurrentUserId @ApiParam( hidden = true ) String userId ) {

        RestRecord restRecord = appApplyService.applyAppOnShelf( applyType,appIdList,userId );

        return restRecord;
    }
}
