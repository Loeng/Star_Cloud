package cn.com.bonc.sce.model;

public class Banner {
    private String id;
    private Integer order;
    private String url;
    private String appId;
    private Integer isShown;
    private String type;
    private Integer isDelete;

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder( Integer order ) {
        this.order = order;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId( String appId ) {
        this.appId = appId;
    }

    public Integer getIsShown() {
        return isShown;
    }

    public void setIsShown( Integer isShown ) {
        this.isShown = isShown;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Integer isDelete ) {
        this.isDelete = isDelete;
    }
}
