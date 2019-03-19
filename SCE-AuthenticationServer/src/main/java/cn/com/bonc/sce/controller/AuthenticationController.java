package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.DateConstants;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.encrypt.AppSecretAutoManageService;
import cn.com.bonc.sce.encrypt.SingleInstanceAppSecretAutoManageService;
import cn.com.bonc.sce.encrypt.UnstandardEncryptedDataException;
import cn.com.bonc.sce.exception.UnsupportedAuthenticationTypeException;
import cn.com.bonc.sce.model.SSOAuthentication;
import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AuthenticationService;
import cn.com.bonc.sce.service.LoginService;
import cn.com.bonc.sce.service.UserService;
import cn.com.bonc.sce.tool.RestApiUtil;
import cn.com.bonc.sce.utils.GeneratorVerifyCode;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
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

    @Value("{sce.appId}")
    private String appId;

    @Value("{sce.appToken}")
    private String appToken;

    private UserService userService;
    private LoginService loginService;
    private AppSecretAutoManageService appSecretAutoManageService;
    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController( UserService userService, LoginService loginService, SingleInstanceAppSecretAutoManageService singleInstanceAppSecretAutoManageService, AuthenticationService authenticationService ) {
        this.userService = userService;
        this.loginService = loginService;
        this.appSecretAutoManageService = singleInstanceAppSecretAutoManageService;
        this.authenticationService = authenticationService;
    }

    /**
     * #TODO 理应删除 loginType 字段，并且为每种登录方式单独创建接口。
     */
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
        User authenticatedUser;

        /*
         * 1. 检查应用Id与应用Token
         */
        if( authentication.getAppId() == null || authentication.getAppToken() == null ){
            authentication.setAppId( this.appId );
            authentication.setAppToken( this.appToken );
        }else {
            if( !authentication.getAppToken().equals(authenticationService.getAppToken(authentication.getAppId()))){
                log.info("应用Id或应用Token无效");
                return new RestRecord( 152, WebMessageConstants.SCE_WEB_MSG_152);
            }
        }

        /*
         * 2. 判断用户的登录类型是否支持，并查找是否存在匹配的用户数据
         */
        try {
            authenticatedUser = loginService.getUserInfo( authentication );
        } catch ( UnsupportedAuthenticationTypeException e ) {
            log.warn( MessageConstants.SCE_MSG_1000, request.getRemoteAddr(), authentication.getAuthType() );
            return new RestRecord( 100, WebMessageConstants.SCE_PORTAL_MSG_100 );
        }
        if ( authenticatedUser != null ) {
            log.info( MessageConstants.SCE_MSG_1001, authenticatedUser.getUserId(), RestApiUtil.getIpAddr( request ) );
        }

        /*
         * 3. 检查用户的登录信息是否匹配
         */
        // 查找不到用户数据
        if ( authenticatedUser == null ) {
            return new RestRecord( 101, WebMessageConstants.SCE_PORTAL_MSG_101 );
        } else {
            // 密码不匹配
            if ( !authentication.getPassword().equals( authenticatedUser.getAccount().getPassword() ) ) {
                return new RestRecord( 101, WebMessageConstants.SCE_PORTAL_MSG_101 );
            }
            // 验证账户是否停用
            if ( authenticatedUser.getLoginPermissionStatus() == 0 ) {
                return new RestRecord( 103, WebMessageConstants.SCE_PORTAL_MSG_103 );
            }
            // 验证账户是否已注销
            if ( authenticatedUser.getIsDelete() == 0 ) {
                return new RestRecord( 104, WebMessageConstants.SCE_PORTAL_MSG_104 );
            }
        }

        /*
         * 4. 生成登录信息
         */
        Map loginResult = loginService.generateLoginResult( authenticatedUser, new Date( System.currentTimeMillis() + DateConstants.THIRTY_MINUTE ) );

        /*
         * 5. 更新用户登录状态（是否首次登录）
         * 以 2019.03.04 的系统设计来看，所有除系统管理员外的账户类型都需要在初次登陆时进行用户数据验证。理应当用户数据校验完成
         * 后由前台发起用户认证状态(首次登录完成、用户信息确认完成、初登陆密码修改完成等)的修改请求。故此处暂不做任何处理
         */
        // loginService.confirmUserFirstLogin( authenticatedUser );

        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, loginResult );
    }

    /**
     * 生成并获取验证码
     *
     * @Return 验证码图片路径
     */
    @ApiOperation( value = "验证码生成接口", notes = "验证码生成接口", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping( "/generator" )
    @ResponseBody
    public RestRecord generator() throws IOException {
        GeneratorVerifyCode generator = new GeneratorVerifyCode();
        BASE64Encoder encoder = new BASE64Encoder();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Map< String, Object > info = new HashMap<>();
        try {
            log.info( "开始调用验证码生成器。。。" );
            Map< String, Object > verifyCode = generator.drawCode( os );
            String code = appSecretAutoManageService.encryptData( verifyCode.get( "code" ).toString() );
            ImageIO.write( ( BufferedImage ) verifyCode.get( "image" ), "jpg", os );
            byte[] bytes = os.toByteArray();
            String images = encoder.encode( bytes ).trim().replaceAll( "\n", "" ).replaceAll( "\r", "" );
            info.put( "status", "success" );
            info.put( "verify_code", code );
            info.put( "images", images );
            info.put( "generator_date", verifyCode.get( "generator_date" ) );
            log.info( "图形验证码生成成功，验证码信息:{}", verifyCode );
        } catch ( IOException e ) {
            info.put( "status", "fail" );
            log.error( "验证码生成失败，异常信息:{}", e );
        } finally {
            os.flush();
            os.close();
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, info );
    }

    /**
     * 验证码输入验证
     */
    @ApiOperation( value = "验证码验证接口", notes = "验证码验证接口", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "input_code", value = "用户输入的验证码", paramType = "query", required = true ),
            @ApiImplicitParam( name = "verify_code", value = "生成的验证码", paramType = "query", required = true ),
            @ApiImplicitParam( name = "generator_date", value = "生成验证码的时间", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping( "/verify" )
    public RestRecord verify( @RequestParam( "input_code" ) String input_code, @RequestParam( "verify_code" ) String verify_code, @RequestParam( "generator_date" ) long generator_date ) {
        try {
            String real_code = appSecretAutoManageService.decryptData( verify_code );
            log.info( "解密之后的验证码信息：{}", real_code );
            if ( StringUtils.isEmpty( input_code ) ) {
                log.info( "验证码为空,请重新输入!" );
                return new RestRecord( 500, WebMessageConstants.SCE_PORTAL_MSG_410, "验证码为空，请重新输入!" );
            } else if ( ( System.currentTimeMillis() - generator_date ) / 1000 / 60 > 2 ) {
                log.info( "验证码已失效，请刷新页面重新获取" );
                return new RestRecord( 500, WebMessageConstants.SCE_PORTAL_MSG_410, "验证码已失效，请刷新页面重新获取!" );
            } else if ( input_code.equals( real_code ) ) {
                log.info( "验证成功!" );
                return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, "验证成功" );
            } else {
                log.info( "验证失败!" );
                return new RestRecord( 500, WebMessageConstants.SCE_PORTAL_MSG_410, "验证失败" );
            }
        } catch ( UnstandardEncryptedDataException e ) {
            log.error( "解密验证码{}失败，异常信息为：{}", verify_code, e );
            return new RestRecord( 500, WebMessageConstants.SCE_PORTAL_MSG_412, "解密失败，验证异常,请重试!" );
        }

    }

    /**
     * 长期 ticket 换取30秒 temp_token 接口 （同时更新ticket时间）
     * @param userId 用户id
     * @param response response.header 存放ticket
     * @return RestRecord
     */
    @GetMapping( "/temp_token" )
    public RestRecord temp_token(@CurrentUserId String userId, HttpServletResponse response){
        if(userId.equals("")){
            return new RestRecord(150, WebMessageConstants.SCE_WEB_MSG_150);
        }
        User user = userService.getUserByUserId(userId);
        String temp_token = loginService.generateTicket( user, new Date( System.currentTimeMillis() + DateConstants.THIRTY_SECOND ) );
        response.addHeader("temp_token", temp_token);
        String ticket = loginService.generateTicket( user, new Date( System.currentTimeMillis() + DateConstants.THIRTY_SECOND ) );
        response.addHeader("authentication", ticket);
        System.out.println("temp_token = " + ticket);
        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200);
    }

    /**
     * 临时token(30秒)换取正式ticket(3分钟)接口
     * @param userId 用户id
     * @return RestRecord
     */
    @GetMapping( "/ticket" )
    public RestRecord ticket(@CurrentUserId String userId, HttpServletResponse response){
        if(userId.equals("")){
            return new RestRecord(150, WebMessageConstants.SCE_WEB_MSG_150);
        }
        User user = userService.getUserByUserId(userId);
        String ticket = loginService.generateTicket( user, new Date( System.currentTimeMillis() + DateConstants.THREE_MINUTE ) );
        response.addHeader("authentication", ticket);
        System.out.println("ticket = " + ticket);
        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200);
    }

    /**
     * 旧的ticket换取新的ticket
     * @return RestRecord
     */
    @GetMapping( "/refresh" )
    public RestRecord refresh(@CurrentUserId String userId, HttpServletResponse response){
        if(userId.equals("")){
            return new RestRecord(150, WebMessageConstants.SCE_WEB_MSG_150);
        }
        User user = userService.getUserByUserId(userId);
        String ticket = loginService.generateTicket( user, new Date( System.currentTimeMillis() + DateConstants.THREE_MINUTE ) );
        response.addHeader("authentication", ticket);
        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200);
    }



}
