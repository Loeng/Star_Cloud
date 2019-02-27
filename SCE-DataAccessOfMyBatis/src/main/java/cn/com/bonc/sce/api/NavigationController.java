package cn.com.bonc.sce.api;

import cn.com.bonc.sce.bean.NavigationBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.NavigationService;
import com.alibaba.druid.support.json.JSONUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        List<NavigationBean> navList = navigationService.getNavListByChannel(channelId);
        return new RestRecord( 200, navList );
    }

    @ApiOperation(value = "添加导航栏", notes="获取前端编辑数据，后台写入数据库", httpMethod = "POST")
    @PostMapping("/addNav")
    @ResponseBody
    public RestRecord addNav(@RequestBody @ApiParam( example = "{\"columnName\": \"这是导航title\",\"columnUrl\": \"这是导航链接\",\"channelId\": 5}" ) String json ){
        Map map = (Map) JSONUtils.parse(json);
        String columnName = (String) map.get("columnName");
        System.out.println(columnName);
        String columnUrl = (String)map.get("columnUrl");
        int channelId = (int) map.get("channelId");
        if (navigationService.addNav(columnName,columnUrl,channelId) == 1){
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
        }
    }
}
