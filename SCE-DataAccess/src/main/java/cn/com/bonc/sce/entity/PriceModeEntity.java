package cn.com.bonc.sce.entity;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 计费模式实体
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table( name = "SCE_MARKET_PRICE_MODE", schema = "STARCLOUDMARKET" )
public class PriceModeEntity implements Serializable {
    @Id
    @Column( name = "ID" )
    private String id;

    @Column( name = "APP_ID" )
    private String appId;

    @Column( name = "APP_VERSION" )
    private String appVersion;

    @Column( name = "MODE_NAME" )
    private String modeName;

    @Column( name = "MODE_DESC" )
    private String modeDesc;

    @Column( name = "MODE_DURATION_TYPE" )
    private String modeDurationType;

    @Column( name = "MODE_START_TIME" )
    private Date modeStartTime;

    @Column( name = "MODE_END_TIME" )
    private Date modeEndTime;

    @Column( name = "MODE_PRICE" )
    private String modePrice;

    @Column( name = "MODE_USER_COUNT" )
    private String modeUserCount;

    @Column( name = "MODE_TIME_UNIT" )
    private String modeTimeUnit;

    @Column( name = "MODE_TIME" )
    private String modeTime;

    @Column( name = "IS_RENEW" )
    private String isRenew;

    @Column( name = "SHOWING_ORDER" )
    private String showingOrder;

    @Column( name = "IS_DELETE" )
    private Integer isDelete;

}
