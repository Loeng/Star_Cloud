package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 7:50
 */
@Slf4j
@Repository
public class UserDao {

    public User getUserById( String userId ) {
        return new User( 0, "RGM79","11111","321321" );
    }

//    public User getUserByEmail (String email);

    public boolean deleteUserById( String userId ) {
        return false;
    }

    public boolean updateUser( User user ) {
        return false;
    }

    public User createUser( User user ) {
        return null;
    }

    public boolean updateUserName( String userId, String username ) {
        return false;
    }

    public List< User > getAll() {
        return null;
    }
}
