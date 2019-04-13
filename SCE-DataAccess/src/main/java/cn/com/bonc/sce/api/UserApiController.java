package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.AccountDao;
import cn.com.bonc.sce.dao.RoleRelDao;
import cn.com.bonc.sce.dao.UserDao;
import cn.com.bonc.sce.entity.Account;
import cn.com.bonc.sce.entity.School;
import cn.com.bonc.sce.entity.user.User;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
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

    private String splitStr = "市";

    @Autowired
    public UserApiController( UserDao userDao, AccountDao accountDao, RoleRelDao roleRelDao ) {
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

    @PostMapping( "/login-phoneNumber-is/" )
    @ResponseBody
    public User getUserByLoginNameOrPhoneNumber( @RequestBody String phoneNumber ) {
           return userDao.findUserByPhoneNumber( phoneNumber );
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

    @PostMapping( "/{userId}/info/correction" )
    @ResponseBody
    public RestRecord changeUserInfoIntegrityAndAccuracyStatus( @PathVariable( "userId" ) String userId, @RequestBody Boolean isAllCorrect ) {
        int effeted = userDao.updateUserLoginStatus( userId, isAllCorrect ? 1 : 0 );
        if ( effeted == 0 ) {
            log.error( MessageConstants.SCE_MSG_1020, userId, isAllCorrect );
            return new RestRecord( 1020 );
        } else {
            log.info( MessageConstants.SCE_MSG_1021, isAllCorrect ? "非" : "是" );
            return RestRecord.success();
        }
    }

    @GetMapping( "/detailed/{userId}" )
    @ResponseBody
    public RestRecord getUserInfo( @PathVariable( "userId" ) String userId ) {
        try {
            User user = userDao.findUserByUserId( userId );
            String address = user.getAddress();
            if ( !StringUtils.isEmpty( address ) ) {
                String[] strs = address.split( splitStr );
                user.setBriefAddress( strs[ 0 ] + splitStr );
            }
            List< String > list = roleRelDao.getRoleTable( userId );
            if ( list != null && list.size() > 0 ) {
                String sql = "SELECT * FROM " + list.get( 0 ) + " WHERE USER_ID =" + "'" + userId + "'";
                Query query = entityManager.createNativeQuery( sql );
                query.unwrap( org.hibernate.SQLQuery.class ).setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
                user.setUserDetailedInfo( query.getResultList() );
            }


            if ( user.getUserType() == 1 ) {
                //查询学生学校名称和学生年级
                School school = new School();
                school.setSchoolName( userDao.findSchoolByOrganizationId( user.getOrganizationId() ) );
                school.setGrade( userDao.findGradeByUserId( userId ) );
                user.setSchool( school );
            } else if ( user.getUserType() == 2 ) {
                //查询教师所在单位
                School school = new School();
                school.setSchoolName( userDao.findSchoolByOrganizationId( user.getOrganizationId() ) );
                user.setSchool( school );
            }
            user.setSecret( null );
            Account account = user.getAccount();
            account.setPassword( null );
            user.setAccount( account );
            return new RestRecord( 200, user );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }

    @PostMapping( "/detailed" )
    @ResponseBody
    @Transactional
    public RestRecord updateUserInfo( @RequestBody User user ) {
        int num = 0;
        try {
            String conditionUser = "";
            String conditionRole = "";
            String userId = user.getUserId();
            String username = user.getUserName();
            String address = user.getAddress();
            String number = user.getCertificateNumber();
            String mailAddress = user.getMailAddress();
            String phoneNumber = user.getPhoneNumber();
            String gender = user.getGender();
            if ( !StringUtils.isEmpty( username ) ) {
                conditionUser = conditionUser + ",USER_NAME='" + username + "'";
            }
            if ( !StringUtils.isEmpty( address ) ) {
                conditionUser = conditionUser + ",ADDRESS='" + address + "'";
            }
            if ( !StringUtils.isEmpty( number ) ) {
                conditionUser = conditionUser + ",CERTIFICATE_NUMBER='" + number + "'";
            }
            if ( !StringUtils.isEmpty( mailAddress ) ) {
                conditionUser = conditionUser + ",MAIL_ADDRESS='" + mailAddress + "'";
            }
            if ( !StringUtils.isEmpty( phoneNumber ) ) {
                conditionUser = conditionUser + ",PHONE_NUMBER='" + phoneNumber + "'";
            }
            if ( !StringUtils.isEmpty( gender ) ) {
                conditionUser = conditionUser + ",GENDER='" + gender + "'";
            }
            if ( conditionUser.length() > 0 ) {
                String sqlUser = "UPDATE STARCLOUDPORTAL.SCE_COMMON_USER SET " + conditionUser.substring( 1 ) + " WHERE USER_ID = " + "'" + userId + "'";
                Query query = entityManager.createNativeQuery( sqlUser );
                num += query.executeUpdate();
            }


            Map< String, String > userDetailedInfo = ( Map< String, String > ) user.getUserDetailedInfo();
            if ( ObjectUtils.isEmpty( userDetailedInfo ) ) {
                return new RestRecord( 200, num );
            }
            for ( Map.Entry< String, String > entry : userDetailedInfo.entrySet() ) {
                if ( entry.getKey().equals( "USER_ID" ) || entry.getKey().equals( "IS_DELETE" ) ) {
                    continue;
                }
                conditionRole = conditionRole + "," + entry.getKey() + "='" + entry.getValue() + "'";
            }
            List< String > list = roleRelDao.getRoleTable( userId );
            if ( list != null && list.size() > 0 && conditionRole.length() > 0 ) {
                String tableName = list.get( 0 );
                String sqlRole = "UPDATE " + tableName + " SET " + conditionRole.substring( 1 ) + " WHERE USER_ID = " + "'" + userId + "'";
                Query query = entityManager.createNativeQuery( sqlRole );
                num += query.executeUpdate();
            }
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 407, MessageConstants.SCE_MSG_407, e );
        }
        return new RestRecord( 200, num );
    }

    /**
     * 用户登陆后
     * 更新用户【登陆时间】和【登陆次数】
     *
     * @param userId
     * @return
     */
    @PostMapping( "/login-time-counts/{userId}" )
    @ResponseBody
    @Transactional
    public RestRecord updateUserLoginTimeAndCounts( @PathVariable( "userId" ) String userId ) {
        return new RestRecord( 200, userDao.updateUserLoginTimeAndCounts( userId ) );
    }

}
