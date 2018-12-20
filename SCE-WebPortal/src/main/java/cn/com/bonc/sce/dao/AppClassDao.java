package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.appListAndClass.AppClass;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 应用分类
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Repository
@FeignClient( "sce-data-access" )
public interface AppClassDao {
    /**
     * 添加应用分类
     *
     * @param appClass 信息
     * @return 是否添加成功
     */
    @RequestMapping( value = "/appClass", method = RequestMethod.POST )
    public RestRecord insertAppClass( AppClass appClass );

    /**
     * 通过id删除应用分类
     *
     * @param classId id
     * @return 删除是否成功
     */
    @RequestMapping( value = "/appClass/{classId}", method = RequestMethod.DELETE )
    public RestRecord deleteAppClassById( @PathVariable( "classId" )Integer classId );

    /**
     * 更新应用分类
     *
     * @param classId 应用分类信息
     * @return appClass
     */
    @RequestMapping( value = "/appClass/{classId}", method = RequestMethod.PUT )
    public RestRecord updateAppClassInfo( AppClass classId );

    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    @RequestMapping( value = "/appClass", method = RequestMethod.GET )
    public RestRecord getAllAppClassInfo();
}
