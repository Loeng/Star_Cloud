package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppMarketDao;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppMarketService {

    @Autowired
    private AppMarketDao appMarketDao;


    public RestRecord appCount(){
        return appMarketDao.appCount();
    }

    public RestRecord userToDo( String userId, String pageNum, String pageSize ) {
        return appMarketDao.userToDo( userId, pageNum, pageSize );
    }

    public RestRecord backlog(Map< String, Object > backlog, List<Map> list) {
        String appId = backlog.get( "appId" ).toString();
        String appToken = backlog.get( "appToken" ).toString();
        String userId = list.get(0).get("userId").toString();
        return appMarketDao.backlog( appId, appToken, userId, backlog );
    }

    public RestRecord backlog_patch( String userId, Map map ){
        return appMarketDao.backlog_patch( userId, map );
    }

}
