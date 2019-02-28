package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.NavigationDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Charles on 2019/2/27.
 */

@Service
@Slf4j
public class NavigationService {

    @Autowired
    private NavigationDao navigationDao;

    public RestRecord getNavListByChannel(Integer channelId){
        return navigationDao.getNavListByChannel(channelId);
    }

    public RestRecord addNav(String json){
        return navigationDao.addNav(json);
    }

    public RestRecord editNav(String json){
        return  navigationDao.editNav(json);
    }

    public RestRecord getSchools(String keywords, Integer pageNum, Integer pageSize){
        return navigationDao.getSchools(keywords,pageNum,pageSize);
    }
}
