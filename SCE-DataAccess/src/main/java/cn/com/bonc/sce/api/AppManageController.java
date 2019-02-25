package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.entity.AppInfoEntity;
import cn.com.bonc.sce.entity.AppTypeEntity;
import cn.com.bonc.sce.model.AppAddModel;
import cn.com.bonc.sce.model.PlatformAddModel;
import cn.com.bonc.sce.repository.*;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppManageService;
import cn.com.bonc.sce.service.AppNameTypeService;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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

    @Autowired
    private AppNameTypeRepository appNameTypeRepository;

    @Autowired
    /**
     * yanmin
     */
    private AppNameTypeService appNameTypeService;

    @Autowired
    private AppManageService appManageService;

    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 新增软件
     *
     * @param appInfo 应用信息， value为json格式
     * @return
     */
    @PostMapping( "/{uid}" )
    public RestRecord addAppInfo( @RequestBody AppAddModel appInfo,
                                  @PathVariable( "uid" ) String uid ) {
        RestRecord ret;
        try {
            ret = appManageService.addAppInfo( appInfo, uid );
        } catch ( Exception e ) {
            log.error( "{}", e.getMessage() );
            ret = new RestRecord( 423, WebMessageConstants.SCE_PORTAL_MSG_423, e.getMessage() );
        }
        return ret;
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
        RestRecord ret;
        try {
            ret = appManageService.addPlatFormInfo( platFormInfo, userId );
        } catch ( Exception e ) {
            log.error( "{}", e.getMessage() );
            ret = new RestRecord( 423, WebMessageConstants.SCE_PORTAL_MSG_423, e.getMessage() );
        }
        return ret;
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
                appInfoRepository.deleteAppInfo( appIdList, uid );
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

        boolean existsName = StrUtil.isNotBlank( appName );
        boolean existsType = ( appType != null && appType != 0 );

        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        Page< Map< String, Object > > list = null;
        // 名字类型都有输入
        if ( existsName && existsType ) {
            appName = StrUtil.builder().append( "%" ).append( appName ).append( "%" ).toString();
            list = appNameTypeRepository.selectAppListByNameAndType( appName, appType, pageable );
        }

        // 只输入名字
        if ( existsName && !existsType ) {
            appName = StrUtil.builder().append( "%" ).append( appName ).append( "%" ).toString();
            list = appNameTypeRepository.selectAppListByName( appName, pageable );
        }

        // 只输入类型
        if ( !existsName && existsType ) {
            list = appNameTypeRepository.selectAppListByType( appType, pageable );
        }

        if ( !existsName && !existsType ) {
            list = appNameTypeRepository.selectAppList( pageable );
        }

        if ( null != list ) {
            Map temp = new HashMap<>( list.getSize() );
            temp.put( "data", list.getContent() );
            temp.put( "totalPage", list.getTotalPages() );
            temp.put( "totalCount", list.getTotalElements() );
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200, temp );
        } else {
            return new RestRecord( 500, MessageConstants.SCE_MSG_406 );
        }

        //TODO:这个数据随便查的，要重写
//        return appNameTypeService.selectAppListByNameAndType( appName, appType, orderType, sort, platformType, pageNum, pageSize );

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
       /* List<Map<String, Object>> content = appDetailInfo.getContent();
        for (Map<String,Object> appDetail:content) {

        }*/
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
    public RestRecord isOpenApp( @PathVariable String appId, @RequestParam( "userId" ) String userId ) {
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
    public RestRecord applyAppOnShelf( @RequestParam( "applyType" ) Integer applyType, @RequestBody List< Map > appIdList, @RequestParam( "userId" ) String userId ) {
        String type = String.valueOf( applyType );
        int appInfo = 0;
        for ( Map map : appIdList ) {
            String appId = String.valueOf( map.get( "appId" ) );
            String appVersion = String.valueOf( map.get( "appVersion" ) );
            appInfo += marketAppVersionRepository.applyAppOnShelfByUserId( "5", userId, appId, appVersion );
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
        try {
            RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
            Page< List< Map< String, Object > > > page;
            if ( "6".equals( auditStatus ) ) { // 查询暂存列表  1，审核中2，迭代审核3，未通过审核，4已上架（运营中）， 5应用下架  6,暂存
                Pageable newpageable = PageRequest.of( pageNum - 1, pageSize, "desc".equalsIgnoreCase( "desc" ) ? Sort.Direction.DESC : Sort.Direction.ASC, "APP_NAME" );
                page = appInfoRepository.getTempAPPInfoByTypeIdAndKeyword( typeId, keyword, newpageable );
                Map< String, Object > temp = new HashMap<>( 16 );
                temp.put( "data", page.getContent() );
                temp.put( "totalPage", page.getTotalPages() );
                temp.put( "totalCount", page.getTotalElements() );
                restRecord.setData( temp );
                return restRecord;
            } else {

                //根据userId查询厂商id
                Long companyId = companyInfoRepository.getCompanyIdByUid( userId );

                StringBuilder sql = new StringBuilder( "SELECT * FROM STARCLOUDMARKET.\"APP_MANAGE_INFO_VIEW\" V WHERE 1 = 1 " );
                //是管理员？
                if ( companyId != null ) {
                    sql.append( " AND V.COMPANY_ID = " ).append( companyId );
                }
                //app分类id
                if ( typeId > 0 ) {
                    sql.append( " AND V.APP_TYPE_ID = " ).append( typeId );
                }
                //平台类型
                if ( !StringUtils.isEmpty( platformType ) ) {
                    sql.append( " AND V.APP_SOURCE = " ).append( " '" ).append( platformType ).append( "' " );
                }
                //appName
                if ( !StringUtils.isEmpty( keyword ) ) {
                    sql.append( " AND V.APP_NAME LIKE '%" ).append( keyword ).append( "%'" );
                }
                sql.append( " AND V.APP_STATUS ='" ).append( auditStatus ).append( "' " );
                //  sql.append( " ORDER BY DOWNLOAD_COUNT " ).append( "desc".equalsIgnoreCase( downloadCount ) ? Sort.Direction.DESC : Sort.Direction.ASC );
                Session session = entityManager.unwrap( org.hibernate.Session.class );
                NativeQuery query = session.createNativeQuery( sql.toString() );
                query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
                int start = ( pageNum - 1 ) * pageSize;
                int total = query.getResultList().size();
                //判断分页
                if ( start < total && pageSize > 0 ) {
                    query.setFirstResult( start );
                    query.setMaxResults( pageSize );
                }
                Map< String, Object > temp = new HashMap<>( 16 );
                temp.put( "data", query.getResultList() );
                temp.put( "totalPage", ( total + pageSize - 1 ) / pageSize );
                temp.put( "totalCount", total );
                restRecord.setData( temp );
                return restRecord;

            }
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
            Pageable pageable = PageRequest.of( pageNum - 1, pageSize, Sort.Direction.DESC, "CREATE_TIME" );
            if ( appType == 0 ) {
                //平台应用 没有分类
                page = appInfoRepository.getAppListInfoByPlatform( userId, "pt", pageable );
            } else {
                //根据typeId查
                page = appInfoRepository.getAppListInfoByTypeIdandPlatform( appType, userId, "pt", pageable );
            }

        } else {
            List< String > properties = new ArrayList<>( 2 );
            properties.add( "time".equalsIgnoreCase( orderType ) ? "CREATE_TIME" : "DOWNLOAD_COUNT" );
            properties.add( "APP_ID" );
            Pageable pageable = PageRequest.of( pageNum - 1, pageSize, new Sort( "asc".equalsIgnoreCase( sort ) ? Sort.Direction.ASC : Sort.Direction.DESC, properties ) );
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
