package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.TeacherRecommendService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Slf4j
@Api( value = "教师推荐应用接口", tags = "教师推荐应用接口" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RestController
@RequestMapping( "/teacher-recommend-app" )
public class TeacherRecommendAppController {

    private TeacherRecommendService teacherRecommendService;

    @Autowired
    public TeacherRecommendAppController( TeacherRecommendService teacherRecommendService ) {
        this.teacherRecommendService = teacherRecommendService;
    }

    /**
     * 添加教师推荐应用
     *
     * @param teacherId       教师Id
     * @param recommendPerMap 应用Id和应用于推荐时段
     * @return 是否添加成功
     */
    @ApiOperation( value = "新增教师热门应用", notes = "新增教师热门应用，可新增多个", httpMethod = "POST" )
    @PostMapping
    @ResponseBody
    public RestRecord addTeacherRecommendApp(
            @RequestParam( "teacherId" ) @ApiParam( value = "教师用户Id", required = true ) String teacherId,
            @RequestBody @ApiParam( value = "应用于推荐时段\r[{\r\"appId\":\"String\",\r\"recommendStartTime\":1545636712000,\r\"recommendEndTime\":1545636712000},{\r\"略\"}]", allowMultiple = true, example = "{\"123\":\"123\"}" )
                    List< Map< String, Object > > recommendPerMap ) {
        return teacherRecommendService.addTeacherRecommendApp( teacherId, recommendPerMap );
    }

    /**
     * 教师修改单个推荐应用的时长
     * * 1. 修改教师推荐表中对应记录中的推荐时间
     *
     * @param teacherId       教师用户Id
     * @param recommendPerMap 应用Id和应用于推荐时段
     * @return
     */
    @ApiOperation( value = "修改推荐应用的时长", notes = "修改推荐市场，可修改多个", httpMethod = "PUT" )
    @PutMapping
    @ResponseBody
    public RestRecord updateTeacherRecommendAppInfo(
            @CurrentUserId @ApiParam( hidden = true ) String teacherId,
            @RequestBody @ApiParam( value = "应用于推荐时段\r[{\r\"appId\":\"String\",\r\"recommendStartTime\":\"1545636712000\",\r\"recommendEndTime\":\"1545636712000\"},{\r\"略\":\"略\"}]" )
                    List< Map< String, Object > > recommendPerMap ) {
        return teacherRecommendService.updateTeacherRecommendAppInfo( teacherId, recommendPerMap );
    }


    /**
     * 删除教师推荐应用
     *
     * @param teacherId 教师Id
     * @param appIdList 应用Id列表
     * @return
     */
    @ApiOperation( value = "删除教师推荐应用", notes = "删除教师推荐应用，可删除多个", httpMethod = "DELETE" )
    @DeleteMapping
    @ResponseBody
    public RestRecord deleteTeacherRecommendApp(
            @CurrentUserId @ApiParam( hidden = true ) String teacherId,
            @RequestParam @ApiParam( value = "应用Id列表" ) List< String > appIdList ) {
        return teacherRecommendService.deleteTeacherRecommendApp( teacherId, appIdList );
    }

    /**
     * 教师推荐应用查询
     * * 1. 查询在时限内某教师推荐的应用信息
     *
     * @param teacherId 教师Id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageNum   页码
     * @param pageSize  每页数量
     * @return
     */
    @ApiOperation( value = "教师推荐应用查询", notes = "查询在时限内某教师推荐的应用信息", httpMethod = "GET" )
    @GetMapping
    @ResponseBody
    public RestRecord selectTeacherRecommendAppList(
            @CurrentUserId @ApiParam( hidden = true ) String teacherId,
            @RequestParam( value = "startTime", defaultValue = "1970-01-01 00:00:00" ) @ApiParam( value = "开始时间", example = "2012-06-18 00:00:00" ) String startTime,
            @RequestParam( value = "endTime", defaultValue = "2099-01-01 00:00:00" ) @ApiParam( value = "结束时间", example = "2019-06-18 23:59:59" ) String endTime,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize ) {
        System.out.println( startTime );
        System.out.println( endTime );
        return teacherRecommendService.selectTeacherRecommendAppList( teacherId, startTime, endTime, pageNum, pageSize );
    }

    @ApiOperation( "查询教师推荐应用列表" )
    @GetMapping( "/list" )
    @ResponseBody
    public RestRecord getTeacherCommendList( @CurrentUserId @ApiParam( hidden = true ) String userId,
                                             @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) int pageNum,
                                             @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) int pageSize ) {
        return teacherRecommendService.getTeacherCommendList( userId, pageNum, pageSize );
    }


    @ApiOperation( "点击教师推荐按钮" )
    @PostMapping( "/commend" )
    @ResponseBody
    public RestRecord updateIsCommend( @CurrentUserId @ApiParam( hidden = true ) String userId,
                                       @RequestParam @ApiParam( value = "应用Id" ) String appId ) {
        return teacherRecommendService.updateIsCommend( userId, appId );
    }
}
