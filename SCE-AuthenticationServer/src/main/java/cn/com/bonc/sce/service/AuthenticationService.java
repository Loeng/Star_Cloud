package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppDaoClient;
import cn.com.bonc.sce.dao.UserDaoClient;
import cn.com.bonc.sce.model.User;
import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.Map;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/22 17:56
 */
@Slf4j
@Service
public class AuthenticationService {

    UserDaoClient userDao;

    AppDaoClient appDaoClient;

    @Value( "{sce.publicKey}" )
    String publicKey;

    @Autowired
    public AuthenticationService( UserDaoClient userDao, AppDaoClient appDaoClient ) {
        this.userDao = userDao;
        this.appDaoClient = appDaoClient;
    }


    /**
     * 验证JWT
     * 如果是用户的JWT，则需要在payload中加入userId
     * 如果不是用户的JWT，则需要在payload中加入appId
     *
     * @param ticket 凭证
     *
     * @return JWT中的body(Claims类型)
     */
    public Claims validateJWT( String ticket ) {
        String payloadsStr = Base64.decodeStr( ticket.split( "\\." )[ 1 ] );
        Map payloadsMap = JSONUtil.toBean( payloadsStr, Map.class );
        Claims claims = null;
        Object userId = payloadsMap.get( "userId" );
        PublicKey publicKey;
        if ( userId == null ) {
            //不需要验证appId与appToken，JWT本身验证成功就证明为平台颁发的凭证，因此注释掉appId与appToken的验证
//            String appId = payloadsMap.get( "appId" ).toString();
//            String appToken = payloadsMap.get( "appToken" ).toString();
//            if( appId == null || appToken == null ){
//                log.warn( "JWT认证失败->不存在的appId或appToken" );
//                return null;
//            }
//            if( !appDaoClient.getAppToken( appId ).equals( appToken ) ){
//                log.warn( "JWT认证失败->appId与appToken不匹配" );
//                return null;
//            }
            publicKey = SecureUtil.generatePublicKey( "EC", Base64.decode( this.publicKey ) );
        } else {
            User user = userDao.getUserById( userId.toString() );
            publicKey = user.getSecretKeyPair().getPublicKey();
        }
        try {
            claims = Jwts.parser().setSigningKey( publicKey ).parseClaimsJws( ticket ).getBody();
        } catch ( ExpiredJwtException e ) {
            log.warn( "JWT认证失败->JWT超时" );
        } catch ( Exception e ) {
            e.printStackTrace();
            log.warn( "JWT认证失败->{}", e.getMessage() );
        }
        return claims;
    }

    public String getAppToken( String appId ) {
        return appDaoClient.getAppToken( appId );
    }

}
