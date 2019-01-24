package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户密码表
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Repository
public interface UserPasswordDao extends JpaRepository< UserPassword, Integer > {

    @Override
    UserPassword save( UserPassword user );


    @Query("update UserPassword  userPassword set userPassword.password = :passWord where  userPassword.userId = :userId")
    int updatePasswordById( @Param( "userId" ) String userId,@Param( "passWord" ) String passWord );


}