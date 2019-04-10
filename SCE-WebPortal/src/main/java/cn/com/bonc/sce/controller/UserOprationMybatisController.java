package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.UserOperationMybatisService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Api( value = "用户信息增删改相关接口", tags = "用户相关接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping( "/user-info-mybatis" )
public class UserOprationMybatisController {

    @Autowired
    private UserOperationMybatisService userOperationMybatisService;

    @PutMapping( "/updateUserInfo" )
    @ResponseBody
    public RestRecord updateUserInfoById( @RequestBody Map< String, Object > userInfo ) {
        return userOperationMybatisService.updateUserInfoById( userInfo );
    }

    @PutMapping( "/updatePassword" )
    @ResponseBody
    public RestRecord updatePassword( @RequestBody Map< String, Object > info ) {
        return userOperationMybatisService.updatePassword( info );
    }

    /**
     * 用户头像修改接口
     *
     * @param resourceId
     * @param userId     用户id
     * @return
     */
    @ApiOperation( value = "用户头像修改接口", notes = "用户头像修改接口", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "resourceId", value = "头像图片ID", paramType = "query" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping( "/updateUserHeadPortrait" )
    @ResponseBody
    public RestRecord updateUserHeadPortrait( @RequestParam( "resourceId" ) Integer resourceId,
                                              @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return userOperationMybatisService.updateUserHeadPortrait( userId, resourceId );
    }

}
