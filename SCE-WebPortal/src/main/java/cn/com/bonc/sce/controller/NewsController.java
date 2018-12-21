package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.PortalMessageConstants;
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
@Api( value = "新闻增删改相关接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
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
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "title", dataType = "String", value = "新闻标题", paramType = "query", required = true ),
            @ApiImplicitParam( name = "content", dataType = "String", value = "新闻内容", paramType = "query", required = true ),
            @ApiImplicitParam( name = "picId", dataType = "Integer", value = "对应图片信息", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertNews( News news ) {
        return newsService.insertNews( news );
    }

    /**
     * 删除教育新闻
     *
     * @param list 新闻Id列表
     * @return 删除新闻是否成功
     */
    @ApiOperation( value = "删除教育新闻", notes = "删除教育新闻", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "list", dataType = "String", value = "新闻Id列表", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 408, message = MessageConstants.SCE_MSG_408, response = RestRecord.class )
    } )
    @DeleteMapping( "/{list}" )
    @ResponseBody
    public RestRecord deleteNewsByIdList( @PathVariable( "list" ) String list ) {
        return newsService.deleteNewsByIdList( list );
    }


    /**
     * 更改教育新闻
     *
     * @param news 新闻
     * @return 更新新闻是否成功
     */
    @ApiOperation( value = "更改教育新闻", notes = "更改教育新闻", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "newsId", dataType = "Integer", value = "新闻ID", paramType = "query", required = true ),
            @ApiImplicitParam( name = "title", dataType = "String", value = "新闻标题", paramType = "query", required = true ),
            @ApiImplicitParam( name = "content", dataType = "String", value = "新闻内容", paramType = "query", required = true ),
            @ApiImplicitParam( name = "picId", dataType = "Integer", value = "对应图片信息", paramType = "query", required = true ),
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PutMapping
    @ResponseBody
    public RestRecord updateNews( News news ) {
        return newsService.updateNews( news );
    }

    /**
     * 查询新闻列表接口
     *
     * @param auditStatus 新闻审核状态
     * @param startDate   查询起始日期
     * @param endDate     查询结束日期
     * @param pageNum     分页页码
     * @param pageSize    分页每页条数
     * @return 分页后的新闻列表
     */
    @ApiOperation( value = "查询新闻列表接口", notes = "查询新闻列表接口", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "auditStatus", dataType = "String", value = "审核状态", paramType = "path", required = false ),
            @ApiImplicitParam( name = "startDate", dataType = "String", value = "查询起始日期", paramType = "path", required = false ),
            @ApiImplicitParam( name = "endDate", dataType = "String", value = "查询结束日期", paramType = "path", required = false ),
            @ApiImplicitParam( name = "pageNum", dataType = "Integer", value = "分页参数-页码", paramType = "path", required = true ),
            @ApiImplicitParam( name = "pageSize", dataType = "Integer", value = "分页参数-每页条数", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping( "/{auditStatus}/{startDate}/{endDate}/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getNewsList( @PathVariable( "auditStatus" ) String auditStatus,
                                   @PathVariable( "startDate" ) String startDate,
                                   @PathVariable( "endDate" ) String endDate,
                                   @PathVariable( "pageNum" ) Integer pageNum,
                                   @PathVariable( "pageSize" ) Integer pageSize) {
        return newsService.getNewsList( auditStatus, startDate, endDate, pageNum, pageSize );
    }

    /**
     * 查询新闻详情接口
     *
     * @param newsId 新闻id
     * @return 分页后的新闻列表
     */
    @ApiOperation( value = "查询新闻详情接口", notes = "查询新闻详情接口", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "newsId", dataType = "Integer", value = "新闻id", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping( "/{newsId}" )
    @ResponseBody
    public RestRecord getNews( @PathVariable( "newsId" ) Integer newsId ) {
        return newsService.getNews( newsId );
    }
}
