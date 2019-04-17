package cn.com.bonc.sce.model;

import lombok.Data;

/**
 * 学校
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Data
public class School {
    private Long id;
    private String schoolName;    //学校名称
    private String authorityName;
    private String schoolAddress;  //学校地址
    private String telephone;     //联系电话
    private Integer institutionId;  //所属教育局ID
    private String grade;
    private String englishName; //学校英文名称
    private String postcode;   //学校邮编
    private String province;  //省
    private String city;      //市
    private String area;      //区
    private String schoolType;   //办学类型
    private String schoolmasterName;  //校长姓名
    private String orgCode;   //组织机构代码
    private String email;  //电子邮箱
    private String homepage;  //主页地址
    private String schoolRunning;   //学校办别
    private String schoolDistrict; //所属学区
    private Integer isDelete;
}
