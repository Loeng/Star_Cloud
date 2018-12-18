package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.app.AppInfoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
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
    public Boolean addHotRecommendAppList( @RequestParam List< String > appIdList,
                                           @PathVariable( "userId" ) String userId );

    /**
     * 查询所有热门应用
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return
     */
    @RequestMapping( value = "/hotApp", method = RequestMethod.GET )
    public List< AppInfoEntity > selectHotRecommendAppList( @RequestParam( value = "pageNum", defaultValue = "1", required = false ) int pageNum,
                                                            @RequestParam( value = "pageSize", defaultValue = "10", required = false ) int pageSize );


    /**
     * 删除热门应用
     *
     * @param appIdList app的id用","分割
     * @param userId
     * @return
     */
    @RequestMapping( value = "/hotApp/{userId}/{appIdList}", method = RequestMethod.DELETE )
    public int deleteHotRecommendApp( @PathVariable( "appIdList" ) List< String > appIdList,
                                      @PathVariable( "userId" ) String userId );

}
