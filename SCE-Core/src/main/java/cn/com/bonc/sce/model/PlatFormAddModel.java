package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 平台资源上传参数
 *
 * @author jc_D
 * @description 用于绑定前端传来的json
 * @date 2018/12/26
 **/
@ApiModel
@Data
public class PlatFormAddModel {
    @NotBlank( message = "应用名称不能为空" )
    @ApiModelProperty( name = "应用名称" )
    private String appName;

    @NotNull( message = "应用类型不能为空" )
    @ApiModelProperty( name = "类型id", example = "101" )
    private Long appTypeId;

    @NotBlank( message = "应用描述不能为空" )
    @ApiModelProperty( name = "应用描述" )
    private String appNotes;

    @NotBlank( message = "新版特性不能为空" )
    @ApiModelProperty( name = "新版特性" )
    private String newFeatures;

    @NotBlank( message = "安装说明不能为空" )
    @ApiModelProperty( name = "安装说明" )
    private String installInfo;

    @NotNull( message = "安装包不能为空" )
    @ApiModelProperty( name = "应用版本" )
    private Integer storeLocation;

    @NotBlank( message = "版本号不能为空" )
    @ApiModelProperty( name = "版本号" )
    private String appVersion;

    @NotNull( message = "包名不能为空" )
    @ApiModelProperty( name = "包名" )
    private String packageName;

    @NotNull( message = "应用图标不能为空" )
    @ApiModelProperty( name = "应用图标" )
    private Integer appIcon;

    @NotBlank( message = "indexUrl不能为空" )
    @ApiModelProperty( name = "主页链接" )
    private String indexUrl;

    @NotBlank( message = "测试url不能为空" )
    @ApiModelProperty( name = "测试url" )
    private String testUrl;

    @ApiModelProperty( name = "pc端界面截图(json数组)" )
    private Set< Integer > appPcPic;
}
