package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by YueHaibo on 2018/12/12.
 */
@FeignClient( value = "sce-data-access" )
public interface TeacherRecommendDao {

    @RequestMapping( value = "/teacher-recommend-app", method = RequestMethod.POST )
    RestRecord addTeacherRecommendApp(
            @RequestParam( "teacherId" ) String teacherId,
            @RequestBody List< Map< String, Object > > recommendPerMap );

    @RequestMapping( value = "/teacher-recommend-app", method = RequestMethod.PUT )
    public RestRecord updateTeacherRecommendAppInfo(
            @RequestParam( "teacherId" ) String teacherId,
            @RequestBody List< Map< String, Object > > recommendPerMap
    );

    @RequestMapping( value = "/teacher-recommend-app", method = RequestMethod.DELETE )
    public RestRecord deleteTeacherRecommendApp(
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( "appIdList" ) List< String > appIdList );

    @RequestMapping( value = "/teacher-recommend-app", method = RequestMethod.GET )
    public RestRecord selectTeacherRecommendAppList(
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( value = "startTime", required = false ) String startTime,
            @RequestParam( value = "endTime", required = false ) String endTime,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) int pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) int pageSize );
}
