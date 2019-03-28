package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AgencyService;
import cn.com.bonc.sce.service.ParentService;
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
    public RestRecord bindStudent(@RequestBody @ApiParam( example = "{\"applyUserId\": \"c0190f3e1ae54dc890a4a2afee10f527\",\"relationship\": \"父子\",\"targetUserId\": \"c0190f3e1ae54dc890a4a2afee10f527\",\"applyType\":0,\"bindUserId\":\"c0190f3e1ae54dc890a4a2afee10f527\"}" ) String json){
        return parentService.bindStudent(json);
    }
}
