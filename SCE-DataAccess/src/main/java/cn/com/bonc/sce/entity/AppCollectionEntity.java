package cn.com.bonc.sce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author BTW
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "SCE_USER_APP_COLLECTION", schema = "STARCLOUDMARKET" )
public class AppCollectionEntity {

    @Id
    @Column ( name = " USER_ID " )
    private String userId;

    @Column ( name = "APP_ID" )
    private String appId;

    @Column ( name = "IS_DELETE" )
    private Long isDelete = 1L;
}
