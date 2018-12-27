package cn.com.bonc.sce.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author BTW
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "SCE_MARKET_APP_OPEN", schema = "STARCLOUDMARKET" )
public class AppOpenEntity {

    @Id
    @GeneratedValue
    @Column( name = "ID" )
    private Integer id;

    @Column( name = "APP_ID" )
    private String appId;

    @Column( name = "OPEN_TIME" )
    private java.util.Date openTime;

    @Column( name = "USER_ID" )
    private String userId;

    @Column( name = "IS_DELETE" )
    private Long isDelete = 1L;

}
