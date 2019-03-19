package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppMarketDao;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class AppMarketService {

    @Autowired
    private AppMarketDao appMarketDao;


    public RestRecord appCount(){
        return appMarketDao.appCount();
    }

    public RestRecord userToDo(String userId, String pageNum, String pageSize){
        return appMarketDao.userToDo(userId, pageNum, pageSize);
    }

    public RestRecord backlog(Map<String, Object> backlog, String userId){
        RestRecord restRecord = appMarketDao.backlog(userId, backlog);
//        for(String operateUserId : (List<String>) backlog.get("users")){
//            socket.sendMessage(operateUserId, backlog.get("content"));
//        }
        return restRecord;
    }

    public RestRecord backlog_patch(String userId, String backlogId, String status){
        Map map = new HashMap();
        map.put("backlogId", backlogId);
        map.put("status", status);
        return appMarketDao.backlog_patch(userId, map);
    }

}
