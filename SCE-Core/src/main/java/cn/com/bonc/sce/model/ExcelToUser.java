package cn.com.bonc.sce.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.*;

/**
 * @Author: Vloader
 * @Auther: 管 理 员
 * @Date: 2018/12/26 16:09
 * @Description:
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExcelToUser {
    @Excel( name = "姓名" )
    private String userName;

    @Excel( name = "性别", replace = { "男_1", "女_0" }, orderNum = "1" )
    private String gender;

    @Excel( name = "证件号", orderNum = "2" )
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

    @Excel( name = "家长证件号", orderNum = "30")
    private String parentCertificationNumber;

    @Excel( name = "家长联系方式", orderNum = "31")
    private String parentTelephone;

    @Excel( name = "家长电子邮箱", orderNum = "32")
    private String parentEmail;

    @Excel( name = "学校ID")
    private String organizationId;

    private String schoolName;

    private String loginName;

    private String userType;

    @Excel( name = "证件类型", replace = { "身份证_1", "护照_2" })
    private String certificateType;

    @Excel( name = "家长证件类型")
    private String parentCertificateType;

    private String  secret;

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

}
