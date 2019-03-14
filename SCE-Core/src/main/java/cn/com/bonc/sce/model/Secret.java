package cn.com.bonc.sce.model;

import cn.com.bonc.sce.tool.CryptoUtil;
import cn.hutool.crypto.asymmetric.RSA;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

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
        String publicKey = secret.substring( 0, 392 );
        String privateKey = secret.substring( 392, length );

        RSA rsa = new RSA( privateKey, publicKey );
        this.keyPair = new KeyPair( rsa.getPublicKey(), rsa.getPrivateKey() );
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
     * 其中编码后的公钥长度为92  私钥为124
     * @return
     */
    public static String ES256GenerateSecret(){
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.ES256);

        String privateKeyBase64 = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        String publicKeyBase64 = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        return privateKeyBase64 + publicKeyBase64;
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

    public static void main( String[] args ) {

    }
}
