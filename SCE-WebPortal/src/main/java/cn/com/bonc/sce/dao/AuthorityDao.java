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
 * 机构
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Repository
@FeignClient( "sce-data-access" )
public interface AuthorityDao {
    /**
     * 获取机构
     *
     * @return 获取机构
     */
    @RequestMapping( value = "/authoritys", method = RequestMethod.GET )
    public RestRecord getAll(@RequestParam( "pageNum" ) Integer pageNum,
                             @RequestParam( "pageSize" ) Integer pageSize);
}
