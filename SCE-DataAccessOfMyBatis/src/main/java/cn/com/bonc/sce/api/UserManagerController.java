package cn.com.bonc.sce.api;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.bean.TeacherInfoBean;
import cn.com.bonc.sce.bean.UserBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.InfoTeacherModel;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.UserService;
import cn.com.bonc.sce.tool.IdWorker;
import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/8.
 */
@Slf4j
@RestController
@RequestMapping("/userManager")
public class UserManagerController {

    @Autowired
    private UserService userService;
    @Autowired
    private IdWorker idWorker;

    @ApiOperation(value = "删除用户", notes = "通过用户id，删除用户", httpMethod = "DELETE")
    @DeleteMapping("/delUser")
    @ResponseBody
    public RestRecord delUser(@RequestParam("id") String id) {
        int count = userService.delUser(id);
        if (count == 1) {
            return new RestRecord(200, MessageConstants.SCE_MSG_0200, 1);
        } else {
            return new RestRecord(408, MessageConstants.SCE_MSG_408);
        }
    }

    @ApiOperation(value = "密码重置", notes = "重置密码为初始密码", httpMethod = "PUT")
    @PutMapping("/resetPwd")
    @ResponseBody
    public RestRecord resetPwd(@RequestParam("id") String id) {
        int count = userService.resetPwd(id, "star123!");
        if (count == 1) {
            return new RestRecord(200, MessageConstants.SCE_MSG_0200, 1);
        } else {
            return new RestRecord(408, MessageConstants.SCE_MSG_407);
        }
    }

    @ApiOperation(value = "登录权限控制", notes = "修改登录权限", httpMethod = "PUT")
    @PutMapping("/editLogin")
    @ResponseBody
    public RestRecord editLogin(@RequestParam("id") String id,
                                @RequestParam("loginPermissionStatus") Integer loginPermissionStatus) {

        int newStatus = (loginPermissionStatus == 0) ? 1 : 0;
        int count = userService.updateLoginPermission(id, newStatus);
        if (count == 1) {
            return new RestRecord(200, MessageConstants.SCE_MSG_0200, count);
        } else {
            return new RestRecord(407, MessageConstants.SCE_MSG_407);
        }
    }

    @ApiOperation(value = "教育机构下学校列表查询", notes = "通过教育机构id查询学校列表", httpMethod = "GET")
    @GetMapping("/getSchools4edu/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getSchools4edu(@RequestParam("id") long id,
                                     @PathVariable(value = "pageNum") Integer pageNum,
                                     @PathVariable(value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SchoolBean> schoolList = userService.getSchools4edu(id);
        PageInfo pageInfo = new PageInfo(schoolList);
        return new RestRecord(200, MessageConstants.SCE_MSG_0200, pageInfo);
    }

    @ApiOperation(value = "教育机构下学校删除", notes = "通过教育机构id和学校id删除学校", httpMethod = "DELETE")
    @DeleteMapping("/delSchools4edu")
    @ResponseBody
    public RestRecord delSchools4edu(@RequestParam("id") long id,
                                     @RequestParam("institutionId") long institutionId) {
        int count = userService.delSchools4edu(id, institutionId);
        if (count == 1) {
            return new RestRecord(200, MessageConstants.SCE_MSG_0200, 1);
        } else {
            return new RestRecord(408, MessageConstants.SCE_MSG_408);
        }
    }

    @ApiOperation(value = "获取教育机构下列表", notes = "获取教育机构下列表", httpMethod = "POST")
    @PostMapping("/getInstitutionList/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getInstitutionList(@RequestBody String json,
                                         @PathVariable(value = "pageNum") Integer pageNum,
                                         @PathVariable(value = "pageSize") Integer pageSize) {
        Map map = (Map) JSONUtils.parse(json);
        String id = (String) map.get("id");
        String institutionName = (String) map.get("institutionName");
        String loginPermissionStatus = (String) map.get("loginPermissionStatus");

        PageHelper.startPage(pageNum, pageSize);
        List<Map> list = userService.getInstitutions(id, institutionName, loginPermissionStatus);
        PageInfo pageInfo = new PageInfo(list);
        return new RestRecord(200, MessageConstants.SCE_MSG_0200, pageInfo);
    }

    @ApiOperation(value = "获取教育机构下列表", notes = "获取教育机构下列表", httpMethod = "GET")
    @GetMapping("/getInstitutions")
    @ResponseBody
    public List<Map> getInstitutions(@RequestParam("id") String id,
                                     @RequestParam("institutionName") String institutionName,
                                     @RequestParam("loginPermissionStatus") String loginPermissionStatus) {
        List<Map> list = userService.getInstitutions(id, institutionName, loginPermissionStatus);
        return list;
    }

    @Transactional
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/register")
    @ResponseBody
    public RestRecord register(@RequestBody @ApiParam(example = "{\"loginName\": \"测试张\",\"secret\": \"123456\",\"phoneNumber\": \"12345678901\",\"userType\": 1,\"userName\": \"测试张\"}") String json) {
        Map map = (Map) JSONUtils.parse(json);
        String loginName = (String) map.get("loginName");
        String password = (String) map.get("password");
        String phoneNumber = (String) map.get("phoneNumber");
        Integer userType = (Integer) map.get("userType");
        String userName = (String) map.get("userName");

        UserBean user = new UserBean();
        Long userId = idWorker.nextId();
        user.setLoginName(loginName);
        user.setUserType(userType);
        user.setPhoneNumber(phoneNumber);
        user.setUserName(userName);
        user.setIsDelete(1);
        user.setUserId(userId);
        user.setAccountStatus(0);

        if (userService.isExist(loginName) > 0) {
            return new RestRecord(1022, MessageConstants.SCE_MSG_1022);
        }

        AccountBean account = new AccountBean();
        long accountId = idWorker.nextId();
        account.setId(accountId);
        account.setPassword(password);
        account.setIsDelete(1);
        account.setUserId(userId);

        if (userService.saveUser(user) == 1 && userService.saveAccount(account) == 1) {
            return new RestRecord(200, MessageConstants.SCE_MSG_0200, 1);
        } else {
            return new RestRecord(409, MessageConstants.SCE_MSG_409);
        }
    }

    @ApiOperation(value = "通过用户名获取电话号码", notes = "忘记密码时的身份认证", httpMethod = "GET")
    @GetMapping("/getPhone")
    @ResponseBody
    public RestRecord getPhone(@RequestParam("loginName") String loginName) {
        Map map = userService.getPhone(loginName);
        if (map != null) {
            return new RestRecord(200, MessageConstants.SCE_MSG_0200, map);
        } else {
            return new RestRecord(406, MessageConstants.SCE_MSG_406);
        }
    }

    @ApiOperation(value = "通过用户名修改密码", notes = "重设密码", httpMethod = "PUT")
    @PutMapping("/updatePwdByName")
    @ResponseBody
    public RestRecord updatePwdByName(@RequestParam("loginName") String loginName,
                                      @RequestParam("password") String password) {
        int count = userService.updatePwdByName(loginName, password);
        if (count == 1) {
            return new RestRecord(200, MessageConstants.SCE_MSG_0200, 1);
        } else {
            return new RestRecord(407, MessageConstants.SCE_MSG_407);
        }
    }

    @ApiOperation(value = "通过用户名和输入的身份证信息在后台验证", notes = "验证身份证信息是否正确", httpMethod = "GET")
    @GetMapping("/testCertificate")
    @ResponseBody
    public RestRecord testCertificate(@RequestParam("loginName") String loginName,
                                      @RequestParam("certificate") String certificate) {
        String tempCer = userService.testCertificate(loginName);
        if (tempCer.equals(certificate)) {
            return new RestRecord(200, MessageConstants.SCE_MSG_0200);
        } else {
            return new RestRecord(1010, "身份证验证失败");
        }
    }

    @ApiOperation(value = "获取在职教师列表", notes = "获取查询条件，返回在职教师列表", httpMethod = "GET")
    @GetMapping("/getTeachers/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getTeachers(@RequestParam("organizationId") long organizationId,
                                  @RequestParam(value = "userName", required = false) String userName,
                                  @RequestParam(value = "loginName", required = false) String loginName,
                                  @RequestParam(value = "gender", required = false) String gender,
                                  @RequestParam(value = "position", required = false) String position,
                                  @RequestParam(value = "accountStatus", required = false) Integer accountStatus,
                                  @PathVariable(value = "pageNum") Integer pageNum,
                                  @PathVariable(value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map> teachers = userService.getTeachers(organizationId, userName, loginName, gender, position, accountStatus);
        PageInfo pageInfo = new PageInfo(teachers);
        return new RestRecord(200, MessageConstants.SCE_MSG_0200, pageInfo);
    }

    @ApiOperation(value = "删除教师", notes = "通过用户id，删除用户", httpMethod = "DELETE")
    @DeleteMapping("/delTeacher")
    @ResponseBody
    @Transactional
    public RestRecord delTeacher(@RequestParam("id") String id) {
        int count1 = userService.delUser(id);
        int count2 = userService.delTeacher(id);
        if (count1 == 1 && count2 == 1) {
            return new RestRecord(200, MessageConstants.SCE_MSG_0200, 1);
        } else {
            return new RestRecord(408, MessageConstants.SCE_MSG_408);
        }
    }

    @ApiOperation(value = "通过教师id获取教师详细信息", notes = "通过教师id获取教师详细信息", httpMethod = "GET")
    @GetMapping("/getTeacherInfo")
    @ResponseBody
    public RestRecord getTeacherInfo(@RequestParam("id") String id) {
        return new RestRecord(200, MessageConstants.SCE_MSG_0200, userService.getTeacherInfo(id));
    }

    @ApiOperation(value = "通过教师id修改教师详细信息", notes = "通过教师id修改教师详细信息", httpMethod = "PUT")
    @PutMapping("/editTeacherInfo")
    @ResponseBody
    @Transactional
    public RestRecord editTeacherInfo(@RequestBody String json) {
        Map map = (Map) JSONUtils.parse(json);
        String USER_ID = (String) map.get("USER_ID");
        Integer CERTIFICATE_TYPE = (Integer) map.get("CERTIFICATE_TYPE");
        String CERTIFICATE_NUMBER = (String) map.get("CERTIFICATE_NUMBER");
        String USER_NAME = (String) map.get("USER_NAME");
        String GENDER = (String) map.get("GENDER");
        String PHONE_NUMBER = (String) map.get("PHONE_NUMBER");
        String MAIL_ADDRESS = (String) map.get("MAIL_ADDRESS");
        String BIRTHDATE = (String) map.get("BIRTHDATE");
        String NATION_CODE = (String) map.get("NATION_CODE");
        String NATIONLITY = (String) map.get("NATIONLITY");
        String ACADEMIC_QUALIFICATION = (String) map.get("ACADEMIC_QUALIFICATION");
        String WORK_NUMBER = (String) map.get("WORK_NUMBER");
        String SCHOOL_TIME = (String) map.get("SCHOOL_TIME");
        String TEACH_TIME = (String) map.get("TEACH_TIME");
        String JOB_CODE = (String) map.get("JOB_CODE");
        Integer TEACH_RANGE = (Integer) map.get("TEACH_RANGE");

        int userEdit = userService.editUser(USER_ID, CERTIFICATE_TYPE, CERTIFICATE_NUMBER, USER_NAME,
                GENDER, PHONE_NUMBER, MAIL_ADDRESS, BIRTHDATE,NATION_CODE,NATIONLITY);
        int teacherEdit = userService.editTeacher(USER_ID, ACADEMIC_QUALIFICATION,
                WORK_NUMBER, SCHOOL_TIME, TEACH_TIME, JOB_CODE, TEACH_RANGE);

        if (userEdit == 1 && teacherEdit == 1) {
            return new RestRecord(200, MessageConstants.SCE_MSG_0200, 1);
        } else {
            return new RestRecord(407, MessageConstants.SCE_MSG_407, 0);
        }
    }

    @ApiOperation(value = "添加教师", notes = "直接添加或者通过转入添加，通过前端传入的addType判断添加方式", httpMethod = "POST")
    @PostMapping("/addTeacher")
    @ResponseBody
    @Transactional
    public RestRecord addTeacher(@RequestBody String json) {
        Map map = (Map) JSONUtils.parse(json);
        Integer ADDTYPE = (Integer) map.get("ADDTYPE");
        if (ADDTYPE == 0) {//建新用户
            Long USER_ID = idWorker.nextId();
            String ORGANIZATION_ID = (String) map.get("ORGANIZATION_ID");
            Integer CERTIFICATE_TYPE = (Integer) map.get("CERTIFICATE_TYPE");
            String CERTIFICATE_NUMBER = (String) map.get("CERTIFICATE_NUMBER");
            String USER_NAME = (String) map.get("USER_NAME");
            String GENDER = (String) map.get("GENDER");
            String PHONE_NUMBER = (String) map.get("PHONE_NUMBER");
            String MAIL_ADDRESS = (String) map.get("MAIL_ADDRESS");
            String BIRTHDATE = (String) map.get("BIRTHDATE");
            String NATION_CODE = (String) map.get("NATION_CODE");
            String NATIONLITY = (String) map.get("NATIONLITY");
            String ACADEMIC_QUALIFICATION = (String) map.get("ACADEMIC_QUALIFICATION");
            String WORK_NUMBER = (String) map.get("WORK_NUMBER");
            String SCHOOL_TIME = (String) map.get("SCHOOL_TIME");
            String TEACH_TIME = (String) map.get("TEACH_TIME");
            String JOB_CODE = (String) map.get("JOB_CODE");
            Integer TEACH_RANGE = (Integer) map.get("TEACH_RANGE");

            int count1 = userService.addUser(USER_ID, CERTIFICATE_TYPE, CERTIFICATE_NUMBER,
                    USER_NAME, GENDER, PHONE_NUMBER, ORGANIZATION_ID, MAIL_ADDRESS, BIRTHDATE);
            int count2 = userService.addTeacher(USER_ID, NATION_CODE, NATIONLITY, ACADEMIC_QUALIFICATION,
                    WORK_NUMBER, SCHOOL_TIME, TEACH_TIME, JOB_CODE, TEACH_RANGE);
            if (count1 == 1 && count2 == 1) {
                return new RestRecord(200, MessageConstants.SCE_MSG_0200, 1);
            } else {
                return new RestRecord(409, MessageConstants.SCE_MSG_409, 0);
            }
        } else {//转入
            Long ID = idWorker.nextId();
            String USER_ID = (String) map.get("USER_ID");
            String APPLY_USER_ID = (String) map.get("APPLY_USER_ID");
            String TEA_WORK_NUMBER = (String) map.get("TEA_WORK_NUMBER");
            String TEA_ENTRANCE_YEAR = (String) map.get("TEA_ENTRANCE_YEAR");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            Date ENTRANCE_YEAR = null;
            try {
                ENTRANCE_YEAR = format.parse(TEA_ENTRANCE_YEAR);
            } catch (ParseException e) {
                return new RestRecord(409, MessageConstants.SCE_MSG_409, 0);
            }
            String TEA_POSITION = (String) map.get("TEA_POSITION");
            String TEACH_RANGE = (String) map.get("TEACH_RANGE");
            String ORIGIN_SCHOOL_ID = (String) map.get("ORIGIN_SCHOOL_ID");
            String TARGET_SCHOOL_ID = (String) map.get("TARGET_SCHOOL_ID");
            return new RestRecord(200, MessageConstants.SCE_MSG_0200,
                    userService.transInto(ID, USER_ID, APPLY_USER_ID, ORIGIN_SCHOOL_ID, TARGET_SCHOOL_ID, TEA_WORK_NUMBER, ENTRANCE_YEAR, TEA_POSITION, TEACH_RANGE));
        }

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
        PageHelper.startPage(pageNum, pageSize);
        List<Map> teachers = userService.getTransferTeachers(getType,organizationId, userName, loginName, gender, position, accountStatus);
        PageInfo pageInfo = new PageInfo(teachers);
        return new RestRecord(200, MessageConstants.SCE_MSG_0200, pageInfo);
    }

    @GetMapping( "/getStudents/{pageNum}/{pageSize}" )
    public RestRecord getStudents(@RequestParam( value = "userName", required = false ) String userName,
                                  @RequestParam( value = "loginName", required = false ) String loginName,
                                  @RequestParam( value = "studentNumber", required = false ) String studentNumber,
                                  @RequestParam( value = "gender", required = false ) String gender,
                                  @RequestParam( value = "grade", required = false ) String grade,
                                  @RequestParam( value = "accountStatus", required = false ) String accountStatus,
                                  @PathVariable String pageNum,
                                  @PathVariable String pageSize,
                                  @CurrentUserId String userId){
        return userService.getStudents(userName, loginName, studentNumber, gender, grade, accountStatus, userId, pageNum, pageSize);
    }

    @GetMapping( "/getStudentInfo/{userId}" )
    public RestRecord getStudentInfo( @PathVariable("userId") String userId ){
        return userService.getStudentInfo(userId);
    }

    @PutMapping( "/editStudent" )
    public RestRecord editStudent(@RequestBody Map map){
        return userService.editStudent(map);
    }

    @DeleteMapping( "delStudent" )
    public RestRecord delStudent(@RequestParam String userId){
        return userService.delStudent(userId);
    }

    @PostMapping( "/addStudent" )
    public RestRecord addStudent(@RequestBody Map map,
                                 @CurrentUserId String userId){
        return userService.addStudent(map, userId);
    }

    @ApiOperation(value = "通过用户id修改教师从业信息", notes = "通过用户id修改教师从业信息", httpMethod = "PUT")
    @PutMapping("/editTeacherPracticeInfo")
    @ResponseBody
    public RestRecord editTeacherPracticeInfo(@RequestBody @ApiParam( "教师从业信息对象" ) InfoTeacherModel model) {

        String USER_ID = model.getUserId(); //用户ID
        String TEACH_CERTIFICATION = model.getTeachCertification(); //教师资格证号
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date TEACH_TIME;
        Date SCHOOL_TIME;
        try {
            TEACH_TIME = sdf.parse(model.getSchoolAge());  //参加工作年月
            SCHOOL_TIME = sdf.parse(model.getSchoolTime()); //来校年月
        }catch (Exception e){
            return new RestRecord( 421, WebMessageConstants.SCE_PORTAL_MSG_421 );
        }
        String JOB_PROFESSION = model.getJobProfession(); //岗位职业
        Integer TEACH_RANGE = Integer.parseInt(model.getTeachRange()); //任课学段
        String WORK_NUMBER = model.getWorkNumber(); //工号

        int teacherEdit = userService.editTeacherPracticeInfo(USER_ID, TEACH_CERTIFICATION, TEACH_TIME, SCHOOL_TIME,
                JOB_PROFESSION, TEACH_RANGE, WORK_NUMBER);

        return new RestRecord(200, MessageConstants.SCE_MSG_0200, teacherEdit);
    }

    @ApiOperation(value = "通过用户id获取教师从业信息接口", notes="通过用户id获取教师从业信息", httpMethod = "GET")
    @GetMapping("/getTeacherInfoById/{userId}")
    @ResponseBody
    public RestRecord getTeacherInfoById( @PathVariable( "userId" ) String userId ) {
        TeacherInfoBean user = userService.getTeacherInfoById(userId);
        if ( user == null ) {
            return new RestRecord( 1010, MessageConstants.SCE_MSG_1010, userId );
        } else {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, user );
        }
    }

    @ApiOperation(value = "新增教师从业信息接口", notes = "新增教师从业信息", httpMethod = "POST")
    @PostMapping("/addTeacherInfo")
    @ResponseBody
    public RestRecord addTeacherInfo(@RequestBody @ApiParam( "教师从业信息对象" ) InfoTeacherModel model) {

        String USER_ID = model.getUserId(); //用户ID
        String TEACH_CERTIFICATION = model.getTeachCertification(); //教师资格证号
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date TEACH_TIME;
        Date SCHOOL_TIME;
        try {
            TEACH_TIME = sdf.parse(model.getSchoolAge());  //参加工作年月
            SCHOOL_TIME = sdf.parse(model.getSchoolTime()); //来校年月
        }catch (Exception e){
            return new RestRecord( 421, WebMessageConstants.SCE_PORTAL_MSG_421 );
        }
        String JOB_PROFESSION = model.getJobProfession(); //岗位职业
        Integer TEACH_RANGE = Integer.parseInt(model.getTeachRange()); //任课学段
        String WORK_NUMBER = model.getWorkNumber(); //工号
        Integer IS_DELETE = 1;
        int teacherEdit = userService.addTeacherInfo(USER_ID, TEACH_CERTIFICATION, TEACH_TIME, SCHOOL_TIME,
                JOB_PROFESSION, TEACH_RANGE, WORK_NUMBER,IS_DELETE);

        return new RestRecord(200, MessageConstants.SCE_MSG_0200, teacherEdit);
    }

    @ApiOperation(value = "通过用户id获取用户信息接口", notes="通过用户id获取用户信息", httpMethod = "GET")
    @GetMapping("/{userId}")
    @ResponseBody
    public RestRecord getUserById( @PathVariable( "userId" ) String userId ) {
        UserBean user = userService.getUserById(userId);
        if ( user == null ) {
            return new RestRecord( 1010, MessageConstants.SCE_MSG_1010, userId );
        } else {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, user );
        }
    }
}
