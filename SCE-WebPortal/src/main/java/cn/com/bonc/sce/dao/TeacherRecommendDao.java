package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.TeacherRecommend;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Repository
@FeignClient( "sce-data-access" )
public interface TeacherRecommendDao {

    @RequestMapping( value = "/TeacherRecommendApp", method = RequestMethod.POST )
    public boolean addTeacherRecommendApp(
            @RequestParam( "appId" ) String appId,
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( "recommendPeroId" ) String recommendPeroid );

    @RequestMapping( value = "/TeacherRecommendApp/{teacherId}", method = RequestMethod.POST )
    public List< TeacherRecommend > selectRecommendAppListByTeacherId(
            @PathVariable( "teacherId" ) String teacherId,
            @RequestParam( value = "timePeroid", required = false ) String timePeroid);

    @RequestMapping( value = "/TeacherRecommendApp/list", method = RequestMethod.POST )
    public Integer addTeacherRecommendAppList(
            @RequestParam( "appId" ) List< String > appIdList,
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( "recommendPeroidMap" ) Map< String, Object > recommendPeroidMap );

    @RequestMapping( value = "/TeacherRecommendApp", method = RequestMethod.PATCH )
    public Integer updateRecommendAppInfo(
            @RequestParam( "appId" ) String appId,
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

}
