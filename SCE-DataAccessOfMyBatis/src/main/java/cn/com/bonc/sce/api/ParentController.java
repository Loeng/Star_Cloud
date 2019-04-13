package cn.com.bonc.sce.api;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
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
            "\t\"applyType\": 1,\n" +
            "\t\"studentLoginName\": \"xs_0\"\n" +
            "}" ) String json){
        Map map = (Map) JSONUtils.parse(json);
        String targetUserId ="";
        String applyUserId = null;
        String studentLoginName = null;
        String relationship = null;
        Integer applyType = null;
        try {
            applyUserId = map.get("applyUserId").toString();
            studentLoginName = map.get("studentLoginName").toString();
            relationship = map.get("relationship").toString();
            applyType = Integer.parseInt(map.get("applyType").toString());
        }catch (NullPointerException e){
            return new RestRecord( 431, String.format(WebMessageConstants.SCE_PORTAL_MSG_431, "必须"));
        }
        Long id = idWorker.nextId();

        //通过学生账号查询学生id
        String bindUserId = userService.selectUserIdByLoginName(studentLoginName);
        if(bindUserId == null){
            return new RestRecord( 434, String.format(WebMessageConstants.SCE_PORTAL_MSG_434, "") );
        }

        if ( applyType == 1 ){
            targetUserId = bindUserId;
            parentService.bindStudent(id,applyUserId,targetUserId,applyType,bindUserId,relationship);
        } else {
            //直接绑定该家长和学生的亲属关系，并删掉绑定审核表中的审核数据。
            parentService.addStudentParentRel(idWorker.nextId(), applyUserId, bindUserId, 0, relationship);
            parentService.delAudit(applyUserId, bindUserId);
        }
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );

    }

    @ApiOperation(value = "解除家长和学生的绑定关系", notes="解除家长和学生的绑定关系", httpMethod = "DELETE")
    @DeleteMapping("/unbind")
    @ResponseBody
    public RestRecord unbind(@RequestParam( "parentId" )String parentId,@RequestParam( "studentId" )String studentId){
        if (parentService.unbind(parentId,studentId)==1){
            return new RestRecord(200,MessageConstants.SCE_MSG_0200,1);
        }else {
            return new RestRecord(408,MessageConstants.SCE_MSG_408,0);
        }
    }

    @ApiOperation(value = "获取学生对应的家长列表", notes="获取学生对应的家长列表", httpMethod = "GET")
    @GetMapping("/getParentList")
    @ResponseBody
    public RestRecord getParentList(@CurrentUserId String userId, @RequestParam( "id" ) String id){
        return new RestRecord(200,MessageConstants.SCE_MSG_0200,parentService.getParentList(userId, id));
    }

    @ApiOperation(value = "获取申请列表", notes="获取申请列表", httpMethod = "GET")
    @GetMapping("/getApplyList")
    @ResponseBody
    public RestRecord getApplyList(@RequestParam( "id" ) String id){
        return new RestRecord(200,MessageConstants.SCE_MSG_0200,parentService.getApplyList(id));
    }

    @PostMapping("/applyMain")
    public RestRecord applyMain(@CurrentUserId String userId,
                                @RequestBody Map map){
        return parentService.applyMain(userId, map);
    }

    @PutMapping("/auditApplyMain")
    public RestRecord auditApplyMain( @CurrentUserId String userId,
                                      @RequestBody Map map){
        return parentService.auditApplyMain(userId, map);
    }

    @GetMapping("/getMainPhone")
    public RestRecord getMainPhone(@CurrentUserId String userId, @RequestParam String studentLoginName){
        return parentService.getMainPhone(userId, studentLoginName);
    }

}
