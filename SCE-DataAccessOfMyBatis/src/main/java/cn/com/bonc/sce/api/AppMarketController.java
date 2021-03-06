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

import java.util.List;
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

    @GetMapping( "/backlog/{pageNum}/{pageSize}" )
    public RestRecord userToDo( @CurrentUserId String userId, @PathVariable String pageNum, @PathVariable String pageSize ) {
        return appMarketService.userToDo( userId, pageNum, pageSize );
    }

    @PostMapping("/backlog")
    public RestRecord backlog( @RequestParam String appId,
                               @RequestParam String appToken,
                               @RequestParam String userId,
                               @RequestBody Map< String, Object > backlog ) {
        return appMarketService.saveBacklog( appId, appToken, userId, backlog );
    }

    @GetMapping( "/app-token" )
    public String getAppToken( @RequestParam String appId ) {
        return appMarketService.findAppToken( appId );
    }

    @GetMapping( "/appInfo" )
    public Map<String, Object> getAppInfoById( @RequestParam String appId ){
        return appMarketService.getAppInfoById( appId );
    }

    @PutMapping( "/backlog" )
    public RestRecord backlog_patch( @RequestHeader String userId,
                                     @RequestBody Map map ){
        return appMarketService.changeBacklog( userId, map );
    }

    @GetMapping( "/getUserAppAuth/{userId}/{appId}" )
    public int getUserAppAuth( @PathVariable String userId,
                               @PathVariable String appId ){
        return appMarketService.getUserAppAuth( userId, appId );
    }

    @GetMapping( "/user-rest" )
    public RestRecord getUser( @RequestHeader String appId,
                               @RequestHeader String appToken){
        return appMarketService.getUser( appId, appToken );
    }

    @GetMapping( "/user-jwt" )
    public RestRecord getUserJWT( @RequestParam String appId,
                                  @RequestParam List users){
        return appMarketService.getUser( appId, users );
    }

    @GetMapping( "/user-detailed" )
    public RestRecord getUserDetailed( @RequestParam String appId,
                                       @RequestParam String userId){
        return appMarketService.getUserDetailed( appId, userId );
    }

}
