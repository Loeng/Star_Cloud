package cn.com.bonc.sce.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="SCE_FILE_RESOURCE",schema = "STARCLOUDPORTAL")
public class Pic {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SEQ_GEN_FILE_RESOURCE")
    @SequenceGenerator(name="SEQ_GEN_FILE_RESOURCE",allocationSize=1,initialValue=1, sequenceName="SEQ_FILE_RESOURCE")
    @Column(name = "RESOURCE_ID")
    private Integer id;

    @Column(name = "FILE_STORE_PATH")
    private String fileStorePath;

    @Column(name = "FILE_MAPPING_PATH")
    private String fileMappingPath;

    @Column(name = "FILE_TYPE")
    private String fileType;

    @Column(name = "IS_DELETE")
    private Integer isDelete;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;
}