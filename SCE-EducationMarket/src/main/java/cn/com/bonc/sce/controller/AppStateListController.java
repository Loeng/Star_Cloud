package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.dao.AppStateListDao;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api( value = "应用审核列表接口", tags = "应用审核列表接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping( "/app-state" )
public class AppStateListController {

    @Autowired
    private AppStateListDao appStateListDao;

    @ApiOperation( value = "应用列表查询", notes = "应用列表查询", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @GetMapping( "/{state}" )
    public RestRecord getStateList( @ApiParam( "1运营中2上架审核 3迭代审核 4下架审核，5下架" ) @PathVariable( "state" ) Integer state,
                                    @ApiParam( "审核状态（0全部，1，待审核2，已驳回）" ) @RequestParam( value = "auditStatus", required = false ) String auditStatus,
                                    @ApiParam( "应用类型id" ) @RequestParam( value = "typeId", required = false, defaultValue = "0" ) Integer typeId,
                                    @ApiParam( "应用名称" ) @RequestParam( value = "keyword", required = false ) String keyword,
                                    @ApiParam( "排序字段，暂未实现排序" ) @RequestParam( value = "orderBy", required = false ) String orderBy,
                                    @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                                    @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize,
                                    @CurrentUserId @ApiParam( hidden = true ) String userId
    ) {
        return appStateListDao.getStateList( state, auditStatus, typeId, keyword, orderBy, pageNum, pageSize, userId );
    }


}
