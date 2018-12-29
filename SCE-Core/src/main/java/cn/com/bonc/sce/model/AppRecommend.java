package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BTW
 */
@ApiModel
@Data
public class AppRecommend {

    @ApiModelProperty( name = "appId" )
    private String appId;

}
