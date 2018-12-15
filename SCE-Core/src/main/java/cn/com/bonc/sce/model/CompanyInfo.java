package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by YueHaibo on 2018/12/12.
 */
@ApiModel
public class CompanyInfo {
    @ApiModelProperty( "用户Id" )
    private String userId;

    @ApiModelProperty( "厂商Id" )
    private String companyId;

    @ApiModelProperty( "联系电话" )
    private String tel;

    @ApiModelProperty( "地址" )
    private String address;

    @ApiModelProperty( "备注" )
    private String note;

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId( String companyId ) {
        this.companyId = companyId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel( String tel ) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote( String note ) {
        this.note = note;
    }
}
