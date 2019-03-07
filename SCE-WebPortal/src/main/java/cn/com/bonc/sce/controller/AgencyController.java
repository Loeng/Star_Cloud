package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AgencyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Charles on 2019/3/4.
 */

@Slf4j
@RestController
@RequestMapping( "/agent" )
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @ApiOperation(value = "代理商启用与禁用", notes="通过当前代理活跃状态，改变代理状态", httpMethod = "PUT")
    @PutMapping("/editActivity")
    @ResponseBody
    public RestRecord editActivity(@RequestParam( "id" ) long id,@RequestParam( "isActivate" ) Integer isActivate){
        return agencyService.editActivity(id,isActivate);
    }

    @ApiOperation(value = "代理商信息编辑", notes="通过代理商id修改代理商名称和省市区", httpMethod = "PUT")
    @PutMapping("/editInfo")
    @ResponseBody
    public RestRecord editInfo(@RequestBody @ApiParam( example = "{\"id\": 1743,\"AGENT_NAME\": \"测试一下代理商名称\",\"PROVINCE\": \"四川\",\"CITY\": \"成都\",\"AREA\": \"青羊区\"}" ) String json ){
        return agencyService.editInfo(json);
    }

    @ApiOperation(value = "代理商代理学校列表查询", notes="通过代理商id查询代理学校列表", httpMethod = "GET")
    @GetMapping("/getSchools/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getSchools(@RequestParam( "id" ) long id,
                                 @PathVariable (value = "pageNum")Integer pageNum,
                                 @PathVariable (value = "pageSize") Integer pageSize){
        return agencyService.getSchools(id,pageNum,pageSize);
    }

    @ApiOperation(value = "代理商代理学校删除", notes="通过代理商id和学校id删除代理商和学校的代理关系", httpMethod = "DELETE")
    @DeleteMapping("/delSchoolRel")
    @ResponseBody
    public RestRecord delSchoolRel(@RequestParam( "agentId" ) long agentId,
                                 @RequestParam ("schoolId")long schoolId){
        return agencyService.delSchoolRel(agentId,schoolId);
    }

    @ApiOperation(value = "获取代理商列表", notes="获取查询条件，返回代理商列表", httpMethod = "GET")
    @GetMapping("/getAgents/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getAgents(@RequestParam ( value = "agentName",required = false) String agentName,
                                 //@RequestParam(value ="isActivate",required = false ) Integer isActivate,
                                 @RequestParam ( value = "grade",required = false) String grade,
                                 @RequestParam ( value = "agentArea",required = false) String agentArea,
                                 @PathVariable (value = "pageNum")Integer pageNum,
                                 @PathVariable (value = "pageSize") Integer pageSize ){
        return agencyService.getAgents(agentName,grade,agentArea,pageNum,pageSize);
    }

    @ApiOperation(value = "新增代理商信息", notes="获取用户编辑数据，写入代理商信息，并在用户信息表插入对应数据", httpMethod = "POST")
    @PostMapping("/insertInfo")
    @ResponseBody
    public RestRecord insertInfo(@RequestBody @ApiParam( example = "{\"AGENT_NAME\": \"测试一下代理商名称\",\"PROVINCE\": \"四川\",\"CITY\": \"成都\",\"AREA\": \"青羊区\"}" ) String json ){
        return agencyService.insertInfo(json);
    }

    @ApiOperation(value = "删除代理商用户", notes="通过代理商用户id，删除代理商用户", httpMethod = "DELETE")
    @DeleteMapping("/delAgentUser")
    @ResponseBody
    public RestRecord delAgentUser(@RequestParam( "id" ) long id){
        return agencyService.delAgentUser(id);
    }
}
