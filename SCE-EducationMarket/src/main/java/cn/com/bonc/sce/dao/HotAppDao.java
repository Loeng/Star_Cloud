package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient( "sce-data-access" )
public interface HotAppDao {

    /**
     * 添加热门应用
     *
     * @param appIdList app的id用","分割
     * @param userId
     * @return
     */
    @RequestMapping( value = "/hotApp/{userId}", method = RequestMethod.POST )
    RestRecord addHotRecommendAppList( @RequestBody List< String > appIdList,
                                       @PathVariable( "userId" ) String userId );

    /**
     * 查询所有热门应用
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return
     */
    @RequestMapping( value = "/hotApp/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord selectHotRecommendAppList( @PathVariable( "pageNum" ) Integer pageNum,
                                          @PathVariable( "pageSize" ) Integer pageSize );
}
