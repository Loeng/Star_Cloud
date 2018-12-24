package cn.com.bonc.sce.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户消息
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Entity
@Table(name="SCE_COMMON_USER_INFO",schema = "STARCLOUDPORTAL")
public class UserMessage {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN_COMMON_USER_INFO")
    @SequenceGenerator(name="SEQ_GEN_COMMON_USER_INFO",allocationSize=1,initialValue=1, sequenceName="SEQ_COMMON_USER_INFO")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "IS_READ")
    private Integer isRead;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "INFO_ID")
    private Integer messageId;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "IS_DELETE")
    private Integer isDelete;

    @ManyToOne
    @JoinColumn(name="ID", referencedColumnName="INFORMATION_ID", insertable=false, updatable=false)
    private Message message;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead( Integer isRead ) {
        this.isRead = isRead;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId( Integer messageId ) {
        this.messageId = messageId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime( Date createTime ) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Integer isDelete ) {
        this.isDelete = isDelete;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage( Message message ) {
        this.message = message;
    }

    public UserMessage() {

    }

    public UserMessage( Integer isRead, String userId, Integer messageId, Date createTime, Integer isDelete, Message message ) {
        this.isRead = isRead;
        this.userId = userId;
        this.messageId = messageId;
        this.createTime = createTime;
        this.isDelete = isDelete;
        this.message = message;
    }
}
