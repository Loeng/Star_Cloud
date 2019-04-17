package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: yhb
 * @Date: 2018/12/24 9:49
 * @Version 1.0
 */
@Data
@ApiModel( value = "Company", description = "厂商对象" )
public class CompanyInfoModel {
    @ApiModelProperty( name = "厂商ID", hidden = true )
    private Long companyId;

    @ApiModelProperty( name = "厂商名称" )
    private String companyName;

    @ApiModelProperty( name = "厂商地址" )
    private String companyAddress;

    @ApiModelProperty( name = "厂商税号" )
    private String companyTaxNum;

    @ApiModelProperty( name = "备注" )
    private String remarks;

    @ApiModelProperty( name = "是否删除", hidden = true )
    private Long isDelete;

    @ApiModelProperty( name = "法人" )
    private String juridicalPerson;

    @ApiModelProperty( name = "网址" )
    private String companyWebsite;

    @ApiModelProperty( name = "注册号" )
    private String companyRegistationId;

    @ApiModelProperty( name = "厂商邮箱" )
    private String companyEmail;

    @ApiModelProperty( name = "厂商简介" )
    private String companyIntroduction;

    @ApiModelProperty( name = "创建时间", hidden = true )
    private Date establishingTime;

    @ApiModelProperty(name = "邮编")
    private String postcode;

    @ApiModelProperty(name = "公司性质")
    private String property;

    @ApiModelProperty(name = "组织机构代码")
    private String institutionCode;

    @ApiModelProperty(name = "联系电话")
    private String phone;


}
