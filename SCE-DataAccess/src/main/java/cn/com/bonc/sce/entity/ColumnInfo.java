package cn.com.bonc.sce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * banner
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="SCE_COMMON_COLUMN_INFO",schema = "STARCLOUDPORTAL")
public class ColumnInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN_COMMON_COLUMN_INFO")
    @SequenceGenerator(name="SEQ_GEN_COMMON_COLUMN_INFO",allocationSize=1,initialValue=1, sequenceName="SEQ_COMMON_COLUMN_INFO")
    @Column(name = "COLUMN_ID")
    private Integer id;

    @Column(name = "PARENT_COLUMN_ID")
    private Integer pid;

    @Column(name = "CHANNEL_ID")
    private Integer channelId;

    @Column(name = "COLUMN_NAME")
    private String name;

    @Column(name = "COLUMN_STATUS")
    private Integer status;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "IS_DELETE")
    private Integer isDelete;
}
