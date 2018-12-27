package cn.com.bonc.sce.model;

/**
 * 新闻
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
public class News {
    private Integer id;
    private Integer columnId;
    private Integer contentType;
    private String contentTag;
    private String contentTitle;
    private String content;
    private String contentStatus;
    private String rejectOpinion;
    private String createUserId;
    private String updateUserId;
    private String remarks;
    private Integer pidId;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId( Integer columnId ) {
        this.columnId = columnId;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType( Integer contentType ) {
        this.contentType = contentType;
    }

    public String getContentTag() {
        return contentTag;
    }

    public void setContentTag( String contentTag ) {
        this.contentTag = contentTag;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle( String contentTitle ) {
        this.contentTitle = contentTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent( String content ) {
        this.content = content;
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

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId( String createUserId ) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId( String updateUserId ) {
        this.updateUserId = updateUserId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks( String remarks ) {
        this.remarks = remarks;
    }

    public Integer getPidId() {
        return pidId;
    }

    public void setPidId( Integer pidId ) {
        this.pidId = pidId;
    }

    public News() {

    }

    public News( Integer id, Integer columnId, Integer contentType, String contentTag, String contentTitle, String content, String contentStatus, String rejectOpinion, String createUserId, String updateUserId, String remarks, Integer pidId ) {

        this.id = id;
        this.columnId = columnId;
        this.contentType = contentType;
        this.contentTag = contentTag;
        this.contentTitle = contentTitle;
        this.content = content;
        this.contentStatus = contentStatus;
        this.rejectOpinion = rejectOpinion;
        this.createUserId = createUserId;
        this.updateUserId = updateUserId;
        this.remarks = remarks;
        this.pidId = pidId;
    }
}
