package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.PortalMessageConstants;
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
@Api( value = "banner接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
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
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "banner", value = "banner信息", paramType = "body", required = true )

    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertBanner( Banner banner ) {
        return bannerService.insertBanner( banner );
    }

    /**
     * 通过id删除banner
     *
     * @param bannerId id
     * @return 删除是否成功
     */
    @ApiOperation( value = "通过id删除banner", notes = "通过id删除banner", httpMethod = "DELETE" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "bannerId", value = "bannerId", paramType = "header", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @DeleteMapping( "/{bannerId}" )
    @ResponseBody
    public RestRecord deleteBannerById( @PathVariable( "bannerId" )String bannerId ) {
        return bannerService.deleteBannerById( bannerId );
    }

    /**
     * 更新banner
     *
     * @param banner banner信息
     * @return banner
     */
    @ApiOperation( value = "更新banner", notes = "更新banner", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "banner", value = "banner信息", paramType = "body", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PutMapping
    @ResponseBody
    public RestRecord updateBannerInfo( Banner banner ) {
        return bannerService.updateBannerInfo( banner );
    }

    /**
     * 修改url
     *
     * @param bannerId bannerId
     * @param url      待修改的url
     * @return 跟新是否成功
     */
    @ApiOperation( value = "修改url", notes = "修改url", httpMethod = "PATCH" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "bannerId", value = "bannerId", paramType = "header", required = true ),
            @ApiImplicitParam( name = "url", value = "待修改的url", paramType = "body", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PatchMapping( "/{bannerId}" )
    @ResponseBody
    public RestRecord updateBannerUrl( @PathVariable( "bannerId" )String bannerId,@RequestParam( "url" ) String url ) {
        return bannerService.updateBannerUrl( bannerId, url );
    }

    /**
     * 修改应用id
     *
     * @param bannerId bannerId
     * @param appId    appId
     * @return 跟新是否成功
     */
    @ApiOperation( value = "修改appId", notes = "修改appId", httpMethod = "PATCH" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "bannerId", value = "bannerId", paramType = "header", required = true ),
            @ApiImplicitParam( name = "appId", value = "appId", paramType = "body", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PatchMapping( "/{bannerId}" )
    @ResponseBody
    public RestRecord updateBannerAppId( @PathVariable( "bannerId" )String bannerId,@RequestParam( "appId" ) String appId ) {
        return bannerService.updateBannerAppId( bannerId, appId );
    }

    /**
     * 修改轮播次序
     *
     * @param list bannerId
     * @return 修改结果
     */
    @ApiOperation( value = "修改轮播次序", notes = "修改轮播次序", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "list", value = "bannerId顺序", paramType = "POST-Body", required = true, example = "xxx@bonc.com.cn", allowableValues = "0,1,2" ),
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @PutMapping( "/updateBannerOrder" )
    @ResponseBody
    public RestRecord updateBannerOrder( List<String> list ) {
        return bannerService.updateBannerOrder( list );
    }

    /**
     * 获取banner数据
     *
     * @param bannerId bannerId
     * @return banner数据
     */
    @ApiOperation( value = "获取banner数据", notes = "获取banner数据", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "bannerId", value = "bannerId", paramType = "header", required = true )
    } )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping( "/{bannerId}" )
    @ResponseBody
    public RestRecord getBannerById( String bannerId ) {
        return bannerService.getBannerById( bannerId );
    }

    /**
     * 获取所有banner数据
     *
     * @return banner数据list
     */
    @ApiOperation( value = "获取所有banner数据", notes = "获取所有banner数据", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 0, message = "成功", response = RestRecord.class )
    } )
    @GetMapping
    @ResponseBody
    public RestRecord getAllBannersInfo() {
        return bannerService.getAllBannersInfo();
    }
}
