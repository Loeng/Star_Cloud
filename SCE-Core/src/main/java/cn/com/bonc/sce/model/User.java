package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 7:46
 */
@Getter
@Setter
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class User {
    private String userId;
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
    private String organizationId;
    private Integer loginPermissionStatus;
    private String remarks;
    private Integer isDelete;
    private Account account;
    private String secret;
    private Secret secretKeyPair;
    private int isFirstLogin;
    private Map<String,String> userDetailedInfo;

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber( String certificateNumber ) {
        this.certificateNumber = certificateNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    public Map< String, String > getUserDetailedInfo() {
        return userDetailedInfo;
    }

    public void setUserDetailedInfo( Map< String, String > userDetailedInfo ) {
        this.userDetailedInfo = userDetailedInfo;
    }

    public void generateUserSecret() {
        this.secretKeyPair = new Secret();
        this.secret = this.secretKeyPair.getSecretKeyPair();
    }

    public Secret getSecretKeyPair() {
        if ( this.secret != null ) {
            this.secretKeyPair = new Secret( secret );
        }
        return this.secretKeyPair;
    }

    public void setPassword( String newPassword ) {
        if ( account != null ) {
            account.setPassword( newPassword );
        }
    }

    public void setSecretKeyPair( String secretKeyPair ) {
        if ( secretKeyPair != null ) {
            this.secretKeyPair = new Secret( secretKeyPair );
            this.secret = this.getSecretKeyPair().getSecretKeyPair();
        } else {
            this.secretKeyPair = null;
        }
    }
}


