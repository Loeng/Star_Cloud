package cn.com.bonc.sce.bean;

/**
 * Created by Charles on 2019/3/6.
 */
public class AccountBean {

    private long id;
    private String password;
    private int isDelete;
    private long userId;

    public AccountBean( long id, String password, int isDelete, long userId ) {
        this.id = id;
        this.password = password;
        this.isDelete = isDelete;
        this.userId = userId;
    }

    public AccountBean() {

    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( int isDelete ) {
        this.isDelete = isDelete;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId( long userId ) {
        this.userId = userId;
    }
}
