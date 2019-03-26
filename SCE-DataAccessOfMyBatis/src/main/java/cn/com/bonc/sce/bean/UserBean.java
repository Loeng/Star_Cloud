package cn.com.bonc.sce.bean;

import cn.com.bonc.sce.model.Account;
import cn.com.bonc.sce.model.Secret;

import java.util.Date;
import java.util.Map;

/**
 * Created by Charles on 2019/3/6.
 */
public class UserBean {
    private long userId;
    //SCE_COMMON_USER 表中的 USER_ID 是varchar2类型，用long类型的userId执行插入会报错（无效的数字类型）
    private String stringUserId;
    private String loginName;
    private String userName;
    private String gender;
    private Integer userType;
    private String mailAddress;
    private String certificateType;
    private String certificateNumber;
    private String phoneNumber;
    private String address;
    private Date createTime;
    private String loginTime;
    private String lastLoginTime;
    private Long loginCounts;
    private Long organizationId;
    private Integer loginPermissionStatus;
    private String remarks;
    private Integer isDelete;
    private Account account;
    private String secret;
    private Secret secretKeyPair;
    private Integer isFirstLogin=0;
    private String headPortrait;
    private Date birthday;
    private Map<String,String> userDetailedInfo;
    private Integer accountStatus;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getStringUserId() {
        return stringUserId;
    }

    public void setStringUserId( String stringUserId ) {
        this.stringUserId = stringUserId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getLoginCounts() {
        return loginCounts;
    }

    public void setLoginCounts(Long loginCounts) {
        this.loginCounts = loginCounts;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getLoginPermissionStatus() {
        return loginPermissionStatus;
    }

    public void setLoginPermissionStatus(Integer loginPermissionStatus) {
        this.loginPermissionStatus = loginPermissionStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Secret getSecretKeyPair() {
        return secretKeyPair;
    }

    public void setSecretKeyPair(Secret secretKeyPair) {
        this.secretKeyPair = secretKeyPair;
    }

    public Integer getIsFirstLogin() {
        return isFirstLogin;
    }

    public void setIsFirstLogin(Integer isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Map<String, String> getUserDetailedInfo() {
        return userDetailedInfo;
    }

    public void setUserDetailedInfo(Map<String, String> userDetailedInfo) {
        this.userDetailedInfo = userDetailedInfo;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }
}
