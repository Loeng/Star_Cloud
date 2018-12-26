package cn.com.bonc.sce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import cn.com.bonc.sce.entity.AppTopRankView;

/**
 * @author yanmin
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Repository
public interface AppTopRankRepository extends JpaRepository< AppTopRankView, String >, JpaSpecificationExecutor<AppTopRankView>  {
}
