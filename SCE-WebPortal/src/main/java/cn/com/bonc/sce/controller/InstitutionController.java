package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.model.Institution;
import cn.com.bonc.sce.model.InstitutionInfo;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.InstitutionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2019/4/14.
 */
@Slf4j
@Api( value = "教育局相关接口", tags = "教育局相关接口" )
@RestController
@RequestMapping( "/institution" )
public class InstitutionController {

    @Autowired
    InstitutionService institutionService;

    @ApiOperation(value = "通过用户ID修改教育局人员从业信息接口", notes="通过用户ID修改教育局人员从业信息", httpMethod = "PUT")
    @PutMapping("/editInstitutionInfo")
    @ResponseBody
    public RestRecord editInstitutionInfo(@RequestBody @ApiParam( "教育局人员从业信息对象" ) InstitutionInfo info){
        return institutionService.editInstitutionInfo(info);
    }

    @ApiOperation(value = "通过用户ID获取教育局人员从业信息接口", notes="通过用户ID获取教育局人员从业信息", httpMethod = "GET")
    @GetMapping("/getInstitutionInfoByUserId/{userId}")
    @ResponseBody
    public RestRecord getInstitutionInfoByUserId( @PathVariable( "userId" ) String userId ) {
        return institutionService.getInstitutionInfoByUserId(userId);
    }

    /**
     * 新增教育局从业信息
     * @param info
     * @return
     */
    @ApiOperation(value = "新增教育局从业信息接口",notes = "新增教育局从业信息",httpMethod = "POST")
    @PostMapping("/addInstitutionInfo")
    @ResponseBody
    public RestRecord addInstitutionInfo(@ApiParam(name = "info", value = "从业信息", required = true) @RequestBody InstitutionInfo info){
        return institutionService.addInstitutionInfo(info);
    }

    /**
     * 新增教育局信息
     * @param institution
     * @return
     */
    @ApiOperation(value = "新增教育局信息接口",notes = "新增教育局信息",httpMethod = "POST")
    @PostMapping("/addInstitution/{roleId}")
    @ResponseBody
    public RestRecord addInstitution(@ApiParam(name = "institution", value = "教育局实体", required = true) @RequestBody Institution institution, @ApiParam(name = "userId", value = "用户ID", required = true) @RequestParam( "userId" ) String userId, @ApiParam(name = "roleId", value = "角色类型", required = true) @PathVariable( "roleId" ) Integer roleId){
        return institutionService.addInstitution(institution,userId,roleId);
    }

    @ApiOperation(value = "通过教育局ID修改教育局信息接口",notes = "通过教育局ID修改教育局信息",httpMethod = "PUT")
    @PutMapping( "/updateInstitutionById" )
    @ResponseBody
    public RestRecord updateInstitutionById(@RequestBody @ApiParam( "教育局信息对象" ) Institution institution) {
        return institutionService.updateInstitutionById(institution);
    }

    @ApiOperation(value = "通过教育局ID获取教育局信息接口", notes="通过教育局ID获取教育局信息", httpMethod = "GET")
    @GetMapping("/{id}")
    @ResponseBody
    public RestRecord getInstitutionById( @PathVariable( "id" ) Long id ) {
        return institutionService.getInstitutionById(id);
    }

    @ApiOperation(value = "变更或驳回提交教育局信息接口",notes = "变更或驳回教育局信息",httpMethod = "PUT")
    @PutMapping( "/updateInstitutionInfo" )
    @ResponseBody
    public RestRecord updateInstitutionInfo(@RequestBody @ApiParam( "教育局信息对象" ) Institution institution) {
        return institutionService.updateInstitutionInfo(institution);
    }

    @GetMapping( "/getInstitutionInfoList/{INSTITUTION_NAME}/{AUDIT_STATUS}/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getInstitutionInfoList(@PathVariable("INSTITUTION_NAME") String INSTITUTION_NAME,@PathVariable("AUDIT_STATUS") String AUDIT_STATUS
    ,@PathVariable("pageNum") Integer pageNum,@PathVariable("pageSize") Integer pageSize) {
        return institutionService.getInstitutionInfoList(INSTITUTION_NAME,AUDIT_STATUS,pageNum,pageSize);
    }

}
