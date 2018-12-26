package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.entity.AppInfoEntity;
import cn.com.bonc.sce.entity.AppTypeEntity;
import cn.com.bonc.sce.repository.AppInfoRepository;
import cn.com.bonc.sce.repository.AppTypeRepository;
import cn.com.bonc.sce.repository.MarketAppVersionRepository;
import cn.com.bonc.sce.rest.RestRecord;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private AppInfoRepository appInfoRepository;

    @Autowired
    private AppTypeRepository appTypeRepository;

    @Autowired
    private MarketAppVersionRepository marketAppVersionRepository;

    /**
     * 新增应用
     *
     * @param appInfo 应用信息， value为json格式
     * @return
     */
    @PostMapping
    public RestRecord addAppInfo( @RequestBody Map appInfo ) {
        final AppInfoEntity appInfoEntity = JSONUtil.toBean( JSONUtil.parseFromMap( appInfo ), AppInfoEntity.class );
        final AppInfoEntity result = appInfoRepository.saveAndFlush( appInfoEntity );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, result );
    }

    /**
     * 删除应用
     *
     * @param appIdList appId数组
     * @return
     */
    @DeleteMapping( "" ) //TODO 是啥意思?
    public RestRecord deleteApps( @RequestBody List< String > appIdList ) {
        //应用版本表  是否删除字段改为1
        List< AppInfoEntity > appInfoEntities = new ArrayList<>( appIdList.size() );
        appIdList.forEach( id -> {
            final AppInfoEntity appInfoEntity = new AppInfoEntity();
            appInfoEntity.setAppId( id );
            appInfoEntity.setIsDelete( 0 );
            appInfoEntities.add( appInfoEntity );
        } );
        final List< AppInfoEntity > results = appInfoRepository.saveAll( appInfoEntities );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, results );
    }

    /**
     * 编辑应用
     *
     * @param updateAppInfo 应用需更新的字段与更新的值，json字符串
     * @param appId      所需更新的应用ID
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
        //这个数据随便查的，要重写
        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        Page< AppInfoEntity > page = appInfoRepository.findByAppNameLike( "%" + appName + "%", pageable );
        Map< String, Object > temp = new HashMap<>( 16 );
        temp.put( "data", page.getContent() );
        temp.put( "totalPage", page.getTotalPages() );
        temp.put( "totalCount", page.getTotalElements() );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, temp );
    }

    /**
     * 查询单个应用详情
     *
     * @param appId
     * @return
     */
    @GetMapping( "/detail-by-id/{appId}" )
    public RestRecord selectAppById( @PathVariable String appId ) {
        AppInfoEntity appInfo = appInfoRepository.findByAppId( appId );
        return new RestRecord( 200, appInfo );
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
    public RestRecord applyAppOnShelf( @RequestParam Integer applyType, @RequestBody List< String > appIdList, @RequestParam String userId ) {

        String appStatus = applyType == 1 ? "上架审核" : "下架审核";

        int appInfo = marketAppVersionRepository.applyAppOnShelfByUserId( appStatus, appIdList, userId );
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

}
