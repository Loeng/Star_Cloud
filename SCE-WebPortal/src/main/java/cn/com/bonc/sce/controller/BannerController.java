package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.Banner;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.BannerService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * banner接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Slf4j
@RestController
@Api( value = "banner接口", tags = "banner接口"  )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RequestMapping( "/banners" )
public class BannerController {
    @Autowired
    private BannerService bannerService;

    /**
     * 添加banner
     *
     * @param banner 信息
     * @return 是否添加成功
     */
    @ApiOperation( value = "添加banner", notes = "添加banner", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertBanner( @RequestBody @ApiParam( name = "banner", value = "banner信息", required = true ) Banner banner ) {
        return bannerService.insertBanner( banner );
    }

    /**
     * 通过id删除banner
     *
     * @param bannerId id
     * @return 删除是否成功
     */
    @ApiOperation( value = "通过id删除banner", notes = "通过id删除banner", httpMethod = "DELETE" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 408, message = MessageConstants.SCE_MSG_408, response = RestRecord.class )
    } )
    @DeleteMapping( "/{bannerId}" )
    @ResponseBody
    public RestRecord deleteBannerById( @PathVariable( "bannerId" ) @ApiParam( name = "bannerId", value = "bannerId", required = true ) String bannerId ) {
        return bannerService.deleteBannerById( bannerId );
    }

    /**
     * 更新banner
     *
     * @param banner banner信息
     * @return banner
     */
    @ApiOperation( value = "更新banner", notes = "更新banner", httpMethod = "PUT" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PutMapping
    @ResponseBody
    public RestRecord updateBannerInfo( @RequestBody @ApiParam( name = "banner", value = "banner信息", required = true ) Banner banner ) {
        return bannerService.updateBannerInfo( banner );
    }

    /**
     * 修改url
     *
     * @param banner banner信息
     * @return 跟新是否成功
     */
    @ApiOperation( value = "修改url", notes = "修改url", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "bannerId", value = "bannerId", paramType = "body", required = true ),
            @ApiImplicitParam( name = "url", value = "待修改的url", paramType = "body", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PutMapping( "/url" )
    @ResponseBody
    public RestRecord updateBannerUrl( Banner banner ) {
        return bannerService.updateBannerUrl( banner );
    }

    /**
     * 修改应用id
     *
     * @param banner banner信息
     * @return 跟新是否成功
     */
    @ApiOperation( value = "修改appId", notes = "修改appId", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "bannerId", value = "bannerId", paramType = "body", required = true ),
            @ApiImplicitParam( name = "appId", value = "appId", paramType = "body", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PutMapping( "/appId" )
    @ResponseBody
    public RestRecord updateBannerAppId( Banner banner ) {
        return bannerService.updateBannerAppId( banner );
    }

    /**
     * 修改轮播次序
     *
     * @param list bannerId
     * @return 修改结果
     */
    @ApiOperation( value = "修改轮播次序", notes = "修改轮播次序", httpMethod = "PUT" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 407, message = MessageConstants.SCE_MSG_407, response = RestRecord.class )
    } )
    @PutMapping( "/update-banner-order" )
    @ResponseBody
    public RestRecord updateBannerOrder( @RequestParam( "list" ) @ApiParam( name = "list", value = "次序列表", required = true ) List<Integer> list ) {
        return bannerService.updateBannerOrder( list );
    }

    /**
     * 获取banner数据
     *
     * @param bannerId bannerId
     * @return banner数据
     */
    @ApiOperation( value = "获取banner数据", notes = "获取banner数据", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping( "/{bannerId}" )
    @ResponseBody
    public RestRecord getBannerById( @PathVariable( "bannerId" ) @ApiParam( name = "bannerId", value = "bannerId", required = true ) Integer bannerId ) {
        return bannerService.getBannerById( bannerId );
    }

    /**
     * 获取所有banner数据
     *
     * @return banner数据list
     */
    @ApiOperation( value = "获取所有banner数据", notes = "获取所有banner数据", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping
    @ResponseBody
    public RestRecord getAllBannersInfo() {
        return bannerService.getAllBannersInfo();
    }
}
