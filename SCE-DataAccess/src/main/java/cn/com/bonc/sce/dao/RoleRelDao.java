package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.RoleRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}