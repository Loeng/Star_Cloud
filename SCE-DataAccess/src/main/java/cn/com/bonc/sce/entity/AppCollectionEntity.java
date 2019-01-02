package cn.com.bonc.sce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author BTW
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@IdClass( UserAppPrimaryKey.class )
@Table( name = "SCE_USER_APP_COLLECTION", schema = "STARCLOUDMARKET" )
public class AppCollectionEntity implements Serializable{

    @Column( name = " ID " )
    private Integer id;

    @Id
    @Column ( name = " USER_ID " )
    private String userId;

    @Id
    @Column ( name = "APP_ID" )
    private String appId;

    @Column ( name = "IS_DELETE" )
    private Long isDelete = 1L;
}
