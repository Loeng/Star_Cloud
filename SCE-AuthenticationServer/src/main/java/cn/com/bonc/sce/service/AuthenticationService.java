package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppDaoClient;
import cn.com.bonc.sce.dao.UserDaoClient;
import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.tool.MD5Util;
import cn.com.bonc.sce.tool.RestApiUtil;
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
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.util.List;
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
    public Claims validateJWT(NativeWebRequest webRequest){
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String ip = RestApiUtil.getIpAddr( request );
        String ticket = webRequest.getHeader( "authentication" );
        String payloadsStr = null;
        Map payloadsMap = null;
        try{
            payloadsStr = Base64.decodeStr( ticket.split( "\\." )[ 1 ] );
            payloadsMap = JSONUtil.toBean( payloadsStr, Map.class );
        }catch ( NullPointerException e ){
            log.warn( "JWT认证失败 -> 参数authentication缺失" );
        }catch ( IndexOutOfBoundsException e ){
            log.warn( "JWT认证失败 -> 参数authentication不合法" );
        }catch ( Exception e ){
            log.warn( "JWT认证失败 -> 参数authentication解析失败：" + e.getMessage() );
        }
        if( payloadsStr == null || payloadsMap == null ){
            return null;
        }
        Claims claims = null;
        Object userId = payloadsMap.get( "userId" );
        PublicKey publicKey;
        if(userId == null){
            //appId与appToken的验证
            String payloadAppId = payloadsMap.get( "appId" ).toString();
            String headerAppId = webRequest.getHeader( "appId" );
            String headerAppToken = webRequest.getHeader( "appToken" );
            if( headerAppId == null || headerAppToken == null ){
                log.warn( "JWT认证失败 -> appId或appToken为空" );
                return null;
            }
            if( !headerAppId.equals( payloadAppId ) ){
                log.warn( "JWT认证失败 -> appId不存在" );
                return null;
            }
            if( !appDaoClient.getAppToken( headerAppId ).equals( headerAppToken ) ){
                log.warn( "JWT认证失败 -> appId与appToken不匹配" );
                return null;
            }
            publicKey = SecureUtil.generatePublicKey( "EC", Base64.decode( this.publicKey ) );
        } else {
            User user = userDao.getUserById( userId.toString() );
            publicKey = user.getSecretKeyPair().getPublicKey();
        }
        try {
            claims = Jwts.parser().setSigningKey( publicKey ).parseClaimsJws( ticket ).getBody();
        }catch ( ExpiredJwtException e ){
            log.warn( "JWT认证失败 -> 该JWT已超时" );
        }catch ( Exception e ){
            log.warn( "JWT认证失败 -> {}", e.getMessage());
        }
//        if( claims != null && userId != null && !MD5Util.getMd5String( ip ).equals( claims.get("IPAdd") ) ){
//            log.warn( "JWT认证失败 -> IP地址不匹配，当前提交ip地址={}", ip );
//            return null;
//        }
        System.out.println(claims.toString());
        return claims;
    }

    public String getAppToken( String appId ) {
        return appDaoClient.getAppToken( appId );
    }

    public Map<String, Object> getAppInfoById(String appId){
        return appDaoClient.getAppInfoById( appId );
    }

}
