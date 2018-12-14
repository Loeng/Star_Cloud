package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.model.Advise;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AdviseService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 消息通知接口
 */
@Slf4j
@RestController
@Api( value = "消息通知接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RequestMapping( "/advise" )
public class AdviseController {
    @Autowired
    private AdviseService adviseService;

    /**
     * 添加advise
     *
     * @param advise 信息
     * @return 是否添加成功
     */
    @ApiOperation( value = "添加advise", notes = "添加advise信息", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "advise", value = "advice信息", paramType = "body", required = true),
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertAdvise( Advise advise ) {
        return adviseService.insertAdvise( advise );
    }

    /**
     * 通过id删除advise
     *
     * @param adviseId id
     * @return 删除是否成功
     */
    @ApiOperation( value = "通过id删除advise", notes = "通过id删除advise", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "adviseId", value = "消息id", paramType = "header", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @DeleteMapping( "/{adviseId}" )
    @ResponseBody
    public RestRecord deleteAdviseById( @PathVariable( "adviseId" )String adviseId ) {
        return adviseService.deleteAdviseById( adviseId );
    }

    /**
     * 更新advise
     *
     * @param advise advise信息
     * @return advise
     */
    @ApiOperation( value = "更新advise", notes = "更新advise", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "advise", value = "advice信息", paramType = "body", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @PutMapping
    @ResponseBody
    public RestRecord updateAdviseInfo( Advise advise ) {
        return adviseService.updateAdviseInfo( advise );
    }

    /**
     * 获取advise数据
     *
     * @param adviseId adviseId
     * @return advise数据
     */
    @ApiOperation( value = "获取advise数据", notes = "获取advise数据", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "adviseId", value = "消息id", paramType = "header", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/{adviseId}" )
    @ResponseBody
    public RestRecord getAdviseById( @PathVariable( "adviseId" )String adviseId ) {
        return adviseService.getAdviseById( adviseId );
    }

    /**
     * 获取所有advise数据
     *
     * @return advise数据list
     */
    @ApiOperation( value = "获取所有advise数据", notes = "获取所有advise数据", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @GetMapping
    @ResponseBody
    public RestRecord getAllAdvisesInfo() {
        return adviseService.getAllAdvisesInfo();
    }
}
