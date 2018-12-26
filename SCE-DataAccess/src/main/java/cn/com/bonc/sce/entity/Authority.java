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
@Table(name="SCE_ENTITY_SCHOOL",schema = "STARCLOUDPORTAL")
public class Authority {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN_ENTITY_SCHOOL")
    @SequenceGenerator(name="SEQ_GEN_ENTITY_SCHOOL",allocationSize=1,initialValue=1, sequenceName="SEQ_ENTITY_SCHOOL")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "AUTHORITY_NAME")
    private String authorityName;

    @Column(name = "IS_DELETE")
    private Integer isDelete;
}
