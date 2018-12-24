package cn.com.bonc.sce.entity;

import javax.persistence.*;


/**
 * 家长资料表
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Entity
@Table( name = "SCE_INFO_PARENT",schema = "STARCLOUDPORTAL" )
public class ParentInfo {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN_INFO_PARENT" )
    @SequenceGenerator( name = "SEQ_GEN_INFO_PARENT", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_INFO_PARENT" )
    @Column( name = "USER_ID" )
    private String userId;
    @Column( name = "FAMILY_ROLE" )
    private String familyRole;
    @Column( name = "REMARKS" )
    private String remarks;
    @Column( name = "IS_DELETE" )
    private Integer isDelete;

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    public String getFamilyRole() {
        return familyRole;
    }

    public void setFamilyRole( String familyRole ) {
        this.familyRole = familyRole;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks( String remarks ) {
        this.remarks = remarks;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Integer isDelete ) {
        this.isDelete = isDelete;
    }

    public ParentInfo() {

    }

    public ParentInfo( String familyRole, String remarks, Integer isDelete ) {

        this.familyRole = familyRole;
        this.remarks = remarks;
        this.isDelete = isDelete;
    }
}
