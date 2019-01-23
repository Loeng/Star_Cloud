package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel( value = "学生信息" )
public class InfoStudentModel {
    @ApiModelProperty( value = "用户Id", hidden = true )
    private String userId;

    @ApiModelProperty( value = "班号" )
    private String classNumber;

    @ApiModelProperty( value = "年纪" )
    private String grade;

    @ApiModelProperty( value = "备注" )
    private String remarks;

    @ApiModelProperty( value = "是否显示", hidden = true )
    private int isDelete;
}
