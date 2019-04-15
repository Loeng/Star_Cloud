package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.Banner;
import cn.com.bonc.sce.model.School;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.BannerService;
import cn.com.bonc.sce.service.SchoolService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 学校
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Slf4j
@RestController
@Api( value = "学校", tags = "学校"  )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RequestMapping( "/schools" )
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

//    /**
//     * 添加school
//     *
//     * @param school 信息
//     * @return 是否添加成功
//     */
//    @ApiOperation( value = "添加school", notes = "添加school", httpMethod = "POST" )
//    @ApiResponses( {
//            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
//            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
//    } )
//    @PostMapping
//    @ResponseBody
//    public RestRecord insertSchool( @RequestBody @ApiParam( name = "school", value = "信息", required = true ) School school ) {
//        return schoolService.insertSchool( school );
//    }

    /**
     * 获取学校
     *
     * @return 获取学校
     */
    @ApiOperation( value = "获取学校", notes = "获取学校", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping
    @ResponseBody
    public RestRecord getAll(@RequestParam( value = "pageNum", required = false, defaultValue = "1"  ) @ApiParam( name = "pageNum", value = "页码")Integer pageNum,
                             @RequestParam( value = "pageSize", required = false, defaultValue = "10"  ) @ApiParam( name = "pageSize", value = "数量")Integer pageSize) {
        return schoolService.getAll(pageNum,pageSize);
    }

    /**
     * 新增学校实体
     *
     * @return 新增学校实体
     */
    @ApiOperation( value = "新增学校实体", notes = "新增学校实体", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord saveSchool(@ApiParam(example = "{\"schoolName\":\"福建漳州市第一中学\" ,\"authorityName\":\"福建漳州市教育局\" ,\"schoolAddress\":\"福建漳州市龙岩区\" ,\"telephone\":\"18667667998\" ,\"institutionId\":\"2\" ,\"grade\":\"中小学\"}")  @RequestBody  Map map ) {
        return schoolService.saveSchool(map);
    }

    /**
     * 新增学校信息
     * @param school
     * @return
     */
    @ApiOperation(value = "新增学校信息接口",notes = "新增学校信息",httpMethod = "POST")
    @PostMapping("/addSchool/{roleId}")
    @ResponseBody
    public RestRecord addSchool(@ApiParam(name = "school", value = "学校实体", required = true) @RequestBody School school,@ApiParam(name = "userId", value = "用户ID", required = true) @RequestParam( "userId" ) String userId,@ApiParam(name = "roleId", value = "角色类型", required = true) @PathVariable( "roleId" ) Integer roleId){
        return schoolService.addSchool(school,userId,roleId);
    }

    @ApiOperation(value = "通过学校id获取学校信息接口", notes="通过学校id获取学校信息", httpMethod = "GET")
    @GetMapping("/{id}")
    @ResponseBody
    public RestRecord getSchoolById( @PathVariable( "id" ) Integer id ) {
        return schoolService.getSchoolById(id);
    }

    @ApiOperation(value = "通过学校id修改学校信息接口",notes = "通过学校id修改学校信息",httpMethod = "PUT")
    @PutMapping( "/updateSchoolById" )
    @ResponseBody
    public RestRecord updateSchoolById(@RequestBody @ApiParam( "学校信息对象" ) School school) {
        return schoolService.updateSchoolById(school);
    }

    @ApiOperation(value = "变更或驳回提交学校信息接口",notes = "变更或驳回提交学校信息",httpMethod = "PUT")
    @PutMapping( "/updateSchoolInfo" )
    @ResponseBody
    public RestRecord updateSchoolInfo(@RequestBody @ApiParam( "学校信息对象" ) School school,@ApiParam(name = "userId", value = "用户ID", required = true) @RequestParam( "userId" ) String userId,@ApiParam(name = "roleId", value = "角色类型", required = true) @RequestParam( "roleId" ) Integer roleId) {
        return schoolService.updateSchoolInfo(school,userId,roleId);
    }

    /**
     * 获取学校认证列表
     * @return
     */
    @GetMapping( "/getSchoolInfoList/{SCHOOL_NAME}/{SCHOOL_TYPE}/{AUDIT_STATUS}/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getSchoolInfoList(@PathVariable("SCHOOL_NAME") String SCHOOL_NAME,@PathVariable("SCHOOL_TYPE") String SCHOOL_TYPE,@PathVariable("AUDIT_STATUS") Integer AUDIT_STATUS
            , @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        return schoolService.getSchoolInfoList(SCHOOL_NAME,SCHOOL_TYPE,AUDIT_STATUS,pageNum,pageSize);
    }

}
