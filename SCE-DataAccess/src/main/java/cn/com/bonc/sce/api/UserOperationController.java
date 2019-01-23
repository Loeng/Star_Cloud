package cn.com.bonc.sce.api;


import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.UserInfoRepository;
import cn.com.bonc.sce.dao.UserPasswordDao;
import cn.com.bonc.sce.entity.UserPassword;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.model.UserModel;
import cn.com.bonc.sce.rest.RestRecord;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.HashMap;

import java.util.Map;
import java.util.UUID;

/**
 * @author 管理员
 * @Auther: tlz
 * @Date: 2018/12/22 10:54
 * @Description:
 */

@Slf4j
@RestController
@RequestMapping( "/user-info" )
public class UserOperationController {

    public static final String PASSWORD = "star123!";
    public static final Integer LOGIN_PERMISSION_STATUS = 1;
    public static final Integer IS_DELETE = 1;

    private UserInfoRepository userInfoRepository;

    @Autowired
    public UserOperationController( UserInfoRepository userInfoRepository ) {
        this.userInfoRepository = userInfoRepository;
    }

    @Autowired
    private UserPasswordDao passwordDao;

    /**
     * 添加用户信息
     *
     * @param userInfo
     * @return 是否添加成功
     */
    @PostMapping( "/addUser/{roleId}" )
    @ResponseBody
    public RestRecord addUserInfo(
            @PathVariable( "roleId" ) Integer roleId,
            @RequestBody() Map< String, Object > userInfo ) {
        String USER_NAME = "";
        if ( !CollectionUtils.isEmpty( userInfo ) ) {
            log.info( "添加新用户，新用户信息为[{}]", userInfo );
            switch ( roleId ) {
                case 1:
                    // 添加学生
                    addStudent( userInfo );
                    break;
                case 2:
                    // 添加教师
                    addTeacher( userInfo );
                    break;
            }

        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    // 添加教师用户
    private void addTeacher( Map< String, Object > userInfo ) {
        addCommonUser( 2, userInfo );
        // 插入资料表信息待定
    }

    // 添加学生用户
    private void addStudent( Map< String, Object > userInfo ) {
        addCommonUser( 1, userInfo );
        // 插入资料表信息待定
    }

    // 添加用户基本信息（SCE_COMMON_USER,SCE_COMMON_USER_PASSWORD）
    private void addCommonUser( int USER_TYPE, Map< String, Object > userInfo ) {
        String USER_ID = UUID.randomUUID().toString().replaceAll( "-", "" );
        String LOGIN_NAME = "";
        String USER_NAME = "";
        String GENDER = "";
        String MAIL_ADDRESS = "";
        int CERTIFICATE_TYPE = 1; // 身份证类型（身份证、护照、驾驶证） 1. 身份证
        String CERTIFICATE_NUMBER = "";
        String PHONE_NUMBER = "";
        String ADDRESS = "";
        Date CREATE_TIME = new Date();
        String ORGANIZATION_ID = "";
        String PASSWORD = "";
        String SECRET = Secret.generateSecret();
        if ( StringUtils.isNotBlank( userInfo.get( "LOGIN_NAME" ) + "" ) ) {
            LOGIN_NAME = userInfo.get( "LOGIN_NAME" ) + "";
        }
        if ( StringUtils.isNotBlank( userInfo.get( "USER_NAME" ) + "" ) ) {
            USER_NAME = userInfo.get( "USER_NAME" ) + "";
        }
        if ( StringUtils.isNotBlank( userInfo.get( "GENDER" ) + "" ) ) {
            GENDER = userInfo.get( "GENDER" ) + "";
        }
        if ( StringUtils.isNotBlank( userInfo.get( "MAIL_ADDRESS" ) + "" ) ) {
            MAIL_ADDRESS = userInfo.get( "MAIL_ADDRESS" ) + "";
        }
        if ( StringUtils.isNotBlank( userInfo.get( "CERTIFICATE_NUMBER" ) + "" ) ) {
            CERTIFICATE_NUMBER = userInfo.get( "CERTIFICATE_NUMBER" ) + "";
        }
        if ( StringUtils.isNotBlank( userInfo.get( "PHONE_NUMBER" ) + "" ) ) {
            PHONE_NUMBER = userInfo.get( "PHONE_NUMBER" ) + "";
        }
        if ( StringUtils.isNotBlank( userInfo.get( "ADDRESS" ) + "" ) ) {
            ADDRESS = userInfo.get( "ADDRESS" ) + "";
        }
        if ( StringUtils.isNotBlank( userInfo.get( "ORGANIZATION_ID" ) + "" ) ) {
            ORGANIZATION_ID = userInfo.get( "ORGANIZATION_ID" ) + "";

        }
        if ( StringUtils.isNotBlank( userInfo.get( "PASSWORD" ) + "" ) ) {
            PASSWORD = userInfo.get( "PASSWORD" ) + "";
        }
        // 插入用户密码表数据
        UserPassword password = new UserPassword();
        password.setUserId( USER_ID );
        password.setPassword( PASSWORD );
        password.setIsDelete( IS_DELETE );

        userInfoRepository.addCommonUser( USER_ID, LOGIN_NAME, USER_NAME, GENDER, USER_TYPE, MAIL_ADDRESS, CERTIFICATE_TYPE, CERTIFICATE_NUMBER, PHONE_NUMBER, ADDRESS, CREATE_TIME, ORGANIZATION_ID, LOGIN_PERMISSION_STATUS, IS_DELETE, SECRET );
        passwordDao.save( password );
        log.info( "插入用户基本信息[{}]成功，用户类型为[{}]", userInfo, USER_TYPE == 1 ? "学生" : "教师" );
    }

    /**
     * 修改用户信息（登录状态、重置密码、删除）
     *
     * @param userInfo 用户信息
     * @param userId   修改的用户ID
     * @return 修改是否成功
     */
    @PutMapping( "/updateUserInfo" )
    @ResponseBody
    public RestRecord updateTeacherRecommendAppInfo( @RequestBody Map< String, Object > userInfo, @RequestParam( "userId" ) String userId ) {
        int isLogin;
        int isDelete;
        String isReset;
        int status = 0;
        if ( !CollectionUtils.isEmpty( userInfo ) ) {
            if ( null != userInfo.get( "isLogin" ) && !"".equals( userInfo.get( "isLogin" ) ) ) {
                isLogin = ( int ) userInfo.get( "isLogin" );
                log.info( "更正用户[{}]的登录权限[{}]", userId, isLogin );
                status = userInfoRepository.updateUserLoginStatus( isLogin, userId );
            } else if ( null != userInfo.get( "isDelete" ) && !"".equals( userInfo.get( "isDelete" ) ) ) {
                isDelete = ( int ) userInfo.get( "isDelete" );
                log.info( "删除用户[{}]-[{}]", userId, isDelete );
                status = userInfoRepository.deleteUser( isDelete, userId );
            } else if ( null != userInfo.get( "isReset" ) && !"".equals( userInfo.get( "isReset" ) ) ) {
                isReset = PASSWORD;
                log.info( "更正用户[{}]的密码[{}]", userId, isReset );
                status = userInfoRepository.resetUserPassword( isReset, userId );
            }
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, status );
    }

    /**
     * 删除用户信息
     *
     * @param userId 用户ID
     * @return 删除是否成功
     */
    @DeleteMapping
    @ResponseBody
    public RestRecord deleteUserInfo(
            @RequestParam( "userId" ) String userId ) {

        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, "" );
    }

    /**
     * 查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping
    @ResponseBody
    public RestRecord selectUserInfo(
            @RequestParam( "userId" ) String userId ) {
        Map map = new HashMap<>();
        map.put( "userId", "43999" );
        map.put( "userAccount", "X213212123" );
        map.put( "userName", "loader" );
        map.put( "userId", "X213212123" );
        map.put( "gender", "1" );
        map.put( "userType", "2" );
        map.put( "mailAddress", "123112@qq.com" );
        map.put( "phoneNumber", "186678XXX88" );
        map.put( "certificateType", "2" );
        map.put( "certificateNumber", "XR5441161X" );
        map.put( "address", "成都市青羊区青年路" );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, map );
    }

    @PostMapping( "/insert" )
    @ResponseBody
    public RestRecord insertUser( @RequestBody UserModel userModel ) {
//        1	学生;2	教师;3	学校;4	厂家;5	家长;6	代理商;7	机构
        userModel.setSecret( Secret.generateSecret() );
        userModel.setUserId( UUID.randomUUID().toString().replaceAll( "-", "" ) );
        userModel.setCreateTime( new Date() );

        //插入基本信息
        userInfoRepository.insertUser( userModel.getUserId(), userModel.getLoginName(), userModel.getUserName()
                , userModel.getGender(), userModel.getUserType(), userModel.getMailAddress(), userModel.getCertificateType(), userModel.getCertificateNumber()
                , userModel.getPhoneNumber(), userModel.getAddress(), userModel.getCreateTime(), userModel.getOrganizationId(), userModel.getRemarks(), userModel.getSecret() );

        //插入密码
        UserPassword password = new UserPassword();
        password.setUserId( userModel.getUserId() );
        password.setPassword( userModel.getPassword() == null ? "star123!" : userModel.getPassword() );
        passwordDao.save( password );

        //添加用户的代理商资料
        if ( userModel.getInfoAgentModel() != null ) {
            userInfoRepository.insertInfoAgent( userModel.getUserId(),
                    userModel.getInfoAgentModel().getAgentId(),
                    userModel.getInfoAgentModel().getRemarks() );
            return new RestRecord( 256, WebMessageConstants.SCE_PORTAL_MSG_256 );
        }

        //添加用户的厂商资料
        if ( userModel.getInfoCompanyModel() != null ) {
            userInfoRepository.insertInfoCompany( userModel.getUserId(),
                    userModel.getInfoCompanyModel().getCompanyId(),
                    userModel.getInfoCompanyModel().getRemarks() );
            return new RestRecord( 254, WebMessageConstants.SCE_PORTAL_MSG_254 );
        }

        //添加用户的机构资料
        if ( userModel.getInfoInstitutionModel() != null ) {
            userInfoRepository.insertInfoInstitution( userModel.getUserId(),
                    userModel.getInfoInstitutionModel().getInstitutionId(),
                    userModel.getInfoInstitutionModel().getRemarks() );
            return new RestRecord( 257, WebMessageConstants.SCE_PORTAL_MSG_257 );
        }

        //添加用户的家长资料
        if ( userModel.getInfoParentModel() != null ) {
            userInfoRepository.insertInfoParent( userModel.getUserId(),
                    userModel.getInfoParentModel().getFamilyRole(),
                    userModel.getInfoParentModel().getRemarks() );
            return new RestRecord( 255, WebMessageConstants.SCE_PORTAL_MSG_255 );
        }

        //添加用户的学生资料
        if ( userModel.getInfoStudentModel() != null ) {
            userInfoRepository.insertInfoStudent( userModel.getUserId(),
                    userModel.getInfoStudentModel().getClassNumber(),
                    userModel.getInfoStudentModel().getGrade(),
                    userModel.getInfoStudentModel().getRemarks() );
            return new RestRecord( 251, WebMessageConstants.SCE_PORTAL_MSG_251 );
        }

        //添加用户的教师资料
        if ( userModel.getInfoTeacherModel() != null ) {
            userInfoRepository.insertInfoTeacher( userModel.getUserId(),
                    userModel.getInfoTeacherModel().getPosition(),
                    userModel.getInfoTeacherModel().getSubject(),
                    userModel.getInfoTeacherModel().getSchoolAge(),
                    userModel.getInfoTeacherModel().getRemarks() );
            return new RestRecord( 252, WebMessageConstants.SCE_PORTAL_MSG_252 );
        }

        return new RestRecord( 250, WebMessageConstants.SCE_PORTAL_MSG_250 );
    }
}
