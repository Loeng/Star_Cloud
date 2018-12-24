package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: yhb
 * @Date: 2018/12/24 9:49
 * @Version 1.0
 */
@ApiModel(value = "Company",description = "厂商对象")
public class CompanyInfoModel {
    @ApiModelProperty( name = "厂商ID" )
    private long companyId;

    @ApiModelProperty( name = "厂商名称" )
    private String companyName;

    @ApiModelProperty( name = "厂商地址" )
    private String companyAddress;

    @ApiModelProperty( name = "厂商税号" )
    private String companyTaxNum;

    @ApiModelProperty( name = "备注" )
    private String remarks;

    @ApiModelProperty( name = "是否删除" )
    private Long isDelete;

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId( long companyId ) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName( String companyName ) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress( String companyAddress ) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyTaxNum() {
        return companyTaxNum;
    }

    public void setCompanyTaxNum( String companyTaxNum ) {
        this.companyTaxNum = companyTaxNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks( String remarks ) {
        this.remarks = remarks;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Long isDelete ) {
        this.isDelete = isDelete;
    }
}
