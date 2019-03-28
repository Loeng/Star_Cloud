package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.ParentService;
import cn.com.bonc.sce.service.UserService;
import cn.com.bonc.sce.tool.IdWorker;
import com.alibaba.druid.support.json.JSONUtils;
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
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取绑定学生列表", notes="根据家长用户id获取学生列表", httpMethod = "GET")
    @GetMapping("/getStudentList")
    @ResponseBody
    public RestRecord getStudentList(@RequestParam( "id" ) String id){
        return new RestRecord(200,MessageConstants.SCE_MSG_0200,parentService.getStudentList(id));
    }

    @ApiOperation(value = "绑定学生插入审核表", notes="获取绑定信息，请求绑定", httpMethod = "POST")
    @PostMapping("/bindStudent")
    @ResponseBody
    public RestRecord bindStudent(@RequestBody @ApiParam( example = "{\n" +
            "\t\"applyUserId\": \"c0190f3e1ae54dc890a4a2afee10f527\",\n" +
            "\t\"relationship\": \"父子\",\n" +
            "\t\"phone\": \"18782461247\",\n" +
            "\t\"applyType\": 0,\n" +
            "\t\"bindUserId\": \"c0190f3e1ae54dc890a4a2afee10f527\"\n" +
            "}" ) String json){
        Map map = (Map) JSONUtils.parse(json);
        String targetUserId ="";
        String applyUserId = (String) map.get("applyUserId");
        String bindUserId = (String) map.get("bindUserId");
        String relationship = (String) map.get("relationship");
        Long id = idWorker.nextId();
        Integer applyType = (Integer) map.get("applyType");

        if (applyType==0){
            targetUserId = userService.getIdByPhone((String)map.get("phone"));
        }else {
            targetUserId = bindUserId;
        }

        int count = parentService.bindStudent(id,applyUserId,targetUserId,applyType,bindUserId,relationship);
        if (count == 1){
            return new RestRecord(200,MessageConstants.SCE_MSG_0200,1);
        }else {
            return new RestRecord(409,MessageConstants.SCE_MSG_409,0);
        }
    }
}
