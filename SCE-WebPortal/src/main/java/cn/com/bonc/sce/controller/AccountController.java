package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.Account;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AccountService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 账号安全信息相关
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Slf4j
@RestController
@Api( value = "账号安全信息相关", tags = "账号安全信息相关" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RequestMapping( "/account-security" )
public class AccountController {

    private static final String UNDEFINED = "undefined";

    @Autowired
    private AccountService accountSecurityService;

    /**
     * 发送安全验证信息
     *
     * @param phone 手机号
     * @return 验证码
     */
    @ApiOperation( value = "发送安全验证信息", notes = "账号发送安全验证", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = WebMessageConstants.SCE_PORTAL_MSG_409, response = RestRecord.class )
    } )
    @GetMapping( "/send-security-phone-valid/{phone}" )
    @ResponseBody
    public RestRecord sendSecurityPhoneValid( @PathVariable( "phone" ) @ApiParam( name = "phone", value = "手机号" ,required = true) String phone){
        return accountSecurityService.sendSecurityPhoneValid(phone);
    }

    /**
     * 验证安全信息
     *
     * @param phone 手机号
     * @param valid 验证码
     * @return 验证结果和安全码
     */
    @ApiOperation( value = "验证安全信息", notes = "验证安全信息", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 411, message = WebMessageConstants.SCE_PORTAL_MSG_411, response = RestRecord.class )
    } )
    @GetMapping( "/{phone}/{valid}" )
    @ResponseBody
    public RestRecord validInfo(@PathVariable( "phone" ) @ApiParam( name = "phone", value = "手机号" ,required = true) String phone,
                                @PathVariable( "valid" ) @ApiParam( name = "valid", value = "验证码" ,required = true) String valid){
        if( StringUtils.isEmpty( phone )|| UNDEFINED.equals( phone ) ||
                StringUtils.isEmpty( valid )|| UNDEFINED.equals( valid ) ){
            return new RestRecord(500,"error");
        }
        return accountSecurityService.validInfo(phone,valid);
    }

    /**
     * 修改账号信息
     *
     * @param accountSecurity 安全码和账号信息（或者是用户id、用户名和密码）
     * @return 修改结果
     */
    @ApiOperation( value = "修改账号信息", notes = "修改账号信息", httpMethod = "POST" )
    @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 412, message = WebMessageConstants.SCE_PORTAL_MSG_412, response = RestRecord.class )
    } )
    @PostMapping("")
    @ResponseBody
    public RestRecord updateAccount(@RequestBody @ApiParam( name = "accountSecurity", value = "安全码和账号信息（或者是用户id、用户名和密码）", required = true ) Account accountSecurity,@CurrentUserId @ApiParam( hidden = true) String userId){
        accountSecurity.setUserId( userId );
        return accountSecurityService.updateAccount(accountSecurity);
    }
}
