package cn.com.bonc.sce.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table( name = "SCE_MARKET_APP_TYPE" ,schema = "STARCLOUDMARKET" )
public class AppTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "APP_TYPE_ID" )
    private Integer appTypeId;
    @JsonIgnoreProperties(value = { "appTypes" })
    @ManyToMany(mappedBy = "appTypes")
    private Set< AppInfoEntity > appInfo = new HashSet<>();

    @Column( name = "APP_TYPE_NAME" )
    private String appTypeName;

    @Column( name = "REMARKS" )
    private String remarks;

    @Column( name = "IS_DELETE" )
    private Long isDelete = 1L;

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
