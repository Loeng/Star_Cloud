package cn.com.bonc.sce.model;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Date;

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

    @Excel( name = "当前住址", orderNum = "4" )
    private String address;

    @Excel( name = "电子邮箱", orderNum = "5" )
    private String mailAddress;

    @Excel( name = "出生日期", orderNum = "6")
    private String birthDate;

    @Excel( name = "姓名拼音", orderNum = "7")
    private String nameSpell;

    @Excel( name = "年级", orderNum = "8")
    private String grade;

    @Excel( name = "班号", orderNum = "9")
    private String classNumber;

    @Excel( name = "学号", orderNum = "10")
    private String studentNumber;

    @Excel( name = "健康状态", orderNum = "11")
    private String healthCode;

    @Excel( name = "国籍/地区码", orderNum = "12")
    private String nationLity;

    @Excel( name = "籍贯", orderNum = "13")
    private String nativePlace;

    @Excel( name = "婚姻状况", orderNum = "14")
    private String marriAgeCode;

    @Excel( name = "户口所在地行政区划码", orderNum = "15")
    private String accountAreaCode;

    @Excel( name = "出生地", orderNum = "16")
    private String birthPlace;

    @Excel( name = "民族", orderNum = "17")
    private String nationCode;

    @Excel( name = "政治面貌", orderNum = "18")
    private String politicsStatus;

    @Excel( name = "户口类别码", orderNum = "19")
    private String accountClassCode;

    @Excel( name = "入学年月", orderNum = "20")
    private String entranceYear;

    @Excel( name = "职称", orderNum = "21")
    private String position;

    @Excel( name = "任职学科", orderNum = "22")
    private String subject;

    @Excel( name = "教龄", orderNum = "23")
    private String schoolAge;

    @Excel( name = "所学专业", orderNum = "24")
    private String learnSpecialty;

    @Excel( name = "工号", orderNum = "25")
    private String workNumber;

    @Excel( name = "来校年月", orderNum = "26")
    private String schoolTime;

    @Excel( name = "家庭角色", orderNum = "27")
    private String fimilyRole;

    @Excel( name = "学生当前状态", orderNum = "28")
    private String currentStatus;

    @Excel( name = "学生身份证", orderNum = "29")
    private String studentCertificationNumber;

    private String organizationId;

    private String schoolName;

    private String loginName;

    private String userType;

    private String certificateType;

    private String  secret;

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

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNameSpell() {
        return nameSpell;
    }

    public void setNameSpell(String nameSpell) {
        this.nameSpell = nameSpell;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getHealthCode() {
        return healthCode;
    }

    public void setHealthCode(String healthCode) {
        this.healthCode = healthCode;
    }

    public String getNationLity() {
        return nationLity;
    }

    public void setNationLity(String nationLity) {
        this.nationLity = nationLity;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getMarriAgeCode() {
        return marriAgeCode;
    }

    public void setMarriAgeCode(String marriAgeCode) {
        this.marriAgeCode = marriAgeCode;
    }

    public String getAccountAreaCode() {
        return accountAreaCode;
    }

    public void setAccountAreaCode(String accountAreaCode) {
        this.accountAreaCode = accountAreaCode;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getAccountClassCode() {
        return accountClassCode;
    }

    public void setAccountClassCode(String accountClassCode) {
        this.accountClassCode = accountClassCode;
    }

    public String getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(String entranceYear) {
        this.entranceYear = entranceYear;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSchoolAge() {
        return schoolAge;
    }

    public void setSchoolAge(String schoolAge) {
        this.schoolAge = schoolAge;
    }

    public String getLearnSpecialty() {
        return learnSpecialty;
    }

    public void setLearnSpecialty(String learnSpecialty) {
        this.learnSpecialty = learnSpecialty;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getSchoolTime() {
        return schoolTime;
    }

    public void setSchoolTime(String schoolTime) {
        this.schoolTime = schoolTime;
    }

    public String getFimilyRole() {
        return fimilyRole;
    }

    public void setFimilyRole(String fimilyRole) {
        this.fimilyRole = fimilyRole;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getStudentCertificationNumber() {
        return studentCertificationNumber;
    }

    public void setStudentCertificationNumber(String studentCertificationNumber) {
        this.studentCertificationNumber = studentCertificationNumber;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "ExcelToUser{" +
                "userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                ", certificateNumber='" + certificateNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", nameSpell='" + nameSpell + '\'' +
                ", grade='" + grade + '\'' +
                ", classNumber='" + classNumber + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", healthCode='" + healthCode + '\'' +
                ", nationLity='" + nationLity + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", marriAgeCode='" + marriAgeCode + '\'' +
                ", accountAreaCode='" + accountAreaCode + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", nationCode='" + nationCode + '\'' +
                ", politicsStatus='" + politicsStatus + '\'' +
                ", accountClassCode='" + accountClassCode + '\'' +
                ", entranceYear='" + entranceYear + '\'' +
                ", position='" + position + '\'' +
                ", subject='" + subject + '\'' +
                ", schoolAge='" + schoolAge + '\'' +
                ", learnSpecialty='" + learnSpecialty + '\'' +
                ", workNumber='" + workNumber + '\'' +
                ", schoolTime='" + schoolTime + '\'' +
                ", fimilyRole='" + fimilyRole + '\'' +
                ", currentStatus='" + currentStatus + '\'' +
                ", studentCertificationNumber='" + studentCertificationNumber + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", userType='" + userType + '\'' +
                ", certificateType='" + certificateType + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
