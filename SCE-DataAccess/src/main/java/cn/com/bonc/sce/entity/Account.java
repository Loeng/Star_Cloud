package cn.com.bonc.sce.entity;

import javax.persistence.*;

/**
 * 安全
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Entity
@Table(name="SCE_COMMON_USER_PASSWORD")
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN_COMMON_USER_PASSWORD")
    @SequenceGenerator(name="SEQ_GEN_COMMON_USER_PASSWORD",allocationSize=1,initialValue=1, sequenceName="SEQ_COMMON_USER_PASSWORD")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "IS_DELETE")
    private Integer isDelete;

    @Transient
    private String phone;

    @Transient
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Integer isDelete ) {
        this.isDelete = isDelete;
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

    public Account( String userId, String password, Integer isDelete, String phone, String code ) {

        this.userId = userId;
        this.password = password;
        this.isDelete = isDelete;
        this.phone = phone;
        this.code = code;
    }
}
