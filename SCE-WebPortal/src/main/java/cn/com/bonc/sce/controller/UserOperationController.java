package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MarketMessageConstants;
import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.UserOperationService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息增删改相关接口
 *
 * @author BTW
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Slf4j
@Api( value = "用户信息增删改相关接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping("/userOperation")
public class UserOperationController {
    private UserOperationService userOperationService;

    @Autowired
    public UserOperationController ( UserOperationService userOperationService ) {
        this.userOperationService = userOperationService;
    }

    /**
     * 单个用户新增接口
     * @param user 新增用户信息
     * @return 添加用户是否成功
     */
    @ApiOperation( value = "单个用户添加接口", notes = "添加单个用户", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "user", dataType = "User", value = "新增单个用户信息", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = PortalMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord addUserInfo ( User user ) {
        return null;
    }

    /**
     * 用户信息更改接口
     * @param userId 需更改的用户id
     * @param userInfo 需更改的用户具体信息
     * @return 更改用户信息是否成功
     */
    @ApiOperation( value = "用户信息更改接口", notes = "根据用户Id更改用户信息", httpMethod = "PATCH" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "userId", dataType = "String", value = "需更改的用户id", paramType = "path", required = true ),
            @ApiImplicitParam( name = "userInfo", dataType = "String", value = "需更改的具体用户信息", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = PortalMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )
    @PatchMapping("/{userId}")
    @ResponseBody
    public RestRecord updateAppTypeName ( @PathVariable( "userId" ) String userId,
                                          @RequestParam( "userInfo" ) String userInfo ) {
        return null;
    }

    /**
     * 用户信息删除接口
     * @param userId 需删除的用户id
     * @return 删除用户信息是否成功
     */
    @ApiOperation( value = "用户信息删除接口", notes = "根据Id删除对应用户信息及相关关联表中信息", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "userId", dataType = "String", value = "删除的用户ID", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = PortalMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )
    @DeleteMapping("/{userId}")
    @ResponseBody
    public RestRecord deleteAppTypeById ( @PathVariable( "userId" ) String userId ) {
        // 1. 将用户表中状态改为已删除
        // 2. 将所有相关关系表中该用户信息删除
        return null;
    }
}
