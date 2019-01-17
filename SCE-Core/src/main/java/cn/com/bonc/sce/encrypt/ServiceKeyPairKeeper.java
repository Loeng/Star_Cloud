package cn.com.bonc.sce.encrypt;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.tool.CryptoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 启用后可自动维护微服务/应用使用的 公/私钥，且公私钥会定时进行更新，旧公私钥对在一个替换周期内的使用不受影响。
 * <bold>此自动维护服务仅对单实例应用可用，多实例且有负载均衡，又未做路由绑定的服务均不可使用此服务管理应用公私钥</bold>
 * 可使用 <bold>sce.service.secret.auto-management.enable=true</bold> 配置为项目微服务添加此服务。
 * 公私钥根据配置，会自动定时进行更新，默认情况下 12h 更新一次，可使用 <bold>sce.service.secret.auto-management.refresh-period</bold> 进行自定义。
 *
 * @author Leucippus
 * @version 0.1
 * @since 2019/1/17 10:10
 */
@Slf4j
@Service
@ConditionalOnProperty( name = "sce.service.secret.auto-management.enable", havingValue = "true" )
public class ServiceKeyPairKeeper {
    private Map< Integer, Secret > serviceSecretMap;
    private int currentKeyPairNo;
    private ExecutorService secretRefreshThread;
    private static Long DEFAULT_REFRESH_PERIOD = 12 * 60 * 60 * 1000L;

    {
        init();
        enableSecretRefreshTimer();
    }

    /**
     * 启动定时钥匙对更新
     */
    private void enableSecretRefreshTimer() {
        secretRefreshThread = Executors.newSingleThreadExecutor();
        secretRefreshThread.submit( () -> {
            Timer timer = new Timer( true );
            timer.schedule( new TimerTask() {
                @Override
                public void run() {
                    currentKeyPairNo = ( ++currentKeyPairNo % 3 );
                    serviceSecretMap.put( currentKeyPairNo, new Secret() );
                    log.info( MessageConstants.SCE_MSG_0002 );
                }
            }, DEFAULT_REFRESH_PERIOD, DEFAULT_REFRESH_PERIOD );
        } );
    }

    /**
     * 公私钥对初始化
     */
    private void init() {
        this.serviceSecretMap = new HashMap<>( 4 );
        this.serviceSecretMap.put( 0, new Secret() );
        this.currentKeyPairNo = 0;
        log.info( MessageConstants.SCE_MSG_0001, DEFAULT_REFRESH_PERIOD );
    }

    public KeyPair getCurrentKeyPair() {
        return this.serviceSecretMap.get( currentKeyPairNo ).getKeyPair();
    }

    /**
     * 使用服务当前公钥加密数据
     *
     * @param data 待加密数据
     *
     * @return 加密后的字符串
     */
    public String encryptData( String data ) {
        int currentKeyPairNo = this.currentKeyPairNo;
        Secret currentSecret = this.serviceSecretMap.get( currentKeyPairNo );
        return CryptoUtil.RSA_CRYPTO_UTIL_INSTANCE.encryptDataByRSAPublicKey( data, currentSecret.getPublicKey() ) + currentKeyPairNo;
    }

    /**
     * 使用私钥解密数据
     *
     * @param data 待解密数据
     *
     * @return 解密后的数据
     *
     * @throws UnstandardEncryptedDataException 如果加密字符串并非经过此类中的 encryptData 方法加密生成，则会报此错误。
     */
    public String decryptData( String data ) throws UnstandardEncryptedDataException {
        int keyPairNo = 0;
        System.out.println( data );
        try {
            keyPairNo = data.charAt( data.length() - 1 ) - '0';
        } catch ( Exception e ) {
            log.warn( "无效的加密字符串, {}" + e );
            throw new UnstandardEncryptedDataException();
        }

        return CryptoUtil.RSA_CRYPTO_UTIL_INSTANCE.decryptRSADataByPrivateKey( data.substring( 0, data.length() - 1 ), serviceSecretMap.get( keyPairNo ).getPrivateKey() );
    }

    public static void main( String[] args ) {
        ServiceKeyPairKeeper test = new ServiceKeyPairKeeper();
        String encrypted = test.encryptData( "你猜对不对" );
        try {
            System.out.println( encrypted );
            log.info( test.decryptData( encrypted ) );
        } catch ( UnstandardEncryptedDataException e ) {
            e.printStackTrace();
        }
    }

    /**
     * 待加密的字符串没有使用 ServiceKeyPairKeeper 进行加密，故加密数据无法正常进行解密。
     */
    private class UnstandardEncryptedDataException extends Throwable {
        @Override
        public String getMessage() {
            return MessageConstants.SCE_MSG_1100;
        }
    }

}
