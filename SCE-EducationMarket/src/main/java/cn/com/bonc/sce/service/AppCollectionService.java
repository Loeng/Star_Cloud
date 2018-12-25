package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppCollectionDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author BTW
 */
@Slf4j
@Service
public class AppCollectionService {
    private AppCollectionDao appCollectionDao;

    @Autowired
    public AppCollectionService( AppCollectionDao appCollectionDao ) {
        this.appCollectionDao = appCollectionDao;
    }

    public RestRecord addUserAppCollectionInfo( String userId, String appId ) {
        return appCollectionDao.addUserAppOpenInfo( userId, appId );
    }
}
