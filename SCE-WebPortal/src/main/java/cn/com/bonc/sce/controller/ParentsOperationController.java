package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.ParentsInfo;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.ParentsOperationService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
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
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = WebMessageConstants.SCE_PORTAL_MSG_409, response = RestRecord.class )
    } )
    @GetMapping( "/{phone}" )
    @ResponseBody
    public RestRecord getSecurityValidInfo( HttpServletRequest request, @PathVariable( "phone" ) @ApiParam( name = "phone", value = "手机号", required = true ) String phone){
        return parentsOperationService.sendSecurityPhoneValid(phone);
    }

    /**
     * 添加家长信息
     *
     * @param parentsInfo 家长信息
     * @return 添加结果
     */
    @ApiOperation( value = "添加家长信息", notes = "添加家长信息", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 411, message = WebMessageConstants.SCE_PORTAL_MSG_411, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertParentsInfo( @RequestBody @ApiParam( name = "parentsInfo", value = "用户信息", required = true )ParentsInfo parentsInfo){
        return parentsOperationService.insertParentsInfo( parentsInfo );
    }

    /**
     * 用户注册
     *
     * @param info 注册信息
     * @return 添加结果
     */
    @ApiOperation( value = "用户注册", notes = "用户注册", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 411, message = WebMessageConstants.SCE_PORTAL_MSG_411, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping("/free")
    @ResponseBody
    public RestRecord insertUsersInfo( @RequestBody @ApiParam( name = "info", value = "注册信息", required = true )ParentsInfo info){
        return parentsOperationService.insertUsersInfo( info );
    }

    /**
     * 获取审核列表
     *
     * @return 结果
     */
    @ApiOperation( value = "获取审核列表", notes = "获取审核列表", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 411, message = WebMessageConstants.SCE_PORTAL_MSG_411, response = RestRecord.class ),
            @ApiResponse( code = 420, message = WebMessageConstants.SCE_PORTAL_MSG_420, response = RestRecord.class )
    } )
    @GetMapping("/examine")
    @ResponseBody
    public RestRecord getExamine(@RequestParam( value = "pageNum", required = false, defaultValue = "1"  ) @ApiParam( name = "pageNum", value = "页码")Integer pageNum,
                                 @RequestParam( value = "pageSize", required = false, defaultValue = "10"  ) @ApiParam( name = "pageSize", value = "数量")Integer pageSize){
        return parentsOperationService.getExamine(pageNum,pageSize);
    }

    /**
     * 审核通过
     *
     * @param list 通过列表
     * @return 结果
     */
    @ApiOperation( value = "审核通过", notes = "审核通过", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 411, message = WebMessageConstants.SCE_PORTAL_MSG_411, response = RestRecord.class ),
            @ApiResponse( code = 421, message = WebMessageConstants.SCE_PORTAL_MSG_421, response = RestRecord.class )
    } )
    @PostMapping("/examine")
    @ResponseBody
    public RestRecord examine(@RequestBody @ApiParam( name = "list", value = "审核通过列表", required = true )List<String> list){
        return parentsOperationService.examine( list );
    }
}
