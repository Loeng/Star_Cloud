package cn.com.bonc.sce.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;

/**
 * Created by Charles on 2019/3/28.
 */
@Repository
@FeignClient( value = "sce-data-mybatis" )
public interface StudentDao {
}
