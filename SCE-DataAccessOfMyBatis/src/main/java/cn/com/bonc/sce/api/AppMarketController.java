package cn.com.bonc.sce.api;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppMarketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * app商城
 * author wf
 */
@Slf4j
@Api( value = "应用商城接口", tags = "应用商城接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping("/app-portal")
public class AppMarketController {

    private AppMarketService appMarketService;

    @Autowired
    public AppMarketController(AppMarketService appMarketService){
        this.appMarketService = appMarketService;
    }

    @GetMapping("/appCount")
    public RestRecord appCount(){
        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200, appMarketService.getAppCount());
    }

    @GetMapping("/userToDo")
    public RestRecord userToDo(@CurrentUserId String userId){
        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200, appMarketService.userToDo(userId));
    }

    @PostMapping("/backlog")
    public RestRecord backlog(@RequestHeader String appId,
                              @RequestHeader String appToken,
                              @RequestHeader String authentication,
                              @RequestBody Map<String, Object> backlog){
        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200, appMarketService.saveBacklog(appId, appToken, authentication, backlog));
    }

}
