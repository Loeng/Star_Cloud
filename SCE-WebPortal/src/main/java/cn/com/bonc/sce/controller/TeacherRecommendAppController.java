package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.TeacherRecommendService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Api( value = "教师推荐应用接口",tags = "教师推荐应用接口")
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping( "/TeacherRecommendApp" )
public class TeacherRecommendAppController {

    private TeacherRecommendService teacherRecommendService;

    @Autowired
    public TeacherRecommendAppController( TeacherRecommendService teacherRecommendService ) {
        this.teacherRecommendService = teacherRecommendService;
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
    @ApiOperation( value = "将单个应用设置为教师推荐应用", notes = "将用户选择的应用设置为教师推荐应用，添加数据到教师推荐表中", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "应用Id", paramType = "query", required = true ),
            @ApiImplicitParam( name = "teacherId", value = "推荐应用的教师用户Id", paramType = "query", required = true ),
            @ApiImplicitParam( name = "recommendPeroId", value = "应用的推荐时间长度", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "###", response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord addTeacherRecommendApp(
            @RequestParam( "appId" ) String appId,
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( "recommendPeroId" ) String recommendPeroid ) {
        return new RestRecord( 0, teacherRecommendService.addTeacherRecommendApp( appId, teacherId, recommendPeroid ) );
    }

    /**
     * 将一组应用设置为教师推荐应用
     * 1.	将用户选择的应用设置为教师推荐应用，添加数据到教师推荐表中每个推荐应用有其对应的推荐时间
     *
     * @param appIdList          用户选择的一组应用Id列表
     * @param teacherId          推荐应用的教师用户Id
     * @param recommendPeroidMap 应用的推荐时间长度，每个应用可设置不同的推荐时间
     * @return 是否成功添加
     */
    @ApiOperation( value = "将一组应用设置为教师推荐应用", notes = "将用户选择的应用设置为教师推荐应用，添加数据到教师推荐表中每个推荐应用有其对应的推荐时间", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "用户选择的一组应用Id列表", dataType = "list", paramType = "query", required = true ),
            @ApiImplicitParam( name = "teacherId", value = "教师用户Id", paramType = "query", required = true ),
            @ApiImplicitParam( name = "recommendPeroId", value = "应用的推荐时间长度，每个应用可设置不同的推荐时间", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "###", response = RestRecord.class )
    } )
    @PostMapping( "/list" )
    @ResponseBody
    public RestRecord addTeacherRecommendAppList(
            @RequestParam( "appId" ) List< String > appIdList,
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( "recommendPeroidMap" ) Map< String, Object > recommendPeroidMap ) {
        return new RestRecord( 0, teacherRecommendService.addTeacherRecommendAppList( appIdList, teacherId, recommendPeroidMap ) );
    }


    /**
     * 教师推荐应用查询
     * 1. 查询在时限内某教师推荐的应用信息
     *
     * @param teacherId  教师用户Id
     * @param timePeroid 查询的时间范围（可为空）
     * @return 返回查询结果
     */
    @ApiOperation( value = "教师推荐应用查询", notes = "查询在时限内某教师推荐的应用信息", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "teacherId", value = "教师用户Id", paramType = "query", required = true ),
            @ApiImplicitParam( name = "timePeroid", value = "查询的时间范围", paramType = "query", required = false, defaultValue = "" ),
            @ApiImplicitParam( name = "pageNum", value = "页码", paramType = "query", required = false, defaultValue = "1" ),
            @ApiImplicitParam( name = "pageSize", value = "每页显示数量", paramType = "query", required = false, defaultValue = "10" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "###", response = RestRecord.class )
    } )
    @GetMapping( "/{teacherId}" )
    @ResponseBody
    public RestRecord selectTeacherRecommendAppListByTeacherId(
            @PathVariable( "teacherId" ) String teacherId,
            @RequestParam( value = "timePeroid", required = false ) String timePeroid,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) String pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) String pageSize ) {

        return new RestRecord( 0, teacherRecommendService.selectTeacherRecommendAppListByTeacherId( teacherId, timePeroid ) );
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
    @ApiOperation( value = "修改单个推荐应用的时长", notes = "修改教师推荐表中对应记录中的推荐时间", httpMethod = "PATCH" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "应用Id", paramType = "query", required = true ),
            @ApiImplicitParam( name = "teacherId", value = "教师用户Id", paramType = "query", required = true),
            @ApiImplicitParam( name = "recommendPeroId", value = "应用的推荐时间段", paramType = "query",  required = true)
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "###", response = RestRecord.class )
    } )
    @PatchMapping("{appId}")
    @ResponseBody
    public RestRecord updateTeacherRecommendApp(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( "recommendPeroId" ) String recommendPeroid ) {

        return new RestRecord( 0, teacherRecommendService.updateTeacherRecommendAppInfo( teacherId, appId, recommendPeroid ) );
    }

    /**
     * 删除单个教师推荐应用
     * 1. 将教师推荐表中对应记录删除标志位改为已删除
     *
     * @param appId     应用Id
     * @param teacherId 教师用户Id
     * @return 返回是否删除成功
     */
    @ApiOperation( value = "删除单个教师推荐应用", notes = "将教师推荐表中对应记录删除标志位改为已删除", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "应用Id", paramType = "query", required = true ),
            @ApiImplicitParam( name = "teacherId", value = "教师用户Id", paramType = "query", required = true)
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "###", response = RestRecord.class )
    } )
    @DeleteMapping
    @ResponseBody
    public RestRecord deleteTeacherRecommendApp(
            @RequestParam( "appId" ) String appId,
            @RequestParam( "teacherId" ) String teacherId ) {
        return new RestRecord( 0, teacherRecommendService.deleteTeacherRecommendApp( appId, teacherId ) );
    }

    /**
     * 删除一组教师推荐应用
     * 1. 将教师推荐表中对应记录删除标志位改为已删除
     *
     * @param appIdList 应用Id列表
     * @param teacherId 教师用户Id
     * @return 返回删除数量
     */
    @ApiOperation( value = "删除一组教师推荐应用", notes = "将教师推荐表中对应记录删除标志位改为已删除", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "应用Id列表", paramType = "query", required = true ),
            @ApiImplicitParam( name = "teacherId", value = "教师用户Id", paramType = "query", required = true)
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "###", response = RestRecord.class )
    } )
    @DeleteMapping( "/list" )
    @ResponseBody
    public RestRecord deleteTeacherRecommendAppList(
            @RequestParam( "appIdList" ) List< String > appIdList,
            @RequestParam( "teacherId" ) String teacherId ) {
        return new RestRecord( 0, teacherRecommendService.deleteTeacherRecommendAppList( appIdList, teacherId ) );
    }
}
