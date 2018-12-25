package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppDownloadDao;
import cn.com.bonc.sce.dao.AppManageDao;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2018/12/25 15:44
 * @Description:
 */
public class AppDownloadService {

    private AppDownloadDao appDownloadDao;

    @Autowired
    public AppDownloadService( AppDownloadDao appDownloadDao ) {
        this.appDownloadDao= appDownloadDao;
    }

    public RestRecord getUserAppDownloadList( String userId ) {
        return appDownloadDao.getUserAppDownloadList( userId );
    }

}
