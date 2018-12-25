package cn.com.bonc.sce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 新闻
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
@Table(name="SCE_COMMON_COLUMN_CONTENT",schema = "STARCLOUDPORTAL")
public class News {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SEQ_GEN_COMMON_COLUMN_CONTENT")
    @SequenceGenerator(name="SEQ_GEN_COMMON_COLUMN_CONTENT",allocationSize=1,initialValue=1, sequenceName="SEQ_COMMON_COLUMN_CONTENT")
    @Column(name = "CONTENT_ID")
    private Integer id;

    @Column(name = "COLUMN_ID")
    private Integer columnId;

    @Column(name = "CONTENT_TYPE")
    private Integer contentType;

    @Column(name = "CONTENT_TAG")
    private String contentTag;

    @Column(name = "CONTENT_TITLE")
    private String contentTitle;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CONTENT_STATUS")
    private String contentStatus;

    @Column(name = "REJECT_OPINION")
    private String rejectOpinion;

    @CreatedDate
    @Column(name = "CREATE_TIME")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "CREATE_USER_ID")
    private String createUserId;

    @Column(name = "UPDATE_USER_ID")
    private String updateUserId;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "IS_DELETE")
    private Integer isDelete;
}
