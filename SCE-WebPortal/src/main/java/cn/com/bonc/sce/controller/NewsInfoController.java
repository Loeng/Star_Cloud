package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.NewsModel;
import cn.com.bonc.sce.model.NewsStatusModel;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.NewsInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 删除头条新闻
     *
     * @param idList 新闻Id列表
     * @return 删除头条新闻是否成功
     */
    @ApiOperation( value = "删除头条新闻", notes = "删除头条新闻", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "idList", value = "新闻Id,json数组", paramType = "body", required = true ),
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 408, message = MessageConstants.SCE_MSG_408, response = RestRecord.class )
    } )
    @DeleteMapping( "/top-news" )
    @ResponseBody
    public RestRecord deleteTopNewsByIdList( @RequestBody List< Long > idList,
                                             @CurrentUserId @ApiParam( hidden = true ) String userId
    ) {
        return newsInfoService.deleteTopNewsInfo( idList, userId );
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
//            @ApiImplicitParam( name = "contentStatus", value = "新闻审核状态，1为通过审核，2为驳回", paramType = "query", required = true ),
//            @ApiImplicitParam( name = "rejectOpinion", value = "驳回意见", paramType = "query", required = false ),
//            @ApiImplicitParam( name = "contentId", value = "新闻ID", paramType = "query", required = true ),
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping( "/audit-news" )
    @ResponseBody
    public RestRecord updateNewsAuditStatus( @RequestBody @ApiParam( name = "news", value = "新闻审核状态信息", required = true ) NewsStatusModel newsStatusModel,
                                             @CurrentUserId @ApiParam( hidden = true ) String userId
    ) {
        String contentStatus = newsStatusModel.getContentStatus();
        String rejectOpinion = newsStatusModel.getRejectOpinion();
        Long contentId = newsStatusModel.getContentId();
        return newsInfoService.auditNewsInfo( contentStatus, userId, rejectOpinion, contentId );
    }

    /**
     * 新闻发布状态修改接口
     *
     * @return
     */
    @ApiOperation( value = "新闻发布状态修改接口", notes = "新闻发布状态修改接口", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping( "/news-publish-status" )
    @ResponseBody
    public RestRecord updateNewsPublishStatus( @RequestBody @ApiParam( name = "news", value = "新闻发布状态信息", required = true ) NewsStatusModel newsStatusModel,
                                               @CurrentUserId @ApiParam( hidden = true ) String userId
    ) {
        Long contentId = newsStatusModel.getContentId();
        Integer isPublish = newsStatusModel.getIsPublish();
        return newsInfoService.updateNewsPublishStatus( isPublish, userId, contentId );
    }

    /**
     * 新闻详细信息查询接口
     *
     * @return
     */
    @ApiOperation( value = "新闻详细信息查询接口", notes = "新闻详细信息查询接口", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "contentId", value = "新闻ID", paramType = "query" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping( "/one-news-info" )
    @ResponseBody
    public RestRecord selectNewsById( @RequestParam( "contentId" ) Long contentId ) {
        return newsInfoService.selectNewsById( contentId );
    }

    /**
     * 获取所新闻列表
     * @return
     */
    @ApiOperation( value = "根据类型获取新闻分页列表", notes = "根据类型获取新闻分页列表", httpMethod = "POST" )

    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header", required = false )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PostMapping( "/getNewsListByType/{pageSize}/{pageNum}" )
    @ResponseBody
    public RestRecord getAllNews(@PathVariable( "pageSize" ) @ApiParam( "分页条数" ) Integer pageSize,
                                 @PathVariable( "pageNum" ) @ApiParam( "分页页数" ) Integer pageNum,
                                 @RequestBody NewsModel newsModel) {
        return newsInfoService.getAllNews(pageSize, pageNum,newsModel);
    }

    /**
     * 后台头条新闻管理列表查询接口
     *
     * @return
     */
    @ApiOperation( value = "后台头条新闻管理列表查询接口", notes = "后台头条新闻管理列表查询接口", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping( "/top-manage-list" )
    @ResponseBody
    public RestRecord selectTopNewsList() {
        return newsInfoService.selectTopNewsList();
    }

    @ApiOperation( value = "后台头条新闻管理接口", notes = "后台头条新闻管理接口", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping( "/top-order-change" )
    @ResponseBody
    public RestRecord updateTopNewsInfo( @RequestBody @ApiParam( name = "news", value = "新闻信息", required = true ) List< NewsStatusModel > newsList,
                                         @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return newsInfoService.updateTopNewsInfo( newsList, userId );
    }

    @ApiOperation( value = "后台头条新闻添加接口", notes = "后台头条新闻添加接口", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "idList", value = "新闻Id,json数组", paramType = "body", required = true ),
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping( "/new-top-news" )
    @ResponseBody
    public RestRecord addTopNewsInfo( @RequestBody List< Long > idList,
                                      @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        Map map = new HashMap<>( 2 );
        map.put( "idList", idList );
        map.put( "userId", userId );
        return newsInfoService.addTopNewsInfo( map );
    }
}
