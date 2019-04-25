package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 学校
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Repository
public interface SchoolDao extends JpaRepository< School, Integer > {


    Page<School> findByIsDelete( Integer isDelete , Pageable pageable);

    @Override
    School save( School school);

    @Query(value = " select U.USER_ID,\n" +
            "U.LOGIN_NAME,\n" +
            "U.LOGIN_PERMISSION_STATUS,\n" +
            "U.ACCOUNT_STATUS,\n" +
            "U.ORGANIZATION_ID,\n" +
            "U.CREATE_TIME ,\n" +
            "S.SCHOOL_NAME,\n" +
            "S.IS_DELETE,\n" +
            "S.INSTITUTION_NAME,\n" +
            "S.SCHOOL_ADDRESS,\n" +
            "S.TELEPHONE,\n" +
            "S.INSTITUTION_ID,\n" +
            "S.GRADE,\n" +
            "S.DEFAULT_BANNER,\n" +
            "S.P_SCHOOL_ID,\n" +
            "S.AUTHORITY_NAME,\n" +
            "S.ENGLISH_NAME,\n" +
            "S.POSTCODE,\n" +
            "S.SCHOOL_TYPE,\n" +
            "S.USER_ID AS PRINCIPAL_ID,\n" +
            "S.ORG_CODE,\n" +
            "S.EMAIL,\n" +
            "S.HOMEPAGE,\n" +
            "S.SCHOOL_RUNNING,\n" +
            "S.REGION,\n" +
            "S.SCHOOLMASTER_NAME,\n" +
            "S.SCHOOL_DISTRICT,\n" +
            "S.PROVINCE,\n" +
            "S.CITY,\n" +
            "S.AREA,\n" +
            "S.ID FROM \n" +
            " (SELECT USER_ID,LOGIN_NAME,LOGIN_PERMISSION_STATUS,ACCOUNT_STATUS,ORGANIZATION_ID,CREATE_TIME FROM  STARCLOUDPORTAL.SCE_COMMON_USER  WHERE user_type= 3 AND IS_DELETE = 1 AND  ORGANIZATION_ID  IS NOT NULL ) u  \n" +
            "LEFT  JOIN \n" +
            "STARCLOUDPORTAL.SCE_ENTITY_SCHOOL s \n" +
            "ON  u.ORGANIZATION_ID = s.ID   "
            ,nativeQuery = true,
    countQuery = " select count(*) FROM \n" +
            " (SELECT USER_ID,LOGIN_NAME,LOGIN_PERMISSION_STATUS,ACCOUNT_STATUS,ORGANIZATION_ID,CREATE_TIME FROM  STARCLOUDPORTAL.SCE_COMMON_USER  WHERE user_type= 3 AND IS_DELETE = 1 AND  ORGANIZATION_ID  IS NOT NULL ) u  \n" +
            "LEFT  JOIN \n" +
            "STARCLOUDPORTAL.SCE_ENTITY_SCHOOL s \n" +
            "ON  u.ORGANIZATION_ID = s.ID   ")
    Page<List<Map<String,Object> >> selectAllSchool( Integer isDelete , Pageable pageable);


    Optional<School> findById(Long id);

    @Modifying
    @Query(value = "UPDATE STARCLOUDPORTAL.SCE_ENTITY_SCHOOL SET SCHOOL_ADDRESS = ?2,POSTCODE = ?3,PROVINCE = ?4,CITY = ?5,AREA = ?6,SCHOOLMASTER_NAME = ?7,TELEPHONE = ?8,EMAIL = ?9,HOMEPAGE = ?10 WHERE ID = ?1 ", nativeQuery = true)
    int updateSchoolById(Long id, String schoolAddress, String postcode, String province, String city, String area, String schoolMasterName, String telephone, String email,String homepage);

}
