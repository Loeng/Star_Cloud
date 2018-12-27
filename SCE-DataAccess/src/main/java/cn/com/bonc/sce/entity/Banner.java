package cn.com.bonc.sce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * banner
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="SCE_PORTAL_BANNER",schema = "STARCLOUDPORTAL")
public class Banner {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN_PORTAL_BANNER")
    @SequenceGenerator(name="SEQ_GEN_PORTAL_BANNER",allocationSize=1,initialValue=1, sequenceName="SEQ_PORTAL_BANNER")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "BANNER_ORDER")
    private Integer order;

    @Column(name = "IMAGE_URL")
    private Integer url;

    @Column(name = "APP_ID")
    private String appId;

    @Column(name = "IS_SHOWN")
    private Integer isShown;

    @Column(name = "BANNER_TYPE_ID")
    private Integer type;

    @Column(name = "IS_DELETE")
    private Integer isDelete;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @ManyToOne
    @JoinColumn(name="IMAGE_URL", referencedColumnName="RESOURCE_ID", insertable=false, updatable=false)
    private Pic pic;

    @Transient
    private String picUrl;

}
