package cn.com.bonc.sce.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by YueHaibo on 2018/12/12.
 */
@ApiModel
@Entity
@Table( name = "SCE_MARKET_COMPANY", schema = "STARCLOUDMARKET", catalog = "" )
public class CompanyInfo {
    @ApiModelProperty( name = "厂商ID" )
    private long companyId;

    @ApiModelProperty( name = "厂商名称" )
    private String companyName;

    @ApiModelProperty( name = "厂商地址" )
    private String companyAddress;

    @ApiModelProperty( name = "厂商税号" )
    private String compantTaxNum;

    @ApiModelProperty( name = "备注" )
    private String remarks;

    @ApiModelProperty( name = "是否删除" )
    private Long isDelete;

    @Id
    @Column( name = "COMPANY_ID", nullable = false, precision = 0 )
    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId( long companyId ) {
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
    @Column( name = "COMPANT_TAX_NUM", nullable = true, length = 20 )
    public String getCompantTaxNum() {
        return compantTaxNum;
    }

    public void setCompantTaxNum( String compantTaxNum ) {
        this.compantTaxNum = compantTaxNum;
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
    @Column( name = "IS_DELETE", nullable = true, precision = 0 )
    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Long isDelete ) {
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        CompanyInfo that = ( CompanyInfo ) o;
        return companyId == that.companyId &&
                Objects.equals( companyName, that.companyName ) &&
                Objects.equals( companyAddress, that.companyAddress ) &&
                Objects.equals( compantTaxNum, that.compantTaxNum ) &&
                Objects.equals( remarks, that.remarks ) &&
                Objects.equals( isDelete, that.isDelete );
    }

    @Override
    public int hashCode() {

        return Objects.hash( companyId, companyName, companyAddress, compantTaxNum, remarks, isDelete );
    }
}
