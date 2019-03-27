package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.dao.SchoolTeacherDaoforMybatis;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 学校工作台
 * 教师管理-相关接口
 */
@Slf4j
@Api( value = "学校工作台-人员管理-教师管理", tags = "学校工作台-人员管理-教师管理" )
@RestController
@RequestMapping( "/school-teacher-manage" )
public class SchoolTeacherListController {

    @Autowired
    private SchoolTeacherDaoforMybatis schoolTeacherDaoforMybatis;

    /**
     * 查询教师列表
     *
     * @param condition
     * @param userId
     * @return
     */
    @ApiOperation( value = "查询教师列表", notes = "查询教师列表", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @GetMapping( "/list/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getTeacherList( @RequestParam @ApiParam( name = "USER_NAME", value = "用户名" ) Map< String, Object > condition,
                                      @CurrentUserId @ApiParam( hidden = true ) String userId,
                                      @PathVariable( "pageNum" ) Integer pageNum,
                                      @PathVariable( "pageSize" ) Integer pageSize
    ) {
        return schoolTeacherDaoforMybatis.getTeacherList( condition, userId, pageNum, pageSize );
    }

    /**
     * 删除教师
     *
     * @param userId
     * @return
     */
    @ApiOperation( value = "删除教师", notes = "删除教师", httpMethod = "DELETE" )
    @DeleteMapping( "/del/{userId}" )
    @ResponseBody
    public RestRecord deleteTeacher( @PathVariable( "userId" ) String userId ) {
        return schoolTeacherDaoforMybatis.deleteTeacher( userId );
    }

    /**
     * 学校用户
     * 新增教师
     *
     * @return
     */
    @ApiOperation( value = "新增教师", notes = "新增教师", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" ),
    } )
    @PostMapping( "/add" )
    public RestRecord addTeacher( @RequestBody @ApiParam( "{\"USER_NAME\":\"超老师\",\"GENDER\":1,\"CERTIFICATE_NUMBER\":\"身份证号\",\"TEACH_TIME\":1553048511000,\"PHONE_NUMBER\":\"12312321312\",\"POSITION\":\"行政职务\",\"LOGIN_NAME\":\"js_123\",\"PASSWORD\":\"123456\"}" )
                                          Map teacherInfo,
                                  @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return schoolTeacherDaoforMybatis.addTeacher( teacherInfo, userId );
    }

    /**
     * 学校-教师管理
     * 修改教师信息
     *
     * @return
     */
    @ApiOperation( value = "修改教师信息", notes = "修改教师信息", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @PutMapping( "/edit" )
    public RestRecord editTeacher( @RequestBody @ApiParam( "{\"USER_NAME\":\"超老师\",\"GENDER\":1,\"CERTIFICATE_NUMBER\":\"身份证号1\",\"TEACH_TIME\":1553048511000,\"PHONE_NUMBER\":\"12312321312\",\"POSITION\":\"行政职务\",\"LOGIN_NAME\":\"js_123\",\"PASSWORD\":\"1234567\",\"USER_ID\":\"1108629407712542720\"}" )
                                           Map teacherInfo,
                                   @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return schoolTeacherDaoforMybatis.editTeacher( teacherInfo, userId );
    }

}
