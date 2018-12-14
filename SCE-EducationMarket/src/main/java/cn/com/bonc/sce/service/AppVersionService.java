package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppVersionDao;
import cn.com.bonc.sce.model.AppVersionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AppVersionService {
    private AppVersionDao appVersionDao;

    @Autowired
    public AppVersionService( AppVersionDao appVersionDao ) {
        this.appVersionDao = appVersionDao;
    }

    public List< AppVersionInfo > selectAppHistoryVersionList( String appId ) {
        return appVersionDao.selectAppHistoryVersionList( appId );
    }

    public boolean updateAppHistoryVersionInfo( String appId, AppVersionInfo appVersionInfo ) {
        return appVersionDao.updateAppHistoryVersionInfo( appId, appVersionInfo );
    }

    public boolean deleteAppHistoryVersionInfo( String appId, AppVersionInfo appVersionInfo ) {
        return appVersionDao.deleteAppHistoryVersionInfo( appId, appVersionInfo );
    }

    public boolean deleteAppAllVersionInfoById( String appId ) {

        return appVersionDao.deleteAppAllVersionInfoById( appId );
    }

    public boolean createVersionInfo( String appId, AppVersionInfo appVersionInfo ) {
        return appVersionDao.createVersionInfo( appId,  appVersionInfo );
    }
}
