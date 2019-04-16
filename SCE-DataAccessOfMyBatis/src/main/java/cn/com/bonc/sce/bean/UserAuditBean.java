package cn.com.bonc.sce.bean;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2019/4/15.
 */
@Data
public class UserAuditBean {
    private String id;
    private String userId;
    private Integer userType;
    private String entityId;
    private Integer auditStatus;
    private Date auditTime;
    private String auditUserId;
    private String rejectOpinion;
}
