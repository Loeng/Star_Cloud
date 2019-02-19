package cn.com.bonc.sce.bean;

/**
 * @author BTW
 */
public class AppTypeBean {

    private Integer appTypeId;

    private String appTypeName;

    private String remarks;

    private Long isDelete;

    public Integer getAppTypeId() {
        return appTypeId;
    }

    public void setAppTypeId( Integer appTypeId ) {
        this.appTypeId = appTypeId;
    }

    public String getAppTypeName() {
        return appTypeName;
    }

    public void setAppTypeName( String appTypeName ) {
        this.appTypeName = appTypeName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks( String remarks ) {
        this.remarks = remarks;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Long isDelete ) {
        this.isDelete = isDelete;
    }
}
