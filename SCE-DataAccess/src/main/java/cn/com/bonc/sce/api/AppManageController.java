package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.entity.AppInfoEntity;
import cn.com.bonc.sce.entity.AppTypeEntity;
import cn.com.bonc.sce.entity.AppTypeRelEntity;
import cn.com.bonc.sce.entity.MarketAppVersion;
import cn.com.bonc.sce.model.AppAddModel;
import cn.com.bonc.sce.model.AppTypeMode;
import cn.com.bonc.sce.repository.*;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppNameTypeService;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 应用管理api
 * author jc_D
 */
@Slf4j
@RestController
@Transactional( rollbackFor = Exception.class )
@RequestMapping( "/manage-app" )
public class AppManageController {

    @Autowired
    private FileResourceRepository fileResourceRepository;

    @Autowired
    private AppTypeRelRepository appTypeRelRepository;

    @Autowired
    private AppInfoRepository appInfoRepository;

    @Autowired
    private AppTypeRepository appTypeRepository;

    @Autowired
    private MarketAppVersionRepository marketAppVersionRepository;

    @Autowired
    /**
     * yanmin
     */
    private AppNameTypeService appNameTypeService;

    /**
     * 新增应用
     *
     * @param appInfo 应用信息， value为json格式
     * @return
     */
    @PostMapping( "/{uid}" )
    public RestRecord addAppInfo( @RequestBody AppAddModel appInfo,
                                  @PathVariable( "uid" ) String uid ) {
        log.trace( "appinfo::{}", appInfo );
        try {
            //取icon
            String iconId = appInfo.getAppIcon();
            Map< String, Object > iconAd = fileResourceRepository.getFileResourceById( Integer.parseInt( iconId ) );
            String iconAddress = iconAd.get( "FILE_MAPPING_PATH" ).toString();
            //1.appinfo表
            AppInfoEntity appInfoEntity = new AppInfoEntity();
            appInfoEntity.setAppIcon( iconAddress );
            appInfoEntity.setAppName( appInfo.getAppName() );
            appInfoEntity.setCreateUserId( uid );
            appInfoEntity.setCreateTime( new Date() );
            appInfoEntity.setIsDelete( 1 );
            appInfoEntity.setAppSource( "rj" );
            appInfoEntity.setAppNotes( appInfo.getAppNotes() );
            AppInfoEntity info = appInfoRepository.saveAndFlush( appInfoEntity );
            String appId = info.getAppId();
            //2.类型关系表
            AppTypeRelEntity appTypeRelEntity = new AppTypeRelEntity();
            appTypeRelEntity.setAppId( appId );
            appTypeRelEntity.setAppTypeId( appInfo.getAppTypeId() );
            AppTypeRelEntity rel = appTypeRelRepository.saveAndFlush( appTypeRelEntity );

            //根据id取pc图片链接
            String pcUrl = appInfo.getAppPcPic();
            String[] pcId = pcUrl.split( "," );
            StringBuilder sb1 = new StringBuilder();
            for ( String s : pcId ) {
                Map< String, Object > fileStorePath = fileResourceRepository.getFileResourceById( Integer.parseInt( s ) );
                String p = fileStorePath.get( "FILE_MAPPING_PATH" ).toString();
                sb1.append( p ).append( "," );
            }
            //根据id取phone图片链接
            String phoneUrl = appInfo.getAppPhonePic();
            String[] phoneId = phoneUrl.split( "," );
            StringBuilder sb2 = new StringBuilder();
            for ( String s : phoneId ) {
                Map< String, Object > fileStorePath = fileResourceRepository.getFileResourceById( Integer.parseInt( s ) );
                String p = fileStorePath.get( "FILE_MAPPING_PATH" ).toString();
                sb2.append( p ).append( "," );
            }


            //3.版本表
            Set< AppTypeMode > pcSet = appInfo.getPc();
            pcSet.forEach( pc -> {

                //根据addressId获取软件存储路径
                String addressId = pc.getAddress();
                Map< String, Object > ad = fileResourceRepository.getFileResourceById( Integer.parseInt( addressId ) );
                String softwareAddress = ad.get( "FILE_MAPPING_PATH" ).toString();
                //往版本表存东西
                MarketAppVersion marketAppVersion = new MarketAppVersion();
                marketAppVersion.setAppId( appId );
                marketAppVersion.setAppDownloadAddress( softwareAddress );
                marketAppVersion.setAppVersion( pc.getAppVersion() );
                marketAppVersion.setVersionInfo( appInfo.getAppNotes() );
                marketAppVersion.setPackageName( pc.getPackageName() );
                marketAppVersion.setVersionSize( pc.getVersionSize() );
                marketAppVersion.setAppStatus( "1" );
                marketAppVersion.setNewFeatures( appInfo.getNewFeatures() );
                marketAppVersion.setAuthDetail( appInfo.getAuthDetail() );
                marketAppVersion.setAppPcPic( sb1.toString() );
                marketAppVersion.setAppPhonePic( sb2.toString() );
                marketAppVersion.setAuthDetail( appInfo.getAuthDetail() );
                marketAppVersion.setCreateTime( new Date() );
                marketAppVersion.setIsDelete( 1L );
                marketAppVersion.setRunningPlatform( pc.getVersioInfo() );
                marketAppVersion.setCreateUserId( uid );
                marketAppVersionRepository.saveAndFlush( marketAppVersion );
            } );
        } catch ( Exception e ) {
            log.error( "add appInfo fail {}", e );
            return new RestRecord( 423, WebMessageConstants.SCE_PORTAL_MSG_423, e );
        }

//        final AppInfoEntity appInfoEntity = JSONUtil.toBean( JSONUtil.parseFromMap( appInfo ), AppInfoEntity.class );
//        final AppInfoEntity result = appInfoRepository.saveAndFlush( appInfoEntity );
//       return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, result );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, appInfo );
    }

    /**
     * 删除应用
     *
     * @param appIdList appId数组
     * @return
     */
    @DeleteMapping( "/{uid}" ) //TODO 是啥意思?
    public RestRecord deleteApps( @RequestBody List< String > appIdList,
                                  @PathVariable( "uid" ) String uid ) {
        //应用info表  是否删除字段改为1
        try {
            appIdList.forEach( id -> {
                appInfoRepository.deleteAppInfo( appIdList, uid );
            } );
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
    @PutMapping( "/{appId}" )
    public RestRecord updateAppInfo( @RequestBody Map updateAppInfo,
                                     @PathVariable String appId ) {

        final AppInfoEntity appInfoEntity = JSONUtil.toBean( JSONUtil.parseFromMap( updateAppInfo ), AppInfoEntity.class );
        final AppInfoEntity result = appInfoRepository.saveAndFlush( appInfoEntity );
        return new RestRecord( 0, WebMessageConstants.SCE_PORTAL_MSG_200, result );
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
        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        Page< AppInfoEntity > page = appInfoRepository.findByAppSource( platformType, pageable );
        Map< String, Object > temp = new HashMap<>( 16 );
        temp.put( "data", page.getContent() );
        temp.put( "totalPage", page.getTotalPages() );
        temp.put( "totalCount", page.getTotalElements() );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, temp );

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
        //表和表关联，不知道怎么用关联表的字段去排序
        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        Page< AppTypeEntity > page;
        if ( 0 == appType ) {
            page = appTypeRepository.findAll( pageable );
        } else {
            page = appTypeRepository.findByAppTypeId( appType, pageable );
        }
        Map< String, Object > temp = new HashMap<>( 16 );
        temp.put( "data", page.getContent() );
        temp.put( "totalPage", page.getTotalPages() );
        temp.put( "totalCount", page.getTotalElements() );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, temp );

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
        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        Page< AppInfoEntity > page = appInfoRepository.findByAppNameLike( "%" + appName + "%", pageable );
        Map< String, Object > temp = new HashMap<>( 16 );
        temp.put( "data", page.getContent() );
        temp.put( "totalPage", page.getTotalPages() );
        temp.put( "totalCount", page.getTotalElements() );
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
        //TODO:这个数据随便查的，要重写
        return appNameTypeService.selectAppListByNameAndType( appName, appType, orderType, sort, platformType, pageNum, pageSize );

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
        Pageable pageable = PageRequest.of( 0, 10, Sort.Direction.DESC, "CREATE_TIME" );
        Page< Map< String, Object > > appDetailInfo = appInfoRepository.findAppDetailById( appId, pageable );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, appDetailInfo.getContent() );
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
    public RestRecord isOpenApp( @PathVariable String appId,@RequestParam String userId ) {
        Map< String, Object > openInfo = appInfoRepository.findAppOpenInfo( appId, userId );
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
        List< Map< String, Object > > data = appInfoRepository.getAllAuditStatus();
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, data );
    }


    /**
     * 应用上下架
     *
     * @param applyType
     * @param appIdList
     * @param userId
     * @return
     */
    @PostMapping( "/app-on-shelf" )
    public RestRecord applyAppOnShelf( @RequestParam( "applyType" ) Integer applyType, @RequestBody List< String > appIdList, @RequestParam( "userId" ) String userId ) {

        String type = String.valueOf( applyType );
        int appInfo = marketAppVersionRepository.applyAppOnShelfByUserId( type, appIdList, userId );
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
                                               @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                                               @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize
    ) {
        try {
            RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
            Page< List< Map< String, Object > > > page;
            Pageable pageable = PageRequest.of( pageNum - 1, pageSize, "desc".equalsIgnoreCase( downloadCount ) ? Sort.Direction.DESC : Sort.Direction.ASC, "DOWNLOAD_COUNT" );

            //分类id为空
            if ( StringUtils.isEmpty( typeId ) || typeId == 0 ) {
                page = appInfoRepository.getInfoByKeyword( auditStatus, keyword, pageable );
            } else {
                //分类id不为空
                page = appInfoRepository.getInfoByTypeIdAndKeyword( auditStatus, typeId, keyword, pageable );
            }
            Map< String, Object > temp = new HashMap<>( 16 );
            temp.put( "data", page.getContent() );
            temp.put( "totalPage", page.getTotalPages() );
            temp.put( "totalCount", page.getTotalElements() );
            restRecord.setData( temp );
            return restRecord;
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420, e );
        }
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

        Page< List< Map< String, Object > > > page;
        if ( "pt".equalsIgnoreCase( platformType ) ) {
            //平台应用
            Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
            if ( appType == 0 ) {
                //平台应用 没有分类
                page = appInfoRepository.getAppListInfoByPlatform( userId, "pt", pageable );
            } else {
                //根据typeId查
                page = appInfoRepository.getAppListInfoByTypeIdandPlatform( appType, userId, "pt", pageable );
            }

        } else {
            Pageable pageable = PageRequest.of( pageNum - 1, pageSize, "desc".equalsIgnoreCase( sort ) ? Sort.Direction.DESC : Sort.Direction.ASC, "time".equalsIgnoreCase( orderType ) ? "CREATE_TIME" : "DOWNLOAD_COUNT" );
            //软件应用
            if ( appType == 0 ) {
                //查全部
                page = appInfoRepository.getAppListInfoByPlatform( userId, "rj", pageable );
            } else {
                //根据typeId查
                page = appInfoRepository.getAppListInfoByTypeIdandPlatform( appType, userId, "rj", pageable );
            }

        }

        Map< String, Object > temp = new HashMap<>( 16 );
        temp.put( "data", page.getContent() );
        temp.put( "totalPage", page.getTotalPages() );
        temp.put( "totalCount", page.getTotalElements() );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, temp );
    }


    /**
     * 应用统计总量
     *
     * @return
     */
    @GetMapping( "/count-app" )
    public RestRecord getAppCountInfo() {
        return new RestRecord( 200, appInfoRepository.getAppCountInfo() );
    }

    /**
     * app分类信息统计
     *
     * @return
     */
    @GetMapping( "/app-info" )
    public RestRecord getAppInfo() {
        return new RestRecord( 200, appInfoRepository.getAppInfo() );
    }


}
