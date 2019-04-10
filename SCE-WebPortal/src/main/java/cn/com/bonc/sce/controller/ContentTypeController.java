package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.ContentTypeService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author BTW
 */
@Slf4j
@Api( value = "新闻栏目类别相关接口", tags = "新闻栏目类别相关接口" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RestController
@RequestMapping( "/content-type" )
public class ContentTypeController {

    @Autowired
    private ContentTypeService contentTypeService;

    /**
     * 新闻栏目类型信息查询接口
     *
     * @return
     */
    @ApiOperation( value = "新闻栏目类型信息查询接口", notes = "查询新闻栏目类型信息接口", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping
    @ResponseBody
    public RestRecord getAllContentTypeList() {
        return contentTypeService.getAllContentTypeList();
    }

    /**
     * 新闻栏目类型信息新增接口
     *
     * @return
     */
    @ApiOperation( value = "新闻栏目类型信息新增接口", notes = "新增新闻栏目类型信息接口", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "contentType", dataType = "String", value = "新增新闻栏目名称", paramType = "query", required = true ),
            @ApiImplicitParam( name = "isShown", value = "新闻栏目是否显示", paramType = "query", required = true ),
            @ApiImplicitParam( name = "showOrder", value = "新闻栏目显示顺序", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord addContentType( @RequestParam( "contentType" ) String contentType,
                                      @RequestParam( "isShown" ) Integer isShown,
                                      @RequestParam( "showOrder" ) Integer showOrder ) {
        if ( isShown == null ) {
            isShown = 1;
        }
        if ( showOrder == null ) {
            showOrder = 99;
        }
        return contentTypeService.addContentTypeInfo( contentType, isShown, showOrder );
    }

    /**
     * 新闻栏目类型信息更改接口
     *
     * @return
     */
    @ApiOperation( value = "新闻栏目类型信息更改接口", notes = "更改新闻栏目类型信息接口", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "id", value = "更改新闻栏目类型ID", paramType = "query", required = true ),
            @ApiImplicitParam( name = "contentType", dataType = "String", value = "更改新闻栏目名称", paramType = "query", required = true ),
            @ApiImplicitParam( name = "isShown", value = "新闻栏目是否显示", paramType = "query", required = true ),
            @ApiImplicitParam( name = "showOrder", value = "新闻栏目显示顺序", paramType = "query", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping( "/new-type-info" )
    @ResponseBody
    public RestRecord updateAppTypeName( @RequestParam( "id" ) Integer id,
                                         @RequestParam( "contentType" ) String contentType,
                                         @RequestParam( "isShown" ) Integer isShown,
                                         @RequestParam( "showOrder" ) Integer showOrder ) {
        return contentTypeService.updateContentTypeInfo( id, contentType, isShown, showOrder );
    }

    /**
     * @return
     */
    @ApiOperation( value = "新闻栏目类型删除接口", notes = "根据Id删除对应新闻栏目类型", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "id", dataType = "Integer", value = "删除的新闻栏目类型ID", paramType = "path", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @DeleteMapping( "/{id}" )
    @ResponseBody
    public RestRecord deleteContentTypeById( @PathVariable( "id" ) Integer id ) {
        return contentTypeService.deleteContentTypeInfo( id );
    }
}
