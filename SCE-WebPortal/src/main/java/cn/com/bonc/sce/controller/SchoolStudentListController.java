package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.dao.SchoolStudentDaoforMybatis;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 学校工作台
 * 学生管理-相关接口
 */
@Slf4j
@Api( value = "学校工作台-人员管理-学生管理", tags = "学校工作台-人员管理-学生管理" )
@RestController
@RequestMapping( "/school-student-manage" )
public class SchoolStudentListController {

    @Autowired
    private SchoolStudentDaoforMybatis schoolStudentDaoforMybatis;

    /**
     * 查询学生列表
     *
     * @param condition
     * @param userId
     * @return
     */
    @ApiOperation( value = "查询学生列表", notes = "查询学生列表", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @GetMapping( "/list/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getStudentList( @RequestParam @ApiParam( name = "USER_NAME", value = "用户名" ) Map< String, Object > condition,
                                      @CurrentUserId @ApiParam( hidden = true ) String userId,
                                      @PathVariable( "pageNum" ) Integer pageNum,
                                      @PathVariable( "pageSize" ) Integer pageSize
    ) {
        return schoolStudentDaoforMybatis.getStudentList( condition, userId, pageNum, pageSize );
    }

    /**
     * 删除学生
     *
     * @param userId
     * @return
     */
    @ApiOperation( value = "删除学生", notes = "删除学生", httpMethod = "DELETE" )
    @DeleteMapping( "/del/{userId}" )
    @ResponseBody
    public RestRecord deleteStudent( @PathVariable( "userId" ) String userId ) {
        return schoolStudentDaoforMybatis.deleteStudent( userId );
    }

    /**
     * 学校用户
     * 新增学生
     *
     * @return
     */
    @ApiOperation( value = "新增学生", notes = "新增学生", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" ),
    } )
    @PostMapping( "/add" )
    public RestRecord addStudent( @RequestBody @ApiParam( "{\"USER_NAME\":\"超老师\",\"GENDER\":1,\"CERTIFICATE_NUMBER\":\"身份证号\",\"TEACH_TIME\":1553048511000,\"PHONE_NUMBER\":\"12312321312\",\"POSITION\":\"行政职务\",\"LOGIN_NAME\":\"xs_123\",\"PASSWORD\":\"123456\",\"CLASS_NUMBER\":\"2班\",\"GRADE\":\"3年级\"}" )
                                          Map studentInfo,
                                  @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return schoolStudentDaoforMybatis.addStudent( studentInfo, userId );
    }

    /**
     * 学校-学生管理
     * 修改学生信息
     *
     * @return
     */
    @ApiOperation( value = "修改学生信息", notes = "修改学生信息", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @PutMapping( "/edit" )
    public RestRecord editStudent( @RequestBody @ApiParam( "{\"USER_ID\":\"1108933826501017600\",\"USER_NAME\":\"小学生\",\"GENDER\":1,\"CERTIFICATE_NUMBER\":\"身份证号\",\"TEACH_TIME\":1553048511000,\"PHONE_NUMBER\":\"12312321312\",\"POSITION\":\"行政职务\",\"LOGIN_NAME\":\"js_123\",\"PASSWORD\":\"123456\",\"CLASS_NUMBER\":\"2班\",\"GRADE\":\"3年级\"}" )
                                           Map studentInfo,
                                   @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return schoolStudentDaoforMybatis.editStudent( studentInfo, userId );
    }

}
