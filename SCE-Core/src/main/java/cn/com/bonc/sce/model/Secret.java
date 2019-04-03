package cn.com.bonc.sce.model;

import cn.com.bonc.sce.tool.CryptoUtil;
import cn.com.bonc.sce.tool.JWTUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.RSA;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.*;
import java.util.*;

/**
 * 基于 2048bit-RSA 的秘钥对
 *
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/25 16:23
 */
public class Secret {
    private KeyPair keyPair;
    private String algorithm = "RSA";

    public Secret( String secret ) {
        int length = secret.length();
        if ( length > 216 ) {
            String publicKey = secret.substring( 0, 392 );
            String privateKey = secret.substring( 392, length );

            RSA rsa = new RSA( privateKey, publicKey );
            this.keyPair = new KeyPair( rsa.getPublicKey(), rsa.getPrivateKey() );
        } else {
            String publicKey = secret.substring( 0, 124 );
            String privateKey = secret.substring( 124, length );

            PrivateKey privateKeyObj = SecureUtil.generatePrivateKey( "EC", Base64.decode( privateKey ) );
            PublicKey publicKeyObj = SecureUtil.generatePublicKey( "EC", Base64.decode( publicKey ) );
            this.keyPair = new KeyPair( publicKeyObj, privateKeyObj );
        }

    }

    public Secret() {
        this.keyPair = CryptoUtil.RSA_CRYPTO_UTIL_INSTANCE.generateKeyPair();
    }

    public String getSecretKeyPair() {
        if ( keyPair != null ) {
            this.keyPair = CryptoUtil.RSA_CRYPTO_UTIL_INSTANCE.generateKeyPair();
        }
        RSA rsa = new RSA( getPrivateKey(), getPublicKey() );
        return rsa.getPublicKeyBase64() + rsa.getPrivateKeyBase64();
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    /**
     * 自动生成一个由私钥/公钥组成的字符串，格式如下:
     * <bold>^[privateKey][publicKey]&</bold> 私钥长 1624 个字符，公钥长 382 个字符，组成共计 2016 个字符的 secret
     * 此方法只能生成基于 2048bits-RSA 加密算法生成的 RSA 公私钥，如需定制加密算法，请自建 CryptoUtil 并设置相应算法的 keyPairGenerator
     *
     * @return a secret buries secrets
     */
    public static String generateSecret() {
        KeyPair keyPair = CryptoUtil.RSA_CRYPTO_UTIL_INSTANCE.generateKeyPair();

        RSA rsa = new RSA( keyPair.getPrivate(), keyPair.getPublic() );
        return rsa.getPublicKeyBase64() + rsa.getPrivateKeyBase64();
    }

    /**
     * 根据ECC椭圆曲线算法生成公私钥并经过Base64编码
     * 其中编码后的公钥长度为124  私钥为92  共216字符
     *
     * @return a secret buries secrets
     */
    public static String ES256GenerateSecret() {
        KeyPair keyPair = Keys.keyPairFor( SignatureAlgorithm.ES256 );
        String publicKey = Base64.encode( keyPair.getPublic().getEncoded() );
        String privateKry = Base64.encode( keyPair.getPrivate().getEncoded() );

        return publicKey + privateKry;
    }

    public String getPrivateKeyString() {
        if ( this.keyPair != null ) {
            return new RSA( getPrivateKey(), getPublicKey() ).getPrivateKeyBase64();
        }
        return null;
    }

    public String getPublicKeyString() {
        if ( this.keyPair != null ) {
            return new RSA( getPrivateKey(), getPublicKey() ).getPublicKeyBase64();
        }
        return null;
    }

    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    public PublicKey getPublicKey() {
        return this.keyPair.getPublic();
    }

    public static void main( String[] args ) throws Exception {
//        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJsb2dpbiIsIm5iZiI6MTU1NDI2MjIwNCwiaWF0IjoxNTU0MjYyMjA0LCJqdGkiOiIwMzExNTJmNC1iZmUwLTRhNTktOTBkYi0zNjcxYzEzZmU0MzQiLCJydWxlQ29kZSI6InN0dWRlbnRzIiwiSVBBZGQiOiI3NjMzYjZmZmYzODc1MDY5ODQzYjFlZmE4MjQyNmNhNSIsImF1ZCI6IlNDRS1BcHBsaWNhdGlvbiIsImxvZ2luSWQiOiJ4c18xIiwiaXNGaXJzdExvZ2luIjoxLCJpc3MiOiJTQ0UtU1NPIiwidXNlclR5cGUiOjEsInVzZXJJZCI6IjQwMjg4MjNhNjdlNGQzMDYwMTY3ZjRkOWQwYWIwMDAyIiwiZXhwIjoxNTU0ODY3MDAzfQ.T1pqQmx-NJXacUrEauqyJlzlG08RiIxP3WpaUbXHq465vuixWtjFoCKb2x06pvQDmWhO04ifmoLqWIH5n6EiIT6UG4tB0LhMU_33PqMfviouE3VB6Un-B3n-G1AZpKXpH9ho4zIJ2lNvXKw0uwtNCjT9KcFBhfcW9VEP_sUmsvbQYkiccF3UYGSyV6t1iYTwRydZrBVO13oSDiQHsHH4QKNkqssYHrACFoIjtnuj_e3SqTYJ7qDmLcnFRYJUd7Xls8JYA7PsGJT_fYvk0a6qdXj-ACoWN7qugPXDewO3CPdIDlyuojLBTViVMff0bLwCUy3Sgzr_qlugqyOCU7MI3g";
//        Map<String, Object> map = JWTUtil.parseJWT(jwt);
//        System.out.println(map);
//        String key = ES256GenerateSecret();
//        String privateKey = key.substring(124);
////        String privateKey = "MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCDK1UOPy+ImsMFnqF9C9uHsaC/0f5hKM84+wpzlw6Ha0w==";
//        String publicKey = key.substring(0,124);
//
//        Map map = new HashMap();
//        map.put("aa","11");
//        List<String> list = new ArrayList<>();
//        list.add("4028823a67e4d3060167e4d82fc90005");
//        list.add("4028823a67e4d3060167e4d80fbb0004");
//        map.put("users", list);
//        map.put("appId", "4028819f6879ab8401687a3579600015");
//        PrivateKey privateKeyObj = SecureUtil.generatePrivateKey("EC", Base64.decode(privateKey));
//        PublicKey publicKeyObj = SecureUtil.generatePublicKey("EC", Base64.decode(publicKey));
//        KeyPair keyPair = new KeyPair(publicKeyObj, privateKeyObj);
//
//        String jwt = JWTUtil.generateTicketWithSecret( map, keyPair.getPrivate(), new Date(System.currentTimeMillis() + 6000000));
//        System.out.println("jwt--------");
//        System.out.println(jwt);
//        System.out.println("jwt--------");
//        Claims claims = Jwts.parser().setSigningKey(publicKeyObj).parseClaimsJws(jwt).getBody();
//        System.out.println(claims);
//        List<String> users2 = (List)claims.get("users");
//        System.out.println(users2.size());
//        for(String user : users2){
//            System.out.println(user);
//        }
    }

}
