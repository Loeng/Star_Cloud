package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: yhb
 * @Date: 2018/12/25 17:19
 * @Version 1.0
 */
@ApiModel
@Data
public class AppVersionModel {
    @ApiModelProperty( name = "应用Id" )
    private String appId;

    @ApiModelProperty( name = "应用版本" )
    private String appVersion;

    @ApiModelProperty( name = "应用状态" )
    private String appStatus;

    @ApiModelProperty( name = "应用下载地址" )
    private String appDownloadAddress;

    @ApiModelProperty( name = "创建时间", hidden = true )
    private Date createTime;

    @ApiModelProperty( name = "版本信息" )
    private String versionInfo;

    @ApiModelProperty( name = "版本大小" )
    private String versionSize;

    @ApiModelProperty( name = "运行平台" )
    private String runningPlatform;

    @ApiModelProperty( name = "是否显示", hidden = true )
    private Long isDelete;

    @ApiModelProperty( name = "创建者Id", hidden = true )
    private String createUserId;

    @ApiModelProperty( name = "更新者Id", hidden = true )
    private String updateUserId;

    @ApiModelProperty( name = "更新时间", hidden = true )
    private Date updateTime;

    @ApiModelProperty( name = "更新版本描述" )
    private String newFeatures;

    @ApiModelProperty( name = "包名称" )
    private String packageName;

    @ApiModelProperty( name = "作者详情" )
    private String authDetail;

    @ApiModelProperty( name = "手机版图片" )
    private String appPhonePic;

    @ApiModelProperty( name = "电脑版图片" )
    private String appPcPic;

    @ApiModelProperty( name = "应用当前版本" )
    private String currentVersion;

    @ApiModelProperty( name = "应用当前版本" )
    private String installInfo;

    @ApiModelProperty( name = "应用当前版本" )
    private String storeLocation;

    @ApiModelProperty( name = "根路径" )
    private String indexUrl;

    @ApiModelProperty( name = "测试url" )
    private String testUrl;

    @ApiModelProperty( name = "tokenAddress" )
    private String tokenAddress;

    @ApiModelProperty( name = "md5Code" )
    private String md5Code;
}
