package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping( value = "/navigation/getSchools/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord getSchools(@RequestParam("keywords") String keywords,
                          @PathVariable(value = "pageNum") Integer pageNum,
                          @PathVariable(value = "pageSize") Integer pageSize);

    @RequestMapping( value = "/navigation/getBanners", method = RequestMethod.GET )
    RestRecord getBanners (@RequestParam("schoolId") Integer schoolId);

    @RequestMapping( value = "/navigation/editDefaultBanner", method = RequestMethod.PUT )
    RestRecord editDefaultBanner (@RequestParam( "schoolId" ) Integer schoolId,
                                      @RequestParam( "defaultBanner" ) Integer defaultBanner);

    @RequestMapping( value = "/navigation/delBanner", method = RequestMethod.DELETE )
    RestRecord delBanner (@RequestParam( "bannerId" ) Integer bannerId);

}
