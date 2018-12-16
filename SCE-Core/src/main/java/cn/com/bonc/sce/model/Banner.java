package cn.com.bonc.sce.model;

public class Banner {
    private String id;
    private Integer order;
    private String url;
    private String appId;
    private boolean isShown;
    private String type;
    private boolean isDelete;

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

    public boolean isShown() {
        return isShown;
    }

    public void setShown( boolean shown ) {
        isShown = shown;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete( boolean delete ) {
        isDelete = delete;
    }
}
