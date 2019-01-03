package cn.com.bonc.sce.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author BTW
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@IdClass( UserAppPrimaryKey.class )
@Table( name = "SCE_MARKET_APP_OPEN", schema = "STARCLOUDMARKET" )
public class AppOpenEntity implements Serializable{

    @Column( name = "ID" )
    private Integer id;

    @Id
    @Column( name = "APP_ID" )
    private String appId;

    @Column( name = "OPEN_TIME" )
    private java.util.Date openTime;

    @Id
    @Column( name = "USER_ID" )
    private String userId;

    @Column( name = "IS_DELETE" )
    private Long isDelete = 1L;

}
