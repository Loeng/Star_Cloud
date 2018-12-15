package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.TeacherRecommend;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient( "sce-data-access" )
public interface TeacherRecommendDao {

    @RequestMapping( value = "/TeacherRecommendApp", method = RequestMethod.POST )
    public boolean addTeacherRecommendApp(
            @RequestParam( "appId" ) String appId,
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( "recommendPeroId" ) String recommendPeroid );

    @RequestMapping( value = "/TeacherRecommendApp/list", method = RequestMethod.POST )
    public Integer addTeacherRecommendAppList(
            @RequestParam( "appId" ) List< String > appIdList,
            @RequestParam( "teacherId" ) String teacherId,
            @RequestBody Map< String, Object > recommendPeroIdMap );

    @RequestMapping( value = "/TeacherRecommendApp/{appId}", method = RequestMethod.PATCH )
    public Integer updateTeacherRecommendAppInfo(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( "recommendPeroId" ) String recommendPeroid
    );

    @RequestMapping( value = "/TeacherRecommendApp", method = RequestMethod.DELETE )
    public Integer deleteTeacherRecommendApp(
            @RequestParam( "appId" ) String appId,
            @RequestParam( "teacherId" ) String teacherId );

    @RequestMapping( value = "/TeacherRecommendApp/list", method = RequestMethod.DELETE )
    public Integer deleteTeacherRecommendAppList(
            @RequestParam( "appIdList" ) List< String > appIdList,
            @RequestParam( "teacherId" ) String teacherId );

    @RequestMapping( value = "/TeacherRecommendApp/{teacherId}", method = RequestMethod.POST )
    public List< TeacherRecommend > selectTeacherRecommendAppListByTeacherId(
            @PathVariable( "teacherId" ) String teacherId,
            @RequestParam( value = "timePeroid", required = false ) String timePeroid );
}
