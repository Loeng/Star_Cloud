package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.AppVersionInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppVersionDao {
    public List< AppVersionInfo > selectAppHistoryVersionList( String appId ) {
        return null;
    }

    public boolean updateAppHistoryVersionInfo( String appId, AppVersionInfo appVersionInfo ) {
        return false;
    }

    public boolean deleteAppHistoryVersionInfo( String appId, AppVersionInfo appVersionInfo ) {
        return false;
    }

    public boolean deleteAppAllVersionInfoById( String appId ) {
        return false;
    }

    public boolean createVersionInfo( String appId, AppVersionInfo appVersionInfo ) {
        return false;
    }
}
