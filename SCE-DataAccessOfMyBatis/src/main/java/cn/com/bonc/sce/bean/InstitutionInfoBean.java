package cn.com.bonc.sce.bean;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2019/4/14.
 */
@Data
public class InstitutionInfoBean {

    private String userId;
    private String institutionId;
    private String telephone;
    private String address;
    private String remarks;
    private String isDelete;
    private Date workTime;
    private Date entryTime;
    private String jobProfession;
    private String workNumber;

}
