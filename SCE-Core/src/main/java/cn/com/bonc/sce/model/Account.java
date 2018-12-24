package cn.com.bonc.sce.model;

/**
 * 安全
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
public class Account {

    private Integer id;

    private String userId;

    private String password;

    private String phone;

    private String code;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
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

    public Account() {

    }

    public Account( String userId, String password, String phone, String code ) {
        this.userId = userId;
        this.password = password;
        this.phone = phone;
        this.code = code;
    }
}
