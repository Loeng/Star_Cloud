package cn.com.bonc.sce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2019/4/13.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "SCE_USER_AUDIT", schema = "STARCLOUDPORTAL" )
public class UserAudit {

    @Id
    @GeneratedValue
    @Column( name = "ID" )
    private Long id;

    @Column( name = "USER_ID" )
    private String userId;

    @Column( name = "USER_TYPE" )
    private Integer userType;

    @Column( name = "ENTITY_ID" )
    private Integer entityId;

    @Column( name = "AUDIT_STATUS" )
    private Integer auditStatus;

    @Column( name = "AUDIT_TIME" )
    private Date auditTime;

    @Column( name = "AUDIT_USER_ID" )
    private String auditUserId;

    @Column( name = "REJECT_OPINION" )
    private String rejectOpinion;
}
