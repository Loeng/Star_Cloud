package cn.com.bonc.sce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author BTW
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "SCE_FILE_RESOURCE" ,schema = "STARCLOUDPORTAL" )
public class FileResourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "RESOURCE_ID" )
    private Integer resourceId;

    @Column( name = "FILE_STORE_PATH" )
    private String fileStorePath;

    @Column( name = "FILE_MAPPING_PATH" )
    private String fileMappingPath;

    @Column( name = "FILE_TYPE" )
    private String fileType;

    @Column( name = "UPDATE_TIME" )
    private Timestamp updateTime;

    @Column( name = "IS_DELETE" )
    private Long isDelete = 1L;

}
