package cn.com.bonc.sce.authentication;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.SSOAuthentication;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

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
    private static final char AUTH_TYPE_0 = '0';
    private static final char AUTH_TYPE_1 = '1';
    private static final char AUTH_TYPE_2 = '2';

    @ApiOperation( value = "用户登录接口", notes = "用戶名/邮箱/手机号登录都需要走此接口", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "使用" )
    } )
    @PostMapping( produces = "application/json" )
    @ResponseBody
    public RestRecord login( HttpServletRequest request, @NotBlank @RequestBody @ApiParam SSOAuthentication authentication ) {
        boolean unSupportedAuthType = false;

        /*
         * 验证数据有效性并验证用户登录
         */
        if ( authentication.getAuthType() == AUTH_TYPE_0 ) {

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

        /*
         * 生成 jwt ticket
         */

        return null;
    }
}
