package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.AppAddModel;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient( "sce-data-access" )
public interface AppManageDao {


    @RequestMapping( value = "/manage-app/{uid}", method = RequestMethod.POST )
    RestRecord addAppInfo( @RequestBody AppAddModel appInfo,
                           @PathVariable( "uid" ) String uid );

    /**
     * 删除应用信息
     *
     * @param appIdList
     * @param uid
     * @return
     */
    @RequestMapping( value = "/manage-app/{uid}", method = RequestMethod.DELETE )
    RestRecord deleteApps( @RequestBody List< String > appIdList,
                           @PathVariable( "uid" ) String uid );

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
                                    @RequestParam( "pageNum" ) Integer pageNum,
                                    @RequestParam( "pageSize" ) Integer pageSize );

    /**
     * 根据输入名和选择类别查询应用
     */
    @RequestMapping( value = "/manage-app/apps-by-name-type", method = RequestMethod.GET )
    RestRecord selectAppListByNameAndType( @RequestParam( value = "appName", required = false ) String appName,
                                           @RequestParam( value = "appType", required = false, defaultValue = "0" ) Integer appType,
                                           @RequestParam( value = "orderType" ) String orderType,
                                           @RequestParam( value = "sort", required = false, defaultValue = "desc" ) String sort,
                                           @RequestParam( value = "platformType", required = false, defaultValue = "0" ) String platformType,
                                           @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                                           @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize );

    /**
     * 前台全部应用页面展示
     */
    @RequestMapping( value = "/manage-app/condition/{userId}", method = RequestMethod.GET )
    RestRecord getAppListInfoByCondition( @RequestParam( value = "appName", required = false ) String appName,
                                          @RequestParam( value = "appType", required = false, defaultValue = "0" ) Integer appType,
                                          @RequestParam( value = "orderType" ) String orderType,
                                          @RequestParam( value = "sort", required = false, defaultValue = "desc" ) String sort,
                                          @RequestParam( value = "platformType", required = false, defaultValue = "rj" ) String platformType,
                                          @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                                          @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize,
                                          @PathVariable( "userId" ) String userId );

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
    RestRecord getAppListByAuditStatus( @RequestParam( "auditStatus" ) String auditStatus,
                                        @RequestParam( value = "typeId", required = false, defaultValue = "0" ) Integer typeId,
                                        @RequestParam( value = "keyword", required = false ) String keyword,
                                        @RequestParam( value = "downloadCount", required = false, defaultValue = "desc" ) String downloadCount,
                                        @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                                        @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize
    );


    @RequestMapping( value = "/manage-app/count-app", method = RequestMethod.GET )
    RestRecord getAppCountInfo();

    @RequestMapping( value = "/manage-app/app-info", method = RequestMethod.GET )
    RestRecord getAppInfo();

    @RequestMapping( value = "/manage-app//detail/open/{appId}",method = RequestMethod.GET)
    RestRecord isOpenApp( @PathVariable( "appId" ) String appId,@RequestParam("userId") String userId );
}
