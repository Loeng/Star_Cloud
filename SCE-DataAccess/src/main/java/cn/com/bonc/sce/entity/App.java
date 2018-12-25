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
 * app应用
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
@Table(name="SCE_MARKET_APP_INFO")
public class App {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN_MARKET_APP_INFO")
    @SequenceGenerator(name="SEQ_GEN_MARKET_APP_INFO",allocationSize=1,initialValue=1, sequenceName="SEQ_MARKET_APP_INFO")
    @Column(name = "APP_ID")
    private Integer appId;

    @Column(name = "APP_NAME")
    private String appName;

    @Column(name = "COMPANY_ID")
    private Integer companyId;

    @Column(name = "APP_PHONE_PIC")
    private String appPhonePic;

    @Column(name = "APP_PC_PIC")
    private String appPcPic;

    @Column(name = "IS_DELETE")
    private Integer isDelete;

    @Column(name = "CATEGORY_ID")
    private Integer categoryId;

    @Column(name = "CREATE_USER_ID")
    private String createUserId;

    @CreatedDate
    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_USER_ID")
    private String updateUserId;

    @LastModifiedDate
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "APP_COPYRIGHT")
    private String appCopyright;

    @Column(name = "AUDIT_VOUCHER")
    private String auditVoucher;

    @Column(name = "APP_NOTES")
    private String appNotes;

    @Column(name = "IS_HOT_RECOMMEND")
    private Integer isHotRecommend;

    @Column(name = "IS_TOP_RECOMMEND")
    private Integer isTopRecommend;

    @Column(name = "PAYMENT_STATUS")
    private String paymentStatus;

    @Column(name = "APP_SOURCE")
    private String appSource;

    @Column(name = "REMARKS")
    private String remarks;
}
