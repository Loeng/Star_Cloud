package cn.com.bonc.sce.model;

/**
 * @author BTW
 */
public class NewsStatusModel {

    private Long contentId;
    private String contentStatus;
    private String rejectOpinion;
    private Integer isPublish;
    private Integer isTop;
    private Integer topOrder;

    public Long getContentId() {
        return contentId;
    }

    public void setContentId( Long contentId ) {
        this.contentId = contentId;
    }

    public String getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus( String contentStatus ) {
        this.contentStatus = contentStatus;
    }

    public String getRejectOpinion() {
        return rejectOpinion;
    }

    public void setRejectOpinion( String rejectOpinion ) {
        this.rejectOpinion = rejectOpinion;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish( Integer isPublish ) {
        this.isPublish = isPublish;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop( Integer isTop ) {
        this.isTop = isTop;
    }

    public Integer getTopOrder() {
        return topOrder;
    }

    public void setTopOrder( Integer topOrder ) {
        this.topOrder = topOrder;
    }
}
