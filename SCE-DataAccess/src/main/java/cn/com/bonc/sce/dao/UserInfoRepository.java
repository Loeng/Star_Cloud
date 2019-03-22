package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.FamilyInfoEntity;
import cn.com.bonc.sce.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2018/12/22 11:01
 * @Description:
 */
@Transactional( rollbackFor = Exception.class )
public interface UserInfoRepository extends JpaRepository< FamilyInfoEntity, Long >, JpaSpecificationExecutor< User > {


    // 查询和搜索 学校 -> 教师 的列表信息
    @Query( value = "SELECT * FROM  STARCLOUDPORTAL.v_teacher_info WHERE  NVL(USER_NAME,' ')LIKE CONCAT('%',CONCAT(?1,'%'))  AND  NVL(USER_ACCOUNT,' ')LIKE CONCAT('%',CONCAT(?2,'%'))  AND   NVL(AUTHORITY_NAME,' ')LIKE CONCAT('%',CONCAT(?3,'%')) AND  NVL(TO_CHAR(LOGIN_PERMISSION_STATUS,'999'),' ')LIKE CONCAT('%',CONCAT(?4,'%'))", nativeQuery = true )
    Page< Map< String, Object > > findTeacherByCondition( String name, String account, String organization_name, String login, Pageable pageable );

    // 查询和搜索 学校 -> 学生 的列表信息
    @Query( value = "SELECT * FROM STARCLOUDPORTAL.v_student_info WHERE NVL(USER_NAME,' ') LIKE CONCAT('%',CONCAT(?1,'%'))  AND NVL(USER_ACCOUNT,' ')LIKE CONCAT('%',CONCAT(?2,'%'))  AND  NVL(AUTHORITY_NAME,' ')LIKE CONCAT('%',CONCAT(?3,'%')) AND  NVL(TO_CHAR(LOGIN_PERMISSION_STATUS,'999'),' ')LIKE CONCAT('%',CONCAT(?4,'%'))", nativeQuery = true )
    Page< Map< String, Object > > findSchoolByCondition( String name, String account, String organization_name, String login, Pageable pageable );

    // 查询和搜索 学校 -> 家长 的列表信息 // countQuery = "SELECT COUNT(*) FROM \"v_family_info\"  "
    @Query( value = "SELECT * FROM STARCLOUDPORTAL.v_family_info WHERE NVL(USER_NAME,' ') LIKE CONCAT('%',CONCAT(?1,'%')) AND NVL(USER_ACCOUNT,' ')LIKE CONCAT('%',CONCAT(?2,'%')) AND  NVL(TO_CHAR(LOGIN_PERMISSION_STATUS,'999'),' ')LIKE CONCAT('%',CONCAT(?3,'%'))", nativeQuery = true )
    Page< Map< String, Object > > findFamilyByCondition( String name, String account, String login, Pageable pageable );

    @Query( value = "SELECT COUNT(*) FROM STARCLOUDPORTAL.SCE_COMMON_USER WHERE IS_FIRST_LOGIN = 1 AND IS_DELETE=1 ", nativeQuery = true )
    Integer getUserCount();

    @Query( value = "SELECT A.ROLE_ID,A.ROLE_NAME,NVL(B.COUNT, 0) COUNT FROM STARCLOUDPORTAL.SCE_COMMON_USER_ROLE A " +
            "LEFT JOIN ( SELECT USER_TYPE, COUNT( * ) COUNT FROM STARCLOUDPORTAL.SCE_COMMON_USER WHERE IS_FIRST_LOGIN = 1 AND IS_DELETE=1 GROUP BY USER_TYPE ) B " +
            "ON A.ROLE_ID = B.USER_TYPE", nativeQuery = true )
    List< Map< String, Object > > getRoleCount();

    @Query( value = "SELECT COUNT(*) FROM STARCLOUDPORTAL.SCE_COMMON_USER " +
            "WHERE IS_FIRST_LOGIN = 1 AND LOGIN_TIME > ?1", nativeQuery = true )
    Integer getActiveCount( Date lastLoginTime );


    /**
     * @Author : lyy
     * @Desc : 根据姓名和账号查询出自注册的所有用户
     * @Date : 9:44 2018/12/25
     */
    @Query( value = "SELECT u.USER_ID,u.LOGIN_NAME,u.PHONE_NUMBER,u.CERTIFICATE_NUMBER,u.USER_TYPE,u.USER_NAME,u.GENDER,u.ADDRESS,u.LOGIN_PERMISSION_STATUS\n" +
            "FROM STARCLOUDPORTAL.SCE_COMMON_USER u LEFT JOIN STARCLOUDPORTAL.SCE_INFO_THIRD_PARTY p on p.USER_ID = u.USER_ID\n" +
            "WHERE NVL(u.LOGIN_NAME,' ')LIKE CONCAT('%',CONCAT(?1,'%')) AND NVL(u.USER_NAME,' ')LIKE CONCAT('%',CONCAT(?2,'%')) AND  NVL(TO_CHAR(LOGIN_PERMISSION_STATUS,'999'),' ')LIKE CONCAT('%',CONCAT(?3,'%')) AND u.USER_TYPE = 8",
            countQuery = "SELECT count(*)\n" +
                    "FROM STARCLOUDPORTAL.SCE_COMMON_USER u LEFT JOIN STARCLOUDPORTAL.SCE_INFO_THIRD_PARTY p on p.USER_ID = u.USER_ID\n" +
                    "WHERE NVL(u.LOGIN_NAME,' ')LIKE CONCAT('%',CONCAT(?1,'%')) AND NVL(u.USER_NAME,' ')LIKE CONCAT('%',CONCAT(?2,'%')) AND  NVL(TO_CHAR(LOGIN_PERMISSION_STATUS,'999'),' ')LIKE CONCAT('%',CONCAT(?3,'%')) AND u.USER_TYPE = 8",
            nativeQuery = true )
    Page< Map > findSelfRegALLByNameOrCount( String byName, String loginName, String login, Pageable pageable );


    /**
     * @Author : lyy
     * @Desc: 根据机构名称、账号、允许登录做模糊查询出信息
     * @Date : 23:03 2018/12/25
     */

    @Query( value = "SELECT ea.AUTHORITY_NAME AS AUTHORITY_NAME,u.LOGIN_NAME AS LOGIN_NAME,u.LOGIN_PERMISSION_STATUS AS LOGIN_PERMISSION_STATUS\n" +
            "FROM STARCLOUDPORTAL.SCE_COMMON_USER u INNER JOIN STARCLOUDPORTAL.SCE_ENTITY_AUTHORITY ea on ea.ID = u.ORGANIZATION_ID\n" +
            "WHERE NVL(u.LOGIN_NAME,' ') like CONCAT('%',concat(?1,'%')) AND NVL(ea.AUTHORITY_NAME,' ')like CONCAT('%',concat(?2,'%')) " +
            "AND NVL(u.LOGIN_PERMISSION_STATUS,' ') LIKE CONCAT('%',CONCAT(?3,'%'))\t", nativeQuery = true )
    Page< Map > findByOrganizationLike( String loginName, String byAuthName, String status, Pageable pageable );


    /**
     * @Author : lyy
     * @Desc: 模糊查询:厂商名称、账号、允许登录   查询厂商     显示：厂商名称、账号、允许登录
     * @Date : 14:41 2018/12/26
     */
    @Query( value = "SELECT mc.company_name AS company_name,u.LOGIN_NAME AS LOGIN_NAME,u.LOGIN_PERMISSION_STATUS AS LOGIN_PERMISSION_STATUS\n" +
            "FROM STARCLOUDPORTAL.SCE_COMMON_USER u INNER JOIN STARCLOUDPORTAL.SCE_INFO_COMPANY ic \n" +
            "      on ic.USER_ID = u.USER_ID INNER JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY mc on mc.COMPANY_ID = ic.COMPANY_ID\n" +
            "WHERE NVL(u.LOGIN_NAME,' ') like CONCAT('%',concat(?1,'%')) AND NVL(mc.company_name,' ') like CONCAT('%',concat(?2,'%'))\n" +
            "      AND NVL(TO_CHAR(u.LOGIN_PERMISSION_STATUS,'999'),' ')LIKE CONCAT('%',CONCAT(?3,'%'))\t ", nativeQuery = true )
    Page< Map > findByManufacturerLike( String loginName, String organizationName, String status, Pageable pageable );

    // 修改是否允许用户登录
    @Query( value = "UPDATE STARCLOUDPORTAL.SCE_COMMON_USER SET LOGIN_PERMISSION_STATUS = :isLogin WHERE USER_ID = :userId ", nativeQuery = true )
    @Modifying
    int updateUserLoginStatus( @Param( "isLogin" ) int isLogin, @Param( "userId" ) String userId );

    // 删除用户
    @Query( value = "UPDATE STARCLOUDPORTAL.SCE_COMMON_USER SET IS_DELETE = ?1 WHERE USER_ID = ?2 ", nativeQuery = true )
    @Modifying
    int deleteUser( int isDelete, String userId );

    // 重置用户密码
    @Query( value = "UPDATE STARCLOUDPORTAL.SCE_COMMON_USER_PASSWORD SET PASSWORD = ?1 WHERE USER_ID = ?2 ", nativeQuery = true )
    @Modifying
    int resetUserPassword( String isReset, String userId );


    // 插入用户基本信息
    @Query( value = "INSERT INTO STARCLOUDPORTAL.SCE_COMMON_USER VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,NULL ,NULL ,0,?12,?13,NULL ,?14,?15,0,0)",
            nativeQuery = true )
    @Modifying
    int addCommonUser( String user_id, String login_name, String user_name, String gender, int user_type, String mail_address, int certificate_type, String certificate_number, String phone_number, String address, Date create_time, Long organization_id, Integer loginPermissionStatus, Integer isDelete, String secret );


    /**
     * 添加用户
     */
    @Modifying
    @Query( nativeQuery = true,
            value = "INSERT INTO \"STARCLOUDPORTAL\".\"SCE_COMMON_USER\" (\"USER_ID\",\"LOGIN_NAME\",\"USER_NAME\",\"GENDER\",\"USER_TYPE\",\"MAIL_ADDRESS\",\"CERTIFICATE_TYPE\",\"CERTIFICATE_NUMBER\",\"PHONE_NUMBER\",\"ADDRESS\",\"CREATE_TIME\",\"ORGANIZATION_ID\", \"REMARKS\", \"SECRET\" )\n" +
                    "VALUES ( :userId,:loginName,:userName,:gender,:userType,:mailAddress,:certificateType,:certificateNumber,:phoneNumber,:address,:createTime,TO_NUMBER(:organizationId),:remarks,:secret)" )
    int insertUser( @Param( "userId" ) String userId, @Param( "loginName" ) String loginName,
                    @Param( "userName" ) String userName, @Param( "gender" ) String gender,
                    @Param( "userType" ) Integer userType, @Param( "mailAddress" ) String mailAddress,
                    @Param( "certificateType" ) Integer certificateType, @Param( "certificateNumber" ) String certificateNumber,
                    @Param( "phoneNumber" ) String phoneNumber, @Param( "address" ) String address,
                    @Param( "createTime" ) Date createTime, @Param( "organizationId" ) Long organizationId,
                    @Param( "remarks" ) String remarks, @Param( "secret" ) String secret );


    @Modifying
    @Query( nativeQuery = true, value = "INSERT into \"STARCLOUDPORTAL\".\"SCE_INFO_AGENT\" (\"USER_ID\",\"AGENT_ID\",\"REMARKS\") \n" +
            "VALUES (:userId,:agentId,:remarks)" )
    int insertInfoAgent( @Param( "userId" ) String userId,
                         @Param( "agentId" ) String agentId,
                         @Param( "remarks" ) String remarks );

    @Modifying
    @Query( nativeQuery = true, value = "INSERT into \"STARCLOUDPORTAL\".\"SCE_INFO_COMPANY\" (\"USER_ID\",\"COMPANY_ID\",\"REMARKS\") \n" +
            "VALUES (:userId,:companyId,:remarks)" )
    int insertInfoCompany( @Param( "userId" ) String userId,
                           @Param( "companyId" ) String companyId,
                           @Param( "remarks" ) String remarks );

    @Modifying
    @Query( nativeQuery = true, value = "INSERT into \"STARCLOUDPORTAL\".\"SCE_INFO_INSTITUTION\" (\"USER_ID\",\"INSTITUTION_ID\",\"REMARKS\") \n" +
            "VALUES (:userId,:institutionId,:remarks)" )
    int insertInfoInstitution( @Param( "userId" ) String userId,
                               @Param( "institutionId" ) String institutionId,
                               @Param( "remarks" ) String remarks );

    @Modifying
    @Query( nativeQuery = true, value = "INSERT into STARCLOUDPORTAL.SCE_INFO_PARENT (USER_ID,FAMILY_ROLE,REMARKS) " +
            "VALUES (:userId,:familyRole,:remarks)" )
    int insertInfoParent( @Param( "userId" ) String userId,
                          @Param( "familyRole" ) String familyRole,
                          @Param( "remarks" ) String remarks );

    @Modifying
    @Query( nativeQuery = true, value = "INSERT into STARCLOUDPORTAL.SCE_INFO_STUDENT (USER_ID,CLASS_NUMBER,GRADE,REMARKS) " +
            "VALUES (:userId,:classNumber,:grade,:remarks)" )
    int insertInfoStudent( @Param( "userId" ) String userId,
                           @Param( "classNumber" ) String classNumber,
                           @Param( "grade" ) String grade,
                           @Param( "remarks" ) String remarks );

    @Modifying
    @Query( nativeQuery = true, value = "INSERT INTO STARCLOUDPORTAL.SCE_INFO_TEACHER ( USER_ID,\"POSITION\",\"SUBJECT\",SCHOOL_AGE,REMARKS)\n" +
            "VALUES (:userId,:position,:subject,to_number(:schoolAge),:remarks)" )
    int insertInfoTeacher( @Param( "userId" ) String userId,
                           @Param( "position" ) String position,
                           @Param( "subject" ) String subject,
                           @Param( "schoolAge" ) Long schoolAge,
                           @Param( "remarks" ) String remarks );

}
