package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.SSOAuthentication;
import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.LoginService;
import cn.com.bonc.sce.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/21 16:45
 */
@Slf4j
@Api( value = "登录/授权接口", tags = "登录/授权接口" )
@RequestMapping( "/authentication" )
@RestController
public class AuthenticationController {
    /**
     * 本系统支持的登录类型
     */
    private static final int AUTH_TYPE_0 = 0;
    private static final int AUTH_TYPE_1 = 1;
    private static final int AUTH_TYPE_2 = 2;

    private UserService userService;
    private LoginService loginService;

    @Autowired
    public AuthenticationController( UserService userService, LoginService loginService ) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @ApiOperation( value = "用户登录接口", notes = "用戶名/邮箱/手机号登录都需要走此接口", httpMethod = "POST" )
    @ApiResponses( value = {
            @ApiResponse( code = 100, message = WebMessageConstants.SCE_PORTAL_MSG_100 + "样例数据：{\"msg\":\"不支持的登录类型\",\"code\":100}", response = RestRecord.class, examples = @Example( {
                    @ExampleProperty( value = "100", mediaType = "integer" ),
                    @ExampleProperty( value = "go ahead", mediaType = "string" ),
                    @ExampleProperty( value = "some data", mediaType = "object" ),
            } ) ),
            @ApiResponse( code = 100, message = WebMessageConstants.SCE_PORTAL_MSG_101 + "样例数据：{\"msg\":\"" + WebMessageConstants.SCE_PORTAL_MSG_101 + "\",\"code\":101}" )
    } )
    @PostMapping( produces = "application/json" )
    @ResponseBody
    public RestRecord login( HttpServletRequest request, @NotBlank @RequestBody @ApiParam SSOAuthentication authentication ) {
        boolean unSupportedAuthType = false;
        User authenticatedUser = null;
        /*
         * 验证数据有效性并验证用户登录
         */
        if ( authentication.getAuthType() == AUTH_TYPE_0 ) {
            log.info( MessageConstants.SCE_MSG_1001, authentication.getIdentifier(), request.getRemoteAddr() );

            authenticatedUser = userService.checkLoginByLoginName( authentication.getIdentifier() );

        } else if ( authentication.getAuthType() == AUTH_TYPE_1 ) {
            unSupportedAuthType = true;
        } else if ( authentication.getAuthType() == AUTH_TYPE_2 ) {
            unSupportedAuthType = true;
        } else {
            unSupportedAuthType = true;
            log.warn( MessageConstants.SCE_MSG_1000, request.getRemoteAddr(), authentication.getAuthType() );
        }

        if ( unSupportedAuthType ) {
            return new RestRecord( 100, WebMessageConstants.SCE_PORTAL_MSG_100 );
        }

        Map< String, String > data;
        /*
         * 如果匹配到用户数据，则生成 ticket
         */
        if ( authenticatedUser == null ) {
            return new RestRecord( 101, WebMessageConstants.SCE_PORTAL_MSG_101 );
        } else {
            if ( !authentication.getPassword().equals( authenticatedUser.getPassword() ) ) {
                return new RestRecord( 102, WebMessageConstants.SCE_PORTAL_MSG_102 );
            }

            data = new HashMap<>( 2 );
            String ticket = loginService.generateTicket( authenticatedUser );
            data.put( "ticket", ticket );
        }

        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, data );
    }
}
