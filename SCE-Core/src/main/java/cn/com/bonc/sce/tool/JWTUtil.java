package cn.com.bonc.sce.tool;

import cn.com.bonc.sce.constants.DateConstants;
import cn.hutool.core.codec.Base64;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.security.*;
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
@Slf4j
@Component
@Scope( value = "singleton" )
public class JWTUtil {

    /**
     * 为默认
     *
     * @param header JWT header
     * @param claims JWT body
     * @param secret 私钥，必须以 Base64 encode
     *
     * @return 加密过后的 JWT ticket
     */
    public static String generateTicketWithSecret( Map< String, Object > header, Map< String, Object > claims, Key secret ) {
        return defaultBuilder()
                .setHeader( header )
                .addClaims( claims )
                .signWith( secret )
                .compact();
    }

    public static String generateTicketWithSecret( Map< String, Object > claims, Key secret ) {
        return defaultBuilder()
                .addClaims( claims )
                .signWith( secret )
                .compact();
    }

    public static String generateTicketWithSecret( Map< String, Object > claims, Key secret, Date expirationDate ) {
        return defaultBuilder()
                .addClaims( claims )
                .setExpiration( expirationDate )
                .signWith( secret )
                .compact();
    }

    public static String generateTicketWithSecret( Key secret ) {
        return defaultBuilder().signWith( secret ).compact();
    }

    public static boolean volidate( String jwt ) {
        return Jwts.parser().isSigned( jwt );
    }

    @SuppressWarnings( "unchecked" )
    public static JwtBuilder defaultBuilder() {
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

    public static String readJWTClaim( String claim ) {
        return Base64.decodeStr( claim );
    }

    public static void main( String[] args ) throws InvalidKeyException, NoSuchAlgorithmException {
        /*KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance( "RSA" );
        keyPairGenerator.initialize( 2048 );

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        CryptoUtil cryptoUtil = new CryptoUtil();
        KeyPair keyPair2 = cryptoUtil.RSA_CRYPTO_UTIL_INSTANCE.generateKeyPair();

        String ticket = JWTUtil.generateTicketWithSecret( keyPair2.getPrivate() );


        String claims = ticket.split( "\\." )[ 1 ];
        String target = cn.hutool.core.codec.Base64.decodeStr( claims );
        System.out.println( target );*/

        System.out.println( readJWTClaim( "eyJleHAiOjE1NDU4Mzk0MDUsInN1YiI6ImxvZ2luIiwibmJmIjoxNTQ1ODM5MzQ1LCJpYXQiOjE1NDU4MzkzNDUsImp0aSI6IjRjYWYyMGRlLTEzZDEtNDkxMy1hYzhkLTk5NjAyZjZlYzY4YSIsInJ1bGVDb2RlIjoicGFyZW50cyIsImxvZ2luSWQiOiJSR003OSIsInVzZXJUeXBlIjowLCJ1c2VySWQiOiIwIn0" ) );
    }

}
