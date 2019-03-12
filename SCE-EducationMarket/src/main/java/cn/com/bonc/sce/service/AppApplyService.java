package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppManageDao;
import cn.com.bonc.sce.dao.HotAppDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author BTW
 * @version 0.1
 * @since 2018/12/14 17:26
 */
@Slf4j
@Service
public class AppApplyService {

    private AppManageDao  appManageDao;

    @Autowired
    public AppApplyService( AppManageDao appManageDao ) {
        this.appManageDao = appManageDao;
    }

    public RestRecord applyAppOnShelf( Integer applyType, List< Map > appIdList, String userId ) {
        return appManageDao.applyAppOnShelf(applyType, appIdList, userId );
    }

}
