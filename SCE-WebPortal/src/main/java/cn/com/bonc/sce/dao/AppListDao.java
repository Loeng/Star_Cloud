package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 应用列表
 *
 * @author wzm
 * @version 0.1
 * @since 2018/17/12 12:00
 */
@Repository
@FeignClient( "sce-data-access" )
public interface AppListDao {
    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    @RequestMapping( value = "/appList/{classId}/{keyword}", method = RequestMethod.GET )
    public RestRecord getAppListInfo( @PathVariable( "classId" ) Integer classId,
                                      @PathVariable( "keyword" ) String keyword );
    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    @RequestMapping( value = "/appList/classId/{classId}", method = RequestMethod.GET )
    public RestRecord getAppListInfo( @PathVariable( "classId" ) Integer classId );
    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    @RequestMapping( value = "/appList/keyword/{keyword}", method = RequestMethod.GET )
    public RestRecord getAppListInfo( @PathVariable( "keyword" ) String keyword );
    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    @RequestMapping( value = "/appList", method = RequestMethod.GET )
    public RestRecord getAppListInfo();
}
