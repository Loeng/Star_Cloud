package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.entity.AppInfoEntity;
import cn.com.bonc.sce.entity.AppTypeEntity;
import cn.com.bonc.sce.repository.AppInfoRepository;
import cn.com.bonc.sce.repository.AppTypeRepository;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 新增应用
     *
     * @param appInfo 应用信息， value为json格式
     * @return
     */
    @PostMapping
    public RestRecord addAppInfo( @RequestBody Map appInfo ) {
        //暂时没时间写
        return new RestRecord( 200, PortalMessageConstants.SCE_PORTAL_MSG_200 );
    }

    /**
     * 删除应用
     *
     * @param appIdList appId数组
     * @return
     */
    @DeleteMapping( "/{appIdList}" )
    public RestRecord deleteApps( @PathVariable( "appIdList" ) List< String > appIdList ) {
        //暂时没时间写
        //应用版本表  是否删除字段改为1
        return new RestRecord( 200, PortalMessageConstants.SCE_PORTAL_MSG_200 );
    }

    /**
     * 编辑应用
     *
     * @param updateInfo 应用需更新的字段与更新的值，json字符串
     * @param appId      所需更新的应用ID
     * @return
     */
    @PutMapping( "/{appId}" )
    public RestRecord updateAppInfo( @RequestBody Map updateInfo,
                                     @PathVariable String appId ) {
        //暂时没时间写
        return new RestRecord( 0, PortalMessageConstants.SCE_PORTAL_MSG_200 );
    }

    /**
     * 查询全部应用
     *
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */

    @GetMapping( "/all-app/{pageNum}/{pageSize}" )
    public RestRecord getAllAppList( @RequestParam String plantformType,
                                     @PathVariable Integer pageNum,
                                     @PathVariable Integer pageSize
    ) {
        Pageable pageable = PageRequest.of( pageNum, pageSize );
        Page< AppInfoEntity > appInfoList = appInfoRepository.findByAppSource( plantformType, pageable );
        return new RestRecord( 200, appInfoList );
    }

    /**
     * 查询应用分类信息
     *
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */
    @GetMapping( "/all-app-type-list/{plantformType}" )
    public RestRecord getAllAppTypeList( @PathVariable String plantformType ) {
        //这个接口和某人写重了
        return new RestRecord( 200, "这个接口和某人写重了" );
    }


    /**
     * 查询指定类型应用信息
     *
     * @param appType       查询的应用类型id
     * @param orderType     查询排序字段，可为多个字段
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */
    @GetMapping( "/selectAppListByType/{pageNum}/{pageSize}" )
    public RestRecord selectAppListByType( @RequestParam( value = "appType", defaultValue = "all", required = false ) String appType,
                                           @RequestParam( value = "orderType" ) String orderType,
                                           @RequestParam String plantformType,
                                           @RequestParam String sort,
                                           @PathVariable Integer pageNum,
                                           @PathVariable Integer pageSize ) {
        //表和表关联，不知道怎么用关联表的字段去排序
        Pageable pageable = PageRequest.of( pageNum, pageSize );
        Page< AppTypeEntity > appInfoList = appTypeRepository.findByAppTypeId( appType, pageable );
        return new RestRecord( 200, appInfoList );
    }

    /**
     * 根据输入名模糊查询应用
     *
     * @param appName       查询的名字
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */
    @GetMapping( "/apps-by-ame/{pageNum}/{pageNum}" )
    public RestRecord selectAppListByName( @RequestParam String appName,
                                           @RequestParam String plantformType,
                                           @PathVariable Integer pageNum,
                                           @PathVariable Integer pageSize ) {
        Pageable pageable = PageRequest.of( pageNum, pageSize );
        Page< AppInfoEntity > info = appInfoRepository.findByAppNameLike( "%" + appName + "%", pageable );
        return new RestRecord( 200, info );
    }

    /**
     * 根据输入名和选择类别查询应用
     *
     * @param appName
     * @param appType
     * @param orderType
     * @param sort
     * @param plantformType
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping( "/apps-by-name-type/{pageNum}/{pageSize}" )
    public RestRecord selectAppListByNameAndType( @RequestParam String appName,
                                                  @RequestParam String appType,
                                                  @RequestParam String orderType,
                                                  @RequestParam String sort,
                                                  @RequestParam String plantformType,
                                                  @PathVariable Integer pageNum,
                                                  @PathVariable Integer pageSize ) {
        //这个数据随便查的，要重写
        Pageable pageable = PageRequest.of( pageNum, pageSize );
        Page< AppInfoEntity > info = appInfoRepository.findByAppNameLike( "%" + appName + "%", pageable );
        return new RestRecord( 200, info );
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

}
