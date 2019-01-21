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

    @Query(value="SELECT * FROM STARCLOUDPORTAL.SCE_COMMON_USER WHERE USER_TYPE=8",nativeQuery=true)
    Page<Map<String,Object>> getUnExamine( Pageable pageable);

    @Modifying
    @Query( value="UPDATE STARCLOUDPORTAL.SCE_COMMON_USER SET USER_TYPE=2 WHERE USER_ID IN (?1)",nativeQuery=true )
    Integer updateParentStatus( List<String> strList );
}