package cn.com.bonc.sce.model;

import lombok.Data;

import java.util.List;

/**
 * 软件上传参数
 *
 * @author jc_D
 * @description
 * @date 2018/12/26
 **/
@Data
public class AppAddModel {
    private String appName;
    private Long appTypeId;
    private String appVersion;
    private String pckageName;
    private String versionInfo;
    private String newFeatures;
    private String versionSize;
    private String appIcon;
    private String appPcPic;
    private String authDetail;
    private List< AppTypeMode > pc;
    private List< AppTypeMode > phone;


}
