package cn.com.bonc.sce.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.InfoTeacherModel;
import cn.com.bonc.sce.rest.RestRecord;

import cn.com.bonc.sce.service.UserManagerService;
import cn.com.bonc.sce.tool.ParseExcel;
import cn.com.bonc.sce.utils.Run;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/8.
 */
@Slf4j
@RestController
@RequestMapping( "/userManager" )
public class UserManagerController {

    @Autowired
    private UserManagerService userManagerService;

    @ApiOperation(value = "删除用户", notes="通过用户id，删除用户", httpMethod = "DELETE")
    @DeleteMapping("/delUser")
    @ResponseBody
    public RestRecord delUser(@RequestParam( "id" ) String id){
        return userManagerService.delUser(id);
    }

    @ApiOperation(value = "密码重置", notes="重置密码为初始密码", httpMethod = "PUT")
    @PutMapping("/resetPwd")
    @ResponseBody
    public RestRecord resetPwd(@RequestParam( "id" ) String id){
        return userManagerService.resetPwd(id);
    }

    @ApiOperation(value = "登录权限控制", notes="修改登录权限", httpMethod = "PUT")
    @PutMapping("/editLogin")
    @ResponseBody
    public RestRecord editLogin(@RequestParam( "id" ) String id,
                                @RequestParam( "loginPermissionStatus" ) Integer loginPermissionStatus){
        return userManagerService.editLogin(id,loginPermissionStatus);
    }

    @ApiOperation(value = "教育机构下学校列表查询", notes="通过教育机构id查询学校列表", httpMethod = "GET")
    @GetMapping("/getSchools4edu/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getSchools4edu(@RequestParam( "id" ) long id,
                                     @PathVariable (value = "pageNum")Integer pageNum,
                                     @PathVariable (value = "pageSize") Integer pageSize){
        return userManagerService.getSchools4edu(id,pageNum,pageSize);
    }

    @ApiOperation(value = "教育机构下学校删除", notes="通过教育机构id和学校id删除学校", httpMethod = "DELETE")
    @DeleteMapping("/delSchools4edu")
    @ResponseBody
    public RestRecord delSchools4edu(@RequestParam( "id" ) long id,
                                     @RequestParam("institutionId") long institutionId){
        return userManagerService.delSchools4edu(id,institutionId);
    }

    @ApiOperation(value = "教育机构列表", notes="通过条件获取教育机构列表", httpMethod = "POST")
    @PostMapping("/getInstitutionList/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getInstitutions(@RequestBody @ApiParam( example = "{\"id\": 1743,\"institutionName\": \"教育机构名称\",\"loginPermissionStatus\": 1}" ) String json,
                                      @PathVariable (value = "pageNum")Integer pageNum,
                                      @PathVariable (value = "pageSize") Integer pageSize){
        return userManagerService.getInstitutionList(json,pageNum,pageSize);
    }

    @ApiOperation(value = "教育机构下Excel导出", notes="教育机构下的数据导出为Excel", httpMethod = "GET")
    @GetMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(@RequestParam( "id" ) String id,
                            @RequestParam( "institutionName" ) String institutionName,
                            @RequestParam( "loginPermissionStatus" ) String loginPermissionStatus,
                            HttpServletResponse httpServletResponse ){

        List<Map> info = userManagerService.getInstitutions( id, institutionName, loginPermissionStatus );
        System.out.println( info.size() + "" + info.get( 0 ) );
        try {
            List<ExcelExportEntity> entity = new ArrayList<>();
            entity.add( new ExcelExportEntity( "序号", "ROWNUM" ) );
            entity.add( new ExcelExportEntity( "机构名称", "INSTITUTION_NAME" ) );
            entity.add( new ExcelExportEntity( "账号", "LOGIN_NAME" ) );
            entity.add( new ExcelExportEntity( "组织编号", "ID" ) );
            entity.add( new ExcelExportEntity( "状态", "ACCOUNT_STATUS_DEF" ) );
            entity.add( new ExcelExportEntity( "允许登录","LOGIN_PERMISSION_STATUS"));

            Workbook workbook = null;
            ExportParams exportParms = new ExportParams( "标题", "Sheet1" );

            //不固定表头
            exportParms.setFixedTitle( false );
            //导出
            workbook = ExcelExportUtil.exportBigExcel( exportParms, entity, info );
            ExcelExportUtil.closeExportBigExcel();
            //下载
            ParseExcel.downLoadExcel( "教育机构信息.xlsx", httpServletResponse, workbook );

            //return new RestRecord(200, MessageConstants.SCE_MSG_0200);
        } catch (Exception e){
            //return new RestRecord(406, MessageConstants.SCE_MSG_406);
        }
    }

    @ApiOperation(value = "登陆页的用户注册", notes="登陆页的用户注册", httpMethod = "POST")
    @PostMapping("/register")
    @ResponseBody
    public RestRecord register(@RequestBody @ApiParam( value = "{\"loginName\": \"测试张\",\"secret\": \"123456\",\"phoneNumber\": \"12345678901\",\"userType\": 1,\"userName\": \"测试张\"}" ) String json ){
        return userManagerService.register(json);
    }

    @ApiOperation(value = "后台-用户管理-新增", notes="后台-用户管理-新增", httpMethod = "POST")
    @PostMapping("/addUser")
    @ResponseBody
    public RestRecord addUser(@RequestBody @ApiParam( value = "{\"loginName\": \"账号\",\"phoneNumber\": \"12345678901\",\"userType\": 1,\"certificateNumber\": \"511025199310191221\",\"certificateType\": 1,\"userName\": \"测试张\"}" ) String json ){
        return userManagerService.addUser(json);
    }

    @ApiOperation(value = "通过用户名获取电话号码", notes="忘记密码时的身份认证", httpMethod = "GET")
    @GetMapping("/getPhone")
    @ResponseBody
    public RestRecord getPhone(@RequestParam( "loginName" ) String loginName){
        return userManagerService.getPhone(loginName);
    }

    @ApiOperation(value = "通过用户名修改密码", notes="重设密码", httpMethod = "PUT")
    @PutMapping("/updatePwdByName")
    @ResponseBody
    public RestRecord updatePwdByName(@RequestParam( "loginName" ) String loginName,
                                      @RequestParam("password") String password){
        return userManagerService.updatePwdByName(loginName,password);
    }

    @ApiOperation( value = "通过用户名和输入的身份证信息在后台验证", notes = "验证身份证信息是否正确", httpMethod = "GET" )
    @GetMapping( "/testCertificate" )
    @ResponseBody
    public RestRecord testCertificate( @RequestParam( "valid" ) String valid,
                                       @RequestParam( "phoneNumber" ) String phoneNumber,
                                       @RequestParam( "loginName" ) String loginName,
                                       @RequestParam( "certificateType" ) Integer certificateType,
                                       @RequestParam( "certificate" ) String certificate ) {
        return userManagerService.testCertificcate( valid, phoneNumber, loginName, certificateType,certificate );
    }

    @ApiOperation(value = "获取在职教师列表", notes="获取查询条件，返回在职教师列表", httpMethod = "GET")
    @GetMapping("/getTeachers/{pageNum}/{pageSize}")
    @ResponseBody
    /**
     * gender:1 男 ，0 女， null 全部
     * position：null 全部
     * accountStatus：0 未激活，1 已激活，null 全部
     */
    public RestRecord getTeachers(@RequestParam ( value = "organizationId") long organizationId,
                                  @RequestParam ( value = "userName",required = false) String userName,
                                  @RequestParam(value ="loginName",required = false ) String loginName,
                                  @RequestParam ( value = "gender",required = false) String gender,
                                  @RequestParam ( value = "position",required = false) String position,
                                  @RequestParam ( value = "accountStatus",required = false) String accountStatus,
                                  @PathVariable (value = "pageNum")Integer pageNum,
                                  @PathVariable (value = "pageSize") Integer pageSize ){
        return userManagerService.getTeachers(organizationId,userName,loginName,gender,position,accountStatus,pageNum,pageSize);
    }

    @ApiOperation(value = "删除教师", notes="通过用户id，删除用户", httpMethod = "DELETE")
    @DeleteMapping("/delTeacher")
    @ResponseBody
    public RestRecord delTeacher(@RequestParam( "id" ) String id, @CurrentUserId String userId){
        return userManagerService.delTeacher(id, userId);
    }

    @ApiOperation(value = "通过教师id获取教师详细信息", notes="通过教师id获取教师详细信息", httpMethod = "GET")
    @GetMapping("/getTeacherInfo")
    @ResponseBody
    public RestRecord getTeacherInfo(@RequestParam( "id" ) String id){
        return userManagerService.getTeacherInfo(id);
    }

    @ApiOperation(value = "通过教师id修改教师详细信息", notes="通过教师id修改教师详细信息", httpMethod = "PUT")
    @PutMapping("/editTeacherInfo")
    @ResponseBody
    public RestRecord editTeacherInfo(@RequestBody String json, @CurrentUserId String userId){
        return userManagerService.editTeacherInfo(json, userId);
    }

    @ApiOperation(value = "添加教师", notes="直接添加或者通过转入添加，通过前端出入的addType判断添加方式", httpMethod = "POST")
    @PostMapping("/addTeacher")
    @ResponseBody
    public RestRecord addTeacher(@RequestBody String json, @CurrentUserId String userId){
        return userManagerService.addTeacher(json, userId);
    }


    /**
     * @param userName 用户名
     * @param loginName 登录名
     * @param studentNumber 学号
     * @param gender 性别
     * @param grade 年级
     * @param accountStatus 激活状态
     * @param pageNum 页数
     * @param pageSize 每页数量
     * @param userId 用户ID
     * @return RestRecord
     */
    @ApiOperation( value = "查询所有学生", notes = "通过查询条件查询该校所有学生", httpMethod = "GET" )
    @GetMapping("/getStudents/{pageNum}/{pageSize}")
    public RestRecord getStudents(@RequestParam( value = "userName", required = false ) String userName,
                                  @RequestParam( value = "loginName", required = false ) String loginName,
                                  @RequestParam( value = "studentNumber", required = false ) String studentNumber,
                                  @RequestParam( value = "gender", required = false ) String gender,
                                  @RequestParam( value = "grade", required = false ) String grade,
                                  @RequestParam( value = "accountStatus", required = false ) String accountStatus,
                                  @PathVariable String pageNum,
                                  @PathVariable String pageSize,
                                  @CurrentUserId String userId){
        return userManagerService.getStudents(userName, loginName, studentNumber, gender, grade, accountStatus, userId,pageNum,pageSize);
    }

    @GetMapping( "/getStudentInfo/{userId}" )
    public RestRecord getStudentInfo( @PathVariable String userId ){
        return userManagerService.getStudentInfo( userId );
    }

    /**
     * @param map VOLK-民族，NATIONALITY-国籍，ENTRANCE_YEAR-入学年月，GRADE-年级，，SEAT_NUMBER-座号，CLASS_NUMBER-班号，STUDENT_NUMBER-学号
     * @return RestRecord
     */
    @ApiOperation( value = "编辑学生", notes = "根据学生id编辑学生", httpMethod = "PATCH")
    @PutMapping( "/editStudent" )
    public RestRecord editStudent(@RequestBody Map map, @CurrentUserId String userId){
        return userManagerService.editStudent(map, userId);
    }

    @ApiOperation( value = "删除学生", notes = "根据学生ID逻辑删除学生", httpMethod = "DELETE" )
    @DeleteMapping( "/delStudent" )
    public RestRecord delStudent(@RequestParam String userId, @CurrentUserId String currentUserId){
        return userManagerService.delStudent(userId, currentUserId);
    }

    @ApiOperation( value = "添加学生", notes = "添加学生，同时添加或者绑定家长", httpMethod = "POST")
    @PostMapping( "/addStudent" )
    public RestRecord addStudent(@RequestBody Map map, @CurrentUserId String userId){
        return userManagerService.addStudent(map, userId);
    }

    /**
     *
     * @param userName 用户姓名
     * @param loginName 登录名
     * @param studentNumber 学号
     * @param gender 性别
     * @param grade 年级
     * @param applyStatus 审核状态
     * @param transferType 1转入，2转出
     * @param pageNum 页数
     * @param pageSize 每页数量
     * @param userId 当前登录人ID
     * @return RestRecord
     */
    @ApiOperation( value = "获取转入转出的学生列表", notes = "根据参数查询申请中的学生列表", httpMethod = "GET" )
    @GetMapping( "/getTransferStudent/{pageNum}/{pageSize}" )
    public RestRecord getTransferStudent(@RequestParam( value = "userName", required = false ) String userName,
                                         @RequestParam( value = "loginName", required = false ) String loginName,
                                         @RequestParam( value = "studentNumber", required = false ) String studentNumber,
                                         @RequestParam( value = "gender", required = false ) String gender,
                                         @RequestParam( value = "grade", required = false ) String grade,
                                         @RequestParam( value = "applyStatus", required = false ) String applyStatus,
                                         @RequestParam( value = "transferType" ) String transferType,
                                         @PathVariable String pageNum,
                                         @PathVariable String pageSize,
                                         @CurrentUserId String userId){
        return userManagerService.getTransferStudent(userName, loginName, studentNumber, gender, grade, applyStatus, transferType, pageNum, pageSize, userId);
    }


    @ApiOperation(value = "通过用户id修改教师从业信息接口", notes="通过用户id修改教师从业信息", httpMethod = "PUT")
    @PutMapping("/editTeacherPracticeInfo")
    @ResponseBody
    public RestRecord editTeacherPracticeInfo(@RequestBody @ApiParam( "教师从业信息对象" ) InfoTeacherModel model){
        return userManagerService.editTeacherPracticeInfo(model);
    }

    @ApiOperation( value = "通过证件类型和证件号查询父亲信息" ,notes = "通过条件查询父亲信息", httpMethod = "GET" )
    @GetMapping("/getParentInfo/{certificationType}/{certificationNumber}/{userType}")
    public RestRecord getParentInfo(@PathVariable String certificationType, @PathVariable String certificationNumber, @PathVariable String userType){
        return userManagerService.getParentInfo(certificationType, certificationNumber, userType);
    }

    @ApiOperation( value = "教师撤回学生转入的申请", notes = "通过id撤回", httpMethod = "DELETE" )
    @DeleteMapping("/repealApply/{transferId}")
    public RestRecord repealApply(@CurrentUserId String userId, @PathVariable String transferId){
        return userManagerService.repealApply(userId, transferId);
    }

    @ApiOperation( value = "再次申请学生转入", notes = "通过转入申请ID发起再次申请", httpMethod = "PATCH" )
    @PatchMapping("/reCall/{transferId}")
    public RestRecord reCall(@PathVariable String transferId, @CurrentUserId String userId){
        return userManagerService.reCall(transferId, userId);
    }

    @ApiOperation( value = "获取转出申请详细信息", notes = "通过ID获取", httpMethod = "GET" )
    @GetMapping("/getTransferOut/{transferId}")
    public RestRecord getTransferOut(@PathVariable String transferId){
        return userManagerService.getTransferOut(transferId);
    }

    @ApiOperation( value = "转出审核", notes = "学生转出审核", httpMethod = "PATCH" )
    @PatchMapping("/auditTransfer")
    public RestRecord auditTransfer(@CurrentUserId String userId, @RequestBody Map map){
        return userManagerService.auditTransfer(userId, map);
    }


    @ApiOperation(value = "通过用户id获取教师从业信息接口", notes="通过用户id获取教师从业信息", httpMethod = "GET")
    @GetMapping("/getTeacherInfoById/{userId}")
    @ResponseBody
    public RestRecord getTeacherInfoById( @PathVariable( "userId" ) String userId ) {
        return userManagerService.getTeacherInfoById(userId);
    }

    @ApiOperation(value = "新增教师从业信息接口", notes="新增教师从业信息接口", httpMethod = "POST")
    @PostMapping("/addTeacherInfo")
    @ResponseBody
    public RestRecord addTeacherInfo(@RequestBody InfoTeacherModel infoTeacherModel){
        return userManagerService.addTeacherInfo(infoTeacherModel);
    }

    @ApiOperation(value = "通过用户id获取用户信息接口", notes="通过用户id获取用户信息", httpMethod = "GET")
    @GetMapping("/{userId}")
    @ResponseBody
    public RestRecord getUserById( @PathVariable( "userId" ) String userId ) {
        return userManagerService.getUserById(userId);
    }

    @ApiOperation( value = "获取转出教师详细信息", notes = "通过用户id获取", httpMethod = "GET" )
    @GetMapping( "/getTransferTeacherInfo/{transferId}" )
    public RestRecord getTransferTeacherInfo(@PathVariable String transferId){
        return userManagerService.getTransferTeacherInfo(transferId);
    }

    @ApiOperation(value = "获取转入转出教师列表", notes = "获取查询条件，返回教师列表", httpMethod = "GET")
    @GetMapping("/getTransferTeachers/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getTransferTeachers(@RequestParam(value = "getType") Integer getType, @RequestParam("organizationId") long organizationId,
                                          @RequestParam(value = "userName", required = false) String userName,
                                          @RequestParam(value = "loginName", required = false) String loginName,
                                          @RequestParam(value = "gender", required = false) String gender,
                                          @RequestParam(value = "position", required = false) String position,
                                          @RequestParam(value = "accountStatus", required = false) Integer accountStatus,
                                          @PathVariable(value = "pageNum") Integer pageNum,
                                          @PathVariable(value = "pageSize") Integer pageSize) {
        return userManagerService.getTransferTeachers(getType, organizationId, userName, loginName, gender, position, accountStatus, pageNum, pageSize);
    }

    @ApiOperation( value = "审核教师转出", httpMethod = "PATCH")
    @PatchMapping("/auditTeacher")
    public RestRecord auditTeacher(@CurrentUserId String userId, @RequestBody Map map){
        return userManagerService.auditTeacher(userId, map);
    }

    @ApiOperation( value = "教师再次申请转入", notes = "参数为transferId", httpMethod = "PATCH" )
    @PatchMapping("/reCallTeacher")
    public RestRecord reCallTeacher(@RequestBody Map map, @CurrentUserId String userId){
        return userManagerService.reCallTeacher(map, userId);
    }

    @ApiOperation(value = "审核认证申请接口", notes="审核认证申请", httpMethod = "PUT")
    @PutMapping("/updateAudit")
    @ResponseBody
    public RestRecord updateAudit(@CurrentUserId String auditUserId,@RequestBody Map map){
        return userManagerService.updateAudit(auditUserId,map);
    }

    @ApiOperation(value = "审核认证详情接口", notes="审核认证详情", httpMethod = "GET")
    @GetMapping("/getAudit/{userId}")
    @ResponseBody
    public RestRecord getAudit(@PathVariable(value = "userId") String userId){
        return userManagerService.getAudit(userId);
    }


    @ApiOperation(value = "校验登录名或者手机号是否已注册", notes="校验登录名或者手机号是否已注册", httpMethod = "GET")
    @GetMapping("/checkLoginNameOrPhone")
    public RestRecord checkLoginNameOrPhone(@RequestParam(value = "loginName",required = false) String loginName,
                                            @RequestParam(value = "phone",required = false) String phone){
        return userManagerService.checkLoginNameOrPhone(loginName, phone);
    }


    @ApiOperation(value = "实名认证", notes="实名认证", httpMethod = "GET")
    @GetMapping("/authentication")
    public RestRecord authentication(@RequestParam( value = "realName",required = true) String realName,
                                     @RequestParam( value = "idCard" ,required = true) String idCard,
                                     @RequestParam( value = "idCardPicLeft" ,required = true) Integer idCardPicLeft,
                                     @RequestParam( value = "idCardPicRight" ,required = true) Integer idCardPicRight,
                                     @CurrentUserId String userId){
        return userManagerService.authentication(realName, idCard,idCardPicLeft,idCardPicRight,userId);
    }

}
