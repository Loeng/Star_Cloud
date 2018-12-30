package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.AccountDao;
import cn.com.bonc.sce.dao.RoleRelDao;
import cn.com.bonc.sce.dao.UserDao;
import cn.com.bonc.sce.entity.Account;
import cn.com.bonc.sce.entity.user.User;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/25 21:54
 */
@Slf4j
@RestController
@RequestMapping( "/users" )
public class UserApiController {

    private UserDao userDao;
    private AccountDao accountDao;
    private RoleRelDao roleRelDao;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserApiController( UserDao userDao, AccountDao accountDao,RoleRelDao roleRelDao ) {
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.roleRelDao = roleRelDao;
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
        User user = accountDao.getAccountByUserIdAndPassword( account.getUserId(), account.getPassword() ).getUser();

        if ( user == null ) {
            return new RestRecord( 1010, MessageConstants.SCE_MSG_1010, account.getUserId() );
        } else {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, user );
        }
    }

    @PostMapping( "/{userId}/login-status" )
    @ResponseBody
    public RestRecord changeLoginStatus( @PathVariable( "userId" ) String userId, @RequestBody() Integer isFirstLogin ) {
        int effeted = userDao.updateUserLoginStatus( userId, isFirstLogin );
        if ( effeted == 0 ) {
            log.error( MessageConstants.SCE_MSG_1020, userId, isFirstLogin );
            return new RestRecord( 1020 );
        } else {
            log.info( MessageConstants.SCE_MSG_1021, isFirstLogin == 1 ? "非" : "是" );
            return RestRecord.success();
        }
    }

    @GetMapping( "/detailed/{userId}" )
    @ResponseBody
    public RestRecord getUserInfo( @PathVariable( "userId" ) String userId ) {
        try {
            User user = userDao.findUserByUserId( userId );
            List< String > list = roleRelDao.getRoleTable( userId );
            if ( list != null && list.size() > 0 ) {
                String sql = "SELECT * FROM "+list.get( 0 );
                Query query =  entityManager.createNativeQuery(sql);
                List objecArraytList = query.getResultList();
                user.setUserDetailedInfo( objecArraytList );
            }
            return new RestRecord( 200, user );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }
}
