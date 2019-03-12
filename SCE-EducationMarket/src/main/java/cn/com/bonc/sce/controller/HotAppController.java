package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.model.AppRecommend;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.HotAppService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 应用推荐-热门应用接口
 *
 * @author jc_D
 * @version 2.1
 * @update 修改字段命名，将错写的字段名称改为驼峰式，去掉 getter setter
 * @updateFrom 2018/12/26 11:20
 * @updateAuthor wzm
 * @update 修改用户ID获取方式改为从header获取。
 * @updateAuthor yhb
 */
@Slf4j
@Api( value = "应用推荐-热门应用接口", tags = "应用推荐-热门应用接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping( "/hot-app" )
public class HotAppController {

    private HotAppService hotAppService;

    @Autowired
    public HotAppController( HotAppService hotAppService ) {
        this.hotAppService = hotAppService;
    }

    /**
     * 添加热门应用
     *
     * @param appIdList 选项卡上的热门应用id,json数组
     * @return
     */
    @ApiOperation( value = "添加热门应用", notes = "添加热门应用", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appIdList", value = "apId数组", paramType = "body", required = true, example = "{'appIdList':[1,2,3]}" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @PostMapping
    public RestRecord addHotRecommendAppList( @RequestBody List< String > appIdList,
                                              @CurrentUserId String userId ) {
        return hotAppService.addHotRecommendAppList( appIdList, userId );
    }

    /**
     * 添加单个热门应用
     */
    @ApiOperation( value = "添加热门应用", notes = "添加热门应用", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "应用id", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @PostMapping( "/one" )
    @ResponseBody
    public RestRecord addHotRecommendApp( @RequestBody AppRecommend appRecommend,
                                          @CurrentUserId String userId ) throws IOException {
        return hotAppService.addHotRecommendApp( userId, appRecommend.getAppId() );
    }

    /**
     * 删除单个热门应用
     */
    @ApiOperation( value = "删除热门应用", notes = "删除热门应用", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appId", value = "应用id", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @PostMapping( "/sub-one" )
    @ResponseBody
    public RestRecord cancelHotRecommendApp( @RequestBody AppRecommend appRecommend,
                                             @CurrentUserId String userId ) throws IOException {

        return hotAppService.cancelHotRecommendApp( userId, appRecommend.getAppId() );
    }


    /**
     * 查询所有热门应用
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return
     */
    @ApiOperation( value = "查询所有热门应用", notes = "查询所有热门应用", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "pageNum", value = "页面", paramType = "path", required = true ),
            @ApiImplicitParam( name = "pageSize", value = "每页显示数量", paramType = "path", required = true )

    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/{pageNum}/{pageSize}" )
    public RestRecord selectHotRecommendAppList( @PathVariable Integer pageNum,
                                                 @PathVariable Integer pageSize ) {
        // 查询应用表中重点推荐状态为1的应用
        return hotAppService.selectHotRecommendAppList( pageNum, pageSize );
    }

    /**
     * 查询所有热门应用列表
     *
     * @return
     */
    @ApiOperation( value = "查询所有热门应用列表", notes = "查询所有热门应用列表", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/detail-list/{pageNum}/{pageSize}" )
    public RestRecord selectHotAppList( @PathVariable Integer pageNum,
                                        @PathVariable Integer pageSize,
                                        @CurrentUserId String userId ) {
        // 查询应用表中重点推荐状态为1的应用
        return hotAppService.selectHotAppList( pageNum, pageSize, userId );
    }
}
