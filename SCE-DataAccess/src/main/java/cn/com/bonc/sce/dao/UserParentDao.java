package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户表
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Repository
public interface UserParentDao extends JpaRepository< User, Integer > {

    @Override
    User save( User user );

    @Query( value = "SELECT CU.USER_ID userId,CU.LOGIN_NAME userAccount FROM STARCLOUDPORTAL.SCE_COMMON_USER CU\n" +
            "WHERE CU.LOGIN_NAME=?1 AND CU.IS_DELETE=1",nativeQuery=true )
    List<Map<String,String>> selectUserInfo( String userAccount );

    @Query(value="SELECT A.USER_ID,A.LOGIN_NAME,A.USER_NAME PARENT_NAME,A.PHONE_NUMBER,A.CERTIFICATE_NUMBER,B.FAMILY_ROLE RELATION,C.STUDENT_USER_ID,D.LOGIN_NAME STUDENT_LOGIN_NAME,E.PARENT_USER_ID MAIN_PARENT_USER_ID,F.LOGIN_NAME MAIN_PARENT_LOGIN_NAME,A.CREATE_TIME\n" +
            "FROM STARCLOUDPORTAL.SCE_COMMON_USER A\n" +
            "LEFT JOIN STARCLOUDPORTAL.SCE_INFO_PARENT B ON A.USER_ID=B.USER_ID\n" +
            "LEFT JOIN STARCLOUDPORTAL.SCE_INFO_STUDENT_PARENT_REL C ON A.USER_ID=C.PARENT_USER_ID\n" +
            "LEFT JOIN STARCLOUDPORTAL.SCE_COMMON_USER D ON C.STUDENT_USER_ID=D.USER_ID\n" +
            "LEFT JOIN STARCLOUDPORTAL.SCE_INFO_STUDENT_PARENT_REL E ON D.USER_ID=E.STUDENT_USER_ID\n" +
            "LEFT JOIN STARCLOUDPORTAL.SCE_COMMON_USER F ON E.PARENT_USER_ID=F.USER_ID\n" +
            "WHERE A.USER_TYPE=9 AND E.IS_MAIN=1",nativeQuery=true)
    Page<Map<String,Object>> getUnExamine( Pageable pageable);

    @Modifying
    @Query( value="UPDATE STARCLOUDPORTAL.SCE_COMMON_USER SET USER_TYPE=5 WHERE USER_ID IN (?1)",nativeQuery=true )
    Integer updateParentStatus( List<String> strList );
}