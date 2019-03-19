package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppMarketDao;
import cn.com.bonc.sce.filter.Socket;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class AppMarketService {

    @Autowired
    private AppMarketDao appMarketDao;

    @Resource
    Socket socket;

    public RestRecord appCount(){
        return appMarketDao.appCount();
    }

    public RestRecord userToDo(String userId){
        return appMarketDao.userToDo(userId);
    }

    public RestRecord backlog(Map<String, Object> backlog, String userId){
        RestRecord restRecord = appMarketDao.backlog(userId, backlog);
        for(String operateUserId : (List<String>) backlog.get("users")){
            socket.sendMessage(operateUserId, backlog.get("content"));
        }
        return restRecord;
    }
}
