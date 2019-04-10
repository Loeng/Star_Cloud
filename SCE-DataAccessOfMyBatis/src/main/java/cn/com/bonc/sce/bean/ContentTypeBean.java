package cn.com.bonc.sce.bean;

/**
 * @author BTW
 */
public class ContentTypeBean {

    private Integer id;
    private String contentType;
    private Long isDelete;
    private Integer showOrder;
    private Integer isShown;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType( String contentType ) {
        this.contentType = contentType;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Long isDelete ) {
        this.isDelete = isDelete;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder( Integer showOrder ) {
        this.showOrder = showOrder;
    }

    public Integer getIsShown() {
        return isShown;
    }

    public void setIsShown( Integer isShown ) {
        this.isShown = isShown;
    }
}
