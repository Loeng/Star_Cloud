package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.UserBean;
import cn.com.bonc.sce.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Charles on 2019/3/6.
 */
@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public int saveUser(UserBean userBean) {
        return userMapper.saveUser(userBean);
    }

    public int saveAccount(AccountBean account) {
        return userMapper.saveAccount(account);
    }
}
