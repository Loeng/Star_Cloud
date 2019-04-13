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
public class PlatformAddModel {

    @NotBlank( message = "应用名称不能为空" )
    @ApiModelProperty( name = "应用名称" )
    private String appName;

    @NotNull( message = "应用类型不能为空" )
    @ApiModelProperty( name = "类型id", example = "101" )
    private Long appTypeId;

    @NotBlank( message = "版本号不能为空" )
    @ApiModelProperty( name = "版本号" )
    private String appVersion;

    @NotBlank( message = "应用描述不能为空" )
    @ApiModelProperty( name = "应用描述" )
    private String versionInfo;

    @NotBlank( message = "新版特性不能为空" )
    @ApiModelProperty( name = "新版特性" )
    private String newFeatures;

    @NotNull( message = "应用包不能为空" )
    @ApiModelProperty( name = "应用包" )
    private Integer storeLocation;

    @NotNull( message = "应用图标不能为空" )
    @ApiModelProperty( name = "应用图标" )
    private Integer appIcon;

    @ApiModelProperty( name = "应用展示(json数组)" )
    private Set< Integer > appPcPic;

    @ApiModelProperty( name = "[价格与计费模式]相关信息" )
    private Set< PriceModeModel > priceModeModel;

    @ApiModelProperty( name = "财务凭证" )
    private Set< Integer > auditVoucher;

    @ApiModelProperty( name = "合同文件" )
    private Set< Integer > contractFile;

    @ApiModelProperty( name = "厂家比例" )
    private String companyRatio;

    @ApiModelProperty( name = "平台比例" )
    private String platformRatio;

    @ApiModelProperty( name = "当前版本" )
    private String currentVersion;


//    @NotNull( message = "包名不能为空" )
//    @ApiModelProperty( name = "包名" )
//    private String packageName;

//    @NotBlank( message = "indexUrl不能为空" )
//    @ApiModelProperty( name = "主页链接" )
//    private String indexUrl;

//    @NotBlank( message = "测试url不能为空" )
//    @ApiModelProperty( name = "测试url" )
//    private String testUrl;

//    @NotBlank( message = "Token接收地址不能为空" )
//    @ApiModelProperty( name = "Token接收地址" )
//    private String tokenAddress;

    //@NotBlank( message = "应用md5码不能为空" )
//    @ApiModelProperty( name = "测试url" )
//    private String md5Code;

//    @ApiModelProperty( name = "开发者姓名" )
//    private String developerName;
//
//    @ApiModelProperty( name = "开发者身份证号" )
//    private String developerIdNumber;
//
//    @ApiModelProperty( name = "开发者联系电话" )
//    private String developerPhone;
//
//    @ApiModelProperty( name = "主联系人" )
//    private String mainContact;
//
//    @ApiModelProperty( name = "开发者身份证图片" )
//    private Integer developerIdPic;
//
//    @ApiModelProperty( name = "计费模式" )
//    private String chargeMode;

//    @ApiModelProperty( name = "软著凭证" )
//    private Set< Integer > appCopyright;


}
