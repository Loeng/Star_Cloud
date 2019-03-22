package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by jc_D on 2018/12/12.
 */
@FeignClient( value = "sce-data-mybatis" )
public interface SchoolStudentDaoforMybatis {

    /**
     * 查询学生列表
     *
     * @param condition
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping( value = "/school-student-manage/list/{userId}/{pageNum}/{pageSize}", method = RequestMethod.GET )
    public RestRecord getStudentList( @RequestParam Map< String, Object > condition,
                                      @PathVariable( "userId" ) String userId,
                                      @PathVariable( "pageNum" ) Integer pageNum,
                                      @PathVariable( "pageSize" ) Integer pageSize
    );

    /**
     * 学校-学生管理
     * 删除学生
     *
     * @param userId
     * @return
     */
    @RequestMapping( value = "/school-student-manage/del/{userId}", method = RequestMethod.DELETE )
    public RestRecord deleteStudent( @PathVariable( "userId" ) String userId );

    /**
     * 学校-学生管理
     * 新增学生
     *
     * @param studentInfo
     * @param userId
     * @return
     */
    @RequestMapping( value = "/school-student-manage/add/{userId}", method = RequestMethod.POST )
    public RestRecord addStudent( @RequestBody Map studentInfo,
                                  @PathVariable( "userId" ) String userId );

    /**
     * 学校-学生管理
     * 修改学生信息
     *
     * @param studentInfo
     * @param userId
     * @return
     */
    @RequestMapping( value = "/school-student-manage/edit/{userId}", method = RequestMethod.PUT )
    public RestRecord editStudent( @RequestBody Map studentInfo,
                                       @PathVariable( "userId" ) String userId );
}
