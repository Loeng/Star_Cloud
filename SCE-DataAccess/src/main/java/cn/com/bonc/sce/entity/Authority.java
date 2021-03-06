package cn.com.bonc.sce.entity;

import cn.com.bonc.sce.entity.user.User;
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
@Table(name="SCE_ENTITY_INSTITUTION",schema = "STARCLOUDPORTAL")
public class Authority {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN_ENTITY_INSTITUTION")
    @SequenceGenerator(name="SEQ_GEN_ENTITY_INSTITUTION",allocationSize=1,initialValue=1, sequenceName="SEQ_ENTITY_INSTITUTION")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "INSTITUTION_NAME")
    private String authorityName;

    @Column(name = "IS_DELETE")
    private Integer isDelete;

    @Column(name = "PID")
    private Integer pid;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "CITY")
    private String city;

    @Column(name = "PROVINCE")
    private String province;

    @Column(name = "COUNTY")
    private String county;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "TELEPHONE")
    private String telePhone;
}
