package cn.com.bonc.sce.entity.history;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

/**
 * 记录用户的登录状态
 *
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/27 20:03
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "SCE_LOGIN_HISTORY", schema = "STARCLOUDPORTAL" )
public class LoginHistory {
    @Id
    @GeneratedValue( generator = "SCE_SEQ_ID_LOGIN_HIS" )
    @GenericGenerator( name = "SCE_SEQ_ID_LOGIN_HIS", strategy = "uuid" )
    @Column( name = "ID" )
    private String id;

    @Column( name = "USER_ID" )
    private String userId;

    @Column( name = "LOGIN_IP" )
    private String loginIp;

    @Column( name = "LOGIN_TIME" )
    private Date loginTime;
}
