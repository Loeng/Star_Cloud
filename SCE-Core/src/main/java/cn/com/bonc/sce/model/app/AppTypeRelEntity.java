package cn.com.bonc.sce.model.app;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 应用分类关系表（中间表）
 */
@Data
@Entity
@Table( name = "sce_market_app_apptype_rel" )
public class AppTypeRelEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column( name = "ID" )
    private Long id;

    @Column( name = "APP_ID" )
    private Long productTypeId;

    @Column( name = "APP_TYPE_ID" )
    private Long productTypeName;


}
