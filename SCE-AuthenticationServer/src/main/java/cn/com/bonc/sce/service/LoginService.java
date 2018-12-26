package cn.com.bonc.sce.service;

import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.tool.JWTUtil;
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

    public String generateTicket( User authenticatedUser ) {
        Map< String, Object > claims = new HashMap<>( 2 );
        claims.put( "userId", authenticatedUser.getUserId() );
        claims.put( "loginId", authenticatedUser.getLoginName() );
        claims.put( "userType", authenticatedUser.getUserType() );
        // TODO 传入真实的 ruleCode
        claims.put( "ruleCode", "parents" );

//        return JWTUtil.generateKeyWithSecret( claims, authenticatedUser.getSecrete2().getKeyPair().getPrivate() );
        return JWTUtil.generateTicketWithSecret( claims, authenticatedUser.getSecrete2().getPrivateKey() );
    }

//    public static void main( String[] args ) {
//        UserService userService = new UserService( null );
////        User user = userService.checkLoginByLoginName( "", "" );
//        LoginService loginService = new LoginService();
////        String ticket = loginService.generateTicket( user );
//        System.out.println( ticket );
//    }
}
