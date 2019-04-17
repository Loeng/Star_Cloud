package cn.com.bonc.sce.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by YueHaibo on 2018/12/12.
 */
@Entity
@Data
@Table( name = "SCE_MARKET_COMPANY", schema = "STARCLOUDMARKET" )
public class CompanyInfo {

    private Long companyId;
    private String companyName;
    private String companyAddress;
    private String companyTaxNum;
    private String remarks;
    private Long isDelete;
    private String juridicalPerson;
    private String companyWebsite;
    private String companyRegistationId;
    private String companyEmail;
    private String companyIntroduction;
    private Date establishingTime;
    private String postcode;
    private String property;
    private String institutionCode;
    private String phone;

    @Id
    @Column( name = "COMPANY_ID", nullable = false )
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId( Long companyId ) {
        this.companyId = companyId;
    }

    @Basic
    @Column( name = "COMPANY_NAME", nullable = true, length = 50 )
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName( String companyName ) {
        this.companyName = companyName;
    }

    @Basic
    @Column( name = "COMPANY_ADDRESS", nullable = true, length = 200 )
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress( String companyAddress ) {
        this.companyAddress = companyAddress;
    }

    @Basic
    @Column( name = "COMPANY_TAX_NUM", nullable = true, length = 20 )
    public String getCompanyTaxNum() {
        return companyTaxNum;
    }

    public void setCompanyTaxNum( String companyTaxNum ) {
        this.companyTaxNum = companyTaxNum;
    }

    @Basic
    @Column( name = "REMARKS", nullable = true )
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks( String remarks ) {
        this.remarks = remarks;
    }

    @Basic
    @Column( name = "IS_DELETE", nullable = true )
    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Long isDelete ) {
        this.isDelete = isDelete;
    }

    @Basic
    @Column( name = "JURIDICAL_PERSON", nullable = true )
    public String getJuridicalPerson() {
        return juridicalPerson;
    }

    public void setJuridicalPerson( String juridicalPerson ) {
        this.juridicalPerson = juridicalPerson;
    }

    @Basic
    @Column( name = "COMPANY_WEBSITE", nullable = true )
    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite( String companyWebsite ) {
        this.companyWebsite = companyWebsite;
    }

    @Basic
    @Column( name = "COMPANY_REGISTATION_ID", nullable = true )
    public String getCompanyRegistationId() {
        return companyRegistationId;
    }

    public void setCompanyRegistationId( String companyRegistationId ) {
        this.companyRegistationId = companyRegistationId;
    }

    @Basic
    @Column( name = "COMPANY_EMAIL", nullable = true )
    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail( String companyEmail ) {
        this.companyEmail = companyEmail;
    }

    @Basic
    @Column( name = "COMPANY_INTRODUCTION", nullable = true )
    public String getCompanyIntroduction() {
        return companyIntroduction;
    }

    public void setCompanyIntroduction( String companyIntroduction ) {
        this.companyIntroduction = companyIntroduction;
    }

    @Basic
    @Column( name = "ESTABLISHING_TIME", nullable = true )
    public Date getEstablishingTime() {
        return establishingTime;
    }

    public void setEstablishingTime( Date establishingTime ) {
        this.establishingTime = establishingTime;
    }

    @Basic
    @Column( name = "POSTCODE", nullable = true )
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column( name = "PROPERTY", nullable = true )
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Basic
    @Column( name = "INSTITUTION_CODE", nullable = true )
    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    @Basic
    @Column( name = "PHONE", nullable = true )
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        CompanyInfo that = ( CompanyInfo ) o;
        return companyId == that.companyId &&
                Objects.equals( companyName, that.companyName ) &&
                Objects.equals( companyAddress, that.companyAddress ) &&
                Objects.equals( companyTaxNum, that.companyTaxNum ) &&
                Objects.equals( remarks, that.remarks ) &&
                Objects.equals( isDelete, that.isDelete ) &&
                Objects.equals( juridicalPerson, that.juridicalPerson ) &&
                Objects.equals( companyWebsite, that.companyWebsite ) &&
                Objects.equals( companyRegistationId, that.companyRegistationId ) &&
                Objects.equals( companyEmail, that.companyEmail ) &&
                Objects.equals( companyIntroduction, that.companyIntroduction ) &&
                Objects.equals( establishingTime, that.establishingTime )&&
                Objects.equals( postcode, that.postcode )&&
                Objects.equals( property, that.property )&&
                Objects.equals( institutionCode, that.institutionCode )&&
                Objects.equals( phone, that.phone );
    }

    @Override
    public int hashCode() {

        return Objects.hash( companyId, companyName, companyAddress, companyTaxNum, remarks, isDelete,
                juridicalPerson, companyWebsite, companyRegistationId, companyEmail, companyIntroduction, establishingTime,postcode,property,institutionCode,phone );
    }
}
