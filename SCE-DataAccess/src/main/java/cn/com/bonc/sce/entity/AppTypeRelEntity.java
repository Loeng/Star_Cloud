package cn.com.bonc.sce.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 应用分类关系表（中间表）
 */
@Data
//@Entity
//@Table( name = "SCE_MARKET_APP_APPTYPE_REL",schema ="STARCLOUDMARKET" )
public class AppTypeRelEntity implements Serializable {
    //@Id
    //@GeneratedValue
    //@Column( name = "ID" )
    private String id;

    //@Column( name = "APP_ID" )
    private Long productTypeId;

    //@Column( name = "APP_TYPE_ID" )
    private Long productTypeName;


}
