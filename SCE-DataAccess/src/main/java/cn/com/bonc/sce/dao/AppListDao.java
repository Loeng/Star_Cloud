package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 应用列表
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Repository
public interface AppListDao extends JpaRepository<App, Integer> {
    List<App> findByIsDelete( Integer isDelete );

    List<App> findByCategoryIdAndIsDelete( Integer appClassId, Integer isDelete );

    List<App> findByAppNameLikeAndIsDelete( String appName, Integer isDelete );

    List<App> findByCategoryIdAndAppNameLikeAndIsDelete( Integer appClassId, String appName, Integer isDelete );
}
