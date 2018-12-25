package cn.com.bonc.sce.tool;

import cn.com.bonc.sce.constants.DateConstants;
import cn.hutool.crypto.asymmetric.RSA;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 提供对 jwt token 的生成和解密
 *
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/22 20:36
 */
@Component()
@Scope( value = "singleton" )
public class JWTUtil {

    /**
     * 根据默认
     *
     * @param header
     * @param claims
     * @param secret
     * @return
     */
    public String generateKeyWithSecret( Map< String, Object > header, Map< String, Object > claims, Key secret ) {
        return defaultBuilder()
                .setHeader( header )
                .setClaims( claims )
                .signWith( secret )
                .compact();
    }

    public String generateKeyWithSecret( byte[] secret ) {
        return defaultBuilder().signWith( SignatureAlgorithm.RS512, secret ).compact();
    }

    @SuppressWarnings( "unchecked" )
    public JwtBuilder defaultBuilder() {
        Map defaultHeader = Jwts.header()
                .setType( "JWT" );

        return Jwts.builder()
                .setHeader( defaultHeader )
                // .setIssuer( "base" )
                // 一分钟过期
                .setExpiration( new Date( System.currentTimeMillis() + DateConstants.ONE_MINUTE ) )
                .setSubject( "login" )
                // .setAudience( "sce-application" )
                .setNotBefore( new Date() )
                .setIssuedAt( new Date() )
                // TODO 访问统一 id 生成服务获取 id
                .setId( UUID.randomUUID().toString() );
    }

    public static void main( String[] args ) throws InvalidKeyException {
        RSA rsa = new RSA();
        String key = rsa.getPrivateKeyBase64();
        System.out.println( key );


        JWTUtil jwtUtil = new JWTUtil();
        String token = null;
        token = jwtUtil.generateKeyWithSecret( key.getBytes() );
        System.out.println( token );

    }

}
