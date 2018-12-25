package cn.com.bonc.sce.model;

import cn.com.bonc.sce.tool.CryptoUtil;
import cn.hutool.crypto.asymmetric.RSA;

import java.security.*;

/**
 * 秘钥对
 *
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/25 16:23
 */
public class Secret {
    private KeyPair keyPair;
    private String algorithm;

    public Secret( String secret ) {
        String privateKey = secret.substring( 0, 847 );
        String publicKey = secret.substring( 848, 1063 );

        RSA rsa = new RSA( privateKey, publicKey );
        this.keyPair = new KeyPair( rsa.getPublicKey(), rsa.getPrivateKey() );
        this.algorithm = "RSA";
    }

    public Secret() {
        this.keyPair = CryptoUtil.RSA_CRYPTO_UTIL_INSTANCE.generateKeyPair();
    }

    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    public PublicKey getPublicKey() {
        return this.keyPair.getPublic();
    }
}
