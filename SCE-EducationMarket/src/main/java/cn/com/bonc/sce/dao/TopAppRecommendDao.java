package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
