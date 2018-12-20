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
        RestRecord restRecord = new RestRecord();
        restRecord.setMsg( "#######占位，以后统一修改####### addTeacherRecommendApp" );
        Map< String, Object > test = new HashMap<>();
        test.put( "teacherId", teacherId );
        restRecord.setData( test );
//        teacherRecommendRepository.addTeacherRecommendApp( teacherId, recommendPeroIdMap );
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
        return new RestRecord( 0 );
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
            @RequestParam String teacherId,
            @RequestParam List< String > appIdList ) {
//        teacherRecommendRepository.deleteTeacherRecommendApp( teacherId, appIdList );
        RestRecord restRecord = new RestRecord();
        restRecord.setData( appIdList );
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
    public RestRecord selectTeacherRecommendAppListByTeacherId(
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( value = "timePeroid", required = false ) Map< String, Object > timePeroid,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) String pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) String pageSize ) {
//        teacherRecommendRepository.selectTeacherRecommendAppListByTeacherId( teacherId, timePeroid, pageNum, pageSize );
        return new RestRecord();
    }
}
