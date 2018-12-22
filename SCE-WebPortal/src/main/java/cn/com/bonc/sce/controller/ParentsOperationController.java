package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.model.ParentsInfo;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.ParentsOperationService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@Api( value = "家长操作接口", tags = "家长操作接口" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RequestMapping( "/parentsOperation" )
public class ParentsOperationController {

    private static final int SUCCESS = 200;

    @Autowired
    private ParentsOperationService parentsOperationService;

    /**
     * 获取安全验证信息
     *
     * @param phone 手机号
     * @return 验证码
     */
    @ApiOperation( value = "获取安全验证信息", notes = "获取安全验证信息", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = PortalMessageConstants.SCE_PORTAL_MSG_409, response = RestRecord.class )
    } )
    @GetMapping( "/{phone}" )
    @ResponseBody
    public RestRecord getSecurityValidInfo( HttpServletRequest request, @PathVariable( "phone" ) @ApiParam( name = "phone", value = "手机号", required = true ) String phone){
        RestRecord rr = parentsOperationService.getSecurityVaildInfo(phone);
        /*request.getSession().setAttribute( "phoneValid",rr.getMsg() );
        rr.setMsg( null );*/
        return rr;
    }

    /**
     * 添加家长信息
     *
     * @param parentsInfo 家长信息
     * @param valid 验证码
     * @return 添加结果
     */
    @ApiOperation( value = "添加家长信息", notes = "添加家长信息", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 411, message = PortalMessageConstants.SCE_PORTAL_MSG_411, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertParentsInfo( HttpServletRequest request,@RequestBody @ApiParam( name = "parentsInfo", value = "新闻信息", required = true )ParentsInfo parentsInfo ,String valid){
        String sessionValid = (String)request.getSession().getAttribute( "phoneValid" );
        if( !StringUtils.isEmpty(sessionValid)&&sessionValid.equals( valid )) {
            RestRecord rr = parentsOperationService.insertParentsInfo( parentsInfo );
            if(rr.getCode()==SUCCESS){
                request.getSession().removeAttribute( "phoneValid" );
            }
            return rr;
        }
        return new RestRecord(411,PortalMessageConstants.SCE_PORTAL_MSG_411);
    }
}
