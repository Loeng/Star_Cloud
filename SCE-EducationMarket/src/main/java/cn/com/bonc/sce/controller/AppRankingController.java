package cn.com.bonc.sce.controller;

import java.util.List;

import cn.com.bonc.sce.annotation.CurrentUserId;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppRankingService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api( value = "新应用排行——时间排行",tags = "新应用排行——时间排行")
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping("/app-ranking-top")
public class AppRankingController {
	
	 private AppRankingService appRankingService;
	 
	 @Autowired
	 public AppRankingController( AppRankingService appRankingService ) {
		 this.appRankingService = appRankingService;
	 }
	
	 /**
     * 新应用排行
     * @param topSize 
     * 
     */
	@ApiOperation( value = "应用排行", notes = "根据时间排序", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "topSize", dataType = "Integer", value = "大于0的整数", paramType = "query", required = false,defaultValue="10" ),
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping
    @ResponseBody
    public RestRecord latestAppRanking ( @RequestParam( "topSize" ) Integer topSize,
                                         @CurrentUserId @ApiParam( hidden = true ) String userId) {
        RestRecord restRecord = appRankingService.getTopRankAppList( topSize, userId );
        return restRecord;
    }
}
