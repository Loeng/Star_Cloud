package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel( value = "家长信息" )
public class InfoParentModel {
    @ApiModelProperty( value = "用户Id", hidden = true )
    private String userId;

    @ApiModelProperty( value = "家庭角色" )
    private String familyRole;

    @ApiModelProperty( value = "备注" )
    private String remarks;

    @ApiModelProperty( value = "是否显示", hidden = true )
    private int isDelete;
}
