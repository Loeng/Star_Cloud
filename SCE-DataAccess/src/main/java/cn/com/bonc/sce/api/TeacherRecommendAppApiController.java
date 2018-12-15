package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.TeacherRecommendDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping( "/TeacherRecommendApp" )
public class TeacherRecommendAppApiController {

    private TeacherRecommendDao teacherRecommendDao;

    @Autowired
    public TeacherRecommendAppApiController( TeacherRecommendDao teacherRecommendDao ) {
        this.teacherRecommendDao = teacherRecommendDao;
    }

    /**
     * 将单个应用设置为教师推荐应用
     * 1. 将用户选择的应用设置为教师推荐应用，添加数据到教师推荐表中
     *
     * @param appId           用户选择的应用Id
     * @param teacherId       推荐应用的教师用户Id
     * @param recommendPeroid 应用的推荐时间长度
     * @return 是否添加成功
     */
    @PostMapping
    @ResponseBody
    public RestRecord addTeacherRecommendApp(
            @RequestParam( "appId" ) String appId,
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( "recommendPeroId" ) String recommendPeroid ) {
        return new RestRecord( 0, teacherRecommendDao.addTeacherRecommendApp( appId, teacherId, recommendPeroid ) );
    }

    /**
     * 将一组应用设置为教师推荐应用
     * 1.	将用户选择的应用设置为教师推荐应用，添加数据到教师推荐表中每个推荐应用有其对应的推荐时间
     *
     * @param appIdList          用户选择的一组应用Id列表
     * @param teacherId          推荐应用的教师用户Id
     * @param recommendPeroIdMap 应用的推荐时间长度，每个应用可设置不同的推荐时间
     * @return 是否成功添加
     */
    @PostMapping( "/list" )
    @ResponseBody
    public RestRecord addTeacherRecommendAppList(
            @RequestParam( "appId" ) List< String > appIdList,
            @RequestParam( "teacherId" ) String teacherId,
            @RequestBody Map< String, Object > recommendPeroIdMap ) {
        return new RestRecord( 0, teacherRecommendDao.addTeacherRecommendAppList( appIdList, teacherId, recommendPeroIdMap ) );
    }


    /**
     * 教师修改单个推荐应用的时长
     * 1. 修改教师推荐表中对应记录中的推荐时间
     *
     * @param appId           应用Id
     * @param teacherId       教师用户Id
     * @param recommendPeroid 应用的推荐时间段
     * @return 返回是否更新成功
     */
    @PatchMapping( "/{appId}" )
    @ResponseBody
    public RestRecord updateTeacherRecommendAppInfo(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( "recommendPeroId" ) String recommendPeroid ) {
        return new RestRecord( 0, teacherRecommendDao.updateTeacherRecommendAppInfo( teacherId, appId, recommendPeroid ) );
    }

    /**
     * 删除单个教师推荐应用
     * 1. 将教师推荐表中对应记录删除标志位改为已删除
     *
     * @param appId     应用Id
     * @param teacherId 教师用户Id
     * @return 返回是否删除成功
     */
    @DeleteMapping
    @ResponseBody
    public RestRecord deleteTeacherRecommendApp(
            @RequestParam( "appId" ) String appId,
            @RequestParam( "teacherId" ) String teacherId ) {
        return new RestRecord( 0, teacherRecommendDao.deleteTeacherRecommendApp( appId, teacherId ) );
    }

    /**
     * 删除一组教师推荐应用
     * 1. 将教师推荐表中对应记录删除标志位改为已删除
     *
     * @param appIdList 应用Id列表
     * @param teacherId 教师用户Id
     * @return 返回删除数量
     */
    @DeleteMapping( "/list" )
    @ResponseBody
    public RestRecord deleteTeacherRecommendAppList(
            @RequestParam( "appIdList" ) List< String > appIdList,
            @RequestParam( "teacherId" ) String teacherId ) {
        return new RestRecord( 0, teacherRecommendDao.deleteTeacherRecommendAppList( appIdList, teacherId ) );
    }

    /**
     * 教师推荐应用查询
     * 1. 查询在时限内某教师推荐的应用信息
     *
     * @param teacherId  教师用户Id
     * @param timePeroid 查询的时间范围（可为空）
     * @return 返回查询结果
     */
    @GetMapping( "/{teacherId}" )
    @ResponseBody
    public RestRecord selectTeacherRecommendAppListByTeacherId(
            @PathVariable( "teacherId" ) String teacherId,
            @RequestParam( value = "timePeroid", required = false ) String timePeroid,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) String pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) String pageSize ) {
        return new RestRecord( 0, teacherRecommendDao.selectTeacherRecommendAppListByTeacherId( teacherId, timePeroid ) );
    }


}
