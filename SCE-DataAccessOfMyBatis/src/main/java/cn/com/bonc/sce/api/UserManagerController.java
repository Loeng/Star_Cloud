package cn.com.bonc.sce.api;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.bean.*;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.FileResourceDao;
import cn.com.bonc.sce.model.InfoTeacherModel;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.FileResourceService;
import cn.com.bonc.sce.service.UserService;
import cn.com.bonc.sce.tool.IDUtil;
import cn.com.bonc.sce.tool.IdWorker;
import cn.com.bonc.sce.tool.UserPropertiesUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Charles on 2019/3/8.
 */
@Slf4j
@RestController
@RequestMapping( "/userManager" )
public class UserManagerController {

    @Autowired
    private UserService userService;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private FileResourceService fileResourceService;

    private static final String DEFAULT_PASSWORD = "star123!";

    @ApiOperation( value = "删除用户", notes = "通过用户id，删除用户", httpMethod = "DELETE" )
    @DeleteMapping( "/delUser" )
    @ResponseBody
    public RestRecord delUser( @RequestParam( "id" ) String id ) {
        int count = userService.delUser( id );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, 1 );
        } else {
            return new RestRecord( 408, MessageConstants.SCE_MSG_408 );
        }
    }

    @ApiOperation( value = "密码重置", notes = "重置密码为初始密码", httpMethod = "PUT" )
    @PutMapping( "/resetPwd" )
    @ResponseBody
    public RestRecord resetPwd( @RequestParam( "id" ) String id ) {
        int count = userService.resetPwd( id, "star123!" );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, 1 );
        } else {
            return new RestRecord( 408, MessageConstants.SCE_MSG_407 );
        }
    }

    @ApiOperation( value = "登录权限控制", notes = "修改登录权限", httpMethod = "PUT" )
    @PutMapping( "/editLogin" )
    @ResponseBody
    public RestRecord editLogin( @RequestParam( "id" ) String id,
                                 @RequestParam( "loginPermissionStatus" ) Integer loginPermissionStatus ) {

        int newStatus = ( loginPermissionStatus == 0 ) ? 1 : 0;
        int count = userService.updateLoginPermission( id, newStatus );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, count );
        } else {
            return new RestRecord( 407, MessageConstants.SCE_MSG_407 );
        }
    }

    @ApiOperation( value = "教育机构下学校列表查询", notes = "通过教育机构id查询学校列表", httpMethod = "GET" )
    @GetMapping( "/getSchools4edu/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getSchools4edu( @RequestParam( "id" ) long id,
                                      @PathVariable( value = "pageNum" ) Integer pageNum,
                                      @PathVariable( value = "pageSize" ) Integer pageSize ) {
        PageHelper.startPage( pageNum, pageSize );
        List< SchoolBean > schoolList = userService.getSchools4edu( id );
        PageInfo pageInfo = new PageInfo( schoolList );
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, pageInfo );
    }

    @ApiOperation( value = "教育机构下学校删除", notes = "通过教育机构id和学校id删除学校", httpMethod = "DELETE" )
    @DeleteMapping( "/delSchools4edu" )
    @ResponseBody
    public RestRecord delSchools4edu( @RequestParam( "id" ) long id,
                                      @RequestParam( "institutionId" ) long institutionId ) {
        int count = userService.delSchools4edu( id, institutionId );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, 1 );
        } else {
            return new RestRecord( 408, MessageConstants.SCE_MSG_408 );
        }
    }

    @ApiOperation( value = "获取教育机构下列表", notes = "获取教育机构下列表", httpMethod = "POST" )
    @PostMapping( "/getInstitutionList/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getInstitutionList( @RequestBody String json,
                                          @PathVariable( value = "pageNum" ) Integer pageNum,
                                          @PathVariable( value = "pageSize" ) Integer pageSize ) {
        Map map = ( Map ) JSONUtils.parse( json );
        String id = ( String ) map.get( "id" );
        String institutionName = ( String ) map.get( "institutionName" );
        String loginPermissionStatus = ( String ) map.get( "loginPermissionStatus" );

        PageHelper.startPage( pageNum, pageSize );
        List< Map > list = userService.getInstitutions( id, institutionName, loginPermissionStatus );
        PageInfo pageInfo = new PageInfo( list );
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, pageInfo );
    }

    @ApiOperation( value = "获取教育机构下列表", notes = "获取教育机构下列表", httpMethod = "GET" )
    @GetMapping( "/getInstitutions" )
    @ResponseBody
    public List< Map > getInstitutions( @RequestParam( "id" ) String id,
                                        @RequestParam( "institutionName" ) String institutionName,
                                        @RequestParam( "loginPermissionStatus" ) String loginPermissionStatus ) {
        List< Map > list = userService.getInstitutions( id, institutionName, loginPermissionStatus );
        return list;
    }

    @Transactional
    @ApiOperation( value = "用户注册（登陆页的注册和用户管理页的新增用户接口共用）", notes = "用户注册", httpMethod = "POST" )
    @PostMapping( "/register" )
    @ResponseBody
    public RestRecord register( @RequestBody @ApiParam( example = "{\"loginName\": \"测试张\",\"valid\": \"123456\",\"password\":\"password\",\"phoneNumber\": \"12345678901\",\"userType\": 1,\"userName\": \"测试张\"}" ) String json ) {
        Map map = ( Map ) JSONUtils.parse( json );
        String loginName = ( String ) map.get( "loginName" );
        String password = ( String ) map.get( "password" );
        String phoneNumber = ( String ) map.get( "phoneNumber" );
        Integer userType = Integer.parseInt( map.get( "userType" ).toString() );
        String userName = ( String ) map.get( "userName" );
        Integer certificateType = map.get( "certificateType" ) == null ? null : Integer.parseInt( ( map.get( "certificateType" ).toString() ) );
        String certificateNumber = ( String ) map.get( "certificateNumber" );


        UserBean user = new UserBean();
        Long userId = idWorker.nextId();
        user.setLoginName( loginName );
        user.setUserType( userType );
        user.setPhoneNumber( phoneNumber );
        user.setUserName( userName );
        user.setIsDelete( 1 );
        user.setUserId( userId );

        //如果没传身份证号，说明是登陆页注册，不用激活
        if ( certificateNumber == null ) {
            user.setAccountStatus( 1 );
        } else {
            user.setAccountStatus( 0 );
        }
        user.setSecret( Secret.ES256GenerateSecret() );
        user.setCertificateNumber( certificateNumber );
        user.setCertificateType( certificateType );

        if ( userService.isExist( loginName ) > 0 ) {
            return new RestRecord( 1022, MessageConstants.SCE_MSG_1022 );
        }
        //判断手机号是否已被注册
        if ( !StringUtils.isEmpty( userService.getIdByPhone( ( String ) map.get( "phoneNumber" ) ) ) ) {
            return new RestRecord( 1023, MessageConstants.SCE_MSG_1023 );
        }

        AccountBean account = new AccountBean();
        long accountId = idWorker.nextId();
        account.setId( accountId );
        account.setPassword( password );
        account.setIsDelete( 1 );
        account.setUserId( userId );

        if ( userService.saveUser( user ) == 1 && userService.saveAccount( account ) == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, 1 );
        } else {
            return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
        }
    }

    @ApiOperation( value = "通过用户名获取电话号码", notes = "忘记密码时的身份认证", httpMethod = "GET" )
    @GetMapping( "/getPhone" )
    @ResponseBody
    public RestRecord getPhone( @RequestParam( "loginName" ) String loginName ) {
        Map map = userService.getPhone( loginName );
        if ( map != null ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, map );
        } else {
            return new RestRecord( 1024, MessageConstants.SCE_MSG_1024 );
        }
    }

    @ApiOperation( value = "通过用户名修改密码", notes = "重设密码(激活操作)", httpMethod = "PUT" )
    @PutMapping( "/updatePwdByName" )
    @ResponseBody
    public RestRecord updatePwdByName( @RequestParam( "loginName" ) String loginName,
                                       @RequestParam( "password" ) String password ) {
        int count = userService.updatePwdByName( loginName, password );
        int count2 = userService.updateAccountStatusByName(loginName,1);
        if ( count == 1 && count2 == 1) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, 1 );
        } else {
            return new RestRecord( 407, MessageConstants.SCE_MSG_407 );
        }
    }

    @ApiOperation( value = "通过用户名和输入的身份证信息在后台验证", notes = "验证身份证信息是否正确", httpMethod = "GET" )
    @GetMapping( "/testCertificate" )
    @ResponseBody
    public RestRecord testCertificate( @RequestParam( "loginName" ) String loginName,
                                       @RequestParam( "certificate" ) String certificate ) {
        String tempCer = userService.testCertificate( loginName );
        if ( certificate.equals( tempCer ) ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 1010, "身份证验证失败" );
        }
    }

    @ApiOperation( value = "获取在职教师列表", notes = "获取查询条件，返回在职教师列表", httpMethod = "GET" )
    @GetMapping( "/getTeachers/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getTeachers( @RequestParam( "organizationId" ) long organizationId,
                                   @RequestParam( value = "userName", required = false ) String userName,
                                   @RequestParam( value = "loginName", required = false ) String loginName,
                                   @RequestParam( value = "gender", required = false ) String gender,
                                   @RequestParam( value = "position", required = false ) String position,
                                   @RequestParam( value = "accountStatus", required = false ) String accountStatus,
                                   @PathVariable( value = "pageNum" ) Integer pageNum,
                                   @PathVariable( value = "pageSize" ) Integer pageSize ) {
        PageHelper.startPage( pageNum, pageSize );
        List< Map > teachers = userService.getTeachers( organizationId, userName, loginName, gender, position, accountStatus );
        PageInfo pageInfo = new PageInfo( teachers );
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, pageInfo );
    }

    @ApiOperation( value = "删除教师", notes = "通过用户id，删除用户", httpMethod = "DELETE" )
    @DeleteMapping( "/delTeacher" )
    @ResponseBody
    @Transactional
    public RestRecord delTeacher( @RequestParam( "id" ) String id ) {
        int count1 = userService.delUser( id );
        int count2 = userService.delTeacher( id );
        int count3 = userService.delPassword( id );
        if ( count1 == 1 && count2 == 1 && count3 == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, 1 );
        } else {
            return new RestRecord( 408, MessageConstants.SCE_MSG_408 );
        }
    }

    @ApiOperation( value = "通过教师id获取教师详细信息", notes = "通过教师id获取教师详细信息", httpMethod = "GET" )
    @GetMapping( "/getTeacherInfo" )
    @ResponseBody
    public RestRecord getTeacherInfo( @RequestParam( "id" ) String id ) {
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, userService.getTeacherInfo( id ) );
    }

    @ApiOperation( value = "通过教师id修改教师详细信息", notes = "通过教师id修改教师详细信息", httpMethod = "PUT" )
    @PutMapping( "/editTeacherInfo" )
    @ResponseBody
    @Transactional
    public RestRecord editTeacherInfo( @RequestBody String json, @CurrentUserId String userId ) {
        Integer isAdministrators = userService.selectIsAdministortars(userId);
        // 拒绝不是管理员的修改
        if(isAdministrators == null || isAdministrators == 0){
            return new RestRecord( 432, WebMessageConstants.SCE_PORTAL_MSG_436 );
        }
        Map map = ( Map ) JSONUtils.parse( json );
        Integer ISADMINISTRATORS = Integer.parseInt(map.get( "ISADMINISTRATORS" ).toString());
        String USER_ID = ( String ) map.get( "USER_ID" );
        if(userId.equals(USER_ID) && ISADMINISTRATORS == 0 ){
            return new RestRecord( 432, WebMessageConstants.SCE_PORTAL_MSG_436 );
        }
        Integer CERTIFICATE_TYPE = ( Integer ) map.get( "CERTIFICATE_TYPE" );
        String CERTIFICATE_NUMBER = ( String ) map.get( "CERTIFICATE_NUMBER" );
        if ( CERTIFICATE_TYPE == 1 && !UserPropertiesUtil.checkCertificateNumber( CERTIFICATE_NUMBER ) ) {
            log.info( "教师身份证验证未通过" );
            return new RestRecord( 432, "身份证填写不正确" );
        }
        String USER_NAME = ( String ) map.get( "USER_NAME" );
        String GENDER = ( String ) map.get( "GENDER" );
        String PHONE_NUMBER = ( String ) map.get( "PHONE_NUMBER" );
        if ( !UserPropertiesUtil.checkPhone( PHONE_NUMBER ) ) {
            log.info( "教师身手机号验证未通过" );
            return new RestRecord( 432, "手机号填写不正确" );
        }
        String MAIL_ADDRESS = ( String ) map.get( "MAIL_ADDRESS" );
        if ( !UserPropertiesUtil.checkMail( MAIL_ADDRESS ) ) {
            log.info( "教师身邮箱证未通过" );
            return new RestRecord( 432, "邮箱填写不正确" );
        }
        String BIRTHDATE = ( String ) map.get( "BIRTHDATE" );
        String NATION_CODE = ( String ) map.get( "NATION_CODE" );
        String NATIONLITY = ( String ) map.get( "NATIONLITY" );
        String ACADEMIC_QUALIFICATION = ( String ) map.get( "ACADEMIC_QUALIFICATION" );
        String WORK_NUMBER = ( String ) map.get( "WORK_NUMBER" );
        String SCHOOL_TIME = ( String ) map.get( "SCHOOL_TIME" );
        String TEACH_TIME = ( String ) map.get( "TEACH_TIME" );
        String POSITION = ( String ) map.get( "POSITION" );
        Integer TEACH_RANGE = ( Integer ) map.get( "TEACH_RANGE" );


        int userEdit = userService.editUser( USER_ID, CERTIFICATE_TYPE, CERTIFICATE_NUMBER, USER_NAME,
                GENDER, PHONE_NUMBER, MAIL_ADDRESS, BIRTHDATE, NATION_CODE, NATIONLITY, ISADMINISTRATORS );
        int teacherEdit = userService.editTeacher( USER_ID, ACADEMIC_QUALIFICATION,
                WORK_NUMBER, SCHOOL_TIME, TEACH_TIME, POSITION, TEACH_RANGE );

        if ( userEdit == 1 && teacherEdit == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, 1 );
        } else {
            return new RestRecord( 407, MessageConstants.SCE_MSG_407, 0 );
        }
    }

    @ApiOperation( value = "添加教师", notes = "直接添加或者通过转入添加，通过前端传入的addType判断添加方式", httpMethod = "POST" )
    @PostMapping( "/addTeacher" )
    @ResponseBody
    @Transactional
    public RestRecord addTeacher( @RequestBody String json, @CurrentUserId String userId ) {
        Integer isAdministrators = userService.selectIsAdministortars(userId);
        // 拒绝不是管理员的添加
        if(isAdministrators == null || isAdministrators == 0){
            return new RestRecord( 432, WebMessageConstants.SCE_PORTAL_MSG_436 );
        }
        Map map = ( Map ) JSONUtils.parse( json );
        Integer ADDTYPE = Integer.parseInt( map.get( "ADDTYPE" ).toString() );
        if ( ADDTYPE == 0 ) {//建新用户
            String USER_ID = UUID.randomUUID().toString().replace( "-", "" ).toLowerCase();
            String ORGANIZATION_ID = ( String ) map.get( "ORGANIZATION_ID" );
            String ISADMINISTRATORS = ( String ) map.get( "ISADMINISTRATORS" );
            if ( ORGANIZATION_ID == null ) {
                return new RestRecord( 432, "当前账户任何学校相关信息，无法添加" );
            }
            Integer CERTIFICATE_TYPE = ( Integer ) map.get( "CERTIFICATE_TYPE" );
            String CERTIFICATE_NUMBER = ( String ) map.get( "CERTIFICATE_NUMBER" );
            if ( CERTIFICATE_TYPE == 1 && !UserPropertiesUtil.checkCertificateNumber( CERTIFICATE_NUMBER ) ) {
                log.info( "教师身份证验证未通过" );
                return new RestRecord( 432, "身份证填写不正确" );
            }
            if ( userService.selectCountByCertificateNumber( CERTIFICATE_TYPE.toString(), CERTIFICATE_NUMBER ) > 0 ) {
                return new RestRecord( 432, "身份证已被使用" );
            }
            String USER_NAME = ( String ) map.get( "USER_NAME" );
            String GENDER = ( String ) map.get( "GENDER" );
            String PHONE_NUMBER = ( String ) map.get( "PHONE_NUMBER" );
            if ( !UserPropertiesUtil.checkPhone( PHONE_NUMBER ) ) {
                log.info( "教师身手机号验证未通过" );
                return new RestRecord( 432, "手机号填写不正确" );
            }
            if ( userService.selectCountByPhoneNumber( PHONE_NUMBER ) > 0 ) {
                return new RestRecord( 432, "手机号已被使用" );
            }
            String MAIL_ADDRESS = ( String ) map.get( "MAIL_ADDRESS" );
            if ( !UserPropertiesUtil.checkMail( MAIL_ADDRESS ) ) {
                log.info( "教师身邮箱证未通过" );
                return new RestRecord( 432, "邮箱填写不正确" );
            }
            if ( userService.selectCountByMailAddress( MAIL_ADDRESS ) > 0 ) {
                return new RestRecord( 432, "邮箱已被使用" );
            }
            String BIRTHDATE = ( String ) map.get( "BIRTHDATE" );
            String NATION_CODE = ( String ) map.get( "NATION_CODE" );
            String NATIONLITY = ( String ) map.get( "NATIONLITY" );
            String ACADEMIC_QUALIFICATION = ( String ) map.get( "ACADEMIC_QUALIFICATION" );
            String WORK_NUMBER = ( String ) map.get( "WORK_NUMBER" );
            String SCHOOL_TIME = ( String ) map.get( "SCHOOL_TIME" );
            String TEACH_TIME = ( String ) map.get( "TEACH_TIME" );
            String POSITION = ( String ) map.get( "POSITION" );
            Integer TEACH_RANGE = ( Integer ) map.get( "TEACH_RANGE" );
            String secret = Secret.ES256GenerateSecret();
            String loginName = IDUtil.createID( "js_" );
            int count1 = userService.addUser( USER_ID, CERTIFICATE_TYPE, CERTIFICATE_NUMBER,
                    USER_NAME, GENDER, PHONE_NUMBER, ORGANIZATION_ID, MAIL_ADDRESS, BIRTHDATE, NATIONLITY, NATION_CODE, secret, "2", loginName, Integer.parseInt(ISADMINISTRATORS) );
            int count2 = userService.addTeacher( USER_ID, ACADEMIC_QUALIFICATION,
                    WORK_NUMBER, SCHOOL_TIME, TEACH_TIME, POSITION, TEACH_RANGE );
            userService.addPassword( idWorker.nextId(), USER_ID, DEFAULT_PASSWORD );
            if ( count1 == 1 && count2 == 1 ) {
                return new RestRecord( 200, MessageConstants.SCE_MSG_0200, 1 );
            } else {
                return new RestRecord( 409, MessageConstants.SCE_MSG_409, 0 );
            }
        } else {//转入
            Integer CERTIFICATE_TYPE = ( Integer ) map.get( "CERTIFICATE_TYPE" );
            String CERTIFICATE_NUMBER = ( String ) map.get( "CERTIFICATE_NUMBER" );
            String PHONE_NUMBER = ( String ) map.get( "PHONE_NUMBER" );
            String APPLY_USER_ID = ( String ) map.get( "APPLY_USER_ID" );
            String TEA_WORK_NUMBER = ( String ) map.get( "WORK_NUMBER" );
            String TEA_ENTRANCE_YEAR = ( String ) map.get( "SCHOOL_TIME" );
            String ISADMINISTRATORS = ( String ) map.get( "ISADMINISTRATORS" );
            if ( CERTIFICATE_TYPE == 1 && !UserPropertiesUtil.checkCertificateNumber( CERTIFICATE_NUMBER ) ) {
                log.info( "教师身份证验证未通过" );
                return new RestRecord( 432, "身份证填写不正确" );
            }
            Map userInfo = userService.selectUserIdAndOrganizationId( CERTIFICATE_NUMBER, CERTIFICATE_TYPE.toString(), "2" );
            if ( userInfo == null ) {
                return new RestRecord( 432, "请输入已录入教师的证件号" );
            }
            Long ID = idWorker.nextId();
            //通过证件类型和证件号码查询用户ID
            Map teacher = userService.getUserId( CERTIFICATE_TYPE.toString(), CERTIFICATE_NUMBER );
            String USER_ID = teacher.get( "USER_ID" ).toString();
            //检查该教师是否已是本校教师
            String organizationId = userService.getOrganizationIdByUserId( APPLY_USER_ID );
            if ( organizationId == null ) {
                return new RestRecord( 432, "当前账户任何学校相关信息，无法添加" );
            }
            if ( userInfo.get( "ORGANIZATION_ID" ).toString().equals( organizationId ) ) {
                return new RestRecord( 434, "该教师已是本校教师，无需申请转入" );
            }
            //通过申请人查看组织id 并检查该教师是否已申请转入本学校
            int count = userService.selectTransfer( USER_ID, organizationId );
            if ( count > 0 ) {
                return new RestRecord( 435, String.format( WebMessageConstants.SCE_PORTAL_MSG_435, "已提交转入申请" ) );
            }
            SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM" );
            Date ENTRANCE_YEAR = null;
            try {
                ENTRANCE_YEAR = format.parse( TEA_ENTRANCE_YEAR );
            } catch ( ParseException e ) {
                return new RestRecord( 409, MessageConstants.SCE_MSG_409, 0 );
            }
            String TEA_POSITION = ( String ) map.get( "POSITION" );
            String TEACH_RANGE = map.get( "TEACH_RANGE" ).toString();
            String ORIGIN_SCHOOL_ID = teacher.get( "ORGANIZATION_ID" ).toString();
            String TARGET_SCHOOL_ID = ( String ) map.get( "TARGET_SCHOOL_ID" );
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200,
                    userService.transInto( ID, USER_ID, APPLY_USER_ID, ORIGIN_SCHOOL_ID, TARGET_SCHOOL_ID, TEA_WORK_NUMBER, ENTRANCE_YEAR, TEA_POSITION, TEACH_RANGE, Integer.parseInt(ISADMINISTRATORS) ) );
        }

    }

    @ApiOperation( value = "获取转入转出教师列表", notes = "获取查询条件，返回教师列表", httpMethod = "GET" )
    @GetMapping( "/getTransferTeachers/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getTransferTeachers( @RequestParam( value = "getType" ) Integer getType, @RequestParam( "organizationId" ) long organizationId,
                                           @RequestParam( value = "userName", required = false ) String userName,
                                           @RequestParam( value = "loginName", required = false ) String loginName,
                                           @RequestParam( value = "gender", required = false ) String gender,
                                           @RequestParam( value = "position", required = false ) String position,
                                           @RequestParam( value = "accountStatus", required = false ) Integer accountStatus,
                                           @PathVariable( value = "pageNum" ) Integer pageNum,
                                           @PathVariable( value = "pageSize" ) Integer pageSize ) {
        PageHelper.startPage( pageNum, pageSize );
        List< Map > teachers = userService.getTransferTeachers( getType, organizationId, userName, loginName, gender, position, accountStatus );
        PageInfo pageInfo = new PageInfo( teachers );
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, pageInfo );
    }

    @GetMapping( "/getStudents/{pageNum}/{pageSize}" )
    public RestRecord getStudents( @RequestParam( value = "userName", required = false ) String userName,
                                   @RequestParam( value = "loginName", required = false ) String loginName,
                                   @RequestParam( value = "studentNumber", required = false ) String studentNumber,
                                   @RequestParam( value = "gender", required = false ) String gender,
                                   @RequestParam( value = "grade", required = false ) String grade,
                                   @RequestParam( value = "accountStatus", required = false ) String accountStatus,
                                   @PathVariable String pageNum,
                                   @PathVariable String pageSize,
                                   @CurrentUserId String userId ) {
        return userService.getStudents( userName, loginName, studentNumber, gender, grade, accountStatus, userId, pageNum, pageSize );
    }

    @GetMapping( "/getStudentInfo/{userId}" )
    public RestRecord getStudentInfo( @PathVariable( "userId" ) String userId ) {
        return userService.getStudentInfo( userId );
    }

    @PutMapping( "/editStudent" )
    public RestRecord editStudent( @RequestBody Map map ) {
        return userService.editStudent( map );
    }

    @DeleteMapping( "delStudent" )
    public RestRecord delStudent( @RequestParam String userId ) {
        return userService.delStudent( userId );
    }

    /**
     * 根据参数添加学生
     *
     * @param map    transferType 新建类型，1新建学生，2外校转入
     *               bindType 绑定类型，1新建家长，2绑定现有家长
     *               必须字段：studentCertificateType 学生证件类型，studentCertificateNumber 学生证件号，entranceYear 入学年月（格式YYYY-MM-dd）
     *               grade 年级，classNumber 班级，seatNumber 座号，studentNumber 学号，studentUserName 学生姓名，studentBirthDate 学生生日
     *               studentNationality 学生国籍，studentVolk 学生民族，parentCertificateType 家长证件类型，parentCertificateNumber 家长证件号
     *               relationship 家长学生关系
     *               新建家长的必需字段：parentUserName 家长姓名，parentNationality 家长国籍，parentVolk 家长民族，parentPhoneNumber 家长手机号
     *               不必需字段：studentGender 学生性别，studentCode 学生学籍号，parentGender 家长性别，parentMailAddress 家长电子邮箱
     * @param userId 当前用户id
     * @return RestRecord
     */
    @PostMapping( "/addStudent" )
    @Transactional( rollbackFor = Exception.class )
    public RestRecord addStudent( @RequestBody Map map,
                                  @CurrentUserId String userId ) {
        String transferType = map.get( "transferType" ).toString();
        RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        if ( transferType.equals( "1" ) ) {
            restRecord = userService.addStudent( map, userId );
        } else if ( transferType.equals( "2" ) ) {
            restRecord = userService.transferInStudent( map, userId );
        }
        return restRecord;
    }

    @ApiOperation( value = "通过用户id修改教师从业信息", notes = "通过用户id修改教师从业信息", httpMethod = "PUT" )
    @PutMapping( "/editTeacherPracticeInfo" )
    @ResponseBody
    public RestRecord editTeacherPracticeInfo( @RequestBody @ApiParam( "教师从业信息对象" ) InfoTeacherModel model ) {

        String USER_ID = model.getUserId(); //用户ID
        String TEACH_CERTIFICATION = model.getTeachCertification(); //教师资格证号
        DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        Date TEACH_TIME;
        Date SCHOOL_TIME;
        try {
            TEACH_TIME = sdf.parse( model.getSchoolAge() );  //参加工作年月
            SCHOOL_TIME = sdf.parse( model.getSchoolTime() ); //来校年月
        } catch ( Exception e ) {
            return new RestRecord( 421, WebMessageConstants.SCE_PORTAL_MSG_421 );
        }
        String JOB_PROFESSION = model.getJobProfession(); //岗位职业
        Integer TEACH_RANGE = Integer.parseInt( model.getTeachRange() ); //任课学段
        String WORK_NUMBER = model.getWorkNumber(); //工号

        int teacherEdit = userService.editTeacherPracticeInfo( USER_ID, TEACH_CERTIFICATION, TEACH_TIME, SCHOOL_TIME,
                JOB_PROFESSION, TEACH_RANGE, WORK_NUMBER );

        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, teacherEdit );
    }

    @GetMapping( "/getParentInfo/{certificationType}/{certificationNumber}/{userType}" )
    public RestRecord getParentInfo( @PathVariable String certificationType, @PathVariable String certificationNumber, @PathVariable String userType ) {
        return userService.getParentInfo( certificationType, certificationNumber, userType );
    }

    @GetMapping( "/getTransferStudent/{pageNum}/{pageSize}" )
    public RestRecord getTransferStudent( @RequestParam( value = "userName", required = false ) String userName,
                                          @RequestParam( value = "loginName", required = false ) String loginName,
                                          @RequestParam( value = "studentNumber", required = false ) String studentNumber,
                                          @RequestParam( value = "gender", required = false ) String gender,
                                          @RequestParam( value = "grade", required = false ) String grade,
                                          @RequestParam( value = "applyStatus", required = false ) String applyStatus,
                                          @RequestParam( value = "transferType" ) String transferType,
                                          @PathVariable String pageNum,
                                          @PathVariable String pageSize,
                                          @CurrentUserId String userId ) {
        return userService.getTransferStudent( userName, loginName, studentNumber, gender, grade, applyStatus, transferType, pageNum, pageSize, userId );
    }

    @DeleteMapping( "/repealApply/{transferId}" )
    public RestRecord repealApply( @CurrentUserId String userId,
                                   @PathVariable String transferId ) {
        return userService.repealApply( userId, transferId );
    }

    @PutMapping( "/reCall/{transferId}" )
    public RestRecord reCall( @PathVariable String transferId ) {
        return userService.reCall( transferId );
    }

    @GetMapping( "/getTransferOut/{transferId}" )
    public RestRecord getTransferOut( @PathVariable String transferId ) {
        return userService.getTransferOut( transferId );
    }

    /**
     * 学生转出申请的审核
     *
     * @param userId 用户ID
     * @param map    参数 applyStatus：1通过，2不通过；id：申请ID；rejectReason，不通过原因
     * @return RestRecord
     */
    @PutMapping( "/auditTransfer" )
    public RestRecord auditTransfer( @CurrentUserId String userId, @RequestBody Map map ) {
        return userService.auditTransfer( userId, map );
    }

    @ApiOperation( value = "通过用户id获取教师从业信息接口", notes = "通过用户id获取教师从业信息", httpMethod = "GET" )
    @GetMapping( "/getTeacherInfoById/{userId}" )
    @ResponseBody
    public RestRecord getTeacherInfoById( @PathVariable( "userId" ) String userId ) {
        TeacherInfoBean user = userService.getTeacherInfoById( userId );
        if ( user == null ) {
            return new RestRecord( 1010, MessageConstants.SCE_MSG_1010, userId );
        } else {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, user );
        }
    }

    @ApiOperation( value = "新增教师从业信息接口", notes = "新增教师从业信息", httpMethod = "POST" )
    @PostMapping( "/addTeacherInfo" )
    @ResponseBody
    public RestRecord addTeacherInfo( @RequestBody @ApiParam( "教师从业信息对象" ) InfoTeacherModel model ) {

        String USER_ID = model.getUserId(); //用户ID
        String TEACH_CERTIFICATION = model.getTeachCertification(); //教师资格证号
        DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        Date TEACH_TIME;
        Date SCHOOL_TIME;
        try {
            TEACH_TIME = sdf.parse( model.getSchoolAge() );  //参加工作年月
            SCHOOL_TIME = sdf.parse( model.getSchoolTime() ); //来校年月
        } catch ( Exception e ) {
            return new RestRecord( 421, WebMessageConstants.SCE_PORTAL_MSG_421 );
        }
        String JOB_PROFESSION = model.getJobProfession(); //岗位职业
        Integer TEACH_RANGE = Integer.parseInt( model.getTeachRange() ); //任课学段
        String WORK_NUMBER = model.getWorkNumber(); //工号
        Integer IS_DELETE = 1;
        int teacherEdit = userService.addTeacherInfo( USER_ID, TEACH_CERTIFICATION, TEACH_TIME, SCHOOL_TIME,
                JOB_PROFESSION, TEACH_RANGE, WORK_NUMBER, IS_DELETE );

        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, teacherEdit );
    }

    @ApiOperation( value = "通过用户id获取用户信息接口", notes = "通过用户id获取用户信息", httpMethod = "GET" )
    @GetMapping( "/{userId}" )
    @ResponseBody
    public RestRecord getUserById( @PathVariable( "userId" ) String userId ) {
        UserBean user = userService.getUserById( userId );
        if ( user == null ) {
            return new RestRecord( 1010, MessageConstants.SCE_MSG_1010, userId );
        } else {
            String fileStorePath = "";
            if(user.getHeadPortrait()!=null){
                fileStorePath = fileResourceService.getFileStorePath(Integer.parseInt(user.getHeadPortrait()));
            }
            user.setFileStorePath(fileStorePath);
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, user );
        }
    }

    @GetMapping( "/getTransferTeacherInfo/{transferId}" )
    public RestRecord getTransferTeacherInfo( @PathVariable String transferId ) {
        return userService.getTransferTeacherInfo( transferId );
    }

    /**
     * 教师转出申请的审核
     *
     * @param userId 用户ID
     * @param map    参数 applyStatus：1通过，2不通过；id：申请ID；rejectReason，不通过原因
     * @return RestRecord
     */
    @PutMapping( "/auditTeacher" )
    public RestRecord auditTeacher( @CurrentUserId String userId, @RequestBody Map map ) {
        return userService.auditTeacher( userId, map );
    }

    @PutMapping( "/reCallTeacher" )
    public RestRecord reCallTeacher( @RequestBody Map map ) {
        return userService.reCallTeacher( map );
    }

    @ApiOperation(value = "审核认证申请接口", notes="审核认证申请", httpMethod = "PUT")
    @PutMapping("/updateAudit")
    @ResponseBody
    public RestRecord updateAudit(@CurrentUserId String auditUserId,@RequestBody Map map){
        return userService.updateAudit(auditUserId,map);
    }

    @ApiOperation(value = "审核认证详情接口", notes="审核认证详情", httpMethod = "GET")
    @GetMapping("/getAudit/{userId}")
    @ResponseBody
    public RestRecord getAudit(@PathVariable(value = "userId") String userId){
        UserAuditBean user = userService.getAudit( userId );
        if ( user == null ) {
            return new RestRecord( 1010, MessageConstants.SCE_MSG_1010, userId );
        } else {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, user );
        }
    }
}
