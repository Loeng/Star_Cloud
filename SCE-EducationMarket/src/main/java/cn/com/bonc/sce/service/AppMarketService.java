package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppMarketDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppMarketService {

    @Autowired
    AppMarketDao appMarketDao;

    public RestRecord getAppCount(){
        return appMarketDao.selectAppCount();
    }
}
