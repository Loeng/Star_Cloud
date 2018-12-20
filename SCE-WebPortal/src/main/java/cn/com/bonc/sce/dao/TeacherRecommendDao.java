package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient( "sce-data-access" )
public interface TeacherRecommendDao {

    @RequestMapping( value = "/teacher-recommend-app", method = RequestMethod.POST )
    RestRecord addTeacherRecommendApp(
            @RequestParam( "teacherId" ) String teacherId,
            @RequestBody List< Map< String, Object > > recommendPeroIdMap );

    @RequestMapping( value = "/teacher-recommend-app", method = RequestMethod.PUT )
    public RestRecord updateTeacherRecommendAppInfo(
            @RequestParam( "teacherId" ) String teacherId,
            @RequestBody List< Map< String, Object > > recommendPeroIdMap
    );

    @RequestMapping( value = "/teacher-recommend-app", method = RequestMethod.DELETE )
    public RestRecord deleteTeacherRecommendApp(
            @RequestParam( "appId" ) String appId,
            @RequestParam( "teacherId" ) List< String > teacherId );


    @RequestMapping( value = "/teacher-recommend-app", method = RequestMethod.GET )
    public RestRecord selectTeacherRecommendAppListByTeacherId(
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( value = "timePeroid", required = false ) Map< String, Object > timePeroid,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) String pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) String pageSize );
}
