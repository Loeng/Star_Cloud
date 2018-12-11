package cn.com.bonc.sce.rest;

import cn.com.bonc.sce.tool.ExceptionUtil;
import cn.hutool.json.JSONUtil;

import java.io.Serializable;

/**
 * 通用 Restful 返回值封装类
 *
 * @author Leucippus
 * @version 0.1
 * @since 2018/5/14 14:54
 */
public class RestRecord implements Serializable {
    private int code = 0;
    private String msg;
    private Object data;
    private String exceptionStackTrace;
    private Exception exception;

    public void success() {
        this.code = 200;
    }

    public void error() {
        this.code = 500;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg( String msg ) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData( Object data ) {
        this.data = data;
    }

    public Exception getException() {
        return exception;
    }

    public String getExceptionStackTrace() {
        return exceptionStackTrace;
    }

    public void setException( Exception exception ) {
        error();
        this.exception = exception;
        this.exceptionStackTrace = ExceptionUtil.getStackTrace( exception );
    }

    public int getCode() {
        return code;
    }

    public RestRecord() {
    }

    public RestRecord( String msg, Object data, Exception exception ) {
        this.msg = msg;
        this.data = data;
        this.exception = exception;
    }

    public RestRecord( int code, String msg, Object data, Exception exception ) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.exception = exception;
        this.exceptionStackTrace = ExceptionUtil.getStackTrace( exception );
    }

    public RestRecord( int code ) {
        this.code = code;
    }

    public RestRecord( int code, Object data ) {
        this.code = code;
        this.data = data;
    }

    public RestRecord( boolean isSuccess ) {
        this.code = isSuccess ? 200 : 500;
    }

    public RestRecord( int code, String msg ) {
        this.code = code;
        this.msg = msg;
    }

    public RestRecord( boolean isSuccess, String msg ) {
        this.code = isSuccess ? 200 : 500;
        this.msg = msg;
    }

    public RestRecord( int code, String msg, Object data ) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RestRecord( boolean isSuccess, String msg, Object data ) {
        this.code = isSuccess ? 200 : 500;
        this.msg = msg;
        this.data = data;
    }


    public RestRecord( int code, String msg, Exception exception ) {
        this.code = code;
        this.msg = msg;
        this.exception = exception;
        this.exceptionStackTrace = ExceptionUtil.getStackTrace( exception );
    }

    public RestRecord( boolean isSuccess, String msg, Exception exception ) {
        this.code = isSuccess ? 200 : 500;
        this.msg = msg;
        this.exception = exception;
        this.exceptionStackTrace = ExceptionUtil.getStackTrace( exception );
    }

    public boolean isSuccess() {
        return code == 200;
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr( this );
    }

    public static void main( String[] args ) {
        Exception exception = new Exception();

        RestRecord restRecord = new RestRecord( 20, "", "", exception );
        System.out.println(restRecord);
    }
}