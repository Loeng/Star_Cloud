package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppDownloadDao;
import cn.com.bonc.sce.dao.AppManageDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2018/12/25 15:44
 * @Description:
 */
@Service
@Slf4j
public class AppDownloadService {

    private AppDownloadDao appDownloadDao;

    @Autowired
    public AppDownloadService( AppDownloadDao appDownloadDao ) {
        this.appDownloadDao= appDownloadDao;
    }

    public RestRecord getUserAppDownloadList( String userId, Integer pageSize, Integer pageNumber ) {
        return appDownloadDao.getUserAppDownloadList( userId, pageSize, pageNumber );
    }

    public RestRecord addUserDownloadInfo( String userId, String appId ) {
        return appDownloadDao.addUserDownloadInfo( userId, appId );
    }

}
