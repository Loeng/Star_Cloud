package cn.com.bonc.sce.constant;

import lombok.AllArgsConstructor;

/**
 * @author JoeTH
 * @version 0.2
 * @since 2019/3/21 14:17
 */
@AllArgsConstructor
public enum MessageConstants {

    CORS_ALL_ALLOW_ENABLED( 300, 1, "系统将不对跨域访问作出限制" ),

    HTTPS_TO_HTTP_TRANSFER_FILTER_ENABLED( 400, 1, "HTTPS 转 HTTP 协议拦截器开启" );

    private static final String BASIC_INFO = "状态码: %s, %s";
    /**
     * 状态码
     */
    private int code;
    /**
     * 信息类型: [0:基本信息类型，1: 普通日志信息，2：错误信息]
     */
    private int type = 0;
    private String message;
    private String info;

    /**
     * 自动根据信息类型组装完整的日志信息
     */
    private String formatMessage() {
        return String.format( BASIC_INFO, this.code, this.message );
    }

    MessageConstants( int code, int type, String message ) {
        this.code = code;
        this.type = type;
        this.message = message;
        this.info = formatMessage();
    }

    public String getMessage() {
        return this.info;
    }
}
