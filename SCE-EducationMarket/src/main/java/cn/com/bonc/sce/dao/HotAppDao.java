package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用推荐-热门应用接口api
 * @author jc_D
 *
 * @version 2.0
 * @update 修改字段命名，将错写的字段名称改为驼峰式，去掉 getter setter
 * @updateFrom 2018/12/26 11:20
 * @updateAuthor wzm
 */
@FeignClient( "sce-data-access" )
public interface HotAppDao {

    /**
     * 添加热门应用
     *
     * @param appIdList app的id用","分割
     * @param userId
     * @return
     */
    @RequestMapping( value = "/hot-app/{userId}", method = RequestMethod.POST )
    RestRecord addHotRecommendAppList( @RequestBody List< String > appIdList,
                                       @PathVariable( "userId" ) String userId );

    /**
     * 查询所有热门应用
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return
     */
    @RequestMapping( value = "/hot-app/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord selectHotRecommendAppList( @PathVariable( "pageNum" ) Integer pageNum,
                                          @PathVariable( "pageSize" ) Integer pageSize );

    /**
     * 查询所有热门应用列表
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @param userId
     * @return
     */
    @RequestMapping( value = "/hot-app/detail/{pageNum}/{pageSize}/{userId}", method = RequestMethod.GET )
    RestRecord selectHotAppList( @PathVariable( "pageNum" ) Integer pageNum,
                                 @PathVariable( "pageSize" ) Integer pageSize,
                                 @PathVariable( "userId" ) String userId  );


    /**
     * 添加热门应用
     *
     * @param appId app的id
     * @param userId
     * @return
     */
    @RequestMapping( value = "/hot-app/new-one", method = RequestMethod.POST )
    RestRecord addHotRecommendApp( @RequestParam( "userId" ) String userId,
                                   @RequestParam( "appId" ) String appId );

    /**
     * 删除热门应用
     *
     * @param appId app的id
     * @param userId
     * @return
     */
    @RequestMapping( value = "/hot-app/sub-one", method = RequestMethod.POST )
    RestRecord cancelHotRecommendApp( @RequestParam( "userId" ) String userId,
                                      @RequestParam( "appId" ) String appId );

}
