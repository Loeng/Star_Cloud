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
    private String authorityName;
    private String schoolAddress;
    private String telephone;
    private Integer institutionId;
    private String grade;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName( String schoolName ) {
        this.schoolName = schoolName;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName( String authorityName ) {
        this.authorityName = authorityName;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress( String schoolAddress ) {
        this.schoolAddress = schoolAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone( String telephone ) {
        this.telephone = telephone;
    }

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId( Integer institutionId ) {
        this.institutionId = institutionId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade( String grade ) {
        this.grade = grade;
    }
}
