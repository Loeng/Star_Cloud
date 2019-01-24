package cn.com.bonc.sce.entity;

import cn.com.bonc.sce.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 通知
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
@EntityListeners(AuditingEntityListener.class)
@Table(name="SCE_ENTITY_SCHOOL",schema = "STARCLOUDPORTAL")
public class School {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN_ENTITY_SCHOOL")
    @SequenceGenerator(name="SEQ_GEN_ENTITY_SCHOOL",allocationSize=1, sequenceName="SEQ_ENTITY_SCHOOL")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "SCHOOL_NAME")
    private String schoolName;

    @Column(name = "AUTHORITY_NAME")
    private String authorityName;

    @Column(name = "SCHOOL_ADDRESS")
    private String schoolAddress;

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "INSTITUTION_ID")
    private Integer institutionId;

    @Column(name = "GRADE")
    private String grade;

    @Column(name = "IS_DELETE")
    private Integer isDelete;

}
