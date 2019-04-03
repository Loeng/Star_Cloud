package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AgencyService;
import cn.com.bonc.sce.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Charles on 2019/3/28.
 */
@Slf4j
@RestController
@RequestMapping( "/student" )
public class StudentController {
    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "获取家长列表", notes="通过学生id获取家长列表", httpMethod = "GET")
    @GetMapping("/getParents")
    @ResponseBody
    public RestRecord getParents(@RequestParam( "id" ) String id){
        return studentService.getParents(id);
    }

    @ApiOperation(value = "获取家长申请列表", notes="通过学生id获取家长申请列表", httpMethod = "GET")
    @GetMapping("/getApplyList")
    @ResponseBody
    public RestRecord getApplyList(@RequestParam( "id" ) String id){
        return studentService.getApplyList(id);
    }

    @ApiOperation(value = "学生审核", notes="通过学生id获取家长申请列表", httpMethod = "POST")
    @PostMapping("/audit")
    @ResponseBody
    public RestRecord audit(@RequestBody @ApiParam( example = "{\n" +
            "\t\"id\": \"c0190f3e1ae54dc890a4a2afee10f527\",\n" +
            "\t\"relationship\": \"父子\",\n" +
            "\t\"rejectReason\": \"55555555555\",\n" +
            "\t\"applyResult\": 0,\n" +
            "\t\"bindUserId\": \"c0190f3e1ae54dc890a4a2afee10f527\"\n" +
            "\t\"applyUserId\": \"c0190f3e1ae54dc890a4a2afee10f527\"\n" +
            "}" ) String json){
        return studentService.audit(json);
    }
}
