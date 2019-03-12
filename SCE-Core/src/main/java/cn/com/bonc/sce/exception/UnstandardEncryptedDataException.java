package cn.com.bonc.sce.exception;

/**
 * 解析没有使用规定的加密方式进行加密
 * @author Leucippus
 * @version 0.1
 * @since 2019/1/17 11:34
 */
public class UnstandardEncryptedDataException extends Exception {

    public UnstandardEncryptedDataException() {
        super();
    }

    public UnstandardEncryptedDataException( String message ) {
        super( message );
    }

    public UnstandardEncryptedDataException( String message, Throwable cause ) {
        super( message, cause );
    }

    public UnstandardEncryptedDataException( Throwable cause ) {
        super( cause );
    }

    protected UnstandardEncryptedDataException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super( message, cause, enableSuppression, writableStackTrace );
    }
}
