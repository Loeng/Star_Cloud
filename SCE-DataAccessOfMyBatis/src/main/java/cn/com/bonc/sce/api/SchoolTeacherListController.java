package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.SchoolTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 学校工作台
 * 教师管理-相关接口
 */
@Slf4j
@RestController
@RequestMapping( "/school-teacher-manage" )
public class SchoolTeacherListController {

    @Autowired
    private SchoolTeacherService teacherService;

    /**
     * 查询教师列表
     *
     * @param condition
     * @param userId
     * @return
     */
    @GetMapping( "/list/{userId}/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getTeacherList( @RequestParam Map< String, Object > condition,
                                      @PathVariable( "userId" ) String userId,
                                      @PathVariable( "pageNum" ) Integer pageNum,
                                      @PathVariable( "pageSize" ) Integer pageSize
    ) {
        return teacherService.getTeacherList( condition, userId, pageNum, pageSize );
    }

    /**
     * 删除教师
     *
     * @param userId
     * @return
     */
    @DeleteMapping( "/del/{userId}" )
    @ResponseBody
    public RestRecord deleteTeacher( @PathVariable( "userId" ) String userId ) {
        return teacherService.deleteTeacher( userId );
    }

    /**
     * 学校用户
     * 新增教师
     *
     * @return
     */
    @PostMapping( "/add/{userId}" )
    public RestRecord addTeacher( @RequestBody Map teacherInfo,
                                  @PathVariable( "userId" ) String userId ) {
        try {
            return teacherService.addTeacher( teacherInfo, userId );
        } catch ( Exception e ) {
            log.error( "新增教师失败", e.getMessage(), e );
            return new RestRecord( 409, WebMessageConstants.SCE_PORTAL_MSG_423 );
        }
    }

    /**
     * 学校-教师管理
     * 修改教师信息
     *
     * @return
     */
    @PutMapping( "/edit/{userId}" )
    public RestRecord editTeacher( @RequestBody Map teacherInfo,
                                   @PathVariable( "userId" ) String userId ) {
        try {
            return teacherService.editTeacher( teacherInfo, userId );
        } catch ( Exception e ) {
            log.error( "修改教师信息失败", e.getMessage(), e );
            return new RestRecord( 409, WebMessageConstants.SCE_PORTAL_MSG_421 );
        }
    }


}
