package cn.com.bonc.sce.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2018/12/26 16:09
 * @Description:
 */
public class ExcelToUser {
    @Excel( name = "姓名", orderNum = "0"  )
    private String userName;

    @Excel( name = "性别", replace = { "男_1", "女_2" }, orderNum = "1" )
    private String gender;

    @Excel( name = "身份证", orderNum = "2" )
    private String certificateNumber;

    @Excel( name = "联系方式", orderNum = "3" )
    private String phoneNumber;

    @Excel( name = "地址", orderNum = "4" )
    private String address;

    @Excel( name = "电子邮箱", orderNum = "5" )
    private String mailAddress;

    @Excel( name = "组织编号", orderNum = "6" )
    private String organizationId;


    private String loginName;

    private String userType;

    private String certificateType;

    private String  secret;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId( String organizationId ) {
        this.organizationId = organizationId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret( String secret ) {
        this.secret = secret;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName( String loginName ) {
        this.loginName = loginName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType( String userType ) {
        this.userType = userType;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType( String certificateType ) {
        this.certificateType = certificateType;
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

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress( String mailAddress ) {
        this.mailAddress = mailAddress;
    }
}
