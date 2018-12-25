package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.FileResourceEntity;
import org.mapstruct.ObjectFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author BTW
 */
@Transactional(rollbackFor = Exception.class)
public interface FileResourceRepository extends JpaRepository< FileResourceEntity, String > {

    /**
     * 文件存储信息保存
     * @param s
     * @param <S>
     * @return
     */
    @Override
    < S extends FileResourceEntity > S save( S s );
}
