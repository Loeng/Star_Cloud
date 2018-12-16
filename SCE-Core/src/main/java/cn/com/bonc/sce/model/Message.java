package cn.com.bonc.sce.model;

public class Message {
    private String id;
    private String content;
    private Integer type;
    private Integer topicType;
    private String targetId;
    private String sourceId;
    private String sourceAccount;
    private String status;
    private String createTime;
    private boolean isDelete;
    private boolean isRead;

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent( String content ) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType( Integer type ) {
        this.type = type;
    }

    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType( Integer topicType ) {
        this.topicType = topicType;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId( String targetId ) {
        this.targetId = targetId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId( String sourceId ) {
        this.sourceId = sourceId;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount( String sourceAccount ) {
        this.sourceAccount = sourceAccount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime( String createTime ) {
        this.createTime = createTime;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete( boolean delete ) {
        isDelete = delete;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead( boolean read ) {
        isRead = read;
    }
}
