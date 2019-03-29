package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppDaoClient;
import cn.com.bonc.sce.dao.UserDaoClient;
import cn.com.bonc.sce.exception.InvalidTokenException;
import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.tool.JWTUtil;
import cn.com.bonc.sce.tool.MD5Util;
import cn.com.bonc.sce.tool.RestApiUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
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

    @Value( "${sce.publicKey}" )
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
     * @param webRequest Request参数payload
     * @return JWT中的body(Claims类型)
     */
    public Claims validateJWT(NativeWebRequest webRequest) throws InvalidTokenException {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String ip = RestApiUtil.getIpAddr( request );
        String userAgent = request.getHeader( "User-Agent" );
        String ticket = webRequest.getHeader( "authentication" );
        Map payloadsMap = JWTUtil.parseJWT( ticket );
        Claims claims = null;
        Object userId = payloadsMap.get( "userId" );
        PublicKey publicKey;
        if(userId == null){
            //appId与appToken的验证
            String payloadAppId = payloadsMap.get( "appId" ).toString();
            String headerAppId = webRequest.getHeader( "appId" );
            String headerAppToken = webRequest.getHeader( "appToken" );
            if( headerAppId == null || headerAppToken == null ){
                throw new InvalidTokenException( "JWT认证失败 -> appId或appToken为空" );
            }
            if( !headerAppId.equals( payloadAppId ) ){
                throw new InvalidTokenException( "JWT认证失败 -> appId不存在" );
            }
            if( !appDaoClient.getAppToken( headerAppId ).equals( headerAppToken ) ){
                throw new InvalidTokenException( "JWT认证失败 -> appId与appToken不匹配" );
            }
            publicKey = SecureUtil.generatePublicKey( "EC", Base64.decode( this.publicKey ) );
        } else {
            User user = userDao.getUserById( userId.toString() );
            publicKey = user.getSecretKeyPair().getPublicKey();
        }
        try {
            claims = Jwts.parser().setSigningKey( publicKey ).parseClaimsJws( ticket ).getBody();
        }catch ( ExpiredJwtException e ){
            throw new InvalidTokenException( "JWT认证失败 -> 该JWT已超时" );
        }catch ( Exception e ){
            throw new InvalidTokenException( "JWT认证失败 -> " + e.getMessage() );
        }
//        if( claims != null && userId != null && !MD5Util.getMd5String( userAgent ).equals( claims.get( "IPAdd" ) ) ){
//            throw new InvalidTokenException( "JWT认证失败 -> IP地址不匹配，当前提交ip地址=" + ip + "，userAgent=" + userAgent );
//        }
        return claims;
    }

    public String getAppToken( String appId ) {
        return appDaoClient.getAppToken( appId );
    }

    public Map<String, Object> getAppInfoById(String appId){
        return appDaoClient.getAppInfoById( appId );
    }

    public int getUserAppAuth( String userId, String appId ){
        return appDaoClient.getUserAppAuth( userId, appId );
    }

}
