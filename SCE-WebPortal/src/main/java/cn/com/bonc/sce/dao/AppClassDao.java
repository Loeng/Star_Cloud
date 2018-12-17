package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.AppClass;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
     * @param appClassId id
     * @return 删除是否成功
     */
    @RequestMapping( value = "/appClass/{appClassId}", method = RequestMethod.DELETE )
    public RestRecord deleteAppClassById( @PathVariable( "appClassId" )Integer appClassId );

    /**
     * 更新应用分类
     *
     * @param appClass 应用分类信息
     * @return appClass
     */
    @RequestMapping( value = "/appClass/{appClassId}", method = RequestMethod.PUT )
    public RestRecord updateAppClassInfo( AppClass appClass );

    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    @RequestMapping( value = "/appClass/{appClassId}", method = RequestMethod.GET )
    public RestRecord getAllAppClassInfo();
}
