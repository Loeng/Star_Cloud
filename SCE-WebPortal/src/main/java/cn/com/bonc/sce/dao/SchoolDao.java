package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.School;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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
     * 添加school
     *
     * @param school 信息
     * @return 是否添加成功
     */
    @RequestMapping( value = "/schools", method = RequestMethod.POST )
    public RestRecord insertSchool(School school);

    /**
     * 获取学校
     *
     * @return 获取学校
     */
    @RequestMapping( value = "/schools", method = RequestMethod.GET )
    public RestRecord getAll(@RequestParam( "pageNum" ) Integer pageNum,
                             @RequestParam( "pageSize" ) Integer pageSize);

    @RequestMapping( value = "/schools", method = RequestMethod.POST )
    RestRecord saveSchool( Map map );


}
