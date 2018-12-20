package cn.com.bonc.sce.model.appListAndClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 应用分类
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="SCE_MARKET_APP_TYPE")
public class AppClass {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN_MARKET_APP_TYPE")
    @SequenceGenerator(name="SEQ_GEN_MARKET_APP_TYPE",allocationSize=1,initialValue=1, sequenceName="SEQ_MARKET_APP_TYPE")
    @Column(name = "APP_TYPE_ID")
    private Integer id;

    @Column(name = "APP_TYPE_NAME")
    private String typeName;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "IS_DELETE")
    private Integer isDelete;
}
