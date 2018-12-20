package cn.com.bonc.sce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 应用分类表
 *
 * @author jc_D
 * @description
 * @date 2018/12/18
 **/
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "STARCLOUDMARKET.SCE_MARKET_APP_TYPE" )
public class AppTypeEntity {
    @Id
    @GeneratedValue
    @Column( name = "APP_TYPE_ID" )
    private String appTypeId;
    @ManyToMany(mappedBy = "appTypes")
    private Set< AppInfoEntity > appInfo = new HashSet<>();

    @Column( name = "APP_TYPE_NAME" )
    private String appTypeName;

    @Column( name = "REMARKS" )
    private String remarks;

    @Column( name = "IS_DELETE" )
    private Long isDelete;

//    @Override
//    public String toString() {
//        return "AppTypeEntity{" +
//                "appTypeId=" + appTypeId +
//                ", appInfo=" + appInfo +
//                ", appTypeName='" + appTypeName + '\'' +
//                ", remarks='" + remarks + '\'' +
//                ", isDelete=" + isDelete +
//                '}';
//    }
}
