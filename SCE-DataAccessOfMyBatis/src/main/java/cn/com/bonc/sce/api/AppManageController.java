package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.mapper.AppInfoMapper;
import cn.com.bonc.sce.mapper.AppVersionMapper;
import cn.com.bonc.sce.model.AppAddModel;
import cn.com.bonc.sce.model.PlatformAddModel;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 应用管理api
 * author jc_D
 */
@Slf4j
@RestController
@RequestMapping( "/manage-app" )
public class AppManageController {
    @Autowired
    private AppInfoMapper appInfoMapper;

    @Autowired
    private AppVersionMapper appVersionMapper;

    /**
     * 新增软件
     *
     * @param appInfo 应用信息， value为json格式
     * @return
     */
    @PostMapping( "/{uid}" )
    public RestRecord addAppInfo( @RequestBody AppAddModel appInfo,
                                  @PathVariable( "uid" ) String uid ) {
        //todo
        return null;
    }

    /**
     * 新增平台应用
     *
     * @param platFormInfo
     * @param userId
     * @return
     */
    @PostMapping( "/pt/{userId}" )
    public RestRecord addPlatFormInfo( @RequestBody PlatformAddModel platFormInfo,
                                       @PathVariable String userId ) {
        //todo
        return null;
    }


    /**
     * 删除应用
     *
     * @param appIdList appId数组
     * @return
     */
    @Transactional
    @DeleteMapping( "/{uid}" )
    public RestRecord deleteApps( @RequestBody List< String > appIdList,
                                  @PathVariable( "uid" ) String uid ) {
        //应用info表  是否删除字段改为1
        try {
            appInfoMapper.deleteAppInfo( appIdList, uid );
        } catch ( Exception e ) {
            log.error( "delete appInfo fail {}", e );
            return new RestRecord( 423, WebMessageConstants.SCE_PORTAL_MSG_422, e );
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    /**
     * 编辑应用
     *
     * @param updateAppInfo 应用需更新的字段与更新的值，json字符串
     * @param appId         所需更新的应用ID
     * @return
     */
    @Transactional
    @PutMapping( "/{appId}" )
    public RestRecord updateAppInfo( @RequestBody Map updateAppInfo,
                                     @PathVariable String appId ) {
        //todo
        return new RestRecord( 0, WebMessageConstants.SCE_PORTAL_MSG_200, null );
    }

    /**
     * 查询全部应用
     *
     * @param platformType 平台类型（平台应用或软件应用）
     * @return
     */

    @GetMapping( "/all-app" )
    public RestRecord getAllAppList( @RequestParam( value = "platformType", required = false, defaultValue = "0" ) String platformType,
                                     @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                                     @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize
    ) {
        //todo
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );

    }


    /**
     * 查询指定类型应用信息
     *
     * @param appType      查询的应用类型id
     * @param orderType    查询排序字段，单个字段
     * @param platformType 平台类型（平台应用或软件应用）
     * @return
     */
    @GetMapping( "/app-by-type" )
    public RestRecord selectAppListByType( @RequestParam( value = "appType", required = false, defaultValue = "0" ) Integer appType,
                                           @RequestParam( value = "orderType", required = false, defaultValue = "download" ) String orderType,
                                           @RequestParam( value = "platformType", required = false, defaultValue = "0" ) String platformType,
                                           @RequestParam( value = "sort", required = false, defaultValue = "desc" ) String sort,
                                           @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                                           @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize ) {
        //todo
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );

    }

    /**
     * 根据输入名模糊查询应用
     *
     * @param appName      查询的名字
     * @param platformType 平台类型（平台应用或软件应用）
     * @return
     */
    @GetMapping( "/apps-by-name" )
    public RestRecord selectAppListByName( @RequestParam( value = "appName", required = false ) String appName,
                                           @RequestParam( value = "platformType", required = false, defaultValue = "0" ) String platformType,
                                           @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                                           @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize ) {
        //todo
        Map< String, Object > temp = new HashMap<>( 16 );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, temp );
    }

    /**
     * 根据输入名和选择类别查询应用
     *
     * @param appName
     * @param appType
     * @param orderType
     * @param sort
     * @param platformType
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping( "/apps-by-name-type" )
    public RestRecord selectAppListByNameAndType( @RequestParam( value = "appName", required = false ) String appName,
                                                  @RequestParam( value = "appType", required = false, defaultValue = "0" ) Integer appType,
                                                  @RequestParam String orderType,
                                                  @RequestParam( value = "sort", required = false, defaultValue = "desc" ) String sort,
                                                  @RequestParam( value = "platformType", required = false, defaultValue = "0" ) String platformType,
                                                  @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                                                  @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize ) {

        //todo
//        return appNameTypeService.selectAppListByNameAndType( appName, appType, orderType, sort, platformType, pageNum, pageSize );
        return null;
    }

    /**
     * 查询单个应用详情（不包含用户是否开通该APP）
     *
     * @param appId
     * @return
     */
    @GetMapping( "/detail-by-id/{appId}" )
    @ResponseBody
    public RestRecord selectAppById( @PathVariable String appId ) {
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, appInfoMapper.findAppDetailById( appId ) );
    }

    /**
     * DESC: 检测用户是否开通了该APP
     *
     * @param appId 应用ID
     * @return Map
     * @Auther mkl
     */
    @GetMapping( "/detail/open/{appId}" )
    @ResponseBody
    public RestRecord isOpenApp( @PathVariable String appId, @RequestParam( "userId" ) String userId ) {
        Map< String, Object > openInfo = appInfoMapper.findAppOpenInfo( appId, userId );
        Map< String, Object > map = new HashMap<>();
        if ( null == openInfo || CollectionUtils.isEmpty( openInfo ) ) {
            map.put( "OPEN", false );
            log.info( "用户[{}]未开通应用[{}]", userId, appId );
        } else {
            map.put( "OPEN", true );
            log.info( "用户[{}]已开通应用[{}]", userId, appId );
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, map );
    }

    /**
     * 查询[应用审核状态码表]中相关信息
     *
     * @return
     */
    @GetMapping( "all-audit-status" )
    public RestRecord getAllVersionStatus() {
        List< Map< String, Object > > data = appInfoMapper.getAllAuditStatus();
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, data );
    }


    /**
     * 应用下架
     *
     * @param applyType
     * @param appIdList
     * @param userId
     * @return
     */
    @PostMapping( "/app-on-shelf" )
    public RestRecord applyAppOnShelf( @RequestParam( "applyType" ) Integer applyType, @RequestBody List< Map > appIdList, @RequestParam( "userId" ) String userId ) {
        String type = String.valueOf( applyType );
        int appInfo = 0;
        for ( Map map : appIdList ) {
            String appId = String.valueOf( map.get( "appId" ) );
            String appVersion = String.valueOf( map.get( "appVersion" ) );
            appInfo += appVersionMapper.applyAppOnShelfByUserId( "5", userId, appId, appVersion );
        }
        return new RestRecord( 200, appInfo );
    }

    /**
     * 通过审核状态查询app列表
     *
     * @param auditStatus
     * @param typeId
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping( value = "/list-by-audit-status", method = RequestMethod.GET )
    public RestRecord getAppListByAuditStatus( @RequestParam( "auditStatus" ) String auditStatus,
                                               @RequestParam( value = "typeId", required = false, defaultValue = "0" ) Integer typeId,
                                               @RequestParam( value = "keyword", required = false ) String keyword,
                                               @RequestParam( value = "downloadCount", required = false, defaultValue = "desc" ) String downloadCount,
                                               @RequestParam( value = "platformType", required = false, defaultValue = "rj" ) String platformType,
                                               @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                                               @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize,
                                               @RequestParam( value = "userId" ) String userId
    ) {
        //todo
        return null;
    }

    /**
     * 前台全部应用页面
     * 应用列表
     * 条件查询
     *
     * @param appName
     * @param appType
     * @param orderType
     * @param sort
     * @param platformType
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping( "/condition/{userId}" )
    public RestRecord getAppListInfoByCondition( @RequestParam( value = "appName", required = false ) String appName,
                                                 @RequestParam( value = "appType", required = false, defaultValue = "0" ) Integer appType,
                                                 @RequestParam String orderType,
                                                 @RequestParam( value = "sort", required = false, defaultValue = "desc" ) String sort,
                                                 @RequestParam( value = "platformType", required = false, defaultValue = "rj" ) String platformType,
                                                 @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                                                 @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize,
                                                 @PathVariable( "userId" ) String userId ) {
        //todo
        return null;
    }


    /**
     * 应用统计总量
     *
     * @return
     */
    @GetMapping( "/count-app" )
    public RestRecord getAppCountInfo() {
        return new RestRecord( 200, appInfoMapper.getAppCountInfo() );
    }

    /**
     * app分类信息统计
     *
     * @return
     */
    @GetMapping( "/app-info" )
    public RestRecord getAppInfo() {
        return new RestRecord( 200, appInfoMapper.getAppInfo() );
    }


}
