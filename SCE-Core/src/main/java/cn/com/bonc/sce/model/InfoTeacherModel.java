package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户和教师的关系信息
 */
@Data
@ApiModel
public class InfoTeacherModel {
    @ApiModelProperty( value = "用户Id", hidden = true )
    private String userId;

    @ApiModelProperty( value = "学校Id" ,hidden = true,notes = "重复，未使用")
    private String schoolId;

    @ApiModelProperty( value = "职位" )
    private String position;

    @ApiModelProperty( value = "科目" )
    private String subject;

    @ApiModelProperty( value = "教龄" )
    private String schoolAge;

    @ApiModelProperty( value = "备注" )
    private String remarks;

    @ApiModelProperty(value = "是否显示",hidden = true)
    private int isDelete;
}
