package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.model.HistoryAdvise;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.HistoryAdviseService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 历史消息通知接口
 */
@Slf4j
@RestController
@Api( value = "历史消息通知接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RequestMapping( "/historyAdvise" )
public class HistoryAdviseController {
    @Autowired
    private HistoryAdviseService historyHistoryAdviseService;

    /**
     * 添加historyHistoryAdvise
     *
     * @param historyHistoryAdvise 信息
     * @return 是否添加成功
     */
    @ApiOperation( value = "添加historyHistoryAdvise", notes = "添加historyHistoryAdvise", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "historyHistoryAdvise", value = "advice信息", paramType = "body", required = true),
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertHistoryAdvise( HistoryAdvise historyHistoryAdvise ) {
        return historyHistoryAdviseService.insertHistoryAdvise( historyHistoryAdvise );
    }

    /**
     * 通过id删除historyHistoryAdvise
     *
     * @param historyHistoryAdviseId id
     * @return 删除是否成功
     */
    @ApiOperation( value = "通过id删除historyHistoryAdvise", notes = "通过id删除historyHistoryAdvise", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "adviseId", value = "消息id", paramType = "header", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @DeleteMapping( "/{historyHistoryAdviseId}" )
    @ResponseBody
    public RestRecord deleteHistoryAdviseById( @PathVariable( "historyHistoryAdviseId" )String historyHistoryAdviseId ) {
        return historyHistoryAdviseService.deleteHistoryAdviseById( historyHistoryAdviseId );
    }

    /**
     * 更新historyHistoryAdvise
     *
     * @param historyHistoryAdvise historyHistoryAdvise信息
     * @return historyHistoryAdvise
     */
    @ApiOperation( value = "更新historyHistoryAdvise", notes = "更新historyHistoryAdvise", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "historyHistoryAdvise", value = "historyHistoryAdvise信息", paramType = "body", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @PutMapping
    @ResponseBody
    public RestRecord updateHistoryAdviseInfo( HistoryAdvise historyHistoryAdvise ) {
        return historyHistoryAdviseService.updateHistoryAdviseInfo( historyHistoryAdvise );
    }

    /**
     * 获取historyHistoryAdvise数据
     *
     * @param historyHistoryAdviseId historyHistoryAdviseId
     * @return historyHistoryAdvise数据
     */
    @ApiOperation( value = "获取historyHistoryAdvise数据", notes = "获取historyHistoryAdvise数据", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "historyHistoryAdviseId", value = "消息id", paramType = "header", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/{historyHistoryAdviseId}" )
    @ResponseBody
    public RestRecord getHistoryAdviseById( @PathVariable( "historyHistoryAdviseId" )String historyHistoryAdviseId ) {
        return historyHistoryAdviseService.getHistoryAdviseById( historyHistoryAdviseId );
    }

    /**
     * 获取所有historyHistoryAdvise数据
     *
     * @return historyHistoryAdvise数据list
     */
    @ApiOperation( value = "获取所有historyHistoryAdvise数据", notes = "获取所有historyHistoryAdvise数据", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = "成功", response = RestRecord.class )
    } )
    @GetMapping
    @ResponseBody
    public RestRecord getAllHistoryAdvisesInfo() {
        return historyHistoryAdviseService.getAllHistoryAdvisesInfo();
    }
}
