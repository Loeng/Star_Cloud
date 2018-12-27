package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
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
}