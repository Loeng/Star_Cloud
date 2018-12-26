package cn.com.bonc.sce.model;

public class ParentsInfo {
    private String account;
    private String password;
    private String parentName;
    private String parentPhone;
    /**
     * 验证使用
     */
    private String parentPhoneValid;
    private String parentNum;
    private String relationship;
    private String studentAccount;
    private String mainParentPhone;
    /**
     * 验证使用
     */
    private String mainParentPhoneValid;

    public String getAccount() {
        return account;
    }

    public void setAccount( String account ) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName( String parentName ) {
        this.parentName = parentName;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone( String parentPhone ) {
        this.parentPhone = parentPhone;
    }

    public String getParentPhoneValid() {
        return parentPhoneValid;
    }

    public void setParentPhoneValid( String parentPhoneValid ) {
        this.parentPhoneValid = parentPhoneValid;
    }

    public String getParentNum() {
        return parentNum;
    }

    public void setParentNum( String parentNum ) {
        this.parentNum = parentNum;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship( String relationship ) {
        this.relationship = relationship;
    }

    public String getStudentAccount() {
        return studentAccount;
    }

    public void setStudentAccount( String studentAccount ) {
        this.studentAccount = studentAccount;
    }

    public String getMainParentPhone() {
        return mainParentPhone;
    }

    public void setMainParentPhone( String mainParentPhone ) {
        this.mainParentPhone = mainParentPhone;
    }

    public String getMainParentPhoneValid() {
        return mainParentPhoneValid;
    }

    public void setMainParentPhoneValid( String mainParentPhoneValid ) {
        this.mainParentPhoneValid = mainParentPhoneValid;
    }

    public ParentsInfo() {

    }

    public ParentsInfo( String account, String password, String parentName, String parentPhone, String parentPhoneValid, String parentNum, String relationship, String studentAccount, String mainParentPhone, String mainParentPhoneValid ) {

        this.account = account;
        this.password = password;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.parentPhoneValid = parentPhoneValid;
        this.parentNum = parentNum;
        this.relationship = relationship;
        this.studentAccount = studentAccount;
        this.mainParentPhone = mainParentPhone;
        this.mainParentPhoneValid = mainParentPhoneValid;
    }
}

