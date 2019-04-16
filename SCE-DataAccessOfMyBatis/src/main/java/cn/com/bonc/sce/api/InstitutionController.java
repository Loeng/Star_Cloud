package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.Institution;
import cn.com.bonc.sce.model.InstitutionInfo;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.InstitutionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/4/14.
 */
@Slf4j
@RestController
@RequestMapping("/institution")
public class InstitutionController {

    @Autowired
    InstitutionService institutionService;

    @ApiOperation(value = "通过用户ID修改教育局人员从业信息接口", notes = "通过用户ID修改教育局人员从业信息", httpMethod = "PUT")
    @PutMapping("/editInstitutionInfo")
    @ResponseBody
    public RestRecord editInstitutionInfo(@RequestBody @ApiParam("教育局人员从业信息对象") InstitutionInfo info) {
        String USER_ID = info.getUserId(); //用户ID
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date WORK_TIME;
        Date ENTRY_TIME;
        try {
            WORK_TIME = sdf.parse(info.getWorkTime());  //参加工作年月
            ENTRY_TIME = sdf.parse(info.getEntryTime()); //入职年月
        } catch (Exception e) {
            return new RestRecord(421, WebMessageConstants.SCE_PORTAL_MSG_421);
        }
        String JOB_PROFESSION = info.getJobProfession(); //岗位职业
        String WORK_NUMBER = info.getWorkNumber(); //工号

        int institutionEdit = institutionService.editInstitutionInfo(USER_ID, WORK_TIME, ENTRY_TIME, JOB_PROFESSION,
                WORK_NUMBER);

        return new RestRecord(200, MessageConstants.SCE_MSG_0200, institutionEdit);
    }

    @ApiOperation(value = "通过用户ID获取教育局人员从业信息接口", notes = "通过用户ID获取教育局人员从业信息", httpMethod = "GET")
    @GetMapping("/getInstitutionInfoByUserId/{userId}")
    @ResponseBody
    public RestRecord getInstitutionInfoByUserId(@PathVariable("userId") String userId) {
        InstitutionInfo user = institutionService.getInstitutionInfoByUserId(userId);
        if (user == null) {
            return new RestRecord(1010, MessageConstants.SCE_MSG_1010, userId);
        } else {
            return new RestRecord(200, MessageConstants.SCE_MSG_0200, user);
        }
    }

    @ApiOperation(value = "新增教育局人员从业信息接口", notes = "新增教育局人员从业信息", httpMethod = "POST")
    @PostMapping("/addInstitutionInfo")
    @ResponseBody
    public RestRecord addInstitutionInfo(@RequestBody @ApiParam("教育局人员从业信息对象") InstitutionInfo info) {

        String USER_ID = info.getUserId(); //用户ID
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date WORK_TIME;
        Date ENTRY_TIME;
        try {
            WORK_TIME = sdf.parse(info.getWorkTime());  //参加工作年月
            ENTRY_TIME = sdf.parse(info.getEntryTime()); //入职年月
        } catch (Exception e) {
            return new RestRecord(421, WebMessageConstants.SCE_PORTAL_MSG_421);
        }
        String JOB_PROFESSION = info.getJobProfession(); //岗位职业
        String WORK_NUMBER = info.getWorkNumber(); //工号
        Integer IS_DELETE = 1;
        int institutionEdit = institutionService.addInstitutionInfo(USER_ID, WORK_TIME, ENTRY_TIME,
                JOB_PROFESSION, WORK_NUMBER, IS_DELETE);

        return new RestRecord(200, MessageConstants.SCE_MSG_0200, institutionEdit);
    }


    @ApiOperation(value = "新增教育局信息接口", notes = "新增教育局信息", httpMethod = "POST")
    @PostMapping("/addInstitution/{roleId}")
    @ResponseBody
    public RestRecord addInstitution(@RequestBody Institution institution, @RequestParam("userId") String userId, @PathVariable("roleId") Integer roleId) {
        return institutionService.addInstitution(institution, userId, roleId);
    }

    @ApiOperation(value = "通过教育局ID修改教育局信息接口", notes = "通过教育局ID修改教育局信息", httpMethod = "PUT")
    @PutMapping("/updateInstitutionById")
    @ResponseBody
    public RestRecord updateInstitutionById(@RequestBody @ApiParam("教育局信息对象") Institution institution) {
        return institutionService.updateInstitutionById(institution);
    }

    @ApiOperation(value = "通过教育局ID获取教育局信息接口", notes = "通过教育局ID获取教育局信息", httpMethod = "GET")
    @GetMapping("/{id}")
    @ResponseBody
    public RestRecord getInstitutionById(@PathVariable("id") String id) {
        Institution user = institutionService.getInstitutionById(id);
        if (user == null) {
            return new RestRecord(1010, MessageConstants.SCE_MSG_1010, id);
        } else {
            return new RestRecord(200, MessageConstants.SCE_MSG_0200, user);
        }
    }

    @ApiOperation(value = "变更或驳回提交教育局信息接口", notes = "变更或驳回教育局信息", httpMethod = "PUT")
    @PutMapping("/updateInstitutionInfo")
    @ResponseBody
    public RestRecord updateInstitutionInfo(@RequestBody @ApiParam("教育局信息对象") Institution institution) {
        return institutionService.updateInstitutionInfo(institution);
    }

    @GetMapping("/getInstitutionInfoList/{INSTITUTION_NAME}/{AUDIT_STATUS}/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getInstitutionInfoList(@PathVariable("INSTITUTION_NAME") String INSTITUTION_NAME, @PathVariable("AUDIT_STATUS") String AUDIT_STATUS
            , @PathVariable(value = "pageNum") Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List institutionList = institutionService.getInstitutionInfoList(INSTITUTION_NAME, AUDIT_STATUS);
        PageInfo pageInfo = new PageInfo(institutionList);
        RestRecord restRecord = new RestRecord(200, MessageConstants.SCE_MSG_0200, institutionList);
        restRecord.setTotal(pageInfo.getTotal());
        return restRecord;

    }

}
