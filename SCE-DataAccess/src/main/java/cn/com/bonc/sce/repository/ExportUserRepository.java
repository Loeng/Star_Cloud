package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.FileResourceEntity;
import cn.com.bonc.sce.entity.StudentParentRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author BTW
 */
@Transactional(rollbackFor = Exception.class)
public interface ExportUserRepository extends JpaRepository< StudentParentRel, String > {

    /**
     * 保存学生家长关系
     * @param s
     * @param <S>
     * @return
     */
    @Override
    < S extends StudentParentRel > S save( S s );


}
