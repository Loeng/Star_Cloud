package cn.com.bonc.sce.bean;

/**
 * @author BTW
 */
public class AdvertisementBean {

    private Long id;
    private String adUrl;
    private String adName;
    private Integer adPosition;
    private Integer adOrder;
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl( String adUrl ) {
        this.adUrl = adUrl;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName( String adName ) {
        this.adName = adName;
    }

    public Integer getAdPosition() {
        return adPosition;
    }

    public void setAdPosition( Integer adPosition ) {
        this.adPosition = adPosition;
    }

    public Integer getAdOrder() {
        return adOrder;
    }

    public void setAdOrder( Integer adOrder ) {
        this.adOrder = adOrder;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Integer isDelete ) {
        this.isDelete = isDelete;
    }
}
