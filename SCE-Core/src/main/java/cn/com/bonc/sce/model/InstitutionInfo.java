package cn.com.bonc.sce.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2019/4/14.
 */
@Data
public class InstitutionInfo {

    private String userId;
    private String institutionId;
    private String telephone;
    private String address;
    private String remarks;
    private String isDelete;
    private String workTime;
    private String entryTime;
    private String jobProfession;
    private String workNumber;
    
}
