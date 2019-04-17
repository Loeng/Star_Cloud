package cn.com.bonc.sce.service;

import cn.com.bonc.sce.controller.AuthenticationController;
import cn.com.bonc.sce.dao.LoginHistoryDaoClient;
import cn.com.bonc.sce.dao.UserDaoClient;
import cn.com.bonc.sce.exception.UnsupportedAuthenticationTypeException;
import cn.com.bonc.sce.model.SSOAuthentication;
import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.tool.JWTUtil;
import cn.com.bonc.sce.tool.MD5Util;
import cn.com.bonc.sce.tool.RestApiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.PrivateKey;
import java.util.Date;
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
     * @return 字符串形式的 jwt ticket
     */
    public String generateTicket( User authenticatedUser, Date expirationDate, HttpServletRequest request, String subject ) {
        Map< String, Object > claims = new HashMap<>( 2 );
        claims.put( "userId", authenticatedUser.getUserId() );
        claims.put( "loginId", authenticatedUser.getLoginName() );
        claims.put( "userType", authenticatedUser.getUserType() );
        claims.put( "isAdmin", authenticatedUser.getIsAdministrators() );
        claims.put( "auditStatus", 0 );
        // 签发人
        claims.put( "iss", "SCE-SSO" );
        // 受众
        claims.put( "aud", "SCE-Application" );
        claims.put( "ruleCode", roleCodes[ authenticatedUser.getUserType() ] );
        claims.put( "isFirstLogin", authenticatedUser.getIsFirstLogin() );
        // todo 此处需将用户地址单向加密并保存至JWT  防止JWT被窃取后使用
        // ip地址
        String ip = RestApiUtil.getIpAddr( request );
//        claims.put( "IPAdd", MD5Util.getMd5String( ip ) );
        // user-Agent
        String userAgent = request.getHeader( "User-Agent" );
        if ( userAgent == null ) {
            log.warn( "请求中缺少User-Agent" );
            throw new NullPointerException();
        }
        claims.put( "IPAdd", MD5Util.getMd5String( userAgent ) );

        log.info( "生成JWT，用户名：{}，用户IP：{}，userAgent={}", authenticatedUser.getLoginName(), ip, userAgent );
        return JWTUtil.generateTicketWithSecret( claims, authenticatedUser.getSecretKeyPair().getPrivateKey(), expirationDate, subject == null ? AuthenticationController.LOGIN : subject );
    }

    /**
     * 平台与应用间通信所使用的JWT
     *
     * @param claims         payload内容
     * @param expirationDate 过期时间
     * @return ticket
     */
    public String generateTicket( Map< String, Object > claims, PrivateKey privateKey, Date expirationDate, String subject ) {
        claims.put( "iss", "SCE-SSO" );
        claims.put( "aud", "SCE-Application" );

        return JWTUtil.generateTicketWithSecret( claims, privateKey, expirationDate, subject );
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
            //1，根据账号获取用户信息
            authenticatedUser = userService.getUserByLoginName( loginInfo.getIdentifier() );
            if ( authenticatedUser == null ) {
                //2.根据手机号获取用户信息
                authenticatedUser = userService.getUserByPhoneNubmer( loginInfo.getIdentifier() );
            }
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
     * @return 登录信息
     */
    public Map< String, Object > generateLoginResult( User authenticatedUser, Date expirationDate, HttpServletRequest request ) {
        Map< String, Object > data = new HashMap<>( 2 );
        String ticket = generateTicket( authenticatedUser, expirationDate, request, null );
        data.put( "ticket", ticket );
        return data;
    }
}
