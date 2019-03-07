package cn.com.bonc.sce.exception;

public class ImportUserFailedException extends Exception {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ImportUserFailedException() {
    }

    public ImportUserFailedException(String message) {
        super(message);
        this.msg = message;
    }

    public ImportUserFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImportUserFailedException(Throwable cause) {
        super(cause);
    }

    public ImportUserFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
