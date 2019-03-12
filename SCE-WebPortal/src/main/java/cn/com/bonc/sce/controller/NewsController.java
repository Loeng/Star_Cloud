package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.News;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.NewsService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 新闻增删改相关接口
 *
 * @author BTW
 * @version 0.1
 * @since 2018/12/19 14:26
 */
@Slf4j
@Api( value = "新闻增删改相关接口", tags = "新闻增删改相关接口" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RestController
@RequestMapping( "/news" )
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * 新增教育新闻
     *
     * @param news 新闻
     * @return 添加新闻是否成功
     */
    @ApiOperation( value = "新增教育新闻", notes = "新增教育新闻", httpMethod = "POST" )
    @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertNews( @RequestBody @ApiParam( name = "news", value = "新闻信息", required = true ) News news,@CurrentUserId @ApiParam( hidden = true) String userId ) {
        news.setCreateUserId( userId );
        return newsService.insertNews( news );
    }

    /**
     * 删除教育新闻
     *
     * @param list 新闻Id列表
     * @return 删除新闻是否成功
     */
    @ApiOperation( value = "删除教育新闻", notes = "删除教育新闻", httpMethod = "DELETE" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 408, message = MessageConstants.SCE_MSG_408, response = RestRecord.class )
    } )
    @DeleteMapping( "" )
    @ResponseBody
    public RestRecord deleteNewsByIdList( @RequestParam(value = "list") @ApiParam( name = "list", value = "待删除的新闻列表", required = true ) String list ) {
        return newsService.deleteNewsByIdList( list );
    }


    /**
     * 更改教育新闻
     *
     * @param news 新闻
     * @return 更新新闻是否成功
     */
    @ApiOperation( value = "更改教育新闻", notes = "更改教育新闻", httpMethod = "PUT" )
    @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PutMapping
    @ResponseBody
    public RestRecord updateNews( @RequestBody @ApiParam( name = "news", value = "新闻信息", required = true ) News news,@CurrentUserId @ApiParam( hidden = true) String userId ) {
        news.setUpdateUserId( userId );
        return newsService.updateNews( news );
    }

    /**
     * 查询新闻列表接口
     *
     * @param auditStatus 新闻审核状态
     * @param content     内容
     * @param startDate   查询起始日期
     * @param endDate     查询结束日期
     * @param pageNum     分页页码
     * @param pageSize    分页每页条数
     * @return 分页后的新闻列表
     */
    @ApiOperation( value = "查询新闻列表接口", notes = "查询新闻列表接口", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping( "/list/{auditStatus}" )
    @ResponseBody
    public RestRecord getNewsList( @PathVariable( "auditStatus" ) @ApiParam( name = "auditStatus", value = "状态") String auditStatus,
                                   @RequestParam( value = "content", required = false ) @ApiParam( name = "content", value = "内容") String content,
                                   @RequestParam( value = "startDate", required = false ) @ApiParam( name = "startDate", value = "开始时间") String startDate,
                                   @RequestParam( value = "endDate", required = false ) @ApiParam( name = "endDate", value = "结束时间") String endDate,
                                   @RequestParam( value = "pageNum", required = false, defaultValue = "1"  ) @ApiParam( name = "pageNum", value = "页码")Integer pageNum,
                                   @RequestParam( value = "pageSize", required = false, defaultValue = "10"  ) @ApiParam( name = "pageSize", value = "数量")Integer pageSize ) {
        return newsService.getNewsList( auditStatus,content, startDate, endDate, pageNum, pageSize );
    }

    /**
     * 查询新闻详情接口
     *
     * @param newsId 新闻id
     * @return 分页后的新闻列表
     */
    @ApiOperation( value = "查询新闻详情接口", notes = "查询新闻详情接口", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping( "/{newsId}" )
    @ResponseBody
    public RestRecord getNews( @PathVariable( "newsId" ) @ApiParam( name = "newsId", value = "新闻id" ,required = true) Integer newsId ) {
        return newsService.getNews( newsId );
    }
}
