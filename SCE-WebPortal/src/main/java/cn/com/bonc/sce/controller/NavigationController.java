package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.NavigationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Charles on 2019/2/27.
 */

@Slf4j
@RestController
@RequestMapping( "/navigation" )
public class NavigationController {

    @Autowired
    private NavigationService navigationService;

    @ApiOperation(value = "导航栏查询", notes="通过参数channel_id获取导航列表", httpMethod = "GET")
    @GetMapping("/getNavListByChannel")
    @ResponseBody
    public RestRecord getNavListByChannel(@RequestParam ( "channelId" ) Integer channelId){
        return navigationService.getNavListByChannel(channelId);
    }

    @ApiOperation(value = "添加导航栏", notes="获取前端编辑数据，后台写入数据库", httpMethod = "POST")
    @PostMapping("/addNav")
    @ResponseBody
    public RestRecord addNav(@RequestBody @ApiParam( example = "{\"columnName\": \"这是导航title\",\"columnUrl\": \"这是导航链接\",\"channelId\": 5}" ) String json ){
        return navigationService.addNav(json);
    }

    @ApiOperation(value = "编辑导航栏", notes="获取前端编辑数据，后台写入数据库", httpMethod = "PUT")
    @PutMapping("/editNav")
    @ResponseBody
    public RestRecord editNav(@RequestBody @ApiParam( example = "{\"columnName\": \"这是导航title\",\"columnUrl\": \"这是导航链接\",\"columnId\": 3}" ) String json ){
        return navigationService.editNav(json);
    }
}
