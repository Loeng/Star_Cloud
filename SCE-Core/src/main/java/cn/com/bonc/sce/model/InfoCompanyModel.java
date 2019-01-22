package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户和厂商关系信息
 */
@Data
@ApiModel
public class InfoCompanyModel {
    @ApiModelProperty( value = "用户Id", hidden = true )
    private String userId;

    @ApiModelProperty( value = "厂商Id" )
    private String companyId;

    @ApiModelProperty( value = "电话号码", hidden = true, notes = "和user表重复，未使用" )
    private String telephone;

    @ApiModelProperty( value = "地址", hidden = true, notes = "和user表重复，未使用" )
    private String address;

    @ApiModelProperty( value = "备注" )
    private String remarks;

    @ApiModelProperty(value = "是否显示",hidden = true)
    private int isDelete;
}
