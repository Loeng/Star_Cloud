package cn.com.bonc.sce.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 联合主键类
 * @author BTW
 */
@Data
public class UserAppPrimaryKey implements Serializable{

    private String appId;

    private String userId;
}
