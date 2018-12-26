package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Repository
@FeignClient( "sce-data-access" )
public interface AppManageDao {


    @RequestMapping( value = "/manage-app", method = RequestMethod.POST )
    RestRecord addAppInfo( @RequestBody Map appInfo );

    @RequestMapping( value = "/manage-app", method = RequestMethod.DELETE )
    RestRecord deleteApps( @RequestBody List< String > appIdList );

    /**
     * 编辑应用
     *
     * @param updateInfo 应用需更新的字段与更新的值，json字符串
     * @param appId      所需更新的应用ID
     * @return
     */
    @RequestMapping( value = "/manage-app/{appId}", method = RequestMethod.PUT )
    RestRecord updateAppInfo( @RequestBody Map updateInfo,
                              @PathVariable( "appId" ) String appId );

    /**
     * 查询全部应用
     *
     * @param platformType 平台类型（平台应用或软件应用）
     * @return
     */

    @RequestMapping( value = "/manage-app/all-app", method = RequestMethod.GET )
    RestRecord getAllAppList( @RequestParam( value = "platformType", required = false, defaultValue = "0" ) String platformType,
                              @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                              @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize

    );


    /**
     * 查询指定类型应用信息
     *
     * @return
     */
    @RequestMapping( value = "/manage-app/app-by-type", method = RequestMethod.GET )
    RestRecord selectAppListByType( @RequestParam( value = "appType", required = false, defaultValue = "0" ) Integer appType,
                                    @RequestParam( value = "orderType", required = false, defaultValue = "download" ) String orderType,
                                    @RequestParam( value = "platformType", required = false, defaultValue = "0" ) String platformType,
                                    @RequestParam( value = "sort", required = false, defaultValue = "desc" ) String sort,
                                    @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                                    @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize );

    /**
     * 根据输入名模糊查询应用
     */
    @RequestMapping( value = "/manage-app/apps-by-name", method = RequestMethod.GET )
    RestRecord selectAppListByName( @RequestParam( "appName" ) String appName,
                                    @RequestParam( "platformType" ) String platformType,
                                    @PathVariable( "pageNum" ) Integer pageNum,
                                    @PathVariable( "pageSize" ) Integer pageSize );

    /**
     * 根据输入名和选择类别查询应用
     */
    @RequestMapping( value = "/manage-app/apps-by-name-type", method = RequestMethod.GET )
    RestRecord selectAppListByNameAndType( @RequestParam( value = "appName", required = false ) String appName,
                                           @RequestParam( value = "appType", required = false, defaultValue = "0" ) Integer appType,
                                           @RequestParam(value ="orderType" ) String orderType,
                                           @RequestParam( value = "sort", required = false, defaultValue = "desc" ) String sort,
                                           @RequestParam( value = "platformType", required = false, defaultValue = "0" ) String platformType,
                                           @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                                           @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize );

    /**
     * 查询单个应用详情
     */
    @RequestMapping( value = "/manage-app/detail-by-id/{appId}", method = RequestMethod.GET )
    RestRecord selectAppById( @PathVariable( "appId" ) String appId );


    /**
     * 应用上下架
     *
     * @param applyType
     * @param appIdList
     * @param userId
     * @return 上下架是否成功
     * @author tlz
     */
    @RequestMapping( value = "/manage-app/app-on-shelf", method = RequestMethod.POST )
    RestRecord applyAppOnShelf( @RequestParam( "applyType" ) Integer applyType, @RequestBody List< String > appIdList, @RequestParam( "userId" ) String userId );

    /**
     * 查询[应用审核状态码表]中相关信息
     *
     * @return
     */
    @RequestMapping( value = "/manage-app/all-audit-status", method = RequestMethod.GET )
    RestRecord getAllAuditStatus();

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
    @RequestMapping( value = "/manage-app/list-by-audit-status", method = RequestMethod.GET )
    public RestRecord getAppListByAuditStatus( @RequestParam( "auditStatus" ) String auditStatus,
                                               @RequestParam( value = "typeId", required = false, defaultValue = "0" ) Integer typeId,
                                               @RequestParam( value = "keyword", required = false ) String keyword,
                                               @RequestParam( value = "downloadCount", required = false, defaultValue = "desc" ) String downloadCount,
                                               @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                                               @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize
    );
}
