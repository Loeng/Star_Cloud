package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.appListAndClass.AppClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 应用分类
 *
 * @author wzm
 * @version 0.1
 * @since 2018/17/12 12:00
 */
@Repository
@Transactional
public interface AppClassDao extends JpaRepository<AppClass, Integer> {

    AppClass save( AppClass appClass );

    @Modifying
    @Query( "UPDATE AppClass a SET a.isDelete=1 WHERE a.id=?1" )
    Integer updateDeleteStatusById( Integer id );

    List<AppClass> findByIsDelete(Integer isDelete);
}
