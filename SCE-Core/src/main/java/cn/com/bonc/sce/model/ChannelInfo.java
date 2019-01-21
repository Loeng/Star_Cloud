package cn.com.bonc.sce.model;

public class ChannelInfo {
    private Integer id;
    private Integer pid;
    private String name;
    private String status;
    private String remarks;
    private Integer isDelete;
    private String picUrl;

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

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl( String picUrl ) {
        this.picUrl = picUrl;
    }

    public ChannelInfo() {

    }

    public ChannelInfo( Integer id, Integer pid, String name, String status, String remarks, Integer isDelete, String picUrl ) {

        this.id = id;
        this.pid = pid;
        this.name = name;
        this.status = status;
        this.remarks = remarks;
        this.isDelete = isDelete;
        this.picUrl = picUrl;
    }
}
