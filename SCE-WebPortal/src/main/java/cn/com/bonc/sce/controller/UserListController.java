package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.UserListService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author jc_D
 * @description 用户列表查询相关接口：全部、分角色、自定义查询
 * @date 2018/12/15
 **/
@Slf4j
@Api( value = "用户列表查询相关接口", tags = "用户列表查询相关接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping( "/user-list" )
public class UserListController {

    @Autowired
    private UserListService userListService;


    /**
     * 根据角色id查询用户信息
     *
     * @param roleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation( value = "根据角色id查询用户", notes = "根据角色id查询用户", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "roleId", value = "角色ID", paramType = "query", required = true ),
            @ApiImplicitParam( name = "pageNum", value = "页码", paramType = "query", required = false, defaultValue = "1" ),
            @ApiImplicitParam( name = "pageSize", value = "每页显示数量", paramType = "query", required = false, defaultValue = "10" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/role/{roleId}/{pageNum}/{pageSize}" )
    public RestRecord getUserInfoByRole( @PathVariable String roleId,
                                         @PathVariable( "pageNum" ) Integer pageNum,
                                         @PathVariable( "pageSize" ) Integer pageSize ) {

        return userListService.getUserInfoByRole( roleId, pageNum, pageSize );
    }


    /**
     * 根据条件查询
     *
     * @param conditionMap
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation( value = "根据条件查询", notes = "根据条件查询", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "查询条件", value = "查询条件", paramType = "body", required = true ),
            @ApiImplicitParam( name = "pageNum", value = "页码", paramType = "query", required = true ),
            @ApiImplicitParam( name = "pageSize", value = "每页显示数量", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/condition/{pageNum}/{pageSize}" )
    public RestRecord getUserInfoByCondition( @RequestBody Map conditionMap,
                                              @PathVariable( "pageNum" ) Integer pageNum,
                                              @PathVariable( "pageSize" ) Integer pageSize ) {

        return userListService.getUserInfoByCondition( conditionMap, pageNum, pageSize );
    }
}
