package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.SchoolStudentService;
import cn.com.bonc.sce.service.SchoolStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 学校工作台
 * 学生管理-相关接口
 */
@Slf4j
@RestController
@RequestMapping( "/school-student-manage" )
public class SchoolStudentListController {

    @Autowired
    private SchoolStudentService schoolStudentService;

    /**
     * 查询学生列表
     *
     * @param condition
     * @param userId
     * @return
     */
    @GetMapping( "/list/{userId}/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getStudentList( @RequestParam Map< String, Object > condition,
                                      @PathVariable( "userId" ) String userId,
                                      @PathVariable( "pageNum" ) Integer pageNum,
                                      @PathVariable( "pageSize" ) Integer pageSize
    ) {
        return schoolStudentService.getStudentList( condition, userId, pageNum, pageSize );
    }

    /**
     * 删除学生
     *
     * @param userId
     * @return
     */
    @DeleteMapping( "/del/{userId}" )
    @ResponseBody
    public RestRecord deleteStudent( @PathVariable( "userId" ) String userId ) {
        return schoolStudentService.deleteStudent( userId );

    }

    /**
     * 学校用户
     * 新增学生
     *
     * @return
     */
    @PostMapping( "/add/{userId}" )
    public RestRecord addStudent( @RequestBody Map studentInfo,
                                  @PathVariable( "userId" ) String userId ) {
        try {
            return schoolStudentService.addStudent( studentInfo, userId );
        } catch ( Exception e ) {
            log.error( "新增学生失败", e.getMessage(), e );
            return new RestRecord( 409, WebMessageConstants.SCE_PORTAL_MSG_423 );
        }
    }

    /**
     * 学校用户
     * 编辑学生
     *
     * @return
     */
    @PutMapping( "/edit/{userId}" )
    public RestRecord editStudent( @RequestBody Map studentInfo,
                                   @PathVariable( "userId" ) String userId ) {
        try {
            return schoolStudentService.editStudent( studentInfo, userId );
        } catch ( Exception e ) {
            log.error( "修改学生信息失败", e.getMessage(), e );
            return new RestRecord( 409, WebMessageConstants.SCE_PORTAL_MSG_423 );
        }
    }


}
