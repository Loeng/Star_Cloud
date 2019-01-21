package cn.com.bonc.sce.entity;

import javax.persistence.*;

/**
 * 角色信息对应表
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Entity
@Table( name = "SCE_COMMON_USER_ROLE_REL",schema = "STARCLOUDPORTAL" )
public class RoleRel {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN_COMMON_USER_ROLE_REL" )
    @SequenceGenerator( name = "SEQ_GEN_COMMON_USER_ROLE_REL", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_COMMON_USER_ROLE_REL" )
    @Column( name = "ID" )
    private Integer id;
    @Column( name = "USER_ID" )
    private String userId;
    @Column( name = "ROLE_ID" )
    private Integer roleId;
    @Column( name = "REMARKS" )
    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId( Integer roleId ) {
        this.roleId = roleId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks( String remarks ) {
        this.remarks = remarks;
    }

    public RoleRel() {

    }

    public RoleRel( String userId, Integer roleId, String remarks ) {

        this.userId = userId;
        this.roleId = roleId;
        this.remarks = remarks;
    }
}
