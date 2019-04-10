package cn.com.bonc.sce.model;

import java.util.Date;

/**
 * @author BTW
 */
public class NewsRecordModel {

    private Long id;
    private String userId;
    private Long newsId;
    private Date recordTime;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId( Long newsId ) {
        this.newsId = newsId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime( Date recordTime ) {
        this.recordTime = recordTime;
    }
}
