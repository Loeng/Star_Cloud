package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.UserDaoClient;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户相关服务
 *
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
     * @param loginName 登录id
     *
     * @return 用户数据
     */
    public User getUserByLoginName( String loginName ) {
        return userDao.getUserByLoginName( loginName );
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
     */
    public User checkLoginByLoginName( String loginName ) {
        // TODO 和 DataAccess 连接起来
//       return userDao.getUserByLoginName( loginName );
        User user = new User( "bonc1234", 5 );
        user.generateSecret();
        return user;
    }

    // TODO 解决 JSON 序列化的问题
//    public User getUserByUserId( String userId ) {
//        Map< String, Object > userInfo = userDao.getUserById( userId );
//
//        return new User( String.valueOf( userInfo.get( "userId" ) ), String.valueOf( userInfo.get( "loginName" ) ), String.valueOf( userInfo.get( "password" ) ) );
//    }

    public User getUserByUserId( String userId ) {
        return userDao.getUserById( userId );
    }
}
