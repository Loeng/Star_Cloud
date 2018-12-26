package cn.com.bonc.sce.entity;

import javax.persistence.*;


/**
 * 学生家长对应表
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Entity
@Table( name = "SCE_INFO_STUDENT_PARENT_REL",schema = "STARCLOUDPORTAL" )
public class StudentParentRel {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN_INFO_STUDENT_PAREBNT_REL" )
    @SequenceGenerator( name = "SEQ_GEN_INFO_STUDENT_PAREBNT_REL", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_INFO_STUDENT_PAREBNT_REL" )
    @Column( name = "ID" )
    private Integer id;
    @Column( name = "PARENT_USER_ID" )
    private String parentUserId;
    @Column( name = "STUDENT_USER_ID" )
    private String studentUserId;
    @Column( name = "IS_MAIN" )
    private Integer isMain;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId( String parentUserId ) {
        this.parentUserId = parentUserId;
    }

    public String getStudentUserId() {
        return studentUserId;
    }

    public void setStudentUserId( String studentUserId ) {
        this.studentUserId = studentUserId;
    }

    public Integer getIsMain() {
        return isMain;
    }

    public void setIsMain( Integer isMain ) {
        this.isMain = isMain;
    }

    public StudentParentRel() {

    }
}
