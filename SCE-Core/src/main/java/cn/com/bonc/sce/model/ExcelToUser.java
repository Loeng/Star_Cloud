package cn.com.bonc.sce.model;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @Author: Vloader
 * @Auther: 管 理 员
 * @Date: 2018/12/26 16:09
 * @Description:
 */
public class ExcelToUser {
    @Excel( name = "姓名" )
    private String userName;

    @Excel( name = "性别", replace = { "男_1", "女_0" }, orderNum = "1" )
    private String gender;

    @Excel( name = "身份证", orderNum = "2" )
    private String certificateNumber;

    @Excel( name = "联系方式", orderNum = "3" )
    private String phoneNumber;

    @Excel( name = "电子邮箱", orderNum = "4" )
    private String mailAddress;

    @Excel( name = "出生日期", orderNum = "5")
    private String birthDate;

    @Excel( name = "年级", orderNum = "6")
    private String grade;

    @Excel( name = "班号", orderNum = "7")
    private String classNumber;

    @Excel( name = "学号", orderNum = "8")
    private String studentNumber;

    @Excel( name = "国籍", orderNum = "9")
    private String nationLity;

    @Excel( name = "籍贯", orderNum = "10")
    private String nativePlace;

    @Excel( name = "民族", orderNum = "11")
    private String nationCode;

    @Excel( name = "参加工作年月", orderNum = "17")
    private String teachTime;

    @Excel( name = "入学年月", orderNum = "13")
    private String entranceYear;

    @Excel( name = "职业岗位", orderNum = "14")
    private String position;

    @Excel( name = "学历", orderNum = "15")
    private String academicQualification;

    @Excel( name = "管理员", replace = { "是_1","否_0" }, orderNum = "16")
    private String isAdministrators;

    @Excel( name = "任课学段", replace = { "学前教育_0", "小学_1", "普通初中_2", "普通高中_3", "职业初中_4", "职业高中_5", "成人中等专业学校_6", "成人中学_7", "特殊教育_8", "其他_9" }, orderNum = "1")
    private String teachRange;

    @Excel( name = "工号", orderNum = "18")
    private String workNumber;

    @Excel( name = "来校年月", orderNum = "19")
    private String schoolTime;

    @Excel( name = "家庭角色", orderNum = "20")
    private String familyRole;

    @Excel( name = "学生当前状态", orderNum = "21")
    private String currentStatus;

    @Excel( name = "学生身份证", orderNum = "22")
    private String studentCertificationNumber;

    @Excel( name = "座号", orderNum = "23")
    private String seatNumber;

    @Excel( name = "学籍号", orderNum = "24")
    private String studentCode;

    //下面为学生模板中的家长
    @Excel( name = "家长姓名", orderNum = "25")
    private String parentName;

    @Excel( name = "家长性别", replace = { "男_1", "女_0" }, orderNum = "26")
    private String parentGender;

    @Excel( name = "家长学生关系", orderNum = "27")
    private String parentFamify;

    @Excel( name = "家长国籍", orderNum = "28")
    private String parentNationLity;

    @Excel( name = "家长民族", orderNum = "29")
    private String parentNationCode;

    @Excel( name = "家长身份证", orderNum = "30")
    private String parentCertificationNumber;

    @Excel( name = "家长联系方式", orderNum = "31")
    private String parentTelephone;

    @Excel( name = "家长电子邮箱", orderNum = "32")
    private String parentEmail;

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

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getTeachTime() {
        return teachTime;
    }

    public void setTeachTime(String teachTime) {
        this.teachTime = teachTime;
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

    public String getAcademicQualification() {
        return academicQualification;
    }

    public void setAcademicQualification(String academicQualification) {
        this.academicQualification = academicQualification;
    }

    public String getIsAdministrators() {
        return isAdministrators;
    }

    public void setIsAdministrators(String isAdministrators) {
        this.isAdministrators = isAdministrators;
    }

    public String getTeachRange() {
        switch (teachRange){
            case "学前教育":
                return "0";
            case "小学":
                return "1";
            case "普通初中":
                return "2";
            case "普通高中":
                return "3";
            case "职业初中":
                return "4";
            case "职业高中":
                return "5";
            case "成人中等专业学校":
                return "6";
            case "成人中学":
                return "7";
            case "特殊教育":
                return "8";
            case "其他":
                return "9";
        }
        return "";
    }

    public void setTeachRange(String teachRange) {
        this.teachRange = teachRange;
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

    public String getFamilyRole() {
        return familyRole;
    }

    public void setFamilyRole(String familyRole) {
        this.familyRole = familyRole;
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

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentGender() {
        return parentGender;
    }

    public void setParentGender(String parentGender) {
        this.parentGender = parentGender;
    }

    public String getParentFamify() {
        return parentFamify;
    }

    public void setParentFamify(String parentFamify) {
        this.parentFamify = parentFamify;
    }

    public String getParentNationLity() {
        return parentNationLity;
    }

    public void setParentNationLity(String parentNationLity) {
        this.parentNationLity = parentNationLity;
    }

    public String getParentNationCode() {
        return parentNationCode;
    }

    public void setParentNationCode(String parentNationCode) {
        this.parentNationCode = parentNationCode;
    }

    public String getParentCertificationNumber() {
        return parentCertificationNumber;
    }

    public void setParentCertificationNumber(String parentCertificationNumber) {
        this.parentCertificationNumber = parentCertificationNumber;
    }

    public String getParentTelephone() {
        return parentTelephone;
    }

    public void setParentTelephone(String parentTelephone) {
        this.parentTelephone = parentTelephone;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
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
                ", mailAddress='" + mailAddress + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", grade='" + grade + '\'' +
                ", classNumber='" + classNumber + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", nationLity='" + nationLity + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", nationCode='" + nationCode + '\'' +
                ", teachTime='" + teachTime + '\'' +
                ", entranceYear='" + entranceYear + '\'' +
                ", position='" + position + '\'' +
                ", academicQualification='" + academicQualification + '\'' +
                ", isAdministrators='" + isAdministrators + '\'' +
                ", teachRange='" + teachRange + '\'' +
                ", workNumber='" + workNumber + '\'' +
                ", schoolTime='" + schoolTime + '\'' +
                ", familyRole='" + familyRole + '\'' +
                ", currentStatus='" + currentStatus + '\'' +
                ", studentCertificationNumber='" + studentCertificationNumber + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", studentCode='" + studentCode + '\'' +
                ", parentName='" + parentName + '\'' +
                ", parentGender='" + parentGender + '\'' +
                ", parentFamify='" + parentFamify + '\'' +
                ", parentNationLity='" + parentNationLity + '\'' +
                ", parentNationCode='" + parentNationCode + '\'' +
                ", parentCertificationNumber='" + parentCertificationNumber + '\'' +
                ", parentTelephone='" + parentTelephone + '\'' +
                ", parentEmail='" + parentEmail + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", userType='" + userType + '\'' +
                ", certificateType='" + certificateType + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
