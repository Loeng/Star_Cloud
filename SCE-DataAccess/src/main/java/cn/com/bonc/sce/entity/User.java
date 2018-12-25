package cn.com.bonc.sce.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 * @update
 */
@Entity
@EntityListeners( AuditingEntityListener.class )
@Table( name = "SCE_COMMON_USER", schema = "STARCLOUDPORTAL" )
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN_COMMON_USER" )
    @SequenceGenerator( name = "SEQ_GEN_COMMON_USER", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_COMMON_USER" )
    @Column( name = "USER_ID" )
    private String userId;
    @Column( name = "USER_ACCOUNT" )
    private String userAccount;
    @Column( name = "USER_NAME" )
    private String userName;
    @Column( name = "GENDER" )
    private String gender;
    @Column( name = "USER_TYPE" )
    private Integer userType;
    @Column( name = "MAIL_ADDRESS" )
    private String mailAddress;
    @Column( name = "CERTIFICATE_TYPE" )
    private String certificateType;
    @Column( name = "CERTIFICATE_NUMBER" )
    private String certificateNumber;
    @Column( name = "PHONE_NUMBER" )
    private String phoneNumber;
    @Column( name = "ADDRESS" )
    private String address;
    @CreatedDate
    @Column( name = "CREATE_TIME" )
    private Date createTime;
    @Column( name = "LOGIN_TIME" )
    private String loginTime;
    @Column( name = "LAST_LOGIN_TIME" )
    private String lastLoginTime;
    @Column( name = "LOGINTIMES" )
    private Long logintimes;
    @Column( name = "ORGANIZATION_ID" )
    private String organizationId;
    @Column( name = "LOGIN_PERMISSION_STATUS" )
    private Integer loginPermissionStatus;
    @Column( name = "REMARKS" )
    private String remarks;
    @Column( name = "IS_DELETE" )
    private Integer isDelete;

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount( String userAccount ) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender( String gender ) {
        this.gender = gender;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType( Integer userType ) {
        this.userType = userType;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress( String mailAddress ) {
        this.mailAddress = mailAddress;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType( String certificateType ) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber( String certificateNumber ) {
        this.certificateNumber = certificateNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime( Date createTime ) {
        this.createTime = createTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime( String loginTime ) {
        this.loginTime = loginTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime( String lastLoginTime ) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getLogintimes() {
        return logintimes;
    }

    public void setLogintimes( Long logintimes ) {
        this.logintimes = logintimes;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId( String organizationId ) {
        this.organizationId = organizationId;
    }

    public Integer getLoginPermissionStatus() {
        return loginPermissionStatus;
    }

    public void setLoginPermissionStatus( Integer loginPermissionStatus ) {
        this.loginPermissionStatus = loginPermissionStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks( String remarks ) {
        this.remarks = remarks;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Integer isDelete ) {
        this.isDelete = isDelete;
    }

    public User() {

    }

    public User( String userAccount, String userName, String gender, Integer userType, String mailAddress, String certificateType, String certificateNumber, String phoneNumber, String address, Date createTime, String loginTime, String lastLoginTime, Long logintimes, String organizationId, Integer loginPermissionStatus, String remarks, Integer isDelete ) {

        this.userAccount = userAccount;
        this.userName = userName;
        this.gender = gender;
        this.userType = userType;
        this.mailAddress = mailAddress;
        this.certificateType = certificateType;
        this.certificateNumber = certificateNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.createTime = createTime;
        this.loginTime = loginTime;
        this.lastLoginTime = lastLoginTime;
        this.logintimes = logintimes;
        this.organizationId = organizationId;
        this.loginPermissionStatus = loginPermissionStatus;
        this.remarks = remarks;
        this.isDelete = isDelete;
    }
}
