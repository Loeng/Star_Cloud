package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.LoginService;
import cn.com.bonc.sce.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/21 16:45
 */
@Slf4j
@Api( tags = "用户数据获取接口" )
@RequestMapping( "/users" )
@RestController
public class UserInfoController {

    private UserService userService;
    private LoginService loginService;

    @Autowired
    public UserInfoController( UserService userService, LoginService loginService ) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @ApiOperation( value = "用户数据查询接口", notes = "使用 login_name 进行登录", httpMethod = "GET" )
    @GetMapping( value = "/{userId}", produces = "application/json" )
    @ResponseBody
    public RestRecord getUserInfoByUserId( HttpServletRequest request, @ApiParam() @PathVariable( "userId" ) String userId ) {
        User user = userService.getUserByUserId( userId );
        userService.clearSensitiveInformation( user );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, user );
    }

    /**
     * 获取用户
     *
     * @return 获取用户
     */
    @ApiOperation( value = "获取用户", notes = "获取用户", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping("/detailed/{userId}")
    @ResponseBody
    public RestRecord getUserInfo(@PathVariable( "userId" ) @ApiParam( name = "userId", value = "用户id") String userId) {
        return userService.getUserInfo(userId);
    }

    /**
     * 修改用户
     *
     * @return 修改用户
     */
    @ApiOperation( value = "修改用户", notes = "修改用户", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @PostMapping("/detailed")
    @ResponseBody
    public RestRecord updateUserInfo( @RequestBody @ApiParam( name = "user", value = "用户") User user) {
        return userService.updateUserInfo(user);
}
}
