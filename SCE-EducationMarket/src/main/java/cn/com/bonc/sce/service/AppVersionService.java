package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppVersionDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class AppVersionService {
    private AppVersionDao appVersionDao;

    @Autowired
    public AppVersionService( AppVersionDao appVersionDao ) {
        this.appVersionDao = appVersionDao;
    }

    public RestRecord selectAppHistoryVersionList( String appId ,String appVersion) {
        return appVersionDao.selectAppHistoryVersionList( appId ,appVersion);
    }

    public RestRecord updateAppHistoryVersionInfo( String appId, Map<String,String> marketAppVersion ) {
        return appVersionDao.updateAppHistoryVersionInfo( appId, marketAppVersion );
    }

    public RestRecord deleteAppHistoryVersionInfo( String appId, Map<String,String> marketAppVersion ) {
        return appVersionDao.deleteAppHistoryVersionInfo( appId, marketAppVersion );
    }

    public RestRecord deleteAppAllVersionInfoById( String appId ) {

        return appVersionDao.deleteAppAllVersionInfoById( appId );
    }

    public boolean createVersionInfo( String appId, Map<String,String> marketAppVersion ) {
        return appVersionDao.createVersionInfo( appId,  marketAppVersion );
    }
}
