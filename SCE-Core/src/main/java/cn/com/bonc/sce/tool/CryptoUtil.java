package cn.com.bonc.sce.tool;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;

import java.security.*;

/**
 * 此工具类用以：
 * <pere>
 * 1. 生成各类非对称加密秘钥
 * 2. 提供堆成加密工具
 * 3. 提供摘要工具
 * </pere>
 *
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/25 11:51
 */
@Slf4j
public class CryptoUtil {

    public static final CryptoUtil RSA_CRYPTO_UTIL_INSTANCE = new CryptoUtil();

    private static KeyPairGenerator keyPairGenerator;

    public CryptoUtil() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance( "RSA" );
            keyPairGenerator.initialize( 2048 );
        } catch ( NoSuchAlgorithmException e ) {
            log.error( "不支持的加密算法", e );
        }
    }

    /**
     * 自动生成经 RSA 加密的密钥对
     *
     * @return RSA 密钥对
     */
    public KeyPair generateKeyPair() {
        return keyPairGenerator.genKeyPair();
    }

    /**
     * 根据指定加密算法名称自动生成加密秘钥
     *
     * @param algorithm 加密算法
     *
     * @return 秘钥对
     */
    public static KeyPair generateKeyPair( String algorithm ) throws NoSuchAlgorithmException {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance( algorithm );
            return keyPairGenerator.genKeyPair();
        } catch ( NoSuchAlgorithmException e ) {
            log.warn( "在生成公钥-私钥对时使用了不支持的 algorithm 类型: ", algorithm );
            throw e;
        }
    }

    /**
     * 加密数据
     *
     * @param data    待加密数据
     * @param keyPair 秘钥对
     *
     * @return 被加密后的数据
     */
    public byte[] encryptData( String data, KeyPair keyPair ) {
        return new RSA( keyPair.getPrivate(), null ).encrypt( data, "UTF-8", KeyType.PrivateKey );
    }

    /**
     * 通过私钥加密数据
     *
     * @param data       待加密数据
     * @param privateKey 私钥
     *
     * @return 加密后的数据
     */
    public byte[] encryptDataByPrivateKey( String data, PrivateKey privateKey ) {
        return new RSA( privateKey, null ).encrypt( data, "UTF-8", KeyType.PrivateKey );
    }

    /**
     * 通过私钥加密数据
     *
     * @param data       待加密数据
     * @param privateKey 私钥
     *
     * @return 加密后的数据
     */
    public byte[] encryptDataByPrivateKey( String data, String privateKey ) {
        return new RSA( privateKey, null ).encrypt( data, "UTF-8", KeyType.PrivateKey );
    }

    /**
     * 通过公钥加密数据
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     *
     * @return 加密后的数据
     */
    public byte[] encryptDataByPublicKey( String data, String publicKey ) {
        return new RSA( null, publicKey ).encrypt( data, "UTF-8", KeyType.PublicKey );
    }

    /**
     * 通过公钥加密数据
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     *
     * @return 加密后的数据
     */
    public byte[] encryptDataByPublicKey( String data, PublicKey publicKey ) {
        return new RSA( null, publicKey ).encrypt( data, "UTF-8", KeyType.PublicKey );
    }

    /**
     * 解密数据
     *
     * @param encryptedData 待解密数据
     * @param privateKey    私钥
     *
     * @return 解密后的数据
     */
    public String decryptRSAData( String encryptedData, PrivateKey privateKey ) {
        RSA rsa = new RSA( privateKey, null );
        byte[] aByte = HexUtil.decodeHex( encryptedData );
        byte[] decrypt = rsa.decrypt( aByte, KeyType.PrivateKey );
        return StrUtil.str( decrypt, CharsetUtil.CHARSET_UTF_8 );
    }

    public static void main( String[] args ) {
        RSA rsa = new RSA();
        String publicKey = rsa.getPublicKeyBase64();
        String privateKey = rsa.getPrivateKeyBase64();

        System.out.println( privateKey + publicKey);

//        System.out.println(generateKeyPair( SignatureAlgorithm.ES256.name() ));
    }
}
