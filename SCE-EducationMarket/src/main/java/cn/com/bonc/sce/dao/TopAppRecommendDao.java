package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient( "sce-data-access" )
public interface TopAppRecommendDao {

    /**
     * 添加重点推荐应用
     *
     * @param appIdList app的id用","分割
     * @param userId
     * @return
     */
    @RequestMapping( value = "/top-app/{userId}", method = RequestMethod.POST )
    RestRecord addTopRecommendAppList( @RequestBody List< String > appIdList,
                                       @PathVariable( "userId" ) String userId );

    /**
     * 查询所有重点推荐应用
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return
     */
    @RequestMapping( value = "/top-app/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord selectTopRecommendAppList( @PathVariable( "pageNum" ) Integer pageNum,
                                          @PathVariable( "pageSize" ) Integer pageSize );

    /**
     * 添加单个重点推荐应用
     *
     * @param appId app的id
     * @param userId
     * @return
     */
    @RequestMapping( value = "/top-app/new-one", method = RequestMethod.POST )
    RestRecord addTopRecommendApp( @RequestParam( "userId" ) String userId,
                                   @RequestParam( "appId" ) String appId );

    /**
     * 删除单个重点推荐应用
     *
     * @param appId app的id
     * @param userId
     * @return
     */
    @RequestMapping( value = "/top-app/sub-one", method = RequestMethod.POST )
    RestRecord cancelTopRecommendApp( @RequestParam( "userId" ) String userId,
                                      @RequestParam( "appId" ) String appId );
}
