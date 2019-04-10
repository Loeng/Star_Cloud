package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.NewsModel;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.NewsInfoService;
import cn.com.bonc.sce.service.NewsService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author BTW
 */
@Slf4j
@Api( value = "新闻相关接口", tags = "新闻相关接口" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RestController
@RequestMapping( "/news-info" )
public class NewsInfoController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsInfoService newsInfoService;

    /**
     * 新增教育新闻
     *
     * @param newsModel 新闻
     * @return 添加新闻是否成功
     */
    @ApiOperation( value = "新增教育新闻", notes = "新增教育新闻", httpMethod = "POST" )
    @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping( "/new-info" )
    @ResponseBody
    public RestRecord insertNews( @RequestBody @ApiParam( name = "news", value = "新闻信息", required = true ) NewsModel newsModel,
                                  @CurrentUserId @ApiParam( hidden = true ) String userId
    ) {

        newsModel.setCreateUserId( userId );
        return newsInfoService.insertNewsInfo( newsModel );
    }

    /**
     * 删除教育新闻
     *
     * @param idList 新闻Id列表
     * @return 删除新闻是否成功
     */
    @ApiOperation( value = "删除教育新闻", notes = "删除教育新闻", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "idList", value = "新闻Id,json数组", paramType = "body", required = true ),
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 408, message = MessageConstants.SCE_MSG_408, response = RestRecord.class )
    } )
    @DeleteMapping
    @ResponseBody
    public RestRecord deleteNewsByIdList( @RequestBody List< Long > idList,
                                          @CurrentUserId @ApiParam( hidden = true ) String userId
    ) {
        return newsInfoService.deleteNewsInfo( idList, userId );
    }


    /**
     * 更改教育新闻
     *
     * @param newsModel 新闻
     * @return 更新新闻是否成功
     */
    @ApiOperation( value = "更改教育新闻", notes = "更改教育新闻", httpMethod = "POST" )
    @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PostMapping( "/updated-info" )
    @ResponseBody
    public RestRecord updateNews( @RequestBody @ApiParam( name = "news", value = "新闻信息", required = true ) NewsModel newsModel,
                                  @CurrentUserId @ApiParam( hidden = true ) String userId
    ) {
        newsModel.setUpdateUserId( userId );
        return newsInfoService.updateNewsInfo( newsModel );
    }

    /**
     * 新闻审核接口
     *
     * @return
     */
    @ApiOperation( value = "新闻审核接口", notes = "新闻审核接口", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "contentStatus", value = "新闻审核状态，1为通过审核，2为驳回", paramType = "query", required = true ),
            @ApiImplicitParam( name = "rejectOpinion", value = "驳回意见", paramType = "query", required = false ),
            @ApiImplicitParam( name = "contentId", value = "新闻ID", paramType = "query", required = true ),
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping( "/audit-news" )
    @ResponseBody
    public RestRecord updateAppTypeName( @RequestParam( "contentStatus" ) String contentStatus,
                                         @RequestParam( "rejectOpinion" ) String rejectOpinion,
                                         @RequestParam( "contentId" ) Long contentId,
                                         @CurrentUserId @ApiParam( hidden = true ) String userId
    ) {
        return newsInfoService.auditNewsInfo( contentStatus, userId, rejectOpinion, contentId );
    }


}
