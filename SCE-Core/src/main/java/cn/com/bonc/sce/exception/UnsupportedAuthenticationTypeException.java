package cn.com.bonc.sce.exception;

/**
 * 采用不支持的登录方式进行登录( loginType 不正确)
 * TODO 因为 loginType 理应被废弃，故此类也应考虑同步 deprecate。
 * @author JoeTH
 * @version 0.1
 * @since 2019/3/4 17:00
 */
public class UnsupportedAuthenticationTypeException extends Exception {
    public UnsupportedAuthenticationTypeException( String message ) {
        super( message );
    }

    public UnsupportedAuthenticationTypeException( String message, Throwable cause ) {
        super( message, cause );
    }

    public UnsupportedAuthenticationTypeException() {
    }

    public UnsupportedAuthenticationTypeException( Throwable cause ) {
        super( cause );
    }

    public UnsupportedAuthenticationTypeException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super( message, cause, enableSuppression, writableStackTrace );
    }
}
