package cn.com.bonc.sce.service;

import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.tool.JWTUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/25 17:25
 */
@Service
public class LoginService {

    public String generateAccessToken( User authenticatedUser ) {
        Map< String, Object > claims = new HashMap<>( 2 );
        claims.put( "userId", authenticatedUser.getUserId() );
        claims.put( "loginId", authenticatedUser.getUsername() );

//        return JWTUtil.generateKeyWithSecret( claims, authenticatedUser.getSecret().getKeyPair().getPrivate() );
        return JWTUtil.generateTicketWithSecret( claims, authenticatedUser.getSecret().getPrivateKey() );
    }

    public static void main( String[] args ) {
        UserService userService = new UserService( null );
        User user = userService.checkLoginByLoginId( "", "" );
        LoginService loginService = new LoginService();
        String ticket = loginService.generateAccessToken( user );
        System.out.println( ticket );
    }
}
