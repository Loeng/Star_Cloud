package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.ColumnInfo;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

/**
 * 栏目接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/26 15:00
 */
@Repository
@FeignClient( "sce-data-access" )
public interface ColumnInfoDao {
    /**
     * 添加栏目
     *
     * @param columnInfo 添加栏目
     * @return 是否添加成功
     */
    @RequestMapping( value = "/columns", method = RequestMethod.POST )
    public RestRecord insert( ColumnInfo columnInfo );

    /**
     * 通过id删除
     *
     * @param id id
     * @return 删除是否成功
     */
    @RequestMapping( value = "/columns/{id}", method = RequestMethod.DELETE )
    public RestRecord deleteById( @PathVariable( "id" ) String id );

    /**
     * 更新栏目
     *
     * @param columnInfo 栏目
     * @return columnInfo
     */
    @RequestMapping( value = "/columns", method = RequestMethod.PUT )
    public RestRecord updateInfo( ColumnInfo columnInfo ) ;

    /**
     * 获取所有栏目数据
     *
     * @return 栏目数据list
     */
    @RequestMapping( value = "/columns", method = RequestMethod.GET )
    public RestRecord getAll();
}
