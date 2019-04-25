package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.UserDaoClient;
import cn.com.bonc.sce.dao.UserDaoOfMybatis;
import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.rest.RestRecord;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.bouncycastle.util.Integers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    private UserDaoOfMybatis userDaoOfMybatis;

    @Autowired
    public UserService( UserDaoClient userDao, UserDaoOfMybatis userDaoOfMybatis ) {
        this.userDao = userDao;
        this.userDaoOfMybatis = userDaoOfMybatis;
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
     * 根据用户的 手机号 获取用户信息
     *
     * @param phoneNumber 手机号
     * @return 用户数据
     */
    public User getUserByPhoneNubmer( String phoneNumber ) {
        return userDao.getUserByPhoneNubmer( phoneNumber );
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
     * 获取认证状态
     *
     * @param organizationId @return 1为已认证
     */
    public int getAuditStatus( Long organizationId ) {
        if(organizationId==null){
            return 0;
        }
        RestRecord restRecord = userDao.getAuditStatusByEntityId( organizationId );

        if ( restRecord.getCode() == 200 && restRecord.getData() != null ) {
            JSONObject json = JSONUtil.parseObj( restRecord.getData() );
            Integer auditStatus = Integer.parseInt( json.get( "auditStatus" ).toString() );
            return auditStatus == 1 ? 1 : 0;
        }

        return 0;
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
     *
     * @param userId
     * @return
     */
    public RestRecord updateUserLoginTimeAndCounts( String userId ) {
        return userDao.updateUserLoginTimeAndCounts( userId );
    }

    public RestRecord getUser( HttpServletRequest request ) {
        String appId = request.getHeader( "appId" );
        String appToken = request.getHeader( "appToken" );
        if ( appId == null || appToken == null ) {
            return new RestRecord( 431, String.format( WebMessageConstants.SCE_PORTAL_MSG_431, "必需" ) );
        }
        return userDaoOfMybatis.getUser( appId, appToken );
    }

    public RestRecord getUserJWT( List< Map > claims ) {
        if ( claims.size() < 1 || claims.get( 0 ).get( "appId" ) == null ) {
            return new RestRecord( 151, WebMessageConstants.SCE_WEB_MSG_151 );
        }
        String appId = claims.get( 0 ).get( "appId" ).toString();
        List users = ( List ) claims.get( 0 ).get( "users" );
        return userDaoOfMybatis.getUserJWT( appId, users );
    }

    public RestRecord getUserDetailed( List< Map > claims ) {
        if ( claims.size() < 1 || claims.get( 0 ).get( "appId" ) == null ) {
            return new RestRecord( 151, WebMessageConstants.SCE_WEB_MSG_151 );
        }
        String appId = claims.get( 0 ).get( "appId" ).toString();
        String userId = claims.get( 0 ).get( "userId" ).toString();
        return userDaoOfMybatis.getUserDetailed( appId, userId );
    }

}
