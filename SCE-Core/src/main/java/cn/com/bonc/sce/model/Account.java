package cn.com.bonc.sce.model;

/**
 * 安全
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
public class Account {

    private Long id;

    private String userId;

    private String password;

    private String newPassword;

    private String phone;

    private String code;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword( String newPassword ) {
        this.newPassword = newPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone( String phone ) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode( String code ) {
        this.code = code;
    }
}
