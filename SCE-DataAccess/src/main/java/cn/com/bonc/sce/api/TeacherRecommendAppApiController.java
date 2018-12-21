package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.TeacherRecommendRepository;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YueHaibo on 2018/12/12.
 */
@Slf4j
@RestController
@RequestMapping( "/teacher-recommend-app" )
public class TeacherRecommendAppApiController {

    private TeacherRecommendRepository teacherRecommendRepository;

    @Autowired
    public TeacherRecommendAppApiController( TeacherRecommendRepository teacherRecommendRepository ) {
        this.teacherRecommendRepository = teacherRecommendRepository;
    }

    /**
     * 添加教师推荐应用
     *
     * @param teacherId          教师Id
     * @param recommendPeroIdMap 应用Id和应用于推荐时段
     * @return 是否添加成功
     */
    @ApiOperation( value = "新增教师热门应用", notes = "新增教师热门应用，可新增多个", httpMethod = "POST" )
    @PostMapping
    @ResponseBody
    public RestRecord addTeacherRecommendApp(
            @RequestParam( "teacherId" ) String teacherId,
            @RequestBody List< Map< String, Object > > recommendPeroIdMap ) {
//        RestRecord restRecord = new RestRecord();
//        restRecord.setMsg( "#######占位，以后统一修改####### addTeacherRecommendApp" );
//        Map< String, Object > test = new HashMap<>();
//        test.put( "teacherId", teacherId );
//        restRecord.setData( test );
//        teacherRecommendRepository.addTeacherRecommendApp( teacherId, recommendPeroIdMap );
        RestRecord restRecord = new RestRecord();
        Map< String, Object > resultMap = new HashMap<>();
        restRecord.setMsg( "This is ApiController response message ! Method name addTeacherRecommendApp" );
        resultMap.put( "Param1", teacherId );
        resultMap.put( "Param2", recommendPeroIdMap );
        restRecord.setData( resultMap );
        return restRecord;
    }

    /**
     * 教师修改单个推荐应用的时长
     * * 1. 修改教师推荐表中对应记录中的推荐时间
     *
     * @param teacherId          教师用户Id
     * @param recommendPeroIdMap 应用Id和应用于推荐时段
     * @return
     */
    @PutMapping
    @ResponseBody
    public RestRecord updateTeacherRecommendAppInfo(
            @RequestParam( "teacherId" ) String teacherId,
            @RequestBody List< Map< String, Object > > recommendPeroIdMap ) {
//        teacherRecommendRepository.updateTeacherRecommendAppInfo( teacherId, recommendPeroIdMap );
        RestRecord restRecord = new RestRecord();
        Map< String, Object > resultMap = new HashMap<>();
        restRecord.setMsg( "This is ApiController response message ! Method name updateTeacherRecommendAppInfo" );
        resultMap.put( "Param1", teacherId );
        resultMap.put( "Param2", recommendPeroIdMap );
        restRecord.setData( resultMap );
        return restRecord;
    }


    /**
     * 删除教师推荐应用
     *
     * @param teacherId 教师Id
     * @param appIdList 应用Id列表
     * @return
     */
    @DeleteMapping
    @ResponseBody
    public RestRecord deleteTeacherRecommendApp(
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( value = "appIdList", required = true ) List< String > appIdList ) {
//        teacherRecommendRepository.deleteTeacherRecommendApp( teacherId, appIdList );
        RestRecord restRecord = new RestRecord();
        Map< String, Object > resultMap = new HashMap<>();
        restRecord.setMsg( "This is ApiController response message ! Method name deleteTeacherRecommendApp" );
        resultMap.put( "Param1", teacherId );
        resultMap.put( "Param2", appIdList );
        restRecord.setData( resultMap );
        return restRecord;
    }

    /**
     * 教师推荐应用查询
     * 1. 查询在时限内某教师推荐的应用信息
     *
     * @param teacherId  教师用户Id
     * @param timePeroid 查询的时间范围（可为空）
     * @return 返回查询结果
     */
    @GetMapping
    @ResponseBody
    public RestRecord selectTeacherRecommendAppList(
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( value = "startTime", required = false ) String startTime,
            @RequestParam( value = "endTime", required = false ) String endTime,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) String pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) String pageSize ) {
//        teacherRecommendRepository.selectTeacherRecommendAppList( teacherId, timePeroid, pageNum, pageSize );
        RestRecord restRecord = new RestRecord();
        Map< String, Object > resultMap = new HashMap<>();
        restRecord.setMsg( "This is ApiController response message ! Method name selectTeacherRecommendAppList" );
        resultMap.put( "Param1", teacherId );
        resultMap.put( "Param2", startTime );
        resultMap.put( "Param3", endTime );
        resultMap.put( "Param4", pageNum );
        resultMap.put( "Param5", pageSize );
        restRecord.setData( resultMap );
        return restRecord;
    }
}
