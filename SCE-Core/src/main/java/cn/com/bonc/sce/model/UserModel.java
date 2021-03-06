package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 此类用作接受参数，创建用户使用
 */
@ApiModel
@Data
public class UserModel {

    @ApiModelProperty(value = "用户Id",hidden = true)
    private String userId;

    @ApiModelProperty( value = "账号", required = true )
    private String loginName;

    @ApiModelProperty( value = "姓名", required = true )
    private String userName;

    @ApiModelProperty( value = "性别", required = true )
    private String gender;

    @ApiModelProperty( value = "用户类型", required = true )
    private int userType;

    @ApiModelProperty( value = "邮箱地址" )
    private String mailAddress;

    @ApiModelProperty( value = "证件类型" )
    private int certificateType;

    @ApiModelProperty( value = "证件号" )
    private String certificateNumber;

    @ApiModelProperty( value = "电话号码" )
    private String phoneNumber;

    @ApiModelProperty( value = "地址" )
    private String address;

    @ApiModelProperty( value = "创建时间", hidden = true )
    private Date createTime;

    @ApiModelProperty( value = "登录时间", hidden = true )
    private Date loginTime;

    @ApiModelProperty( value = "上一次登录时间", hidden = true )
    private Date lastLoginTime;

    @ApiModelProperty( value = "登录次数", hidden = true )
    private int loginCounts;

    @ApiModelProperty( value = "组织Id" ,notes = "实际上为学校Id")
    private String organizationId;

    @ApiModelProperty( value = "是否允许登录", hidden = true )
    private int loginPermissionStatus;

    @ApiModelProperty( value = "备注" )
    private String remarks;

    @ApiModelProperty( value = "是否显示", hidden = true )
    private int isDelete;

    @ApiModelProperty( value = "密钥", hidden = true)
    private String secret;

    @ApiModelProperty( value = "密码", required = true)
    private String password;

    @ApiModelProperty( value = "厂商用户信息" )
    private InfoCompanyModel infoCompanyModel;

    @ApiModelProperty( value = "教师用户信息" )
    private InfoTeacherModel infoTeacherModel;

    @ApiModelProperty( value = "代理商用户信息" )
    private InfoAgentModel infoAgentModel;

    @ApiModelProperty( value = "机构用户信息" )
    private InfoInstitutionModel infoInstitutionModel;

    @ApiModelProperty( value = "家长用户信息" )
    private InfoParentModel infoParentModel;

    @ApiModelProperty( value = "学生用户信息" )
    private InfoStudentModel infoStudentModel;
}
