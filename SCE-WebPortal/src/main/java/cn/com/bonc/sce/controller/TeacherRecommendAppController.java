package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.TeacherRecommendService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by YueHaibo on 2018/12/12.
 */
@Slf4j
@Api( value = "教师推荐应用接口", tags = "教师推荐应用接口" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
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
     * @param teacherId          教师Id
     * @param recommendPeroIdMap 应用Id和应用于推荐时段
     * @return 是否添加成功
     */
    @ApiOperation( value = "新增教师热门应用", notes = "新增教师热门应用，可新增多个", httpMethod = "POST" )
    @PostMapping
    @ResponseBody
    public RestRecord addTeacherRecommendApp(
            @RequestParam( "teacherId" ) @ApiParam( value = "教师用户Id", required = true ) String teacherId,
            @RequestBody @ApiParam( value = "应用于推荐时段\r[{\r\"appId\":\"String\",\r\"recommendStartTime\":\"2018-01-01\",\r\"recommendEndTime\":\"2018-02-02\"},{\r\"略\":\"略\"}]", allowMultiple = true, example = "{\"123\":\"123\"}" )
                    List< Map< String, Object > > recommendPeroIdMap ) {
        /*
        RestRecord restRecord = new RestRecord();
        restRecord.setMsg( "#######占位，以后统一修改####### addTeacherRecommendApp" );
        Map< String, Object > test = new HashMap<>();
        test.put( "teacherId", teacherId );
        test.put( "requestData", recommendPeroIdMap );
        restRecord.setData( test );
        teacherRecommendService.addTeacherRecommendApp( teacherId, recommendPeroIdMap );
        return restRecord;
        */
        return teacherRecommendService.addTeacherRecommendApp( teacherId, recommendPeroIdMap );
    }

    /**
     * 教师修改单个推荐应用的时长
     * * 1. 修改教师推荐表中对应记录中的推荐时间
     *
     * @param teacherId          教师用户Id
     * @param recommendPeroIdMap 应用Id和应用于推荐时段
     * @return
     */
    @ApiOperation( value = "修改推荐应用的时长", notes = "修改推荐市场，可修改多个", httpMethod = "PUT" )
    @PutMapping
    @ResponseBody
    public RestRecord updateTeacherRecommendAppInfo(
            @RequestParam( "teacherId" ) @ApiParam( value = "教师用户Id", required = true ) String teacherId,
            @RequestBody @ApiParam( value = "应用于推荐时段\r[{\r\"appId\":\"String\",\r\"recommendStartTime\":\"2018-01-01\",\r\"recommendEndTime\":\"2018-02-02\"},{\r\"略\":\"略\"}]", allowMultiple = true, example = "{\"123\":\"123\"}" )
                    List< Map< String, Object > > recommendPeroIdMap ) {
        return teacherRecommendService.updateTeacherRecommendAppInfo( teacherId, recommendPeroIdMap );
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
            @RequestParam @ApiParam( value = "教师Id", required = true ) String teacherId,
            @RequestParam @ApiParam( value = "应用Id列表" ) List< String > appIdList ) {
        ArrayList< String > temp = new ArrayList<>();
        for ( String temp1 : appIdList ) {
            temp.add( temp1 );
        }
        RestRecord restRecord = new RestRecord();
        restRecord.setData( appIdList );
        System.out.println( appIdList );
//        return restRecord;
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
            @RequestParam( "teacherId" ) @ApiParam( value = "教师Id", required = true ) String teacherId,
            @RequestParam( value = "startTime", required = false ) @ApiParam( "开始时间" ) String startTime,
            @RequestParam( value = "endTime", required = false ) @ApiParam( "结束时间" ) String endTime,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) String pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) String pageSize ) {
        return teacherRecommendService.selectTeacherRecommendAppList( teacherId, startTime, endTime, pageNum, pageSize );
    }
}
