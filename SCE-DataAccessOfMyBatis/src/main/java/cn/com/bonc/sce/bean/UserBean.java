package cn.com.bonc.sce.bean;

import cn.com.bonc.sce.model.Account;
import cn.com.bonc.sce.model.Secret;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * Created by Charles on 2019/3/6.
 */
@Data
public class UserBean {
    private long userId;
    //SCE_COMMON_USER 表中的 USER_ID 是varchar2类型，用long类型的userId执行插入会报错（无效的数字类型）
    private String stringUserId;
    private String loginName;
    private String userName;
    private String gender;
    private Integer userType;
    private String mailAddress;
    private String certificateType;
    private String certificateNumber;
    private String phoneNumber;
    private String address;
    private Date createTime;
    private String loginTime;
    private String lastLoginTime;
    private Long loginCounts;
    private Long organizationId;
    private Integer loginPermissionStatus;
    private String remarks;
    private Integer isDelete;
    private Account account;
    private String secret;
    private Secret secretKeyPair;
    private Integer isFirstLogin=0;
    private String headPortrait;
    private String birthday;
    private Map<String,String> userDetailedInfo;
    private Integer accountStatus;
    private Integer isAdministrators;
    private String nationality;
    private String volk;
    private String educationalBackground;
    private Integer loginTimes;
}
