package cn.com.bonc.sce.bean;

/**
 * Created by Charles on 2019/2/27.
 *
 * 导航栏bean
 */
public class NavigationBean {
    private Integer columnId;
    private Integer parentColumnId;
    private Integer channelId;
    private String columnName;
    private String columnStatus;
    private String remarks;
    private Integer isDeleted;
    private String columnUrl;

    public String getColumnUrl() {
        return columnUrl;
    }

    public void setColumnUrl(String columnUrl) {
        this.columnUrl = columnUrl;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public Integer getParentColumnId() {
        return parentColumnId;
    }

    public void setParentColumnId(Integer parentColumnId) {
        this.parentColumnId = parentColumnId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnStatus() {
        return columnStatus;
    }

    public void setColumnStatus(String columnStatus) {
        this.columnStatus = columnStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
