package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
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
        String id = null;
        String relationship = null;
        String rejectReason = null;
        Integer applyResult = null;
        String bindUserId = null;
        String applyUserId = null;
        try {
            id = map.get("id").toString();
            relationship = map.get("relationship").toString();
            rejectReason = map.get("rejectReason").toString();
            applyResult = Integer.parseInt(map.get("applyResult").toString());
            bindUserId = map.get("bindUserId").toString();
            applyUserId = map.get("applyUserId").toString();
        }catch (NullPointerException e){
            return new RestRecord( 431, String.format(WebMessageConstants.SCE_PORTAL_MSG_431,"必要") );
        }catch (NumberFormatException e){
            return new RestRecord( 431, "applyResult参数不正确");
        }

        studentService.audit(id,applyResult,rejectReason);
        if(applyResult == 1){
           studentService.addRelation(idWorker.nextId(),applyUserId,bindUserId,relationship);
        }
        return new RestRecord(200,MessageConstants.SCE_MSG_0200,1);
    }
}
