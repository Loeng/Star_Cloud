package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AuthenticationService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * 用于登录验证、授权管理相关
 *
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/12 11:28
 */
@Slf4j
@Api( value = "登录/授权接口", tags = "登录/授权接口", hidden = true )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RequestMapping( "/authentication" )
@RestController
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController( AuthenticationService authenticationService ) {
        this.authenticationService = authenticationService;
    }

    /**
     * 用户登录接口
     *
     * @param userIdentifier 用户身份验证信息,可以是
     * @return 用户成功登陆则返回用户的登录状态，否则
     */
    @ApiOperation( value = "用户登录接口", notes = "用戶名/邮箱/手机号登录都需要走此接口", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authType", dataType = "String", value = "验证类型 : 0/用户名密码登录，1/手机号登录, 2/邮箱登录", paramType = "query", required = true, example = "1", allowableValues = "0,1,2" ),
            @ApiImplicitParam( name = "userIdentifier", dataType = "Number", value = "用户身份识别信息，和 authType 必须匹配，如 authType 为 0 则改值必须是用户名", paramType = "query", required = true ),
            @ApiImplicitParam( name = "encryptedPassword", value = "加密后的用户登录密码", paramType = "query", required = true )
    } )
    /*@ApiResponses( value = {
            @ApiResponse( code = 100, message = WebMessageConstants.SCE_PORTAL_MSG_100, response = RestRecord.class, examples = @Example( {
                    @ExampleProperty( value = "100", mediaType = "integer" ),
                    @ExampleProperty( value = "go ahead", mediaType = "string" ),
                    @ExampleProperty( value = "some data", mediaType = "object" ),
            } ) )
    } )*/
    @PostMapping( produces = "application/json" )
    @ResponseBody
    public RestRecord userLogin(
            @RequestParam @Max( value = 3, message = WebMessageConstants.SCE_PORTAL_MSG_100 ) @Min( value = 0, message = WebMessageConstants.SCE_PORTAL_MSG_100 ) Integer authType,
            @RequestParam @NotEmpty String userIdentifier,
            @RequestParam @NotEmpty String encryptedPassword ) {
        return authenticationService.checkUserIdentityByEmail( userIdentifier, encryptedPassword );
    }

    @PostMapping( value = "/test" ,consumes = "*/*;charset=UTF-8")
    @ResponseBody
    public RestRecord test( @RequestBody User user ) {
        System.out.println( "success" );
        return new RestRecord( 0, "success" );
    }

    @GetMapping( "test" )
    @ResponseBody
    public RestRecord test2() {
        return new RestRecord( 200, "it works" );
    }
}
