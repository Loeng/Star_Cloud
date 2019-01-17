package cn.com.bonc.sce.encrypt;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2019/1/17 16:49
 *
 * TODO 现在已经有了单实例应用的公私钥维护服务，以后还得增加支持多实例、进行了负载均衡的服务的版本。
 */
public interface AppSecretAutoManageService {
    String encryptData(String sourceData);

    String decryptData( String encryptedData ) throws UnstandardEncryptedDataException;
}
