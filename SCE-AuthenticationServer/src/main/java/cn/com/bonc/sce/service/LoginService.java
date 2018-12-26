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

    /**
     * 根据用户私钥生成 ticket
     * @param authenticatedUser 登录用户
     * @return 字符串形式的 jwt ticket
     */
    public String generateTicket( User authenticatedUser ) {
        Map< String, Object > claims = new HashMap<>( 2 );
        claims.put( "userId", authenticatedUser.getUserId() );
        claims.put( "loginId", authenticatedUser.getLoginName() );
        claims.put( "userType", authenticatedUser.getUserType() );
        // TODO 传入真实的 ruleCode
        claims.put( "ruleCode", "parents" );

        return JWTUtil.generateTicketWithSecret( claims, authenticatedUser.getSecretKeyPair().getPrivateKey() );
    }
}
