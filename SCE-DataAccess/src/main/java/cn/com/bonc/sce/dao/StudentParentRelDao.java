package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.StudentParentRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学生家长对应
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Repository
@Transactional
public interface StudentParentRelDao extends JpaRepository< StudentParentRel, Integer > {
    @Override
    StudentParentRel save( StudentParentRel user );
}