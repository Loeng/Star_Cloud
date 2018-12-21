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

    @RequestMapping( value = "/manage-app/{appIdList}", method = RequestMethod.GET )
    RestRecord deleteApps( @PathVariable( "appIdList" ) List< String > appIdList );

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
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */

    @RequestMapping( name = "/manage-app/all-app/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord getAllAppList( @RequestParam String plantformType,
                              @PathVariable( "pageNum" ) Integer pageNum,
                              @PathVariable( "pageSize" ) Integer pageSize
    );

    /**
     * 查询应用分类信息
     *
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */
    @RequestMapping( name = "/manage-app/all-app-type-list/{plantformType}", method = RequestMethod.GET )
    RestRecord getAllAppTypeList( @PathVariable( "plantformType" ) String plantformType );


    /**
     * 查询指定类型应用信息
     *
     * @return
     */
    @RequestMapping( value = "/manage-app/selectAppListByType/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord selectAppListByType( @RequestParam( "appType" ) String appType,
                                    @RequestParam( "orderType" ) String orderType,
                                    @RequestParam( "plantformType" ) String plantformType,
                                    @RequestParam( "sort" ) String sort,
                                    @PathVariable( "pageNum" ) Integer pageNum,
                                    @PathVariable( "pageSize" ) Integer pageSize );

    /**
     * 根据输入名模糊查询应用
     */
    @RequestMapping( value = "/manage-app/apps-by-ame/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord selectAppListByName( @RequestParam( "appName" ) String appName,
                                    @RequestParam( "plantformType" ) String plantformType,
                                    @PathVariable( "pageNum" ) Integer pageNum,
                                    @PathVariable( "pageSize" ) Integer pageSize );

    /**
     * 根据输入名和选择类别查询应用
     */
    @RequestMapping( value = "/manage-app/apps-by-name-type/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord selectAppListByNameAndType( @RequestParam( "appName" ) String appName,
                                           @RequestParam( "appType" ) String appType,
                                           @RequestParam( "orderType" ) String orderType,
                                           @RequestParam( "sort" ) String sort,
                                           @RequestParam( "plantformType" ) String plantformType,
                                           @PathVariable( "pageNum" ) Integer pageNum,
                                           @PathVariable( "pageSize" ) Integer pageSize );

    /**
     * 查询单个应用详情
     */
    @RequestMapping( value = "/manage-app/detail-by-id/{appId}", method = RequestMethod.GET )
    RestRecord selectAppById( @PathVariable( "appId" ) String appId );
}
