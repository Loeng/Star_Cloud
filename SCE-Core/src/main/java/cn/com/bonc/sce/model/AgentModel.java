package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author BTW
 */
@Data
@ApiModel( value = "Agent", description = "代理对象" )
public class AgentModel {

    @ApiModelProperty( name = "代理商ID", hidden = true )
    private Long id;

    @ApiModelProperty( name = "代理名称" )
    private String agentName;

    @ApiModelProperty( name = "学段" )
    private String grade;

    @ApiModelProperty( name = "代理区域" )
    private String agentArea;

    @ApiModelProperty( name = "代理地址" )
    private String agentAddress;

    @ApiModelProperty( name = "代理税号" )
    private String agentTaxNum;

    @ApiModelProperty( name = "代理法人" )
    private String juridicalPerson;

    @ApiModelProperty( name = "代理网址" )
    private String agentWebsite;

    @ApiModelProperty( name = "注册ID" )
    private String agentRegistationId;

    @ApiModelProperty( name = "联系邮箱" )
    private String agentEmail;

    @ApiModelProperty( name = "创立时间" )
    private Date establishingTime;

    @ApiModelProperty( name = "代理简介" )
    private String agentIntroduction;

    @ApiModelProperty( name = "邮编" )
    private String postcode;

    @ApiModelProperty( name = "公司性质" )
    private String property;

    @ApiModelProperty( name = "组织机构码" )
    private String institutionCode;

    @ApiModelProperty( name = "联系电话" )
    private String phone;

    @ApiModelProperty( name = "省" )
    private String province;

    @ApiModelProperty( name = "市" )
    private String city;

    @ApiModelProperty( name = "区" )
    private String area;
}
