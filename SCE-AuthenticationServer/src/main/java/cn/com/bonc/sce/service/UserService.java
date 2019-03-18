package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.UserDaoClient;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.rest.RestRecord;
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

    public User getUserByUserId( String userId ) {
        return userDao.getUserById( userId );
    }

    /**
     * 清除用户敏感数据
     */
    public void clearSensitiveInformation( User userInfo ) {
        userInfo.setSecret( null );
        userInfo.setSecretKeyPair( null );
        userInfo.setAccount( null );
        userInfo.setCertificateType( null );
        userInfo.setCertificateNumber( null );
        userInfo.setAddress( null );
        userInfo.setPassword( null );
    }

    /**
     * 获取用户
     *
     * @return 获取用户
     */
    public RestRecord getUserInfo( String userId ) {
        return userDao.getUserInfo( userId );
    }

    /**
     * 修改用户
     *
     * @return 修改用户
     */
    public RestRecord updateUserInfo( User user ) {
        return userDao.updateUserInfo( user );
    }

    /**
     * 修改用户数据完整性/正确性显示参数
     *
     * @param userId     用户Id
     * @param allCorrect 用户数据是否完整/正确
     * @return
     */
    public int changeUserInfoIntegrityAndAccuracyStatus( String userId, boolean allCorrect ) {
        return userDao.changeUserInfoIntegrityAndAccuracyStatus( userId, allCorrect ).getCode();
    }

    public RestRecord updatePasswordById( String userId, String passWord ) {
        return userDao.updatePasswordById( userId, passWord );
    }

    /**
     * 更新用户【登陆时间】和【登陆次数】
     * @param userId
     * @return
     */
    public RestRecord updateUserLoginTimeAndCounts( String userId ) {
        return userDao.updateUserLoginTimeAndCounts( userId );
    }


}
