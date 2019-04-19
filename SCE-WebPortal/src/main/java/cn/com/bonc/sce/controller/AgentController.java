package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.AgentModel;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AgentService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author BTW
 */
@Slf4j
@Api(value = "代理信息接口", tags = "代理信息接口")
@ApiResponses({
        @ApiResponse(code = 500, message = "服务器内部错误", response = RestRecord.class),
        @ApiResponse(code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class)
})
@RestController
@RequestMapping("/agent")
public class AgentController {

    private AgentService agentService;

    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    /**
     * 添加单个代理信息
     *
     * @param agentModel 用户输入的厂商信息
     * @return 返回是否添加成功
     */
    @ApiOperation(value = "添加单个代理信息", notes = "新建代理信息", httpMethod = "POST")
    @PostMapping
    @ResponseBody
    public RestRecord addAgentInfo(
            @RequestBody @ApiParam(name = "agentModel", value = "代理信息对象", required = true)
                    AgentModel agentModel) {
        return agentService.saveAgentInfo(agentModel);
    }

    @ApiOperation(value = "新增代理商信息接口", notes = "新增代理商信息", httpMethod = "POST")
    @PostMapping("/addAgent/{roleId}")
    @ResponseBody
    public RestRecord addAgent(
            @ApiParam(name = "company", value = "代理商信息对象", required = true)
            @RequestBody AgentModel agent, @ApiParam(name = "userId", value = "用户ID", required = true) @RequestParam("userId") String userId, @ApiParam(name = "roleId", value = "角色类型", required = true) @PathVariable("roleId") Integer roleId) {
        return agentService.addAgent(agent, userId, roleId);
    }

    @ApiOperation(value = "通过代理商ID修改代理商信息接口", notes = "通过代理商ID修改代理商信息", httpMethod = "PUT")
    @PutMapping("/updateAgentById")
    @ResponseBody
    public RestRecord updateAgentById(
            @RequestBody @ApiParam(name = "agent", value = "代理商信息对象", required = true)
                    AgentModel agent) {
        return agentService.updateAgentById(agent);
    }

    @ApiOperation(value = "通过代理商ID获取代理商信息接口", notes = "通过代理商ID获取代理商信息", httpMethod = "GET")
    @GetMapping("/{id}")
    @ResponseBody
    public RestRecord getAgentById(
            @PathVariable(value = "id") @ApiParam(value = "代理商Id", required = true) String id) {
        return agentService.getAgentById(id);
    }

    @ApiOperation(value = "变更或驳回提交代理商信息接口", notes = "变更或驳回提交代理商信息", httpMethod = "PUT")
    @PutMapping("/updateAgent")
    @ResponseBody
    public RestRecord updateAgent(@RequestBody @ApiParam("代理商信息对象") AgentModel agent) {
        return agentService.updateAgent(agent);
    }

    @ApiOperation(value = "查询所有代理用户信息", notes = "查询所有代理用户信息", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", paramType = "path", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", paramType = "path", required = true)

    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功", response = RestRecord.class)
    })
    @GetMapping("/all-user-info/{pageNum}/{pageSize}")
    public RestRecord getAllAgentUserInfo(@PathVariable Integer pageNum,
                                          @PathVariable Integer pageSize) {
        return agentService.getAllAgentUserInfo(pageNum, pageSize);
    }

    /**
     * 代理商认证列表
     *
     * @param agentName
     * @param property
     * @param auditStatus
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取代理商认证审核列表接口",notes = "获取代理商认证审核列表信息",httpMethod = "GET")
    @GetMapping("/getAgentInfo/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getAgentInfo(@RequestParam(value = "agentName",required = false) @ApiParam( name = "agentName", value = "代理商名称") String agentName,@RequestParam(value = "property",required = false) @ApiParam( name = "property", value = "公司性质") String property, @RequestParam(value = "auditStatus",required = false) @ApiParam( name = "auditStatus", value = "审核状态") Integer auditStatus
            , @PathVariable("pageNum") @ApiParam( name = "pageNum", value = "当前页数", required = true) Integer pageNum,@PathVariable("pageSize") @ApiParam( name = "pageSize", value = "每页记录数",required = true) Integer pageSize) {
        return agentService.getAgentInfo(agentName, property, auditStatus, pageNum, pageSize);
    }

    /**
     * 代理商配置学校，待选学校列表
     *
     * @return
     */
    @GetMapping("/getActingSchoolList/{school_name}")
    @ResponseBody
    public RestRecord getActingSchoolList(@PathVariable("school_name") String school_name) {
        return agentService.getActingSchoolList(school_name);
    }

    /**
     * 代理商配置学校，待选学校列表
     *
     * @return
     */
    @GetMapping("/getHasBeenActingSchoolList/{ID}")
    public RestRecord getHasBeenActingSchoolList(@PathVariable("ID") Integer ID) {
        return agentService.getHasBeenActingSchoolList(ID);
    }

    /**
     * 代理商配置学校，增加学校
     *
     * @return
     */
    @PostMapping("/addActingSchool")
    @ResponseBody
    public RestRecord addActingSchool(@RequestBody Map<String, String> info) {
        return agentService.addActingSchool(info);
    }

    /**
     * 代理商配置学校，删除学校
     *
     * @return
     */
    @DeleteMapping("/deleteActingSchool")
    @ResponseBody
    public RestRecord deleteActingSchool(@RequestBody Map<String, String> info) {
        return agentService.deleteActingSchool(info);
    }

}
