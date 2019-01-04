package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
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
    @NotBlank( message = "软件名称不能为空" )
    @ApiModelProperty( name = "app名称" )
    private String appName;

    @NotNull( message = "软件类型不能为空" )
    @ApiModelProperty( name = "类型id", example = "101" )
    private Long appTypeId;

    @NotBlank( message = "软件描述不能为空" )
    @ApiModelProperty( name = "软件描述" )
    private String appNotes;

    @NotBlank( message = "新版特性不能为空" )
    @ApiModelProperty( name = "新版特性" )
    private String newFeatures;

    @NotNull( message = "软件图标不能为空" )
    @ApiModelProperty( name = "软件图标" )
    private Integer appIcon;

    @ApiModelProperty( name = "pc端界面截图(json数组)" )
    private Set< Integer > appPcPic;

    @ApiModelProperty( name = "手机端界面截图(json数组)" )
    private Set< Integer > appPhonePic;

    @ApiModelProperty( name = "权限详情用,分隔" )
    private Set< String > authDetail;

    @ApiModelProperty( name = "上传的相关信息" )
    private Set< AppTypeMode > pc;


}
