package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 软件上传子参数
 *
 * @author jc_D
 * @description 用于绑定前端传来的json
 * @date 2018/12/27
 **/
@ApiModel
@Data
public class AppTypeMode {
    @ApiModelProperty( name = "版本号" )
    private String appVersion;
    @ApiModelProperty( name = "运行平台" )
    private String versioInfo;
    @ApiModelProperty( name = "软件上传后返回的id" )
    private String address;
    @ApiModelProperty( name = "软件大小" )
    private String versionSize;
    @ApiModelProperty( name = "包名" )
    private String packageName;
}
