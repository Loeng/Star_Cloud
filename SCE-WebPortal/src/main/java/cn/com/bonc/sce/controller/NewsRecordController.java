package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.NewsRecordModel;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.NewsRecordService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author BTW
 */
@Slf4j
@Api( value = "新闻浏览记录相关接口", tags = "新闻浏览记录相关接口" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RestController
@RequestMapping( "/news-record" )
public class NewsRecordController {

    @Autowired
    private NewsRecordService newsRecordService;

    /**
     * 新增教育新闻
     *
     * @param newsRecordModel 新闻浏览记录
     * @return 添加新闻是否成功
     */
    @ApiOperation( value = "新增新闻浏览记录", notes = "新增新闻浏览记录", httpMethod = "POST" )
    @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping( "/new-record" )
    @ResponseBody
    public RestRecord insertNews( @RequestBody @ApiParam( name = "newsRecord", value = "新闻浏览信息", required = true ) NewsRecordModel newsRecordModel
//            ,
//                                  @CurrentUserId @ApiParam( hidden = true ) String userId
    ) {
        newsRecordModel.setUserId( "cbba6bdd709c4095866d7e1ac95f05a8" );
        return newsRecordService.insertNewsRecord( newsRecordModel );
    }
}
