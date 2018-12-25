package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.SSOAuthentication;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.UserService;
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
    /**
     * 本系统支持的登录类型
     */
    private static final int AUTH_TYPE_0 = 0;
    private static final int AUTH_TYPE_1 = 1;
    private static final int AUTH_TYPE_2 = 2;

    private UserService userService;


    @ApiOperation( value = "用户登录接口", notes = "用戶名/邮箱/手机号登录都需要走此接口", httpMethod = "POST" )
    @ApiResponses( value = {
            @ApiResponse( code = 100, message = WebMessageConstants.SCE_PORTAL_MSG_100 + "样例数据：{\"msg\":\"不支持的登录类型\",\"code\":100}", response = RestRecord.class, examples = @Example( {
                    @ExampleProperty( value = "100", mediaType = "integer" ),
                    @ExampleProperty( value = "go ahead", mediaType = "string" ),
                    @ExampleProperty( value = "some data", mediaType = "object" ),
            } ) )
    } )
    @PostMapping( produces = "application/json" )
    @ResponseBody
    public RestRecord login( HttpServletRequest request, @NotBlank @RequestBody @ApiParam SSOAuthentication authentication ) {
        boolean unSupportedAuthType = false;

        /*
         * 验证数据有效性并验证用户登录
         */
        if ( authentication.getAuthType() == AUTH_TYPE_0 ) {
            log.info( MessageConstants.SCE_MSG_1001, authentication.getIdentifier(), request.getRemoteAddr() );
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, "{\"ticket\":\"MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKxhZpRB7bB37k/M0zd8Cv0VmcP0ALNihACwyZZ9Ziwf9rbnknFNL2l9VUCQ1M0z1Q0yBDjIISGYnqZAE6bmxpy831rcdTsoDG9MHcHt46rMTYGn2uqd9x1CIumA4Qhvpvm2JS1DQygoCfJ+visR42G5EbSCdMT+a1HKMmt1FT2pAgMBAAECgYBYKgzcAQg/fGd/mwPgWeoI3TZi2XZOSTG4ZLMF7CcIpGR70d69YjvAmWC0AB9GwJ7T++Pa4sjHNRbXcuhaMagchTplDB44zLbrUhyCByU2ykdLzN4XBT5i3W4lwOI0egAA9Z8HSVmNzr9o+nod0ZSlN8kfeCuchQK5WLkod91vbQJBANS+aP36+sxOFTxRyLeJYCnCRUrQ+Xx93EwKXWEYOo6Rr3kwujI1RfuOlmp2T7+mtJ+xtcENlqNlHwC/nqE0rX8CQQDPbgcPIEhixa0FAaAlnAr8/GwehPl/tYsD+Ro/2MV5+UqXoHvkg4ebQQEC0huVy2BBXqgZP50f4LviLqwpfHjXAkBVGSFYTteZd7Zo3XIDcJElww8CoVraoGHJjO/TUeTCeKYPPR2Nzd+Dg4LJbS/zQpTHuEcyxOO30lA3dW7XnwGzAkBb1oMZl1l1IcAw7z9QZ34V9EyKVqWAfYhHAgs1KWyFTHJSH6O60OFBQ86GyS+daqX7S0VxqFCQxJdq1O80jv+RAkB40RB0RRwW03KHLVcGLAmrdVyOYm2Hwlq+h1jpmGjHQg5fo8xUbS9oalVFSBQBzGKN+RWHpSoZYDQYv5FaEO90\n\"" );
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

    public static void main( String[] args ) {
    }
}
