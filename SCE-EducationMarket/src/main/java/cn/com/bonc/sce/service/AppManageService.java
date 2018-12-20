package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppManageDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppManageService {

    private AppManageDao marketAppDao;

    @Autowired
    public AppManageService( AppManageDao marketAppDao ) {
        this.marketAppDao = marketAppDao;
    }


}
