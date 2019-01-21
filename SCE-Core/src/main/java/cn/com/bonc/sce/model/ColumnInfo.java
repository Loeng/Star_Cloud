package cn.com.bonc.sce.model;

public class ColumnInfo {
    private Integer id;
    private Integer pid;
    private Integer channelId;
    private String name;
    private Integer status;
    private String remarks;
    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid( Integer pid ) {
        this.pid = pid;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId( Integer channelId ) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus( Integer status ) {
        this.status = status;
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

    public ColumnInfo() {

    }

    public ColumnInfo( Integer id, Integer pid, Integer channelId, String name, Integer status, String remarks, Integer isDelete ) {

        this.id = id;
        this.pid = pid;
        this.channelId = channelId;
        this.name = name;
        this.status = status;
        this.remarks = remarks;
        this.isDelete = isDelete;
    }
}
