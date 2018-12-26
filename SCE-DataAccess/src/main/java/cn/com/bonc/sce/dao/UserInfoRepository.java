package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.FamilyInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Map;


/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2018/12/22 11:01
 * @Description:
 */

public interface UserInfoRepository extends JpaRepository<FamilyInfoEntity,Long>{


     // 查询和搜索 学校 -> 教师 的列表信息
   @Query(value = "SELECT * FROM  STARCLOUDPORTAL.v_teacher_info WHERE USER_NAME LIKE CONCAT('%',CONCAT(?1,'%'))  AND USER_ACCOUNT LIKE CONCAT('%',CONCAT(?2,'%'))  AND  AUTHORITY_NAME LIKE CONCAT('%',CONCAT(?3,'%'))",nativeQuery = true)
    Page<Map<String,Object>> findTeacherByCondition(String name, String account, String organization_name, Pageable pageable);

     // 查询和搜索 学校 -> 学生 的列表信息
    @Query(value = "SELECT * FROM STARCLOUDPORTAL.v_student_info WHERE USER_NAME LIKE CONCAT('%',CONCAT(?1,'%'))  AND USER_ACCOUNT LIKE CONCAT('%',CONCAT(?2,'%'))  AND  AUTHORITY_NAME LIKE CONCAT('%',CONCAT(?3,'%'))",nativeQuery = true)
    Page<Map<String,Object>> findSchoolByCondition(String name, String account, String organization_name, Pageable pageable);


    // 查询和搜索 学校 -> 家长 的列表信息 // countQuery = "SELECT COUNT(*) FROM \"v_family_info\"  "
   @Query( value = "SELECT * FROM STARCLOUDPORTAL.v_family_info WHERE USER_NAME LIKE CONCAT('%',CONCAT(?1,'%')) AND USER_ACCOUNT LIKE CONCAT('%',CONCAT(?2,'%'))", nativeQuery = true)
    Page<Map<String,Object>> findFamilyByCondition(@Param("name") String name, @Param("account") String account, Pageable pageable);



}
