package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppManageDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AppManageService {

    private AppManageDao appManageDao;

    public AppManageService() {

    }

    @Autowired
    public AppManageService( AppManageDao appManageDao ) {
        this.appManageDao = appManageDao;
    }

    /**
     * 新增应用
     *
     * @param appInfo 应用信息， value为json格式
     * @return
     */
    public RestRecord addAppInfo( Map appInfo ) {
        return appManageDao.addAppInfo( appInfo );
    }

    /**
     * 删除应用
     *
     * @param appIdList appId数组
     * @return
     */
    public RestRecord deleteApps( List< String > appIdList ) {
        return appManageDao.deleteApps( appIdList );
    }

    /**
     * 编辑应用
     *
     * @param updateInfo 应用需更新的字段与更新的值，json字符串
     * @param appId      所需更新的应用ID
     * @return
     */
    public RestRecord updateAppInfo( Map updateInfo, String appId ) {
        return appManageDao.updateAppInfo( updateInfo, appId );
    }

    /**
     * 查询全部应用
     *
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */

    public RestRecord getAllAppList( String plantformType, Integer pageNum, Integer pageSize
    ) {

        return appManageDao.getAllAppList( plantformType, pageNum, pageSize );
    }

    /**
     * 查询应用分类信息
     *
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */
    public RestRecord getAllAppTypeList( String plantformType ) {
        return appManageDao.getAllAppTypeList( plantformType );
    }


    /**
     * 查询指定类型应用信息
     *
     * @param appType       查询的应用类型
     * @param orderType     查询排序字段，可为多个字段
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */
    public RestRecord selectAppListByType( String appType, String orderType, String plantformType, String sort, Integer pageNum, Integer pageSize ) {
        return appManageDao.selectAppListByType( appType, orderType, plantformType, sort, pageNum, pageSize );
    }

    /**
     * 根据输入名模糊查询应用
     *
     * @param appName       查询的名字
     * @param plantformType 平台类型（平台应用或软件应用）
     * @return
     */
    public RestRecord selectAppListByName( String appName, String plantformType, Integer pageNum, Integer pageSize ) {
        return appManageDao.selectAppListByName( appName, plantformType, pageNum, pageSize );
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
    public RestRecord selectAppListByNameAndType( String appName, String appType, String orderType, String sort, String plantformType, Integer pageNum, Integer pageSize ) {
        return selectAppListByNameAndType( appName, appType, orderType, sort, plantformType, pageNum, pageSize );
    }

    /**
     * 查询详情
     *
     * @param appId
     * @return
     */
    public RestRecord selectAppById( @PathVariable String appId ) {
        return appManageDao.selectAppById( appId );
    }

}
