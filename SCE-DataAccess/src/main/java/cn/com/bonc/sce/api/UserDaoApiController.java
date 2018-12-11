package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.UserDao;
import cn.com.bonc.sce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 11:08
 */
@RestController
@RequestMapping( "/users" )
public class UserDaoApiController {
    private UserDao userDao;

    @Autowired
    public UserDaoApiController( UserDao userDao ) {
        this.userDao = userDao;
    }

    /**
     * 根据用户 id 获取用户数据
     * @param userId 用户 id
     * @return 用户数据
     */
    @GetMapping( "/{userId}" )
    @ResponseBody
    public User getUserById( @PathVariable( "userId" ) String userId ) {
        return userDao.getUserById( userId );
    }
}
