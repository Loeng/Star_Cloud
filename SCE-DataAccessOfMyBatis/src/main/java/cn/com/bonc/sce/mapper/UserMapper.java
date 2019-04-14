package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.bean.TeacherInfoBean;
import cn.com.bonc.sce.bean.UserBean;
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

    String testCertificate(@Param("loginName") String loginName);

    String getIdByPhone(@Param("phone") String phone);

    List<Map> getTeachers(@Param("organizationId")long organizationId,
                          @Param("userName")String userName, @Param("loginName")String loginName,
                          @Param("gender")String gender, @Param("position")String position,
                          @Param("accountStatus")Integer accountStatus);

    int delTeacher( @Param( "id" ) String id );

    @Select( "SELECT * FROM STARCLOUDPORTAL.SCE_COMMON_USER WHERE LOGIN_NAME =#{loginName}" )
    List< Map< String, Object > > getUserInfoByLoginName( @RequestParam( "loginName" ) String loginName );

    Map getTeacherInfo(@Param("id") String id);

    int editUser(@Param("user_id") String user_id, @Param("certificate_type")Integer certificate_type,
                 @Param("certificate_number")String certificate_number, @Param("user_name")String user_name,
                 @Param("gender")String gender, @Param("phone_number")String phone_number,
                 @Param("mail_address")String mail_address, @Param("birthdate")String birthdate,
                 @Param("nationCode") String nationCode, @Param("nationality") String nationality);

    int editTeacher(@Param("user_id") String user_id,
                    @Param("academic_qualification")String academic_qualification, @Param("work_number")String work_number,
                    @Param("school_time")String school_time, @Param("teach_time")String teach_time,
                    @Param("job_code")String job_code, @Param("teach_range")Integer teach_range);

    int addUser(@Param("user_id") Long user_id, @Param("certificate_type")Integer certificate_type,
                @Param("certificate_number")String certificate_number, @Param("user_name")String user_name,
                @Param("gender")String gender, @Param("phone_number")String phone_number,
                @Param("organization_id")String organization_id, @Param("mail_address") String mail_address,
                @Param("birthdate")String birthdate);

    int addTeacher(@Param("user_id")Long user_id,@Param("nation_code")String nation_code,
                   @Param("nationlity")String nationlity, @Param("academic_qualification")String academic_qualification,
                   @Param("work_number")String work_number, @Param("school_time")String school_time,
                   @Param("teach_time")String teach_time, @Param("job_code")String job_code,
                   @Param("teach_range")Integer teach_range);


    int transInto(@Param("id")Long id, @Param("user_id")String user_id, @Param("apply_user_id")String apply_user_id,
                  @Param("origin_school_id")String origin_school_id, @Param("target_school_id")String target_school_id,
                  @Param("tea_work_number")String tea_work_number, @Param("entrance_year")Date entrance_year,
                  @Param("tea_position")String tea_position, @Param("tea_range")String tea_range);

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

    void insertStudent(Map map);


    int editTeacherPracticeInfo(@Param("user_id") String user_id,@Param("teach_certification")String teach_certification,@Param("teach_time") Date teach_time,
                    @Param("school_time")Date school_time, @Param("job_profession")String job_profession,
                    @Param("teach_range")String teach_range, @Param("work_number")String work_number);

    TeacherInfoBean getTeacherInfoById(@Param("userId") String userId);

    UserBean getUserById(@Param("userId") String userId);
}
