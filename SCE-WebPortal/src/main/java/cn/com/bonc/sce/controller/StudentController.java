package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AgencyService;
import io.swagger.annotations.ApiOperation;
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
    private AgencyService agencyService;

    @ApiOperation(value = "代理商启用与禁用", notes="通过当前代理活跃状态，改变代理状态", httpMethod = "PUT")
    @PutMapping("/editActivity")
    @ResponseBody
    public RestRecord editActivity(@RequestParam( "id" ) long id, @RequestParam( "isActivate" ) Integer isActivate){
        return agencyService.editActivity(id,isActivate);
    }
}
