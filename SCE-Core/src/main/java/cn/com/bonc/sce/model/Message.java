package cn.com.bonc.sce.model;

/**
 * 总消息
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
public class Message {
    private Integer id;
    private String content;
    private Integer type;
    private Integer topicType;
    private String targetId;
    private String sourceId;
    //private String sourceAccount;
    private String status;
    private String createTime;
    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Integer isDelete ) {
        this.isDelete = isDelete;
    }

    public Message() {

    }

    public Message( String content, Integer type, Integer topicType, String targetId, String sourceId, String status, String createTime, Integer isDelete ) {

        this.content = content;
        this.type = type;
        this.topicType = topicType;
        this.targetId = targetId;
        this.sourceId = sourceId;
        this.status = status;
        this.createTime = createTime;
        this.isDelete = isDelete;
    }
}
