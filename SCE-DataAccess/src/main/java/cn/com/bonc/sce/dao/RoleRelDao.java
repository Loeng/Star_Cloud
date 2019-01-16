package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.RoleRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色信息对应
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Repository
public interface RoleRelDao extends JpaRepository< RoleRel, Integer > {
    @Override
    RoleRel save( RoleRel user );

    @Query(value="SELECT B.INFO_TABLE_NAME FROM STARCLOUDPORTAL.SCE_COMMON_USER_ROLE_REL A\n" +
            "LEFT JOIN STARCLOUDPORTAL.SCE_ROLE_INFOTABLE_REL B ON A.ROLE_ID=B.ROLE_ID\n" +
            "WHERE A.USER_ID=?1",nativeQuery=true)
    List<String> getRoleTable( String userId);

    @Query(value="SELECT * FROM STARCLOUDPORTAL.SCE_COMMON_USER_ROLE_REL A\n" +
            "LEFT JOIN STARCLOUDPORTAL.SCE_COMMON_USER B ON A.USER_ID=B.USER_ID\n" +
            "WHERE ROLE_ID=8",nativeQuery=true)
    List<Map<String,Object> > getUnExamine();

    @Modifying
    @Query( "UPDATE RoleRel r SET r.roleId=2 WHERE r.userId IN (?1)" )
    Integer updateParentStatus( List<String> list );
}