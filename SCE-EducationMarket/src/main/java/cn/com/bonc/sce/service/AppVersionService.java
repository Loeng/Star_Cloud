package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppVersionDao;
import cn.com.bonc.sce.model.AppVersionModel;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Slf4j
@Service
public class AppVersionService {
    private AppVersionDao appVersionDao;

    @Autowired
    public AppVersionService( AppVersionDao appVersionDao ) {
        this.appVersionDao = appVersionDao;
    }

    public RestRecord queryAppVersion( String appId, String appVersion, int pageNum, int pageSize ) {
        return appVersionDao.queryAppVersion( appId, appVersion, pageNum, pageSize );
    }

    public RestRecord updateAppHistoryVersionInfo( String appId, String appVersion, AppVersionModel marketAppVersion ) {
        return appVersionDao.updateAppHistoryVersionInfo( appId, appVersion, marketAppVersion );
    }

    public RestRecord deleteAppHistoryVersionInfo( String appId, String appVersion ) {
        return appVersionDao.deleteAppHistoryVersionInfo( appId, appVersion );
    }

    public RestRecord createVersionInfo( String appId, String userId, AppVersionModel marketAppVersion ) {
        return appVersionDao.createVersionInfo( appId, userId, marketAppVersion );
    }


}
