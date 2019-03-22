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

    public RestRecord getChannel(Integer channelType){
        return navigationDao.getChannel(channelType);
    }

    public RestRecord addChannel(String json){
        return navigationDao.addChannel(json);
    }

    public RestRecord editChannel(String json){
        return  navigationDao.editChannel(json);
    }

    public RestRecord getSchools(String keywords, Integer pageNum, Integer pageSize){
        return navigationDao.getSchools(keywords,pageNum,pageSize);
    }

    public RestRecord getBanners(Integer schoolId){
        return navigationDao.getBanners(schoolId);
    }

    public RestRecord editDefaultBanner(Integer schoolId,Integer defaultBanner){
        return navigationDao.editDefaultBanner(schoolId,defaultBanner);
    }

    public RestRecord delBanner(Integer bannerId){
        return navigationDao.delBanner(bannerId);
    }

    public RestRecord delChannel(String channelId) {
        return navigationDao.delChannel(channelId);
    }
}
