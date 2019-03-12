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

    @ApiOperation(value = "导航栏查询", notes="通过参数channelType获取导航列表", httpMethod = "GET")
    @GetMapping("/getChannel")
    @ResponseBody
    public RestRecord getChannel(@RequestParam ( "channelType" ) Integer channelType){
        return navigationService.getChannel(channelType);
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

    @ApiOperation(value = "获取学校机构列表", notes="获取查询条件，返回学校机构列表", httpMethod = "GET")
    @GetMapping("/getSchools/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getSchools(@RequestParam ( "keywords" ) String keywords,
                                 @PathVariable (value = "pageNum")Integer pageNum,
                                 @PathVariable (value = "pageSize") Integer pageSize ){
        return navigationService.getSchools(keywords,pageNum,pageSize);
    }

    @ApiOperation(value = "获取学校机构对应的banner", notes="根据学校id，返回学校对应banner列表", httpMethod = "GET")
    @GetMapping("/getBanners")
    @ResponseBody
    public RestRecord getBanners(@RequestParam ( "schoolId" ) Integer schoolId ){
        return navigationService.getBanners(schoolId);
    }

    @ApiOperation(value = "学校设置默认banner", notes="获取学校id和当前banner状态，修改默认banner字段", httpMethod = "PUT")
    @PutMapping("/editDefaultBanner")
    @ResponseBody
    public RestRecord editDefaultBanner(
                @RequestParam ( "schoolId" ) Integer schoolId,
                @RequestParam( "defaultBanner" ) Integer defaultBanner){
        return navigationService.editDefaultBanner(schoolId,defaultBanner);
    }

    @ApiOperation(value = "删除某一学校下的banner", notes="获取bannerid，逻辑删除banner", httpMethod = "Delete")
    @DeleteMapping("/delBanner")
    @ResponseBody
    public RestRecord delBanner(
            @RequestParam( "bannerId" ) Integer bannerId){
        return navigationService.delBanner(bannerId);
    }
}
