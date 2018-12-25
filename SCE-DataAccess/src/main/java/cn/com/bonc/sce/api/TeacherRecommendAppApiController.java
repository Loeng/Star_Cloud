package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.entity.TeacherRecommend;
import cn.com.bonc.sce.repository.TeacherRecommendRepository;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
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
     * @param teacherId       教师Id
     * @param recommendPerMap 应用Id和应用于推荐时段
     * @return 是否添加成功
     */
    @ApiOperation( value = "新增教师热门应用", notes = "新增教师热门应用，可新增多个", httpMethod = "POST" )
    @PostMapping
    @ResponseBody
    public RestRecord addTeacherRecommendApp(
            @RequestParam( "teacherId" ) String teacherId,
            @RequestBody List< TeacherRecommend > recommendPerMap ) {
        log.trace( "add teacherRecommendApp APP List : {}", recommendPerMap );
        try {
            for ( TeacherRecommend s : recommendPerMap ) {
                s.setUserId( teacherId );
                s.setIsDelete( 1L );
                teacherRecommendRepository.save( s );
            }
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        } catch ( Exception e ) {
            log.error( "{}", e );
            return new RestRecord( 423, WebMessageConstants.SCE_PORTAL_MSG_423 );
        }
    }

    /**
     * 教师修改单个推荐应用的时长
     * * 1. 修改教师推荐表中对应记录中的推荐时间
     *
     * @param teacherId       教师用户Id
     * @param recommendPerMap 应用Id和应用于推荐时段
     * @return
     */
    @PutMapping
    @ResponseBody
    public RestRecord updateTeacherRecommendAppInfo(
            @RequestParam( "teacherId" ) String teacherId,
            @RequestBody List< TeacherRecommend > recommendPerMap ) {
        log.trace( "add TeacherRecommend TeacherId is {} ,List is {}", teacherId, recommendPerMap );
        try {
            for ( TeacherRecommend s : recommendPerMap ) {
                s.setIsDelete( 1L );
                s.setUserId( teacherId );
                teacherRecommendRepository.save( s );
            }
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        } catch ( Exception e ) {
            log.error( "{}", e );
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_421 );
        }
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
        log.trace( "delete teacherRecommend By appIdList :{}", appIdList );
        try {
            RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
            restRecord.setData( teacherRecommendRepository.deleteTeacherRecommendByAppIdList( teacherId, appIdList ) );
            return restRecord;
        } catch ( Exception e ) {
            log.error( "", e );
            return new RestRecord( 422, WebMessageConstants.SCE_PORTAL_MSG_422 );
        }
    }

    /**
     * todo 连接应用表查询应用数据。目前只能查询当前老师所有推荐的应用
     *
     * @param teacherId 教师Id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageNum   页码
     * @param pageSize  每页大小
     * @return
     */
    @GetMapping
    @ResponseBody
    public RestRecord selectTeacherRecommendAppList(
            @RequestParam( "teacherId" ) String teacherId,
            @RequestParam( value = "startTime", required = false , defaultValue = "1970-01-01 00:00:00" ) String startTime,
            @RequestParam( value = "endTime", required = false , defaultValue = "2099-01-01 00:00:00") String endTime,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) int pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) int pageSize ) {
        log.trace( "query teacherCommend conditions is teacherId:{} startTime:{} endTime:{} pageNum:{} pageSize:{} ", teacherId, startTime, endTime, pageNum, pageSize );
        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        try {
            RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
            Page< List< Map< String, Object > > > page = teacherRecommendRepository.findAllByUserIdAndTime( teacherId, startTime, endTime, pageable );
            Map< String, Object > temp = new HashMap<>( 16 );
            temp.put( "data", page.getContent() );
            temp.put( "totalPage", page.getTotalPages() );
            temp.put( "totalCount", page.getTotalElements() );
            restRecord.setData( temp );
            return restRecord;
        } catch ( Exception e ) {
            log.error( "", e );
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420 );
        }
    }
}
