package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/newsOperation")
public class NewsController {

    /**
     * 查询新闻列表接口
     * @param auditStatus 新闻审核状态
     * @param startDate 查询起始日期
     * @param endDate 查询结束日期
     * @param pageNum 分页页码
     * @param pageSize 分页每页条数
     * @return 分页后的新闻列表
     */
    @ApiOperation( value = "查询新闻列表接口", notes = "查询新闻列表接口", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "auditStatus", dataType = "String", value = "审核状态", paramType = "path", required = false ) ,
            @ApiImplicitParam( name = "startDate", dataType = "String", value = "查询起始日期", paramType = "path", required = false ) ,
            @ApiImplicitParam( name = "endDate", dataType = "String", value = "查询结束日期", paramType = "path", required = false ) ,
            @ApiImplicitParam( name = "pageNum", dataType = "Integer", value = "分页参数-页码", paramType = "path", required = true ) ,
            @ApiImplicitParam( name = "pageSize", dataType = "Integer", value = "分页参数-每页条数", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )
    @GetMapping("/{pageNum}/{pageSize}/{auditingStatus}/{startDate}/{endDate}")
    @ResponseBody
    public RestRecord getNewsList ( @PathVariable( "pageNum" ) Integer pageNum,
                                    @PathVariable( "pageSize" ) Integer pageSize,
                                    @PathVariable( "auditStatus" ) String auditStatus,
                                    @PathVariable( "startDate" ) String startDate,
                                    @PathVariable( "endDate" ) String endDate ) {
        // 在common_column_content表中查询所有栏目id为教育新闻的数据，返回其标题，图片，内容和时间信息
        return null;
    }

    /**
     * 查询新闻详情接口
     * @param newsId 新闻id
     * @return 分页后的新闻列表
     */
    @ApiOperation( value = "查询新闻详情接口", notes = "查询新闻详情接口", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "newsId", dataType = "String", value = "新闻id", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )
    @GetMapping("/{newsId}")
    @ResponseBody
    public RestRecord getNewsDetail ( @PathVariable( "newsId" )  String newsId ) {
        // 在common_column_content表中查询对应id的教育新闻数据，返回其标题，图片，内容详情和时间信息
        return null;
    }

    /**
     * 新增教育新闻列表接口
     * @param newsTitle 新闻标题
     * @param newsContent 新闻内容
     * @param picId 图片信息
     * @return 添加新闻是否成功
     */
    @ApiOperation( value = "新增教育新闻接口", notes = "新增教育新闻接口", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "newsTitle", dataType = "String", value = "新闻标题", paramType = "query", required = true ) ,
            @ApiImplicitParam( name = "newsContent", dataType = "String", value = "新闻内容", paramType = "query", required = true ) ,
            @ApiImplicitParam( name = "picId", dataType = "String", value = "对应图片信息", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord addNews ( @RequestParam( "newsTitle" ) String newsTitle,
                                @RequestParam( "newsContent" ) String newsContent,
                                @RequestParam( "picId" ) String picId ) {
        return null;
    }

    /**
     * 更改教育新闻列表接口
     * @param newsId 新闻Id
     * @param newsTitle 新闻标题
     * @param newsContent 新闻内容
     * @param picId 图片信息
     * @return 更新新闻是否成功
     */
    @ApiOperation( value = "更改教育新闻接口", notes = "更改教育新闻接口", httpMethod = "PATCH" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "newsTitle", dataType = "String", value = "新闻标题", paramType = "query", required = true ) ,
            @ApiImplicitParam( name = "newsContent", dataType = "String", value = "新闻内容", paramType = "query", required = true ) ,
            @ApiImplicitParam( name = "picId", dataType = "String", value = "对应图片信息", paramType = "query", required = true ) ,
            @ApiImplicitParam( name = "newsId", dataType = "String", value = "新闻ID", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )
    @PatchMapping
    @ResponseBody
    public RestRecord updateNewsDetail ( @RequestParam( "newsTitle" ) String newsTitle,
                                         @RequestParam( "newsContent" ) String newsContent,
                                         @RequestParam( "picId" ) String picId,
                                         @RequestParam( "newsId" ) String newsId ) {
        return null;
    }

    /**
     * 删除教育新闻列表接口
     * @param newsIdList 新闻Id列表
     * @return 删除新闻是否成功
     */
    @ApiOperation( value = "删除教育新闻接口", notes = "删除教育新闻接口", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "newsIdList", dataType = "List", value = "新闻Id列表", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_000, response = RestRecord.class )
    } )
    @DeleteMapping("/{newsIdList}")
    @ResponseBody
    public RestRecord updateNewsDetail ( @PathVariable( "newsIdList" ) String newsIdList ) {
        return null;
    }
}
