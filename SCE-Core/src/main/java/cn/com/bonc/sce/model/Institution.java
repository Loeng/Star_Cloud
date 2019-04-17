package cn.com.bonc.sce.model;

import lombok.Data;

/**
 * Created by Administrator on 2019/4/15.
 */
@Data
public class Institution {

    private Long id;
    private String institutionName; //机构名（教育局名称）
    private Integer isDelete;
    private Integer pid; //父ID
    private String district; //区
    private String city; //市
    private String province; //省
    private String county; //县
    private String address; //地址
    private String telephone; //联系电话
    private String userId;
    private String postcode; //邮编
    private String institutionCode; //组织机构码
    private String email; //电子邮箱
    private String homepage; //主页
    private String parentInstitution; //所属主管单位
}
