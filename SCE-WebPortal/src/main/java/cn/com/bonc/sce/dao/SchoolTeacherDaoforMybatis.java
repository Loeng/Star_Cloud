package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by jc_D on 2018/12/12.
 */
@FeignClient( value = "sce-data-mybatis" )
public interface SchoolTeacherDaoforMybatis {

    /**
     * 查询教师列表
     *
     * @param condition
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping( value = "/school-teacher-manage/list/{userId}/{pageNum}/{pageSize}", method = RequestMethod.GET )
    public RestRecord getTeacherList( @RequestParam Map< String, Object > condition,
                                      @PathVariable( "userId" ) String userId,
                                      @PathVariable( "pageNum" ) Integer pageNum,
                                      @PathVariable( "pageSize" ) Integer pageSize
    );

    /**
     * 学校-教师管理
     * 删除教师
     *
     * @param userId
     * @return
     */
    @RequestMapping( value = "/school-teacher-manage/del/{userId}", method = RequestMethod.DELETE )
    public RestRecord deleteTeacher( @PathVariable( "userId" ) String userId );

    /**
     * 学校-教师管理
     * 新增教师
     *
     * @param teacherInfo
     * @param userId
     * @return
     */
    @RequestMapping( value = "/school-teacher-manage/add/{userId}", method = RequestMethod.POST )
    public RestRecord addTeacher( @RequestBody Map teacherInfo,
                                  @PathVariable( "userId" ) String userId );

    /**
     * 学校-教师管理
     * 修改教师信息
     *
     * @param teacherInfo
     * @param userId
     * @return
     */
    @RequestMapping( value = "/school-teacher-manage/edit/{userId}", method = RequestMethod.PUT )
    public RestRecord editTeacher( @RequestBody Map teacherInfo,
                                       @PathVariable( "userId" ) String userId );
}
