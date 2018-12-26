package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.AccountDao;
import cn.com.bonc.sce.dao.UserDao;
import cn.com.bonc.sce.entity.Account;
import cn.com.bonc.sce.entity.user.User;
import cn.com.bonc.sce.rest.RestRecord;
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
    private AccountDao accountDao;

    @Autowired
    public UserApiController( UserDao userDao, AccountDao accountDao ) {
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @GetMapping( "/{userId}" )
    @ResponseBody
    public User getUserById( @PathVariable( "userId" ) String userId ) {
        return userDao.findUserByUserId( userId );
    }

    @PostMapping( "/login-name-is/" )
    @ResponseBody
    public User getUserByLoginName( @RequestBody String loginName ) {
        return userDao.findUserByLoginName( loginName );
    }

    @PostMapping( value = "" )
    @ResponseBody
    public RestRecord getUserById( @RequestBody Account account ) {
        User user = accountDao.getAccountByUserIdAndPassword( account.getUserId(),account.getPassword() ).getUser();

        if ( user == null ) {
            return new RestRecord( 1010, MessageConstants.SCE_MSG_1010, account.getUserId() );
        } else {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, user );
        }

    }
}
