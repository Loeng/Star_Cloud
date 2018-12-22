package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 应用开通相关操作接口
 * @author BTW
 * @version 0.1
 * @since 2018/12/18 17:13
 */
@Slf4j
@Api( value = "应用开通相关操作接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping("/app-open")
public class AppOpenController {

    /**
     * 用户开通应用查询接口
     * @param userId 查询的用户Id
     * @return 用户收藏应用信息集合
     */
    @ApiOperation( value = "用户开通应用查询接口", notes = "根据用户id查询开通应用信息", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "userId", dataType = "String", value = "用户Id", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping("/{userId}")
    @ResponseBody
    public RestRecord getUserAppOpenList ( @PathVariable( "userId" ) String userId ) {
        return null;
    }

    /**
     * 用户开通应用新增接口
     * @param userId 用户Id
     * @param appId  开通的应用Id
     * @return 开通应用是否成功
     */
    @ApiOperation( value = "用户开通应用接口", notes = "用户开通选中的应用", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "userId", dataType = "String", value = "用户Id", paramType = "query", required = true ),
            @ApiImplicitParam( name = "appId", dataType = "String", value = "应用Id", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord addUserAppOpen ( @RequestParam( "userId" ) String userId,
                                             @RequestParam( "appId" ) String appId ) {
        return null;
    }

    /**
     * 用户开通应用删除接口
     * @param userId 用户Id
     * @param appId  待删除的开通应用Id
     * @return 删除开通应用是否成功
     */
    @ApiOperation( value = "用户取消开通应用接口", notes = "用户删除选中的开通应用", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "userId", dataType = "String", value = "用户Id", paramType = "path", required = true ),
            @ApiImplicitParam( name = "appId", dataType = "String", value = "应用Id", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @DeleteMapping("/{userId}/{appId}")
    @ResponseBody
    public RestRecord deleteUserAppOpen ( @RequestParam( "userId" ) String userId,
                                                @RequestParam( "appId" ) String appId ) {
        return null;
    }
}
