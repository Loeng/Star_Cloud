package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.FeignUserDao;
import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/15 19:20
 */
@Slf4j
@Service
public class AuthenticationService {

    private FeignUserDao userDao;

    @Autowired
    public AuthenticationService( FeignUserDao userDao ) {
        this.userDao = userDao;
    }

    public RestRecord checkUserIdentityByEmail( String email, String password) {
        return userDao.getUserById( "11" );
    }
}
