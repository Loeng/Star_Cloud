package cn.com.bonc.sce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.com.bonc.sce.entity.App;

/**
 * 产品信息
 *
 * @author yanmin
 * @version 0.0.1-a
 * @since 2018/12/25 15:00
 */
@Repository
public interface AppManageDao extends JpaRepository<App, Integer> {
	
	
	
}
