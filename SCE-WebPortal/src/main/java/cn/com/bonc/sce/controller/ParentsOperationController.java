package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.model.ParentsInfo;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.ParentsOperationService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 家长操作接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Slf4j
@RestController
@Api( value = "家长操作接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RequestMapping( "/parentsOperation" )
public class ParentsOperationController {
    @Autowired
    private ParentsOperationService parentsOperationService;

    /**
     * 获取安全验证信息
     *
     * @param phone 手机号
     * @return 验证码
     */
    @ApiOperation( value = "获取安全验证信息", notes = "获取安全验证信息", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "phone", value = "手机号", paramType = "header", required = true),
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/{phone}" )
    @ResponseBody
    public RestRecord getSecurityVaildInfo( HttpServletRequest request, @PathVariable( "phone" )String phone){
        RestRecord rr = parentsOperationService.getSecurityVaildInfo(phone);
        request.getSession().setAttribute( "phoneVaild",rr.getMsg() );
        rr.setMsg( null );
        return rr;
    }

    /**
     * 添加家长信息
     *
     * @param parentsInfo 家长信息
     * @param vaild 验证码
     * @return 添加结果
     */
    @ApiOperation( value = "添加家长信息", notes = "添加家长信息", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "parentsInfo", value = "家长信息", paramType = "body", required = true),
            @ApiImplicitParam( name = "vaild", value = "验证码", paramType = "body", required = true),
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertParentsInfo( HttpServletRequest request,ParentsInfo parentsInfo ,String vaild){
        String sessionVaild = (String)request.getSession().getAttribute( "phoneVaild" );
        if(sessionVaild.equals( vaild ))
            return parentsOperationService.insertParentsInfo(parentsInfo);
        return new RestRecord(200,"验证码不正确");
    }
}
