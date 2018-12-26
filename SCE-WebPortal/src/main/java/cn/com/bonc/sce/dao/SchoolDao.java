package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.Notification;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 学校
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Repository
@FeignClient( "sce-data-access" )
public interface SchoolDao {
    /**
     * 获取学校
     *
     * @return 获取学校
     */
    @RequestMapping( value = "/schools", method = RequestMethod.GET )
    public RestRecord getAll();
}
