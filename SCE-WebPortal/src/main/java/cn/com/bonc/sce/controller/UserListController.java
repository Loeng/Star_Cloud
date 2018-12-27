package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.UserListService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;
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
    @ApiOperation( value = "根据角色id和搜索条件查询用户", notes = "根据角色id和搜索条件查询用户", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PostMapping( "/role/{roleId}/{pageNum}/{pageSize}" )
    public RestRecord getUserInfoByRole( @PathVariable( "roleId" )  @ApiParam( name = "roleId", value = "角色ID",required = true ) Integer roleId,
                                         @PathVariable( "pageNum" )  @ApiParam( name = "pageNum", value = "页码",required = true )Integer pageNum,
                                         @PathVariable( "pageSize" ) @ApiParam( name = "pageSize", value = "页数大小",required = true ) Integer pageSize,
                                         @RequestBody(required = false)  @ApiParam( name = "condition", value = "用户参数",required = true )Map<String,Object> condition) {
        return userListService.getUserInfoByRole( roleId, pageNum, pageSize,condition );
    }


    /**
     * 根据条件查询
     *
     * @param conditionMap
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation( value = "根据条件查询", notes = "根据条件查询", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PostMapping( "/condition/{pageNum}/{pageSize}" )
    public RestRecord getUserInfoByCondition( @RequestBody( required = false ) Map conditionMap,
                                              @PathVariable( "pageNum" ) Integer pageNum,
                                              @PathVariable( "pageSize" ) Integer pageSize ) {

        return userListService.getUserInfoByCondition( conditionMap, pageNum, pageSize );
    }
}
