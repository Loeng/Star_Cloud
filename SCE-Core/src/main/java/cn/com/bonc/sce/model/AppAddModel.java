package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * 软件上传参数
 *
 * @author jc_D
 * @description 用于绑定前端传来的json
 * @date 2018/12/26
 **/
@ApiModel
@Data
public class AppAddModel {
    @ApiModelProperty( name = "app名称" )
    private String appName;
    @ApiModelProperty( name = "类型id" )
    private Long appTypeId;
    @ApiModelProperty( name = "软件描述" )
    private Long appNotes;
    @ApiModelProperty( name = "新版特性" )
    private String newFeatures;
    @ApiModelProperty( name = "软件图标" )
    private String appIcon;
    @ApiModelProperty( name = "pc端界面截图" )
    private String appPcPic;
    @ApiModelProperty( name = "手机端界面截图" )
    private String appPhonePic;
    @ApiModelProperty( name = "权限详情用,分隔" )
    private String authDetail;
    @ApiModelProperty( name = "pc版本相关信息" )
    private Set< AppTypeMode > pc;
    @ApiModelProperty( name = "手机版本相关信息" )
    private Set< AppTypeMode > phone;


}
