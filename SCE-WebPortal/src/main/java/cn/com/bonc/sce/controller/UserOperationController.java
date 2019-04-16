package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.UserModel;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.UserOperationService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户信息增删改相关接口
 *
 * @author BTW
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Slf4j
@Api( value = "用户信息增删改相关接口", tags = "用户相关接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping( "/user-info" )
public class UserOperationController {

    @Autowired
    private UserOperationService userOperationService;

   /* @Autowired
    public UserOperationController( UserOperationService userOperationService ) {
        this.userOperationService = userOperationService;
    }*/

    /**
     * 单个用户新增接口1
     *
     * @param map 新增用户信息
     * @return 添加用户是否成功
     */
    @ApiOperation( value = "单个用户添加接口", notes = "添加单个用户", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 0, message = WebMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )
    @PostMapping( "/addUser/{roleId}" )
    @ResponseBody
    public RestRecord addUserInfo(
            @PathVariable( "roleId" ) @ApiParam( name = "roleId", value = "角色ID", required = true ) Integer roleId,
            @RequestBody @ApiParam( example = "{'userId':1231,'userName':'loader','address':'成都市青羊区'...}" ) Map map ) {
        return userOperationService.addUserInfo( roleId, map );
    }

    /**
     * 用户信息更改接口
     *
     * @param userInfo 需更改的用户具体信息
     * @return 更改用户信息是否成功
     */
    @ApiOperation( value = "用户信息更改接口", notes = "根据用户Id更改用户信息", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "userInfo", dataType = "String", value = "需更改的具体用户信息", paramType = "body", required = true ),
            @ApiImplicitParam( name = "userId", dataType = "String", value = "需更改的用户ID", paramType = "query", required = true )
    }
    )
    @ApiResponses( {
            @ApiResponse( code = 0, message = WebMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )

    @PutMapping( "/updateUserInfo" )
    @ResponseBody
    public RestRecord updateUserInfoById(
            @RequestBody Map< String, Object > userInfo,
            @RequestParam( "userId" ) String userId ) {
        return userOperationService.updateUserInfoById( userInfo, userId );
    }

    /**
     * 用户信息删除接口
     *
     * @param userId 需删除的用户id
     * @return 删除用户信息是否成功
     */
    @ApiOperation( value = "用户信息删除接口", notes = "根据Id删除对应用户信息及相关关联表中信息", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "userId", dataType = "String", value = "删除的用户ID", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = WebMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )
    @DeleteMapping( "/{userId}" )
    @ResponseBody
    public RestRecord deleteUserInfoById( @PathVariable( "userId" ) String userId ) {
        // 1. 将用户表中状态改为已删除

        // 2. 将所有相关关系表中该用户信息删除
        return userOperationService.deleteUserInfoById( userId );
    }

    /**
     * 根据用户名查询账号名称
     *
     * @param userName 需查询用户名
     * @return 账号名称
     */
    @ApiOperation( value = "根据用户名查询账号名称", notes = "根据Id查询对应用户信息及相关关联表中信息", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "userName", dataType = "String", value = "用户名", paramType = "path", required = true )
    } )
    @GetMapping( "/{userName}" )
    @ResponseBody
    public RestRecord selectUserInfoByName( @PathVariable( "userName" ) String userName ) {

        return userOperationService.selectUserInfoByName( userName );
    }

    @ApiOperation( value = "添加单个用户", notes = "通过控制接口参数，添加不同角色的用户", httpMethod = "POST" )
    @PostMapping( "/insert" )
    @ResponseBody
    public RestRecord insertUser( @RequestBody @ApiParam( "用户对象" ) UserModel userModel ) {
        return userOperationService.insertUser( userModel );
    }

    @ApiOperation( value = "查询审核状态接口", notes = "查询审核状态", httpMethod = "GET" )
    @GetMapping( "/getAuditStatusByEntityId/{id}/{roleId}" )
    @ResponseBody
    public RestRecord getAuditStatusByEntityId( @PathVariable( "id" ) String id,@PathVariable( "roleId" ) Integer roleId  ) {

        return userOperationService.getAuditStatusByEntityId( id ,roleId);
    }
}
