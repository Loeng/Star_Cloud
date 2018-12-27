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
    // TODO 启动一个定时跟新服务，去拉取角色 code
    private String[] roleCodes = { "none", "vendor", "school", "teacher", "students", "parents", "eduBureau", "agency", "tourist" };


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

        return JWTUtil.generateTicketWithSecret( claims, authenticatedUser.getSecretKeyPair().getPrivateKey() );
    }
}
