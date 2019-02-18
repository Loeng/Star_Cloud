package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: hefan
 * @Date: 2018/12/25
 * @Description:用户应用收藏操作DAO
 */
@FeignClient( "sce-data-access" )
public interface AppCollectDao {

    @RequestMapping( value = "/app-collect/list", method = RequestMethod.GET )
    RestRecord getUserAppCollect(@RequestParam("userId") String userId);

    /**
     * 添加用户收藏应用信息
     * @param userId
     * @param appId
     * @return
     */
    @RequestMapping ( value = "/app-collect/info", method = RequestMethod.POST )
    RestRecord addUserAppOpenInfo( @RequestParam( "userId" ) String userId,
                                   @RequestParam( "appId" ) String appId );

    /**
     * 删除用户收藏应用信息
     * @param userId
     * @param appId
     * @return
     */
    @RequestMapping ( value = "/app-collect/remove-info", method = RequestMethod.POST )
    RestRecord deleteUserAppOpenInfo( @RequestParam( "userId" ) String userId,
                                      @RequestParam( "appId" ) String appId );
}
