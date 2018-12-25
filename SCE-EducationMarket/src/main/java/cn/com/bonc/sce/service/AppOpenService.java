package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppOpenDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yinming on 2018/12/25.
 */
@Service
@Slf4j
public class AppOpenService {
    private AppOpenDao appOpenDao;

    @Autowired
    public AppOpenService( AppOpenDao appOpenDao ) {
        this.appOpenDao= appOpenDao;
    }

    public RestRecord getUserAppOpenList(String userId) {
        return appOpenDao.getUserAppOpenList( userId);
    }
}
