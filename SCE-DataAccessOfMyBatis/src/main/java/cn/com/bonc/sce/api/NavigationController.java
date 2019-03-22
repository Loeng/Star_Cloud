package cn.com.bonc.sce.api;

import cn.com.bonc.sce.bean.NavigationBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.model.Banner;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.NavigationService;
import cn.com.bonc.sce.tool.IdWorker;
import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    @Autowired
    private IdWorker idWorker;

    @ApiOperation(value = "导航栏查询", notes="通过参数channelType获取导航列表", httpMethod = "GET")
    @GetMapping("/getChannel")
    @ResponseBody
    public RestRecord getChannel(@RequestParam ( "channelType" ) Integer channelType){
        List<NavigationBean> navList = navigationService.getChannel(channelType);
        return new RestRecord( 200, navList );
    }

    @ApiOperation(value = "添加导航栏", notes="获取前端编辑数据，后台写入数据库", httpMethod = "POST")
    @PostMapping("/addChannel")
    @ResponseBody
    public RestRecord addChannel(@RequestBody @ApiParam( example = "{\"channelName\": \"这是导航title\",\"channelUrl\": \"这是导航链接\",\"channelType\": 0或1}" ) String json ){
        Map map = (Map) JSONUtils.parse(json);
        String channelName = (String) map.get("channelName");
        String channelUrl = (String)map.get("channelUrl");
        int channelType = (int) map.get("channelType");
        long channelId = idWorker.nextId();
        int isDelete = 1;

        if (navigationService.addChannel(channelName,channelUrl,channelId,channelType,isDelete) == 1){
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
        }
    }

    @ApiOperation(value = "编辑导航栏", notes="获取前端编辑数据，后台写入数据库", httpMethod = "PUT")
    @PutMapping("/editChannel")
    @ResponseBody
    public RestRecord editChannel(@RequestBody @ApiParam(example = "{\"channelName\": \"这是导航title\",\"channelUrl\": \"这是导航链接\",\"channelId\": 3}"  ) String json ){
        Map map = (Map) JSONUtils.parse(json);
        String channelName = (String) map.get("channelName");
        String channelUrl = (String)map.get("channelUrl");
        String channelId = (String) map.get("channelId");
        if (navigationService.editChannel(channelName,channelUrl,channelId) == 1){
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 407, MessageConstants.SCE_MSG_407 );
        }
    }

    @ApiOperation(value = "删除导航栏", notes="获取channelId，删除导航栏", httpMethod = "Delete")
    @DeleteMapping("/delChannel")
    @ResponseBody
    public RestRecord delChannel(@RequestParam( "channelId" ) String channelId){
        if (navigationService.delChannel(channelId) == 1){
            return new RestRecord(200,MessageConstants.SCE_MSG_0200,1);
        }else {
            return new RestRecord(408,MessageConstants.SCE_MSG_408,0);
        }
    }

    @ApiOperation(value = "获取学校机构列表", notes="获取查询条件，返回学校机构列表", httpMethod = "GET")
    @GetMapping("/getSchools/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getSchools(@RequestParam ( "keywords" ) String keywords,
                                 @PathVariable (value = "pageNum") Integer pageNum,
                                 @PathVariable (value = "pageSize") Integer pageSize){

        PageHelper.startPage(pageNum, pageSize);
        List<SchoolBean> schoolList = navigationService.getSchools(keywords);
        PageInfo pageInfo = new PageInfo(schoolList);
        List list = pageInfo.getList();
        return new RestRecord( 200, list );

    }

    @ApiOperation(value = "获取学校机构对应的banner", notes="根据学校id，返回学校对应banner列表", httpMethod = "GET")
    @GetMapping("/getBanners")
    @ResponseBody
    public RestRecord getBanners(@RequestParam ( "schoolId" ) Integer schoolId ){
        List<Banner> banners = navigationService.getBanners(schoolId);
        return new RestRecord(200,banners);
    }

    @ApiOperation(value = "学校设置默认banner", notes="获取学校id和当前banner状态，修改默认banner字段", httpMethod = "PUT")
    @PutMapping("/editDefaultBanner")
    @ResponseBody
    public RestRecord editDefaultBanner(
            @RequestParam ( "schoolId" ) Integer schoolId,
            @RequestParam( "defaultBanner" ) Integer defaultBanner) {
        //传入页面banner值  后台转变
        int newStatus = (defaultBanner==0) ? 1 : 0;
        int count = navigationService.editDefaultBanner(schoolId,newStatus);
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 407, MessageConstants.SCE_MSG_407 );
        }
    }

    @ApiOperation(value = "删除某一学校下的banner", notes="获取学校id和bannerid，逻辑删除banner", httpMethod = "Delete")
    @DeleteMapping("/delBanner")
    @ResponseBody
    public RestRecord delBanner( @RequestParam( "bannerId" ) Integer bannerId){
        int count = navigationService.delBanner(bannerId);
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 408, MessageConstants.SCE_MSG_408 );
        }
    }
}
