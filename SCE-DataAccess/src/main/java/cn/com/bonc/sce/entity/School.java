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
   /* @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN_ENTITY_SCHOOL")
    @SequenceGenerator(name="SEQ_GEN_ENTITY_SCHOOL",allocationSize=1, sequenceName="SEQ_ENTITY_SCHOOL")*/
    @Column(name = "ID")
    private Long id;

    @Column(name = "SCHOOL_NAME")
    private String schoolName;  //学校名称

    @Column(name = "AUTHORITY_NAME")
    private String authorityName;

    @Column(name = "SCHOOL_ADDRESS")
    private String schoolAddress;  //学校地址

    @Column(name = "TELEPHONE")
    private String telephone;   //联系电话

    @Column(name = "INSTITUTION_ID")
    private Integer institutionId;  //所属教育局ID

    @Column(name = "Institution_NAME")
    private String institutionName;

    @Column(name = "GRADE")
    private String grade;

    @Column(name = "IS_DELETE")
    private Integer isDelete;

    @Column(name = "ENGLISH_NAME")
    private String englishName;   //学校英文名称

    @Column(name = "POSTCODE")
    private String postcode; //学校邮编

    @Column(name = "PROVINCE")
    private String province; //省

    @Column(name = "CITY")
    private String city;  //市

    @Column(name = "AREA")
    private String area;  //区

    @Column(name = "SCHOOL_TYPE")
    private String schoolType; //办学类型

    @Column(name = "SCHOOLMASTER_NAME")
    private String schoolmasterName ; //校长姓名

    @Column(name = "ORG_CODE")
    private String orgCode;  //组织机构代码

    @Column(name = "EMAIL")
    private String email;  //电子邮箱

    @Column(name = "HOMEPAGE")
    private String homepage;  //主页地址

    @Column(name = "SCHOOL_RUNNING")
    private String schoolRunning; //学校办别

    @Column(name = "SCHOOL_DISTRICT")
    private String schoolDistrict;  //所属学区
}
