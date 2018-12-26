package cn.com.bonc.sce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import cn.com.bonc.sce.entity.AppManageFunView;
/**
 * yanmin
 * @author Administrator
 * 使用了视图
 * Views are used
 */
@Repository
public interface AppManageFunViewRepository extends JpaRepository< AppManageFunView, String >, JpaSpecificationExecutor<AppManageFunView> {

}
