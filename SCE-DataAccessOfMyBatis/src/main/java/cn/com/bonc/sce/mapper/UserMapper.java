package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/6.
 */
public interface UserMapper {

    int saveUser(UserBean userBean);

    int saveAccount(AccountBean account);

    int delUser(@Param("id")String id);

    int resetPwd(@Param("id")String id, @Param("pwd")String pwd);

    int updateLoginPermission(@Param("id")String id, @Param("newStatus")int newStatus);

    List<SchoolBean> getSchools4edu(@Param("id") long id);

    int delSchools4edu(@Param("id") long id, @Param("institutionId") long institutionId);

    List<Map> getInstitutions(@Param("id") String id, @Param("institutionName") String institutionName, @Param("loginPermissionStatus") String loginPermissionStatus);

    String selectUserIdByLoginName( String LOGIN_NAME );

    int saveUserSelective( UserBean userBean );

    int updateUserByUserIdSelective( UserBean userBean );

    int isExist(@Param("loginName") String loginName);

    Map getPhone(@Param("loginName") String loginName);

    int updatePwdByName(@Param("loginName") String loginName, @Param("password") String password);

    int updateAccountStatusByName(@Param("loginName") String loginName, @Param("accountStatus") int accountStatus);

    String testCertificate(@Param("loginName") String loginName);

    String getIdByPhone(@Param("phone") String phone);

    List<Map> getTeachers(@Param("organizationId")long organizationId,
                          @Param("userName")String userName, @Param("loginName")String loginName,
                          @Param("gender")String gender, @Param("position")String position,
                          @Param("accountStatus")String accountStatus);

    int delTeacher( @Param( "id" ) String id );

    @Select( "SELECT * FROM STARCLOUDPORTAL.SCE_COMMON_USER WHERE LOGIN_NAME =#{loginName}" )
    List< Map< String, Object > > getUserInfoByLoginName( @RequestParam( "loginName" ) String loginName );

    Map getTeacherInfo(@Param("id") String id);

    int editUser(@Param("user_id") String user_id, @Param("certificate_type")Integer certificate_type,
                 @Param("certificate_number")String certificate_number, @Param("user_name")String user_name,
                 @Param("gender")String gender, @Param("phone_number")String phone_number,
                 @Param("mail_address")String mail_address, @Param("birthdate")String birthdate,
                 @Param("nationCode") String nationCode, @Param("nationality") String nationality,
                 @Param("ISADMINISTRATORS") Integer ISADMINISTRATORS);

    int editTeacher(@Param("user_id") String user_id,
                    @Param("academic_qualification")String academic_qualification, @Param("work_number")String work_number,
                    @Param("school_time")String school_time, @Param("teach_time")String teach_time,
                    @Param("position")String position, @Param("teach_range")Integer teach_range);

    int addUser(@Param("user_id") String user_id, @Param("certificate_type")Integer certificate_type,
                @Param("certificate_number")String certificate_number, @Param("user_name")String user_name,
                @Param("gender")String gender, @Param("phone_number")String phone_number,
                @Param("organization_id")String organization_id, @Param("mail_address") String mail_address,
                @Param("birthdate")String birthdate, @Param("nationality")String nationality, @Param("nationCode")String nationCode,
                @Param("secret") String secret, @Param("userType") String userType, @Param("loginName") String loginName, @Param("ISADMINISTRATORS") Integer ISADMINISTRATORS);

    int addTeacher(@Param("user_id")String user_id, @Param("academic_qualification")String academic_qualification,
                   @Param("work_number")String work_number, @Param("school_time")String school_time,
                   @Param("teach_time")String teach_time, @Param("position")String position,
                   @Param("teach_range")Integer teach_range);


    int transInto(@Param("id")Long id, @Param("user_id")String user_id, @Param("apply_user_id")String apply_user_id,
                  @Param("origin_school_id")String origin_school_id, @Param("target_school_id")String target_school_id,
                  @Param("tea_work_number")String tea_work_number, @Param("entrance_year")Date entrance_year,
                  @Param("tea_position")String tea_position, @Param("tea_range")String tea_range, @Param("ISADMINISTRATORS") Integer ISADMINISTRATORS);

    List<Map> getTransferTeachers(@Param("getType")Integer getType,@Param("organizationId")long organizationId,
                                  @Param("userName")String userName, @Param("loginName")String loginName,
                                  @Param("gender")String gender, @Param("position")String position,
                                  @Param("accountStatus")Integer accountStatus);

    List< Map > getStudents( @Param("userName") String userName,
                             @Param("loginName") String loginName,
                             @Param("studentNumber") String studentNumber,
                             @Param("gender") String gender,
                             @Param("grade") String grade,
                             @Param("accountStatus") String accountStatus,
                             @Param("organizationId") String organizationId);

    String getOrganizationIdByUserId( @Param( "userId" ) String userId );

    Map getStudentInfo(@Param("userId") String userId);

    int editStudentOfStudent(Map map);

    int editStudentOfUser(Map map);

    int delStudentOfStudent(@Param("userId") String userId);

    int delStudentOfUser(@Param("userId") String userId);

    int editTeacherPracticeInfo(@Param("user_id") String user_id,@Param("teach_certification")String teach_certification,@Param("teach_time") Date teach_time,
                    @Param("school_time")Date school_time, @Param("job_profession")String job_profession,
                    @Param("teach_range")String teach_range, @Param("work_number")String work_number);

    Map selectParentInfoByCertificationNumber(@Param("certificationType") String certificationType, @Param("certificationNumber") String certificationNumber);

    Map selectStudentInfoByCertificationNumber(@Param("certificationType") String certificationType, @Param("certificationNumber") String certificationNumber);

    Map selectTeacherInfoByCertificationNumber(@Param("certificationType") String certificationType, @Param("certificationNumber") String certificationNumber);

    void insertUserPassword(@Param("id") long id, @Param("userId") String userId, @Param("password") String password);

    void saveUserOfStudent(Map map);

    void saveStudent(Map map);

    void saveUserOfParent(Map map);

    void saveParentStudentRel(@Param("id") long id, @Param("parentUserId") String parentUserId,
                              @Param("studentUserId") String studentUserId, @Param("isMain") int isMain, @Param("relationship") String relationship);

    Map selectUserIdAndOrganizationId(@Param("certificateNumber") String certificateNumber,
                                      @Param("certificateType") String certificateType,
                                      @Param("userType") String userType);

    void addTransfer(Map map);

    List<Map> selectTransferInStudent(@Param("userName") String userName, @Param("loginName") String loginName, @Param("studentNumber") String studentNumber,
                                      @Param("gender") String gender, @Param("grade") String grade, @Param("applyStatus") String applyStatus,
                                      @Param("organizationId") String organizationId);

    List<Map> selectTransferOutStudent(@Param("userName") String userName, @Param("loginName") String loginName, @Param("studentNumber") String studentNumber,
                                    @Param("gender") String gender, @Param("grade") String grade, @Param("organizationId") String organizationId);

    int deleteTransferApply(@Param("id") long id, @Param("organizationId") String organizationId);

    int selectTransfer(@Param("userId") String userId, @Param("organizationId") String organizationId);

    void reCall(@Param("transferId") String transferId);

    Map selectTransferInfo(@Param("transferId") String transferId);

    int auditTransfer(Map map);

    Map selectUserIdAndOrganizationIdByTransferId(@Param("transferId") String transferId);

    void updateStudent(Map map);

    Map selectStudentInfoByTransferId(@Param("transferId") String transferId);

    int editTeacherPracticeInfo(@Param("user_id") String user_id,@Param("teach_certification")String teach_certification,@Param("teach_time") Date teach_time,
                                @Param("school_time")Date school_time, @Param("job_profession")String job_profession,
                                @Param("teach_range")Integer teach_range, @Param("work_number")String work_number);

    TeacherInfoBean getTeacherInfoById(@Param("userId") String userId);

    int addTeacherInfo(@Param("user_id") String user_id,@Param("teach_certification")String teach_certification,@Param("teach_time") Date teach_time,
                                @Param("school_time")Date school_time, @Param("job_profession")String job_profession,
                                @Param("teach_range")Integer teach_range, @Param("work_number")String work_number,@Param("is_delete")Integer is_delete);

    UserBean getUserById(@Param("userId") String userId);

    int delPassword(@Param("id") String id);

    int updateOrganizationId(@Param("organizationId") Long organizationId,@Param("userId") String userId, @Param("TEA_ISADMINISTRATORS") Integer TEA_ISADMINISTRATORS);

    int saveUserAudit(UserAuditBean userAudit);

    UserAuditBean findByUserAuditEntityId(@Param("entityId") Long entityId);

    int updateUserAuditById(UserAuditBean userAudit);

    int checkUser(@Param("certificateType") Integer certificateType,
                  @Param("certificateNumber") String certificateNumber,
                  @Param("phoneNumber") String phoneNumber);

    Map getTransferTeacherInfo(@Param("transferId") String transferId);

    Map selectTeacherInfoByTransferId(@Param("transferId") String transferId);

    void updateTeacher(Map map);

    Map selectTransferInfoByTransferId(@Param("transferId") String transferId);

    int selectCountByCertificateNumber(@Param("certificateType") String certificateType, @Param("certificateNumber") String certificateNumber);

    int selectCountByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    int selectCountByMailAddress(@Param("mailAddress") String mailAddress);

    Integer selectIsAdministortars(@Param("userId") String userId);

    int updateAudit(@Param("auditUserId") String auditUserId,@Param("userId") String userId,@Param("auditStatus") Integer auditStatus,@Param("rejectOpinion") String rejectOpinion);

    UserAuditBean getAudit(@Param("userId") String userId);

    int updateIsadministrators(@Param("userId") String userId);

    int selectAuthStatus(@Param("userId") String userId);
}
