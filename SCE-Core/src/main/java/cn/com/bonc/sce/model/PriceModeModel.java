package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 计费模式相关参数 上传参数
 *
 * @author jc_D
 * @description 用于绑定前端传来的json
 * @date 2018/12/26
 **/
@ApiModel
@Data
public class PriceModeModel {
    @ApiModelProperty( name = "套餐名称" )
    private String modeName;

    @ApiModelProperty( name = "套餐描述" )
    private String modeDesc;

    @ApiModelProperty( name = "套餐时效[(1，永久，2，自定义)]" )
    private String modeDurationType;

    @ApiModelProperty( name = "套餐开始时间" )
    private Date modeStartTime;

    @ApiModelProperty( name = "套餐结束时间" )
    private Date modeEndTime;

    @ApiModelProperty( name = "套餐价格" )
    private String modePrice;

    @ApiModelProperty( name = "价格标准人数" )
    private String modeUserCount;

    @ApiModelProperty( name = "价格标准时长单位" )
    private String modeTimeUnit;

    @ApiModelProperty( name = "价格标准时长" )
    private String modeTime;

    @ApiModelProperty( name = "可否续订,1:可续订 0:不可续订" )
    private String isRenew;

    @ApiModelProperty( name = "显示排序" )
    private String showingOrder;


}
