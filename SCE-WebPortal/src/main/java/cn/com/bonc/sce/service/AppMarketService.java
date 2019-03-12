package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppMarketDao;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppMarketService {

    @Autowired
    private AppMarketDao appMarketDao;

    public RestRecord appCount(){
        return appMarketDao.appCount();
    }

    public RestRecord userToDo(String userId){
        return appMarketDao.userToDo(userId);
    }
}
