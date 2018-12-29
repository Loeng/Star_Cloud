package cn.com.bonc.sce.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 应用分类关系表（中间表）
 */
@Data
@Entity
@Table( name = "SCE_MARKET_APP_APPTYPE_REL", schema = "STARCLOUDMARKET" )
public class AppTypeRelEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "ID" )
    private Long id;

    @Column( name = "APP_ID" )
    private String appId;

    @Column( name = "APP_TYPE_ID" )
    private Long appTypeId;


}
