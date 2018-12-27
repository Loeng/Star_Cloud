package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.FamilyInfoEntity;
import cn.com.bonc.sce.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;


/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2018/12/22 11:01
 * @Description:
 */
@Repository
public interface UserInfoRepository extends JpaRepository<FamilyInfoEntity,Long>, JpaSpecificationExecutor<User> {


     // 查询和搜索 学校 -> 教师 的列表信息
   @Query(value = "SELECT * FROM  STARCLOUDPORTAL.v_teacher_info WHERE USER_NAME LIKE CONCAT('%',CONCAT(?1,'%'))  AND USER_ACCOUNT LIKE CONCAT('%',CONCAT(?2,'%'))  AND  AUTHORITY_NAME LIKE CONCAT('%',CONCAT(?3,'%'))",nativeQuery = true)
    Page<Map<String,Object>> findTeacherByCondition(String name, String account, String organization_name, Pageable pageable);

     // 查询和搜索 学校 -> 学生 的列表信息
    @Query(value = "SELECT * FROM STARCLOUDPORTAL.v_student_info WHERE USER_NAME LIKE CONCAT('%',CONCAT(?1,'%'))  AND USER_ACCOUNT LIKE CONCAT('%',CONCAT(?2,'%'))  AND  AUTHORITY_NAME LIKE CONCAT('%',CONCAT(?3,'%')) AND LOGIN_PERMISSION_STATUS LIKE CONCAT('%',CONCAT(?4,'%'))",nativeQuery = true)
    Page<Map<String,Object>> findSchoolByCondition(String name, String account, String organization_name,String login, Pageable pageable);

    // 查询和搜索 学校 -> 家长 的列表信息 // countQuery = "SELECT COUNT(*) FROM \"v_family_info\"  "
   @Query( value = "SELECT * FROM STARCLOUDPORTAL.v_family_info WHERE USER_NAME LIKE CONCAT('%',CONCAT(?1,'%')) AND USER_ACCOUNT LIKE CONCAT('%',CONCAT(?2,'%'))", nativeQuery = true)
    Page<Map<String,Object>> findFamilyByCondition(@Param("name") String name, @Param("account") String account, Pageable pageable);

    /**
     *@Author : lyy
     *@Desc:查询出自注册的所有用户
     *@Date : 9:43 2018/12/25
     */
    @Query(value = "SELECT u.USER_NAME as USER_NAME,u.LOGIN_NAME as LOGIN_NAME,u.GENDER as GENDER,u.ADDRESS as ADDRESS,u.LOGIN_PERMISSION_STATUS as LOGIN_PERMISSION_STATUS\n" +
            "FROM STARCLOUDPORTAL.SCE_COMMON_USER u INNER JOIN STARCLOUDPORTAL.SCE_INFO_THIRD_PARTY p on p.USER_ID = u.USER_ID",nativeQuery = true)
    Page<Map> findSelfRegAll(Pageable pageable);//显示自主注册的信息

    /**
     *@Author : lyy
     *@Desc : 根据姓名和账号查询出自注册的所有用户  显示：
     *@Date : 9:44 2018/12/25
     */
    @Query(value = "SELECT u.USER_TYPE,u.USER_NAME,u.GENDER,u.ADDRESS,u.LOGIN_PERMISSION_STATUS\n" +
            "FROM STARCLOUDPORTAL.SCE_COMMON_USER u inner JOIN STARCLOUDPORTAL.SCE_INFO_THIRD_PARTY p on p.USER_ID = u.USER_ID\n"+
            "WHERE  u.LOGIN_NAME like CONCAT('%',concat(?1,'%'))  or u.USER_NAME like CONCAT('%',concat(?2,'%'))",nativeQuery = true)
    Page<Map> findSelfRegALLByNameOrCount(String byName, String loginName, Pageable pageable);


    /**
     *@Author : lyy
     *@Desc :查询机构名称、账号、允许登录
     *@Date : 22:44 2018/12/25
     */

    @Query(value = "SELECT ea.AUTHORITY_NAME AS AUTHORITY_NAME,u.LOGIN_NAME AS LOGIN_NAME,u.LOGIN_PERMISSION_STATUS AS LOGIN_PERMISSION_STATUS\n" +
            "FROM STARCLOUDPORTAL.SCE_COMMON_USER u INNER JOIN STARCLOUDPORTAL.SCE_ENTITY_AUTHORITY ea on ea.ID = u.ORGANIZATION_ID",nativeQuery = true)
    Page<Map> findOrganizationAll(Pageable pageable);


    /**
     *@Author : lyy
     *@Desc: 根据机构名称、账号、允许登录做模糊查询出信息
     *@Date : 23:03 2018/12/25
     */

    @Query(value = "SELECT ea.AUTHORITY_NAME AS AUTHORITY_NAME,u.LOGIN_NAME AS LOGIN_NAME,u.LOGIN_PERMISSION_STATUS AS LOGIN_PERMISSION_STATUS\n" +
            "FROM STARCLOUDPORTAL.SCE_COMMON_USER u INNER JOIN STARCLOUDPORTAL.SCE_ENTITY_AUTHORITY ea on ea.ID = u.ORGANIZATION_ID\n" +
            "WHERE u.LOGIN_NAME like CONCAT('%',concat(?1,'%')) or ea.AUTHORITY_NAME like CONCAT('%',concat(?2,'%')) " +
            "or u.LOGIN_PERMISSION_STATUS = ?3",nativeQuery = true)
    Page<Map> findByOrganizationLike(String loginName,String byAuthName,int status,Pageable pageable);

    /**
     *@Author : lyy
     *@Desc :显示:查询厂商名称、账号、允许登录   厂商
     *@Date : 12:48 2018/12/26
     */
    @Query(value = "SELECT mc.company_name AS company_name,u.LOGIN_NAME AS LOGIN_NAME,\n" +
            "       u.LOGIN_PERMISSION_STATUS AS LOGIN_PERMISSION_STATUS\n" +
            "FROM STARCLOUDPORTAL.SCE_COMMON_USER u INNER JOIN STARCLOUDPORTAL.SCE_INFO_COMPANY ic \n" +
            "      on ic.USER_ID = u.USER_ID INNER JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY mc on mc.COMPANY_ID = ic.COMPANY_ID",nativeQuery = true)
    Page<Map> findManufacturerAll(Pageable pageable);

    /**
     *@Author : lyy
     *@Desc: 模糊查询:厂商名称、账号、允许登录   查询厂商     显示：厂商名称、账号、允许登录
     *@Date : 14:41 2018/12/26
     */
    @Query(value = "SELECT mc.company_name AS company_name,u.LOGIN_NAME AS LOGIN_NAME,u.LOGIN_PERMISSION_STATUS AS LOGIN_PERMISSION_STATUS\n" +
            "FROM STARCLOUDPORTAL.SCE_COMMON_USER u INNER JOIN STARCLOUDPORTAL.SCE_INFO_COMPANY ic \n" +
            "      on ic.USER_ID = u.USER_ID INNER JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY mc on mc.COMPANY_ID = ic.COMPANY_ID\n" +
            "WHERE u.LOGIN_NAME like CONCAT('%',concat(?1,'%')) or mc.company_name like CONCAT('%',concat(?2,'%'))\n" +
            "      or u.LOGIN_PERMISSION_STATUS = ?3 ",nativeQuery = true)
    Page<Map> findByManufacturerLike(String loginName,String byMarkName,int status,Pageable pageable);



}
