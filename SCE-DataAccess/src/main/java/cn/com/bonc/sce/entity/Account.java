package cn.com.bonc.sce.entity;

import cn.com.bonc.sce.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 安全
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "SCE_COMMON_USER_PASSWORD", schema = "STARCLOUDPORTAL" )
public class Account {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN_COMMON_USER_PASSWORD" )
    @SequenceGenerator( name = "SEQ_GEN_COMMON_USER_PASSWORD", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_COMMON_USER_PASSWORD" )
    @Column( name = "ID" )
    private Integer id;

    @Id
    @Column( name = "USER_ID" )
    private String userId;

    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false )
    @JoinColumn( name = "user_id" )
    // @JsonIgnore
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    @JsonIdentityInfo( generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id" )
    private User user;

    @Column( name = "PASSWORD" )
    private String password;

    @Column( name = "IS_DELETE" )
    private Integer isDelete;

    @Transient
    private String phone;

    @Transient
    private String code;

    @Transient
    private String newPassword;
}
