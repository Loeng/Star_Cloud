package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author BTW
 */
@ApiModel( value = "Agent", description = "代理对象" )
public class AgentModel {

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

    @ApiModelProperty( name = "代理ID" )
    private String agentRegistationId;

    @ApiModelProperty( name = "代理ID" )
    private String agentEmail;

    @ApiModelProperty( name = "代理ID" )
    private Date establishingTime;

    @ApiModelProperty( name = "代理ID" )
    private String agentIntroduction;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName( String agentName ) {
        this.agentName = agentName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade( String grade ) {
        this.grade = grade;
    }

    public String getAgentArea() {
        return agentArea;
    }

    public void setAgentArea( String agentArea ) {
        this.agentArea = agentArea;
    }

    public String getAgentAddress() {
        return agentAddress;
    }

    public void setAgentAddress( String agentAddress ) {
        this.agentAddress = agentAddress;
    }

    public String getAgentTaxNum() {
        return agentTaxNum;
    }

    public void setAgentTaxNum( String agentTaxNum ) {
        this.agentTaxNum = agentTaxNum;
    }

    public String getJuridicalPerson() {
        return juridicalPerson;
    }

    public void setJuridicalPerson( String juridicalPerson ) {
        this.juridicalPerson = juridicalPerson;
    }

    public String getAgentWebsite() {
        return agentWebsite;
    }

    public void setAgentWebsite( String agentWebsite ) {
        this.agentWebsite = agentWebsite;
    }

    public String getAgentRegistationId() {
        return agentRegistationId;
    }

    public void setAgentRegistationId( String agentRegistationId ) {
        this.agentRegistationId = agentRegistationId;
    }

    public String getAgentEmail() {
        return agentEmail;
    }

    public void setAgentEmail( String agentEmail ) {
        this.agentEmail = agentEmail;
    }

    public Date getEstablishingTime() {
        return establishingTime;
    }

    public void setEstablishingTime( Date establishingTime ) {
        this.establishingTime = establishingTime;
    }

    public String getAgentIntroduction() {
        return agentIntroduction;
    }

    public void setAgentIntroduction( String agentIntroduction ) {
        this.agentIntroduction = agentIntroduction;
    }

}
