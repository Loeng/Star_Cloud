package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.UserDaoClient;
import cn.com.bonc.sce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 用户相关服务
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/22 17:57
 */
@Service
public class UserService {

    private UserDaoClient userDao;

    @Autowired
    public UserService( UserDaoClient userDao ) {
        this.userDao = userDao;
    }

    /**
     * 根据用户的 登录id 获取用户信息
     *
     * @param loginId 登录id
     * @return 用户数据
     */
    public User getUserByLoginId( String loginId ) {
        return userDao.getUserByLoginId( loginId );
    }

    /**
     * 根据用户数据生成 ticket
     *
     * @return
     */
    public String generateLoginTicket() {
        return null;
    }

    /**
     * 通过 loginId 和 密码 获取用户数据，获取不到返回 null
     *
     * @param identifier
     * @param password
     *
     * @return
     */
    public User checkLoginByLoginId( String identifier, String password ) {
        return new User( UUID.randomUUID().toString(), "xs-195122-01", "1" );
    }
}
