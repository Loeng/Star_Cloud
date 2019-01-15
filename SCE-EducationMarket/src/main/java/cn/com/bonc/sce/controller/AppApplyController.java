package cn.com.bonc.sce.controller;

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
 * 厂商应用上架审核申请
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
     * 应用上架审核申请接口
     * @param applyType 请求业务类型（上架或下架,1为上架，0为下架）
     * @param appIdList 请求审核的appId，可为单个id或批量id
     * @param userId 发出上架审核请求的用户id
     * @return 上架审核申请是否发起
     */
    @ApiOperation( value = "应用上下架审核申请接口", notes = "根据选择的应用id向管理员发起上/下架审核申请", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 0, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord applyAppOnShelf ( @RequestBody @ApiParam( name = "appIdList", value = "申请上/下架的应用ID", required = true )  List<Map>  appIdList,
                                        @RequestParam( "applyType" ) @ApiParam( name = "applyType", value = "请求的业务类型（1：上架 0：下架）", required = true, allowableValues = "0,1" ) Integer applyType,
                                        @RequestParam( "userId" ) @ApiParam( name = "userId",  value = "提出上/下架申请的用户ID", required = true ) String userId ) {

        RestRecord restRecord =   appApplyService.applyAppOnShelf( applyType,appIdList,userId );

        return restRecord;
    }
}
