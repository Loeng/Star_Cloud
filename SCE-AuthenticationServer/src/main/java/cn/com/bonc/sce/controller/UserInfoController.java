package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.LoginService;
import cn.com.bonc.sce.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    public RestRecord login( HttpServletRequest request,@ApiParam() @PathVariable( "userId" ) String userId ) {
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, userService.getUserByUserId( userId ) );
    }
}
