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


    @PostMapping
    RestRecord addAppInfo( @RequestBody Map appInfo );

    @DeleteMapping( "/{appIdList}" )
    RestRecord deleteApps( @PathVariable( "appIdList" ) List< String > appIdList );

    /**
     * 编辑应用
     *
     * @param updateInfo 应用需更新的字段与更新的值，json字符串
     * @param appId      所需更新的应用ID
     * @return
     */
    @PutMapping( "/{appId}" )
    RestRecord updateAppInfo( @RequestBody Map updateInfo,
                              @PathVariable String appId );

    /**
     * 查询全部应用
     *
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */

    @GetMapping( "/all-app/{plantformType}/{pageNum}/{pageSize}" )
    RestRecord getAllAppList( @PathVariable( "plantformType" ) String plantformType,
                              @PathVariable( "pageNum" ) Integer pageNum,
                              @PathVariable( "pageSize" ) Integer pageSize
    );

    /**
     * 查询应用分类信息
     *
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */
    @GetMapping( "/all-app-type-list/{plantformType}" )
    RestRecord getAllAppTypeList( @PathVariable( "plantformType" ) String plantformType );


    /**
     * 查询指定类型应用信息
     *
     * @return
     */
    @GetMapping( "/selectAppListByType/{pageNum}/{pageSize}" )
    RestRecord selectAppListByType( @RequestParam( value = "appType", defaultValue = "all", required = false ) String appType,
                                    @RequestParam( value = "orderType" ) String orderType,
                                    @RequestParam String plantformType,
                                    @RequestParam String sort,
                                    @PathVariable( "pageNum" ) Integer pageNum,
                                    @PathVariable( "pageSize" ) Integer pageSize );

    /**
     * 根据输入名模糊查询应用
     */
    @GetMapping( "/apps-by-ame/{pageNum}/{pageSize}" )
    RestRecord selectAppListByName( @RequestParam String appName,
                                    @RequestParam String plantformType,
                                    @PathVariable( "pageNum" ) Integer pageNum,
                                    @PathVariable( "pageSize" ) Integer pageSize );

    /**
     * 根据输入名和选择类别查询应用
     */
    @GetMapping( "/apps-by-name-type/{plantformType}/{pageNum}/{pageSize}" )
    RestRecord selectAppListByNameAndType( @RequestParam String appName,
                                           @RequestParam String appType,
                                           @RequestParam String orderType,
                                           @RequestParam String sort,
                                           @RequestParam String plantformType,
                                           @PathVariable Integer pageNum,
                                           @PathVariable Integer pageSize );

    /**
     * 查询单个应用详情
     */
    @GetMapping( "/detail-by-id/{appId}" )
    RestRecord selectAppById( @PathVariable String appId );
}
