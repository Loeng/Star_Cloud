package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户密码表
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public interface UserPasswordDao extends JpaRepository< UserPassword, Integer > {

    @Override
    UserPassword save( UserPassword user );


    @Query(value = "update  STARCLOUDPORTAL.SCE_COMMON_USER_PASSWORD set PASSWORD = :password where  USER_ID = :userId" ,nativeQuery = true)
    @Modifying
    int updateUserPassword(@Param( "userId" ) String userId,@Param( "password" ) String password );


}