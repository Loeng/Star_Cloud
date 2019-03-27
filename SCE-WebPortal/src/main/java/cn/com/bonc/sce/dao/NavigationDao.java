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

    @RequestMapping( value = "/navigation/getChannel", method = RequestMethod.GET )
    RestRecord getChannel (@RequestParam("channelType") Integer channelType);

    @RequestMapping( value = "/navigation/addChannel", method = RequestMethod.POST )
    RestRecord addChannel(@RequestBody String json);

    @RequestMapping( value = "/navigation/editChannel", method = RequestMethod.PUT )
    RestRecord editChannel(@RequestBody String json);

    @RequestMapping( value = "/navigation/getSchools/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord getSchools(@RequestParam("keywords") String keywords,
                          @PathVariable(value = "pageNum") Integer pageNum,
                          @PathVariable(value = "pageSize") Integer pageSize);

    @RequestMapping( value = "/navigation/getBanners", method = RequestMethod.GET )
    RestRecord getBanners (@RequestParam("schoolId") long schoolId);

    @RequestMapping( value = "/navigation/editDefaultBanner", method = RequestMethod.PUT )
    RestRecord editDefaultBanner (@RequestParam( "schoolId" ) long schoolId,
                                  @RequestParam( "defaultBanner" ) Integer defaultBanner);

    @RequestMapping( value = "/navigation/delBanner", method = RequestMethod.DELETE )
    RestRecord delBanner (@RequestParam( "bannerId" ) long bannerId);

    @RequestMapping( value = "/navigation/delChannel", method = RequestMethod.DELETE )
    RestRecord delChannel(@RequestParam( "channelId" ) String channelId);
}
