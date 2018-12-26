package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 7:50
 */
@Repository
public interface UserDao extends JpaRepository< User, String > {
    public User findUserByUserId( String userId );

    public User findUserByLoginName( String loginName );
}
