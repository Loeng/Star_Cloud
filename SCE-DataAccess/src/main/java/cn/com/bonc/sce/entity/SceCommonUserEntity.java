package cn.com.bonc.sce.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2018/12/22 14:49
 * @Description:
 */
//@Entity
//@Table( name = "SCE_COMMON_USER", schema = "STARCLOUDPORTAL", catalog = "" )
@Deprecated
public class SceCommonUserEntity {
    private String userId;
    private String userAccount;
    private String userName;
    private String gender;
    private Long userType;
    private String mailAddress;
    private Long certificateType;
    private String certificateNumber;
    private String phoneNumber;
    private String address;
    private Time createTime;
    private Time loginTime;
    private Time lastLoginTime;
    private Long logintimes;
    private String organizationId;
    private Long loginPermissionStatus;
    private String remarks;
    private Long isDelete;

    @Id
    @Column( name = "USER_ID" )
    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    @Basic
    @Column( name = "USER_ACCOUNT" )
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount( String userAccount ) {
        this.userAccount = userAccount;
    }

    @Basic
    @Column( name = "USER_NAME" )
    public String getUserName() {
        return userName;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
    }

    @Basic
    @Column( name = "GENDER" )
    public String getGender() {
        return gender;
    }

    public void setGender( String gender ) {
        this.gender = gender;
    }

    @Basic
    @Column( name = "USER_TYPE" )
    public Long getUserType() {
        return userType;
    }

    public void setUserType( Long userType ) {
        this.userType = userType;
    }

    @Basic
    @Column( name = "MAIL_ADDRESS" )
    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress( String mailAddress ) {
        this.mailAddress = mailAddress;
    }

    @Basic
    @Column( name = "CERTIFICATE_TYPE" )
    public Long getCertificateType() {
        return certificateType;
    }

    public void setCertificateType( Long certificateType ) {
        this.certificateType = certificateType;
    }

    @Basic
    @Column( name = "CERTIFICATE_NUMBER" )
    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber( String certificateNumber ) {
        this.certificateNumber = certificateNumber;
    }

    @Basic
    @Column( name = "PHONE_NUMBER" )
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column( name = "ADDRESS" )
    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    @Basic
    @Column( name = "CREATE_TIME" )
    public Time getCreateTime() {
        return createTime;
    }

    public void setCreateTime( Time createTime ) {
        this.createTime = createTime;
    }

    @Basic
    @Column( name = "LOGIN_TIME" )
    public Time getLoginTime() {
        return loginTime;
    }

    public void setLoginTime( Time loginTime ) {
        this.loginTime = loginTime;
    }

    @Basic
    @Column( name = "LAST_LOGIN_TIME" )
    public Time getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime( Time lastLoginTime ) {
        this.lastLoginTime = lastLoginTime;
    }

    @Basic
    @Column( name = "LOGINTIMES" )
    public Long getLogintimes() {
        return logintimes;
    }

    public void setLogintimes( Long logintimes ) {
        this.logintimes = logintimes;
    }

    @Basic
    @Column( name = "ORGANIZATION_ID" )
    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId( String organizationId ) {
        this.organizationId = organizationId;
    }

    @Basic
    @Column( name = "LOGIN_PERMISSION_STATUS" )
    public Long getLoginPermissionStatus() {
        return loginPermissionStatus;
    }

    public void setLoginPermissionStatus( Long loginPermissionStatus ) {
        this.loginPermissionStatus = loginPermissionStatus;
    }

    @Basic
    @Column( name = "REMARKS" )
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks( String remarks ) {
        this.remarks = remarks;
    }

    @Basic
    @Column( name = "IS_DELETE" )
    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Long isDelete ) {
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        SceCommonUserEntity that = ( SceCommonUserEntity ) o;
        return Objects.equals( userId, that.userId ) &&
                Objects.equals( userAccount, that.userAccount ) &&
                Objects.equals( userName, that.userName ) &&
                Objects.equals( gender, that.gender ) &&
                Objects.equals( userType, that.userType ) &&
                Objects.equals( mailAddress, that.mailAddress ) &&
                Objects.equals( certificateType, that.certificateType ) &&
                Objects.equals( certificateNumber, that.certificateNumber ) &&
                Objects.equals( phoneNumber, that.phoneNumber ) &&
                Objects.equals( address, that.address ) &&
                Objects.equals( createTime, that.createTime ) &&
                Objects.equals( loginTime, that.loginTime ) &&
                Objects.equals( lastLoginTime, that.lastLoginTime ) &&
                Objects.equals( logintimes, that.logintimes ) &&
                Objects.equals( organizationId, that.organizationId ) &&
                Objects.equals( loginPermissionStatus, that.loginPermissionStatus ) &&
                Objects.equals( remarks, that.remarks ) &&
                Objects.equals( isDelete, that.isDelete );
    }

    @Override
    public int hashCode() {

        return Objects.hash( userId, userAccount, userName, gender, userType, mailAddress, certificateType, certificateNumber, phoneNumber, address, createTime, loginTime, lastLoginTime, logintimes, organizationId, loginPermissionStatus, remarks, isDelete );
    }
}
