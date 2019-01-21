package cn.com.bonc.sce.encrypt;

import cn.com.bonc.sce.constants.MessageConstants;


/**
 * 待加密的字符串没有使用 ServiceKeyPairKeeper 进行加密，故加密数据无法正常进行解密。
 * @author Leucippus
 * @version 0.1
 * @since 2019/1/17 10:10
 */
public class UnstandardEncryptedDataException extends Throwable {
    @Override
    public String getMessage() {
        return MessageConstants.SCE_MSG_1100;
    }
}
