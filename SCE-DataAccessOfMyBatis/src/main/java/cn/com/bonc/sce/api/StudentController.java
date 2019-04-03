package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.StudentService;
import cn.com.bonc.sce.tool.IdWorker;
import com.alibaba.druid.support.json.JSONUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Charles on 2019/3/28.
 */
@Slf4j
@RestController
@RequestMapping( "/student" )
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private IdWorker idWorker;

    @ApiOperation(value = "获取家长列表", notes="通过学生id获取家长列表", httpMethod = "GET")
    @GetMapping("/getParents")
    @ResponseBody
    public RestRecord getParents(@RequestParam( "id" ) String id){
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200,studentService.getParents(id) );
    }

    @ApiOperation(value = "获取家长申请列表", notes="通过学生id获取家长申请列表", httpMethod = "GET")
    @GetMapping("/getApplyList")
    @ResponseBody
    public RestRecord getApplyList(@RequestParam( "id" ) String id){
        return new RestRecord(200,MessageConstants.SCE_MSG_0200,studentService.getApplyList(id));
    }

    @ApiOperation(value = "学生审核", notes="通过学生id获取家长申请列表", httpMethod = "POST")
    @PostMapping("/audit")
    @ResponseBody
    @Transactional
    public RestRecord audit(@RequestBody @ApiParam( example = "{\"id\": \"c0190f3e1ae54dc890a4a2afee10f527\",\"relationship\": \"父子\",\"rejectReason\": \"55555555555\",\"applyResult\": 0,\"bindUserId\": \"c0190f3e1ae54dc890a4a2afee10f527\",\"applyUserId\": \"c0190f3e1ae54dc890a4a2afee10f527\"}" ) String json){
        Map map = (Map) JSONUtils.parse(json);
        String id = (String) map.get("id");
        String relationship = (String) map.get("relationship");
        String rejectReason = (String) map.get("rejectReason");
        Integer applyResult = (Integer) map.get("applyResult");
        String bindUserId = (String) map.get("bindUserId");
        String applyUserId = (String) map.get("applyUserId");

        int auditCount = studentService.audit(id,applyResult,rejectReason);
        int relationCount = studentService.addRelation(idWorker.nextId(),applyUserId,bindUserId,relationship);

        if (auditCount==1 && relationCount==1){
            return new RestRecord(200,MessageConstants.SCE_MSG_0200,1);
        }else {
            return new RestRecord(407,MessageConstants.SCE_MSG_407,0);
        }
    }
}
