package cn.com.bonc.sce.model;

/**
 * @author BTW
 */
public class NewsStatusModel {

    private Long contentId;
    private String contentStatus;
    private String rejectOpinion;
    private Integer isPublish;

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
}
