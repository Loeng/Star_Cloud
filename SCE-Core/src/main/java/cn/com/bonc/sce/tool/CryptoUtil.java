package cn.com.bonc.sce.tool;

import cn.hutool.core.util.CharsetUtil;
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

    private KeyPairGenerator keyPairGenerator;

    public CryptoUtil() {
        this.keyPairGenerator = null;
        try {
            this.keyPairGenerator = KeyPairGenerator.getInstance( "RSA" );
            this.keyPairGenerator.initialize( 2048 );
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
     * 通过私钥加密数据
     *
     * @param data       待加密数据
     * @param privateKey 私钥
     *
     * @return 加密后的数据
     */
    public String encryptDataByRSAPrivateKey( String data, PrivateKey privateKey ) {
        byte[] encrypt = new RSA( privateKey, null ).encrypt( StrUtil.bytes( data, CharsetUtil.CHARSET_UTF_8 ), KeyType.PublicKey );
        return byteToString( encrypt );
    }

    /**
     * 通过私钥加密数据
     *
     * @param data       待加密数据
     * @param privateKey 私钥
     *
     * @return 加密后的数据
     */
    public String encryptDataByRSAPrivateKey( String data, String privateKey ) {
        byte[] encrypt = new RSA( privateKey, null ).encrypt( StrUtil.bytes( data, CharsetUtil.CHARSET_UTF_8 ), KeyType.PublicKey );
        return byteToString( encrypt );
    }

    /**
     * 通过公钥加密数据
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     *
     * @return 加密后的数据
     */
    public String encryptDataByRSAPublicKey( String data, String publicKey ) {
        return byteToString( new RSA( null, publicKey ).encrypt( StrUtil.bytes( data, CharsetUtil.CHARSET_UTF_8 ), KeyType.PublicKey ) );
    }

    /**
     * 通过公钥加密数据
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     *
     * @return 加密后的数据
     */
    public String encryptDataByRSAPublicKey( String data, PublicKey publicKey ) {
        return byteToString( new RSA( null, publicKey ).encrypt( data, "UTF-8", KeyType.PublicKey ) );
    }

    /**
     * 解密数据
     *
     * @param encryptedData 待解密数据
     * @param privateKey    私钥
     *
     * @return 解密后的数据
     */
    public String decryptRSADataByPrivateKey( String encryptedData, PrivateKey privateKey ) {
        return new String( new RSA( privateKey, null ).decrypt( stringToByte( encryptedData ), KeyType.PrivateKey ) );
    }


    //    把byte[]元素之间添加空格，并转化成字符串返回，
    private static String byteToString( byte[] resouce ) {
        StringBuffer sb = new StringBuffer();
        for ( int i = 0; i < resouce.length; i++ ) {
            if ( i == resouce.length - 1 ) {
                sb.append( Byte.toString( resouce[ i ] ) );
            } else {
                sb.append( Byte.toString( resouce[ i ] ) );
                sb.append( " " );
            }
        }
        return sb.toString();

    }

    //    把字符串按照空格进行拆分成数组，然后转化成byte[],返回
    private static byte[] stringToByte( String resouce ) {
        String[] strArr = resouce.split( " " );
        int len = strArr.length;
        byte[] clone = new byte[ len ];
        for ( int i = 0; i < len; i++ ) {
            clone[ i ] = Byte.parseByte( strArr[ i ] );
        }

        return clone;

    }

    public static void main( String[] args ) {

        KeyPair keyPair = CryptoUtil.RSA_CRYPTO_UTIL_INSTANCE.generateKeyPair();
        RSA rsa = new RSA( keyPair.getPrivate(), keyPair.getPublic() );

        byte[] encrypt = rsa.encrypt( StrUtil.bytes( "我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8 ), KeyType.PublicKey );
        String encrypted = byteToString( encrypt );

        System.out.println( encrypted );


        byte[] decrypt = rsa.decrypt( stringToByte( encrypted ), KeyType.PrivateKey );

        System.out.println( new String( decrypt ) );

        String encrypted2 = CryptoUtil.RSA_CRYPTO_UTIL_INSTANCE.encryptDataByRSAPublicKey( "测试一下", keyPair.getPublic() );

        System.out.println( CryptoUtil.RSA_CRYPTO_UTIL_INSTANCE.decryptRSADataByPrivateKey( encrypted2, keyPair.getPrivate() ) );

    }
}
