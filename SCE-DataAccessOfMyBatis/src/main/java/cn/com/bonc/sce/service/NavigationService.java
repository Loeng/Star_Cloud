package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.NavigationBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.dao.NavigationDao;
import cn.com.bonc.sce.model.Banner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Charles on 2019/2/27.
 */

@Service
@Slf4j
public class NavigationService {

    @Autowired
    private NavigationDao navigationDao;

    public List<NavigationBean> getChannel(Integer channelType){
        return navigationDao.getChannel(channelType);
    }

    public int addNav(String columnName,String columnUrl, Integer channelId){
        return navigationDao.addNav(columnName,columnUrl,channelId);
    }

    public int editNav(String columnName,String columnUrl, Integer columnId){
        return navigationDao.editNav(columnName,columnUrl,columnId);
    }

    public List<SchoolBean> getSchools(String keywords){
        return navigationDao.getSchools(keywords);
    }

    public List<Banner> getBanners(Integer schoolId){
        return navigationDao.getBanners(schoolId);
    }

    public Integer editDefaultBanner(Integer schoolId, Integer newBanner) {
        return navigationDao.editDefaultBanner(schoolId,newBanner);
    }

    public Integer delBanner(Integer bannerId) {
        return navigationDao.delBanner(bannerId);
    }

}
