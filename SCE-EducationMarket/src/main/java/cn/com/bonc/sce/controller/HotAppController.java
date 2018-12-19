package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.HotAppService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用推荐-热门应用接口
 * atuhor jc_D
 */
@Slf4j
@Api( value = "应用推荐-热门应用接口", tags = "应用推荐-热门应用接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping( "/hotApp" )
public class HotAppController {
    @Autowired
    private HotAppService hotAppService;

    /**
     * 添加热门应用
     *
     * @param appIdList 选项卡上的热门应用id,json数组
     * @return
     */
    @ApiOperation( value = "添加热门应用", notes = "添加热门应用", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "appIdList", value = "apid数组", paramType = "path", required = true, example = "[1,2,3]" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @PostMapping
    public RestRecord addHotRecommendAppList( @RequestBody List< String > appIdList ) {

        String userId = "0110100";//userId从后端取

        return hotAppService.addHotRecommendAppList( appIdList, userId );
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
        // 查询应用表中热门推荐状态为1的应用
        return hotAppService.selectHotRecommendAppList( pageNum, pageSize );
    }

}
