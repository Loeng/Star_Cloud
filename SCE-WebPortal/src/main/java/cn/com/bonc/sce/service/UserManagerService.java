package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.LoginDao;
import cn.com.bonc.sce.dao.UserManagerDao;
import cn.com.bonc.sce.model.InfoTeacherModel;
import cn.com.bonc.sce.model.SSOAuthentication;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.VaildSecurityUtils;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/8.
 */
@Service
@Slf4j
public class UserManagerService {

    @Autowired
    private UserManagerDao userManagerDao;

    @Autowired
    private LoginDao loginDao;

    public RestRecord delUser( String id ) {
        return userManagerDao.delUser( id );
    }

    public RestRecord resetPwd( String id ) {
        return userManagerDao.resetPwd( id );
    }

    public RestRecord editLogin( String id, Integer loginPermissionStatus ) {
        return userManagerDao.editLogin( id, loginPermissionStatus );
    }

    public RestRecord getSchools4edu( long id, Integer pageNum, Integer pageSize ) {
        return userManagerDao.getSchools4edu( id, pageNum, pageSize );
    }

    public RestRecord delSchools4edu( long id, long institutionId ) {
        return userManagerDao.delSchools4edu( id, institutionId );
    }

    public RestRecord getInstitutionList( String json, Integer pageNum, Integer pageSize ) {
        return userManagerDao.getInstitutionList( json, pageNum, pageSize );
    }

    public List< Map > getInstitutions( String id, String institutionName, String loginPermissionStatus ) {
        return userManagerDao.getInstitutions( id, institutionName, loginPermissionStatus );
    }

    public RestRecord register( String json ) {
        JSONObject jsonObj = JSONUtil.parseObj( json );
        String valid = ( String ) jsonObj.get( "valid" );
        String phone = ( String ) jsonObj.get( "phoneNumber" );
        String loginName = ( String ) jsonObj.get( "loginName" );
        String password = ( String ) jsonObj.get( "password" );

        String encryptionCode = VaildSecurityUtils.getAccountEncryptionCode( phone, valid );
        if ( !VaildSecurityUtils.checkValid( encryptionCode ) ) {
            return new RestRecord( 411, WebMessageConstants.SCE_PORTAL_MSG_411 );
        }
        RestRecord restRecord = userManagerDao.register( json );
        //注册成功删除验证码,然后获取ticket返给前端
        if ( restRecord.getCode() == 200 ) {
            VaildSecurityUtils.delValid( encryptionCode );
            //获取ticket信息
            SSOAuthentication ssoAuthentication = new SSOAuthentication();
            ssoAuthentication.setAuthType( 0 );
            ssoAuthentication.setIdentifier( loginName );
            ssoAuthentication.setPassword( password );
            ssoAuthentication.setTicketReceiveUrl( "http://www.mysite.com/authentication/ticket" );
            return loginDao.getTicket( ssoAuthentication );

        }
        return restRecord;
    }

    public RestRecord addUser( String json ) {
        //登陆页的注册和后台用户管理的新增用户共用一个dao
        return userManagerDao.register( json );
    }

    public RestRecord getPhone( String loginName ) {
        return userManagerDao.getPhone( loginName );
    }

    public RestRecord updatePwdByName( String loginName, String password ) {
        return userManagerDao.updatePwdByName( loginName, password );
    }

    public RestRecord testCertificcate( String valid, String phoneNumber, String loginName, String certificate ) {
        String encryptionCode = VaildSecurityUtils.getAccountEncryptionCode( phoneNumber, valid );
        if ( !VaildSecurityUtils.checkValid( encryptionCode ) ) {
            return new RestRecord( 411, WebMessageConstants.SCE_PORTAL_MSG_411 );
        }
        RestRecord restRecord = userManagerDao.testCertificate( loginName, certificate );
        //身份认证成功删除验证码
        if ( restRecord.getCode() == 200 ) {
            VaildSecurityUtils.delValid( encryptionCode );
        }
        return restRecord;

    }

    public RestRecord getTeachers( long organizationId, String userName, String loginName, String gender, String position, String accountStatus, Integer pageNum, Integer pageSize ) {
        return userManagerDao.getTeachers( organizationId, userName, loginName, gender, position, accountStatus, pageNum, pageSize );
    }

    public RestRecord delTeacher( String id, String userId ) {
        return userManagerDao.delTeacher( id, userId );
    }

    public RestRecord getTeacherInfo( String id ) {
        return userManagerDao.getTeacherInfo( id );
    }

    public RestRecord editTeacherInfo( String json, String userId ) {
        return userManagerDao.editTeacherInfo( json, userId );
    }

    public RestRecord addTeacher( String json, String userId ) {
        return userManagerDao.addTeacher( json, userId );
    }

    public RestRecord getTransferTeachers( Integer getType, long organizationId, String userName, String loginName, String gender, String position, Integer accountStatus, Integer pageNum, Integer pageSize ) {
        return userManagerDao.getTransferTeachers( getType, organizationId, userName, loginName, gender, position, accountStatus, pageNum, pageSize );
    }

    public RestRecord getStudents( String userName, String loginName, String studentNumber, String gender, String grade, String accountStatus,
                                   String userId, String pageNum, String pageSize ) {
        return userManagerDao.getStudents( userName, loginName, studentNumber, gender, grade, accountStatus, userId, pageNum, pageSize );
    }

    public RestRecord getStudentInfo( String userId ) {
        return userManagerDao.getStudentInfo( userId );
    }

    public RestRecord editStudent( Map map, String userId ) {
        return userManagerDao.editStudent( map, userId );
    }

    public RestRecord delStudent( String userId, String currentUserId ) {
        return userManagerDao.delStudent( userId, currentUserId );
    }

    public RestRecord addStudent( Map map, String userId ) {
        //必需字段验证
        if ( map.get( "transferType" ) == null || map.get( "studentCertificateType" ) == null || map.get( "studentCertificateNumber" ) == null || map.get( "entranceYear" ) == null ||
                map.get( "grade" ) == null || map.get( "classNumber" ) == null || map.get( "seatNumber" ) == null || map.get( "studentNumber" ) == null ) {
            return new RestRecord( 431, String.format( WebMessageConstants.SCE_PORTAL_MSG_431, "必需" ) );
        }
        RestRecord restRecord = null;
        if ( map.get( "studentUserName" ) == null || map.get( "studentBirthDate" ) == null || map.get( "studentNationality" ) == null || map.get( "studentVolk" ) == null ) {
            return new RestRecord( 431, String.format( WebMessageConstants.SCE_PORTAL_MSG_431, "必需" ) );
        }
        if ( map.get( "transferType" ).toString().equals( "1" ) ) {
            String bindType;
            try {
                bindType = map.get( "bindType" ).toString();
            } catch ( NullPointerException e ) {
                return new RestRecord( 431, String.format( WebMessageConstants.SCE_PORTAL_MSG_431, "必需" ) );
            }
            if ( bindType.equals( "1" ) ) {
                if ( map.get( "parentUserName" ) == null || map.get( "parentNationality" ) == null || map.get( "parentVolk" ) == null ||
                        map.get( "parentPhoneNumber" ) == null || map.get( "parentCertificateType" ) == null || map.get( "parentCertificateNumber" ) == null || map.get( "relationship" ) == null ) {
                    return new RestRecord( 431, String.format( WebMessageConstants.SCE_PORTAL_MSG_431, "必需" ) );
                }
            }
        }
        restRecord = userManagerDao.addStudent( map, userId );

        return restRecord;
    }

    public RestRecord getParentInfo( String certificationType, String certificationNumber, String userType ) {
        return userManagerDao.getParentInfo( certificationType, certificationNumber, userType );
    }

    public RestRecord getTransferStudent( String userName, String loginName, String studentNumber, String gender, String grade, String applyStatus,
                                          String transferType, String pageNum, String pageSize, String userId ) {
        return userManagerDao.getTransferStudent( userName, loginName, studentNumber, gender, grade, applyStatus, transferType, pageNum, pageSize, userId );
    }

    public RestRecord repealApply( String userId, String transferId ) {
        return userManagerDao.repealApply( userId, transferId );
    }

    public RestRecord reCall( String transferId, String userId ) {
        return userManagerDao.reCall( transferId, userId );
    }

    public RestRecord getTransferOut( String transferId ) {
        return userManagerDao.getTransferOut( transferId );
    }

    public RestRecord auditTransfer( String userId, Map map ) {
        if ( map.get( "applyStatus" ) == null || map.get( "ids" ) == null ) {
            return new RestRecord( 431, String.format( WebMessageConstants.SCE_PORTAL_MSG_431, "必需" ) );
        }
        return userManagerDao.auditTransfer( userId, map );
    }


    public RestRecord editTeacherPracticeInfo( InfoTeacherModel model ) {
        return userManagerDao.editTeacherPracticeInfo( model );
    }

    public RestRecord getTeacherInfoById( String userId ) {
        return userManagerDao.getTeacherInfoById( userId );
    }

    public RestRecord addTeacherInfo( InfoTeacherModel model ) {
        return userManagerDao.addTeacherInfo( model );
    }

    public RestRecord getUserById( String userId ) {
        return userManagerDao.getUserById( userId );
    }

    public RestRecord getTransferTeacherInfo( String transferId ) {
        return userManagerDao.getTransferTeacherInfo( transferId );
    }

    public RestRecord auditTeacher( String userId, Map map ) {
        return userManagerDao.auditTeacher( userId, map );
    }

    public RestRecord reCallTeacher( Map map, String userId ) {
        if ( map.get( "transferId" ) == null ) {
            return new RestRecord( 431, String.format( WebMessageConstants.SCE_PORTAL_MSG_431, "必需" ) );
        }
        return userManagerDao.reCallTeacher( map, userId );
    }

    public RestRecord updateAudit( String auditUserId,Map map ) {
        return userManagerDao.updateAudit( auditUserId,map);
    }

    public RestRecord getAudit( String userId ) {
        return userManagerDao.getAudit(userId);
    }

}
