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
@Table(name="SCE_ENTITY_INSTITUTION",schema = "STARCLOUDPORTAL")
public class Authority {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN_ENTITY_INSTITUTION")
    @SequenceGenerator(name="SEQ_GEN_ENTITY_INSTITUTION",allocationSize=1,initialValue=1, sequenceName="SEQ_ENTITY_INSTITUTION")
    @Column(name = "ID")
    private String id;

    @Column(name = "INSTITUTION_NAME")
    private String authorityName;

    @Column(name = "IS_DELETE")
    private Integer isDelete;

    @Column(name = "PID")
    private Integer pid;

    @Column(name = "DISTRICT")
    private Integer district;

    @Column(name = "CITY")
    private Integer city;

    @Column(name = "PROVINCE")
    private Integer province;

    @Column(name = "COUNTY")
    private Integer county;

    @Column(name = "ADDRESS")
    private Integer address;

    @Column(name = "TELEPHONE")
    private Integer telePhone;
}
