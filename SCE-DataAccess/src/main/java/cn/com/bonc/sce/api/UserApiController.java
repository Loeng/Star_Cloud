package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.UserDao;
import cn.com.bonc.sce.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/25 21:54
 */
@RestController
@RequestMapping( "/users" )
public class UserApiController {

    private UserDao userDao;

    @Autowired
    public UserApiController( UserDao userDao ) {
        this.userDao = userDao;
    }

    @GetMapping( "{userId}" )
    @ResponseBody
    public User getUserById( @PathVariable( "userId" ) String userId ) {
        return userDao.findUserByUserId( userId );
    }

    @PostMapping( "" )
    @ResponseBody
    public User getUserById( @RequestBody User user ) {
        return userDao.findUserByUserIdAndPassword( user.getUserId(), user.getPassword() );
    }
}
