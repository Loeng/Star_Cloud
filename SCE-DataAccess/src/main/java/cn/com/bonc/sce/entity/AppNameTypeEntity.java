package cn.com.bonc.sce.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 应用分类表
 *
 * @author jc_D
 * @description
 * @date 2018/12/18
 **/
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "SCE_MARKET_APP_INFO", schema = "STARCLOUDMARKET" )
public class AppNameTypeEntity {
    @Id
    @GeneratedValue
    @Column( name = "APP_ID" )
    private String appId;

    @JsonIgnoreProperties(value = { "appInfo" })
    @ManyToMany
    @JoinTable( schema = "STARCLOUDMARKET", name = "SCE_MARKET_APP_APPTYPE_REL", joinColumns = @JoinColumn( name = "APP_ID" ),
            inverseJoinColumns = @JoinColumn( name = "APP_TYPE_ID" ))
    private Set< AppTypeEntity > appTypes = new HashSet<>();

    @Column( name = "APP_NAME" )
    private String appName;

    @ManyToOne
    @JoinColumn( name = "COMPANY_ID" )
    public CompanyInfo companyInfo;
    
    @OneToOne(optional=false)
    @JoinColumn( name = "APP_ID" )
    public AppDownloadCountView appDownloadCount;

    @Column( name = "APP_ICON" )
    private String appIcon;

    @Column( name = "IS_DELETE" )
    private Integer isDelete = 1;

    @Column( name = "CATEGORY_ID" )
    private Long categoryId;

    @Column( name = "CREATE_USER_ID" )
    private String createUserId;

    @Column( name = "CREATE_TIME" )
    private java.util.Date createTime;

    @Column( name = "UPDATE_USER_ID" )
    private String updateUserId;

    @Column( name = "UPDATE_TIME" )
    private java.util.Date updateTime;

    @Column( name = "APP_COPYRIGHT" )
    private String appCopyright;

    @Column( name = "AUDIT_VOUCHER" )
    private String auditVoucher;

    @Column( name = "APP_NOTES" )
    private String appNotes;

    @Column( name = "IS_HOT_RECOMMEND" )
    private Long isHotRecommend;

    @Column( name = "IS_TOP_RECOMMEND" )
    private Long isTopRecommend;

    @Column( name = "PAYMENT_STATUS" )
    private java.util.Date paymentStatus;

    @Column( name = "APP_SOURCE" )
    private String appSource;

    @Column( name = "REMARKS" )
    private String remarks;

/*    @Override
    public String toString() {
        return "AppInfoEntity{" +
                "appId=" + appId +
                ", appTypes=" + appTypes +
                ", appName='" + appName + '\'' +
                ", companyId=" + companyId +
                ", appPhonePic='" + appPhonePic + '\'' +
                ", appPcPic='" + appPcPic + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", categoryId=" + categoryId +
                ", createUserId='" + createUserId + '\'' +
                ", createTime=" + createTime +
                ", updateUserId='" + updateUserId + '\'' +
                ", updateTime=" + updateTime +
                ", appCopyright='" + appCopyright + '\'' +
                ", auditVoucher='" + auditVoucher + '\'' +
                ", appNotes='" + appNotes + '\'' +
                ", isHotRecommend=" + isHotRecommend +
                ", isTopRecommend=" + isTopRecommend +
                ", paymentStatus=" + paymentStatus +
                ", appSource='" + appSource + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }*/
}
