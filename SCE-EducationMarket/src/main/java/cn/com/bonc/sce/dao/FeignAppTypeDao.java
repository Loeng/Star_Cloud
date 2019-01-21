package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AppTypeDao的Feign客户端
 * @author BTW
 * @version 0.1
 * @since 2018/12/14 14:44
 */
@FeignClient( "sce-data-access" )
public interface FeignAppTypeDao {

    /**
     * 获取全部类型名称列表
     * @return
     */
    @RequestMapping( value = "/app-type/all", method = RequestMethod.GET )
    RestRecord getAllAppTypeList();

    /**
     * 新增应用类型分类
     * @param appTypeName
     * @return
     */
    @RequestMapping( value = "/app-type/", method = RequestMethod.POST )
    RestRecord addAppTypeInfo( @RequestParam( "appTypeName" ) String appTypeName );

    /**
     * 修改应用分类名称
     * @param appTypeId
     * @param appTypeName
     * @return
     */
    @RequestMapping( value = "/app-type/new-type-info", method = RequestMethod.POST )
    RestRecord updateAppTypeInfo( @RequestParam( "appTypeId" ) Integer appTypeId,
                                  @RequestParam( "appTypeName" ) String appTypeName );

    /**
     * 删除应用分类
     * @param appTypeId
     * @return
     */
    @RequestMapping( value = "/app-type/{appTypeId}", method = RequestMethod.DELETE )
    RestRecord deleteAppTypeInfo( @PathVariable( "appTypeId" ) Integer appTypeId );
}
