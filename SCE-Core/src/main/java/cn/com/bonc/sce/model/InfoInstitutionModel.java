package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "机构信息")
public class InfoInstitutionModel {
    @ApiModelProperty( value = "用户Id", hidden = true )
    private String userId;

    @ApiModelProperty( value = "机构Id" )
    private String institutionId;

    @ApiModelProperty( value = "电话号码", hidden = true, notes = "和user表重复，未使用" )
    private String telephone;

    @ApiModelProperty( value = "地址", hidden = true, notes = "和user表重复，未使用" )
    private String address;

    @ApiModelProperty( value = "备注" )
    private String remarks;

    @ApiModelProperty(value = "是否显示",hidden = true)
    private int isDelete;
}
