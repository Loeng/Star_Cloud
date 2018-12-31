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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;


/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2018/12/22 11:01
 * @Description:
 */
@Transactional
public interface UserInfoRepository extends JpaRepository<FamilyInfoEntity,Long>, JpaSpecificationExecutor<User> {


     // 查询和搜索 学校 -> 教师 的列表信息
   @Query(value = "SELECT * FROM  STARCLOUDPORTAL.v_teacher_info WHERE USER_NAME LIKE CONCAT('%',CONCAT(?1,'%'))  AND USER_ACCOUNT LIKE CONCAT('%',CONCAT(?2,'%'))  AND  AUTHORITY_NAME LIKE CONCAT('%',CONCAT(?3,'%'))",nativeQuery = true)
    Page<Map<String,Object>> findTeacherByCondition(String name, String account, String organization_name, Pageable pageable);

     // 查询和搜索 学校 -> 学生 的列表信息
    @Query(value = "SELECT * FROM STARCLOUDPORTAL.v_student_info WHERE USER_NAME LIKE CONCAT('%',CONCAT(?1,'%'))  AND USER_ACCOUNT LIKE CONCAT('%',CONCAT(?2,'%'))  AND  AUTHORITY_NAME LIKE CONCAT('%',CONCAT(?3,'%')) AND TO_CHAR(LOGIN_PERMISSION_STATUS,'999') LIKE CONCAT('%',CONCAT(?4,'%'))",nativeQuery = true)
    Page<Map<String,Object>> findSchoolByCondition(String name, String account, String organization_name,String login, Pageable pageable);

    // 查询和搜索 学校 -> 家长 的列表信息 // countQuery = "SELECT COUNT(*) FROM \"v_family_info\"  "
   @Query( value = "SELECT * FROM STARCLOUDPORTAL.v_family_info WHERE USER_NAME LIKE CONCAT('%',CONCAT(?1,'%')) AND USER_ACCOUNT LIKE CONCAT('%',CONCAT(?2,'%'))", nativeQuery = true)
    Page<Map<String,Object>> findFamilyByCondition( String name, String account, Pageable pageable);


    /**
     *@Author : lyy
     *@Desc : 根据姓名和账号查询出自注册的所有用户
     *@Date : 9:44 2018/12/25
     */
    @Query(value = "SELECT u.USER_TYPE,u.USER_NAME,u.GENDER,u.ADDRESS,u.LOGIN_PERMISSION_STATUS\n" +
            "FROM STARCLOUDPORTAL.SCE_COMMON_USER u INNER JOIN STARCLOUDPORTAL.SCE_INFO_THIRD_PARTY p on p.USER_ID = u.USER_ID\n" +
            "WHERE u.LOGIN_NAME LIKE CONCAT('%',CONCAT(?1,'%')) AND u.USER_NAME LIKE CONCAT('%',CONCAT(?2,'%'))",nativeQuery = true)
    Page<Map> findSelfRegALLByNameOrCount(String byName, String loginName, Pageable pageable);


    /**
     *@Author : lyy
     *@Desc: 根据机构名称、账号、允许登录做模糊查询出信息
     *@Date : 23:03 2018/12/25
     */

    @Query(value = "SELECT ea.AUTHORITY_NAME AS AUTHORITY_NAME,u.LOGIN_NAME AS LOGIN_NAME,u.LOGIN_PERMISSION_STATUS AS LOGIN_PERMISSION_STATUS\n" +
            "FROM STARCLOUDPORTAL.SCE_COMMON_USER u INNER JOIN STARCLOUDPORTAL.SCE_ENTITY_AUTHORITY ea on ea.ID = u.ORGANIZATION_ID\n" +
            "WHERE u.LOGIN_NAME like CONCAT('%',concat(?1,'%')) AND ea.AUTHORITY_NAME like CONCAT('%',concat(?2,'%')) " +
            "AND TO_CHAR(u.LOGIN_PERMISSION_STATUS,'999') LIKE CONCAT('%',CONCAT(?3,'%'))\t",nativeQuery = true)
    Page<Map> findByOrganizationLike(String loginName,String byAuthName,String status,Pageable pageable);


    /**
     *@Author : lyy
     *@Desc: 模糊查询:厂商名称、账号、允许登录   查询厂商     显示：厂商名称、账号、允许登录
     *@Date : 14:41 2018/12/26
     */
    @Query(value = "SELECT mc.company_name AS company_name,u.LOGIN_NAME AS LOGIN_NAME,u.LOGIN_PERMISSION_STATUS AS LOGIN_PERMISSION_STATUS\n" +
            "FROM STARCLOUDPORTAL.SCE_COMMON_USER u INNER JOIN STARCLOUDPORTAL.SCE_INFO_COMPANY ic \n" +
            "      on ic.USER_ID = u.USER_ID INNER JOIN STARCLOUDMARKET.SCE_MARKET_COMPANY mc on mc.COMPANY_ID = ic.COMPANY_ID\n" +
            "WHERE u.LOGIN_NAME like CONCAT('%',concat(?1,'%')) AND mc.company_name like CONCAT('%',concat(?2,'%'))\n" +
            "      AND TO_CHAR(u.LOGIN_PERMISSION_STATUS,'999') LIKE CONCAT('%',CONCAT(?3,'%'))\t ",nativeQuery = true)
    Page<Map> findByManufacturerLike(String loginName,String organizationName,String status,Pageable pageable);

    // 修改是否允许用户登录
    @Query(value = "UPDATE STARCLOUDPORTAL.SCE_COMMON_USER SET LOGIN_PERMISSION_STATUS = :isLogin WHERE USER_ID = :userId ",nativeQuery = true)
    @Modifying
    int updateUserLoginStatus(@Param("isLogin") int isLogin,@Param("userId") String userId);

    // 删除用户
    @Query(value = "UPDATE STARCLOUDPORTAL.SCE_COMMON_USER SET IS_DELETE = ?1 WHERE USER_ID = ?2 ",nativeQuery = true)
    @Modifying
    int deleteUser(int isDelete,String userId);

 // 重置用户密码
    @Query(value = "UPDATE STARCLOUDPORTAL.SCE_COMMON_USER_PASSWORD SET PASSWORD = ?1 WHERE USER_ID = ?2 ",nativeQuery = true )
    @Modifying
    int resetUserPassword(String isReset,String userId);



}
