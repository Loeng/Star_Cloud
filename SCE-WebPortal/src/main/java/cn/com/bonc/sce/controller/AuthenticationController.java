package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
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
@Api( value = "登录/授权接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RequestMapping( "/authentication" )
@RestController
public class AuthenticationController {

    /**
     * 用户登录接口
     *
     * @param userIdentifier 用户身份验证信息,可以是
     * @return 用户成功登陆则返回用户的登录状态，否则
     */
    @ApiOperation( value = "用户登录接口", notes = "用戶名/邮箱/手机号登录都需要走此接口", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authType", dataType = "String", value = "验证类型 : 0/用户名密码登录，1/手机号登录, 2/邮箱登录", paramType = "POST-Body", required = true, example = "xxx@bonc.com.cn", allowableValues = "0,1,2" ),
            @ApiImplicitParam( name = "userIdentifier", dataType = "Number", value = "用户身份识别信息，和 authType 必须匹配，如 authType 为 0 则改值必须是用户名", paramType = "POST-Body", required = true ),
            @ApiImplicitParam( name = "encryptedPassword", value = "加密后的用户登录密码", paramType = "POST-body", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 100, message = PortalMessageConstants.SCE_PORTAL_MSG_100, response = RestRecord.class )
    } )
    @PostMapping( produces = "application/json" )
    @ResponseBody
    public RestRecord userLogin(
            @RequestParam @Max( value = 3, message = PortalMessageConstants.SCE_PORTAL_MSG_101 ) @Min( value = 0, message = PortalMessageConstants.SCE_PORTAL_MSG_101 ) Integer authType,
            @RequestParam @NotEmpty String userIdentifier,
            @RequestParam @NotEmpty String encryptedPassword ) {
        return null;
    }
}
