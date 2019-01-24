package cn.com.bonc.sce.model;

/**
 * 学校
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
public class School {
    private String schoolName;
    private Integer authorityName;
    private Integer schoolAddress;
    private Integer telephone;
    private Integer institutionId;
    private Integer grade;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName( String schoolName ) {
        this.schoolName = schoolName;
    }

    public Integer getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName( Integer authorityName ) {
        this.authorityName = authorityName;
    }

    public Integer getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress( Integer schoolAddress ) {
        this.schoolAddress = schoolAddress;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone( Integer telephone ) {
        this.telephone = telephone;
    }

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId( Integer institutionId ) {
        this.institutionId = institutionId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade( Integer grade ) {
        this.grade = grade;
    }
}
