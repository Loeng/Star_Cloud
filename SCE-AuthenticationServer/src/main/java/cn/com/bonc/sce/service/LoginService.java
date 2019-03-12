package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.LoginHistoryDaoClient;
import cn.com.bonc.sce.dao.UserDaoClient;
import cn.com.bonc.sce.exception.UnsupportedAuthenticationTypeException;
import cn.com.bonc.sce.model.SSOAuthentication;
import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.tool.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/25 17:25
 */
@Slf4j
@Service
public class LoginService {
    /**
     * 本系统支持的登录类型
     */
    private static final int AUTH_TYPE_0 = 0;
    private static final int AUTH_TYPE_1 = 1;
    private static final int AUTH_TYPE_2 = 2;

    private LoginHistoryDaoClient loginHistoryDao;
    private UserDaoClient userDao;
    private UserService userService;

    // TODO 启动一个定时跟新服务，去拉取角色 code
    private String[] roleCodes = { "operator", "students", "teacher", "school", "vendor", "parents", "agency", "eduBureau", "tourist", "wait", "9" };

    @Autowired
    public LoginService( LoginHistoryDaoClient loginHistoryDao, UserDaoClient userDao, UserService userService ) {
        this.loginHistoryDao = loginHistoryDao;
        this.userDao = userDao;
        this.userService = userService;
    }

    /**
     * 根据用户私钥生成 ticket
     *
     * @param authenticatedUser 登录用户
     *
     * @return 字符串形式的 jwt ticket
     */
    public String generateTicket( User authenticatedUser ) {
        Map< String, Object > claims = new HashMap<>( 2 );
        claims.put( "userId", authenticatedUser.getUserId() );
        claims.put( "loginId", authenticatedUser.getLoginName() );
        claims.put( "userType", authenticatedUser.getUserType() );
        // 签发人
        claims.put( "iss", "SCE-SSO" );
        // 受众
        claims.put( "aud", "SCE-Application" );
        claims.put( "ruleCode", roleCodes[ authenticatedUser.getUserType() ] );
        claims.put( "isFirstLogin", authenticatedUser.getIsFirstLogin() );

        return JWTUtil.generateTicketWithSecret( claims, authenticatedUser.getSecretKeyPair().getPrivateKey() );
    }

    /**
     * 在确认用户完成首次登录（用户数据验证、用户数据初始化等）后，用以更新用户的 (首次)登录状态。
     * 若用户未登录过，则更新 firstLogin 状态为 1 （已完成首次登录流程）
     *
     * @param authenticatedUser
     */
    public void confirmUserFirstLogin( User authenticatedUser ) {
        // do nothong
    }


    /**
     * 检查登录用户的登录类型、用户信息是否正确
     */
    public User getUserInfo( SSOAuthentication loginInfo ) throws UnsupportedAuthenticationTypeException {
        User authenticatedUser;

        /*
         * 验证数据有效性并验证用户登录信息
         */
        if ( loginInfo.getAuthType() == AUTH_TYPE_0 ) {
            authenticatedUser = userService.getUserByLoginName( loginInfo.getIdentifier() );
        } else if ( loginInfo.getAuthType() == AUTH_TYPE_1 ) {
            // 暂不支持
            throw new UnsupportedAuthenticationTypeException();
        } else if ( loginInfo.getAuthType() == AUTH_TYPE_2 ) {
            // 暂不支持
            throw new UnsupportedAuthenticationTypeException();
        } else {
            throw new UnsupportedAuthenticationTypeException();
        }

        return authenticatedUser;
    }

    /**
     * 用户成功登录后，生成返回给前台的登录信息
     *
     * @param authenticatedUser 登录用户数据
     *
     * @return 登录信息
     */
    public Map< String, String > generateLoginResult( User authenticatedUser ) {
        Map< String, String > data = new HashMap<>( 2 );
        String ticket = generateTicket( authenticatedUser );
        data.put( "ticket", ticket );

        return data;
    }
}
