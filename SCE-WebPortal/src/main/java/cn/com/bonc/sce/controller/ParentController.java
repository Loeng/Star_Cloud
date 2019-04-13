package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.ParentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Charles on 2019/3/28.
 */
@Slf4j
@RestController
@RequestMapping( "/parent" )
public class ParentController {

    @Autowired
    private ParentService parentService;

    @ApiOperation(value = "获取绑定学生列表", notes="根据家长用户id获取学生列表", httpMethod = "GET")
    @GetMapping("/getStudentList")
    @ResponseBody
    public RestRecord getStudentList(@RequestParam( "id" ) String id){
        return parentService.getStudentList(id);
    }

    @ApiOperation(value = "绑定学生", notes="获取绑定信息，请求绑定", httpMethod = "POST")
    @PostMapping("/bindStudent")
    @ResponseBody
    public RestRecord bindStudent(@RequestBody @ApiParam( example = "{\n" +
            "\t\"applyUserId\": \"c0190f3e1ae54dc890a4a2afee10f527\",\n" +
            "\t\"relationship\": \"父子\",\n" +
            "\t\"phone\": \"55555555555\",\n" +
            "\t\"applyType\": 0,\n" +
            "\t\"bindUserId\": \"c0190f3e1ae54dc890a4a2afee10f527\"\n" +
            "}" ) String json){
        return parentService.bindStudent(json);
    }

    @ApiOperation(value = "解除家长和学生的绑定关系", notes="解除家长和学生的绑定关系", httpMethod = "DELETE")
    @DeleteMapping("/unbind")
    @ResponseBody
    public RestRecord unbind(@RequestParam( "parentId" )String parentId,@RequestParam( "studentId" )String studentId){
        return parentService.unbind(parentId,studentId);
    }

    @ApiOperation(value = "获取学生对应的家长列表", notes="获取学生对应的家长列表", httpMethod = "GET")
    @GetMapping("/getParentList")
    @ResponseBody
    public RestRecord getParentList(@CurrentUserId String userId,@RequestParam( "id" ) String id){
        return parentService.getParentList(userId, id);
    }

    @ApiOperation(value = "获取申请列表", notes="获取申请列表", httpMethod = "GET")
    @GetMapping("/getApplyList")
    @ResponseBody
    public RestRecord getApplyList(@RequestParam( "id" ) String id){
        return parentService.getApplyList(id);
    }

    /**
     *  防止重复添加
     * @param userId 用户ID
     * @param map targetUserId studentUserId
     * @return RestRecord
     */
    @ApiOperation( value = "申请成为监护人", notes = "家长提交申请", httpMethod = "POST" )
    @PostMapping( "/applyMain" )
    public RestRecord applyMain(@CurrentUserId String userId, @RequestBody Map map){
            return parentService.applyMain(userId, map);
    }

    /**
     * @param userId 用户id
     * @param map audit-审核，1通过，2不通过  applyUserId  - 申请人id  studentUserId -- 学生id
     * @return RestRecord
     */
    @ApiOperation( value = "审核家长的申请成为监护人", notes = "同意或者不同意申请", httpMethod = "PUT" )
    @PutMapping( "/auditApplyMain" )
    public RestRecord auditApplyMain(@CurrentUserId String userId, @RequestBody Map map){
        return parentService.auditApplyMain(userId, map);
    }

    @ApiOperation( value = "获取手机号", notes = "根据学生账号获取其监护人的手机号", httpMethod = "GET" )
    @GetMapping( "/getMainPhone" )
    public RestRecord getMainPhone(@CurrentUserId String userId, @RequestParam String studentLoginName){
        return parentService.getMainPhone(userId, studentLoginName);
    }

}
