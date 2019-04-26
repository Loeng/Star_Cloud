package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.NewsRecordModel;
import cn.com.bonc.sce.model.NewsReportModel;
import cn.com.bonc.sce.model.ReportApproveModel;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.NewsRecordService;
import cn.com.bonc.sce.service.NewsReportService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author szy
 */
@Slf4j
@Api( value = "新闻举报相关接口", tags = "新闻举报相关接口" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RestController
@RequestMapping( "/news-report" )
public class NewsReportController {

    @Autowired
    private NewsReportService newsReportService;

    /**
     * 新增新闻举报
     *
     * @param newsReportModel 新闻举报记录
     * @return 添加新闻是否成功
     */
    @ApiOperation( value = "新增新举报记录(登陆用户用)", notes = "新增新举报记录(登陆用户用)", httpMethod = "POST" )
    @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping( "/login-news-report" )
    @ResponseBody
    public RestRecord insertReport4LoginUser( @RequestBody @ApiParam( name = "newsReport", value = "新闻举报信息", required = true ) NewsReportModel newsReportModel,
                                  @CurrentUserId @ApiParam( hidden = true ) String userId
    ) {
        newsReportModel.setReportUserId( userId );
        return newsReportService.insertReort( newsReportModel );
    }

    /**
     * 新增新闻举报
     *
     * @param newsReportModel 新闻举报记录
     * @return 添加新闻是否成功
     */
    @ApiOperation( value = "新增新举报记录(游客用)", notes = "新增新举报记录(游客用)", httpMethod = "POST" )
    @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping( "/news-report" )
    @ResponseBody
    public RestRecord insertReport4Customer( @RequestBody @ApiParam( name = "newsReport", value = "新闻举报信息", required = true ) NewsReportModel newsReportModel
    ) {
        return newsReportService.insertReort( newsReportModel );
    }



    @ApiOperation( value = "获取新闻举报列表", notes = "获取新闻举报列表", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header", required = false )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PostMapping( "/getReportList/{pageSize}/{pageNum}" )
    @ResponseBody
    public RestRecord getReportList(@PathVariable( "pageSize" ) @ApiParam( "分页条数" ) Integer pageSize,
                                 @PathVariable( "pageNum" ) @ApiParam( "分页页数" ) Integer pageNum,
                                 @RequestBody NewsReportModel newsModel) {
        return newsReportService.selectReportList(pageSize, pageNum,newsModel);
    }

    @ApiOperation( value = "获取新闻举报详情", notes = "获取新闻举报详情", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header", required = false )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PostMapping( "/selectReportInfo" )
    @ResponseBody
    public RestRecord selectReportInfo(@RequestParam( "reportId" ) String reportId) {
        NewsReportModel bean = new NewsReportModel();
        bean.setReportId(reportId);
        return newsReportService.selectReportInfo(bean);
    }


    @ApiOperation( value = "审核举报", notes = "审核举报", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header", required = false )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PostMapping( "/reportApprove" )
    @ResponseBody
    public RestRecord reportApprove(@RequestBody ReportApproveModel newsModel
            ,@CurrentUserId @ApiParam( hidden = true) String userId
    ) {
        newsModel.setApproveUserId(userId);
        return newsReportService.reportApprove(newsModel);
    }
}
