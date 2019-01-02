package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author yinming
 * @date 2018/12/25
 */
@FeignClient( "sce-data-access" )
public interface AppOpenDao {
    /**
     * 获取用户开通列表
     * @param userId
     * @return
     */
    @RequestMapping( value = "/app-open", method = RequestMethod.GET )
    RestRecord getUserAppOpenList(
            @RequestParam( "userId" ) String userId );

    /**
     * 添加用户开通应用信息
     * @param userId
     * @param appId
     * @return
     */
    @RequestMapping ( value = "/app-open/info", method = RequestMethod.POST )
    RestRecord addUserAppOpenInfo( @RequestParam( "userId" ) String userId,
                                   @RequestParam( "appId" ) String appId );

    /**
     * 删除用户开通应用信息
     * @param userId
     * @param appId
     * @return
     */
    @RequestMapping ( value = "/app-open/remove-info", method = RequestMethod.POST )
    RestRecord deleteUserAppOpenInfo( @RequestParam( "userId" ) String userId,
                                      @RequestParam( "appId" ) String appId );
}
