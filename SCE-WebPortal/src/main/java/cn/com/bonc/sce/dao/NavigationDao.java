package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Charles on 2019/2/27.
 */
@Repository
@FeignClient( value = "sce-data-mybatis" )
public interface NavigationDao {

    @RequestMapping( value = "/navigation/getNavListByChannel", method = RequestMethod.GET )
    RestRecord getNavListByChannel (@RequestParam("channelId") Integer channelId);

    @RequestMapping( value = "/navigation/addNav", method = RequestMethod.POST )
    RestRecord addNav(@RequestBody String json);

    @RequestMapping( value = "/navigation/editNav", method = RequestMethod.PUT )
    RestRecord editNav(@RequestBody String json);
}
