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
    public RestRecord editActivity(@RequestParam( "id" ) Integer id,@RequestParam( "isActivate" ) Integer isActivate){
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
    public RestRecord getSchools(@RequestParam( "id" ) Integer id,
                                 @PathVariable (value = "pageNum")Integer pageNum,
                                 @PathVariable (value = "pageSize") Integer pageSize){
        return agencyService.getSchools(id,pageNum,pageSize);
    }

    @ApiOperation(value = "代理商代理学校删除", notes="通过代理商id和学校id删除代理商和学校的代理关系", httpMethod = "DELETE")
    @DeleteMapping("/delSchoolRel")
    @ResponseBody
    public RestRecord delSchoolRel(@RequestParam( "agentId" ) Integer agentId,
                                 @RequestParam ("schoolId")Integer schoolId){
        return agencyService.delSchoolRel(agentId,schoolId);
    }
}
