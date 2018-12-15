package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.rest.RestRecord;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jc_D
 * @description 用户列表相关接口：全部、分角色、自定义查询
 * @date 2018/12/15
 **/
@Slf4j
@Api( value = "用户列表相关接口", tags = "用户列表相关接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping( "/userList" )
public class UserListController {
    //全部用户
    @ApiOperation( value = "查询全部用户接口", notes = "查询全部用户接口", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/all" )
    @ResponseBody
    public String getAllUserInfo() {
        List< User > userList = new ArrayList<>();
        userList.add( new User() );
        String users = JSONUtil.parseArray( userList ).toString();
        return users;
    }

    //根据角色id查询用户信息
    @ApiOperation( value = "根据角色id查询用户", notes = "根据角色id查询用户", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "roleId", value = "角色ID", paramType = "query", required = true ),
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/role/{roleId}" )
    @ResponseBody
    public String getUserInfoByRole( @PathVariable String roleId ) {
        List< User > userList = new ArrayList<>();
        userList.add( new User() );
        String users = JSONUtil.parseArray( userList ).toString();
        return users;
    }


    //根据条件查询
    @ApiOperation( value = "根据条件查询", notes = "根据条件查询", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "查询条件", value = "查询条件", paramType = "query", required = true ),
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/condition" )
    @ResponseBody
    public String getUserInfoByCondition( @RequestParam Map conditionMap ) {
        List< User > userList = new ArrayList<>();
        userList.add( new User() );
        String users = JSONUtil.parseArray( userList ).toString();
        return users;
    }
}
