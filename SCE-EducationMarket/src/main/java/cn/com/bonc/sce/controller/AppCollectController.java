package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.AppRecommend;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppCollectService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 应用收藏相关操作接口
 *
 * @author BTW
 * @version 0.1
 * @since 2018/12/18 17:13
 */
@Slf4j
@Api( value = "应用收藏相关操作接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping("/app-collect")
public class AppCollectController {
    private AppCollectService appCollectService;

    @Autowired
    public AppCollectController ( AppCollectService appCollectService ) {
        this.appCollectService = appCollectService;
    }

    /**
     * 用户收藏应用查询接口
     * @param userId 查询的用户Id
     * @return 用户收藏应用信息集合
     */
    @ApiOperation( value = "用户收藏应用查询接口", notes = "根据用户id查询收藏应用信息", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "userId", dataType = "String", value = "用户Id", paramType = "query", required = true ),
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping("/list")
    @ResponseBody
    public RestRecord getUserAppCollection ( @RequestParam( "userId" ) String userId1,
                                             @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return appCollectService.getUserAppCollect(userId);
    }

    /**
     * 用户收藏应用新增接口
     * @return 添加应用收藏是否成功
     */
    @ApiOperation( value = "用户添加收藏应用接口", notes = "用户收藏选中的应用", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", dataType = "String", value = "应用Id", paramType = "query", required = true ),
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord addUserAppCollection ( @CurrentUserId @ApiParam( hidden = true ) String userId,
                                             @RequestBody AppRecommend appRecommend ) {
        return appCollectService.addUserAppCollectionInfo( userId, appRecommend.getAppId() );
    }

    /**
     * 用户收藏应用删除接口
     * @param userId 用户Id
     * @param appId  待删除的收藏应用Id
     * @return 删除应用收藏是否成功
     */
    @ApiOperation( value = "用户删除收藏应用接口", notes = "用户删除选中的收藏应用", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", dataType = "String", value = "应用Id", paramType = "path", required = true ),
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @DeleteMapping("/{appId}")
    @ResponseBody
    public RestRecord deleteUserAppCollection ( @CurrentUserId @ApiParam( hidden = true ) String userId,
                                                @PathVariable( "appId" ) String appId ) {
        return appCollectService.deleteUserAppCollectionInfo( userId, appId );
    }

}
