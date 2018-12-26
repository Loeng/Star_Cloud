package cn.com.bonc.sce.model;

/**
 * banner
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
public class Banner {
    private Integer id;
    private Integer order;
    private String url;
    private String appId;
    private Integer isShown;
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
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

    public Integer getType() {
        return type;
    }

    public void setType( Integer type ) {
        this.type = type;
    }

    public Banner() {

    }

    public Banner( Integer id, Integer order, String url, String appId, Integer isShown, Integer type ) {

        this.id = id;
        this.order = order;
        this.url = url;
        this.appId = appId;
        this.isShown = isShown;
        this.type = type;
    }
}
