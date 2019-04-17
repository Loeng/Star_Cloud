package cn.com.bonc.sce.entity.user;

import cn.com.bonc.sce.entity.Account;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表
 *
 * @author wzm
 * @version 2.0
 * @update 修改字段命名，将错写的字段名称改为驼峰式，去掉 getter setter
 * @updateFrom 2018/12/25 21:49
 * @updateAuthor leucippus
 * @since 2018/12/21 9:00
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners( AuditingEntityListener.class )
@Table( name = "SCE_COMMON_USER", schema = "STARCLOUDPORTAL" )
public class User {
    @Id
    @GeneratedValue( generator = "SEQ_GEN_COMMON_USER" )
    @GenericGenerator( name = "SEQ_GEN_COMMON_USER", strategy = "uuid" )
    @Column( name = "USER_ID" )
    private String userId;
    @Column( name = "LOGIN_NAME" )
    private String loginName;
    @Column( name = "USER_NAME" )
    private String userName;
    @Column( name = "GENDER" )
    private String gender;
    @Column( name = "USER_TYPE" )
    private Integer userType;
    @Column( name = "MAIL_ADDRESS" )
    private String mailAddress;
    @Column( name = "CERTIFICATE_TYPE" )
    private String certificateType;
    @Column( name = "CERTIFICATE_NUMBER" )
    private String certificateNumber;
    @Column( name = "PHONE_NUMBER" )
    private String phoneNumber;
    @Transient
    private String briefAddress;
    @Column( name = "ADDRESS" )
    private String address;
    @CreatedDate
    @Column( name = "CREATE_TIME" )
    private Date createTime;
    @Column( name = "LOGIN_TIME" )
    private String loginTime;
    @Column( name = "LAST_LOGIN_TIME" )
    private String lastLoginTime;
    @Column( name = "ORGANIZATION_ID" )
    private String organizationId;
    @Column( name = "LOGIN_PERMISSION_STATUS" )
    private Integer loginPermissionStatus;
    @Column( name = "REMARKS" )
    private String remarks;
    @Column( name = "IS_DELETE" )
    private Integer isDelete;
    @OneToOne( fetch = FetchType.EAGER, mappedBy = "user" )
    @JsonIgnoreProperties( value = { "hibernateLazyInitializer", "handler" } )
    @JsonIdentityInfo( generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id" )
    private Account account;
    @Column( name = "SECRET", length = 2047 )
    private String secret;
    @Column( name = "IS_FIRST_LOGIN" )
    private Integer isFirstLogin;
    @Column( name = "ISADMINISTRATORS" )
    private Integer isAdministrators;
    @Column( name = "ACCOUNT_STATUS" )
    private Integer accountStatus;
    @Transient
    private Object userDetailedInfo;
    @Transient
    private Object school;
    @Column
    private Date birthDate;
    @Transient
    private Object Student;
}
