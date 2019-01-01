package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppCollectDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: hefan
 * @Date: 2018/12/25 22:25
 * @Description: 用户应用操作服务
 */

@Service
@Slf4j
public class AppCollectService {

    private AppCollectDao appCollectDao;

    @Autowired
    public AppCollectService( AppCollectDao appCollectDao ) {
        this.appCollectDao= appCollectDao;
    }

    public RestRecord getUserAppCollect(String userId) {
        return appCollectDao.getUserAppCollect( userId);
    }

    public RestRecord addUserAppCollectionInfo( String userId, String appId ) {
        return appCollectDao.addUserAppOpenInfo( userId, appId );
    }

    public RestRecord deleteUserAppCollectionInfo( String userId, String appId ) {
        return appCollectDao.addUserAppOpenInfo( userId, appId );
    }
}
