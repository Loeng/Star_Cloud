package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.NavigationBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.dao.NavigationDao;
import cn.com.bonc.sce.model.Banner;
import cn.com.bonc.sce.rest.RestRecord;
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

    public int addChannel(String channelName,String channelUrl,long channelId,int channelType,int isDelete){
        return navigationDao.addChannel(channelName,channelUrl,channelId,channelType,isDelete);
    }

    public int editChannel(String channelName,String channelUrl, String channelId){
        return navigationDao.editChannel(channelName,channelUrl,channelId);
    }

    public List<SchoolBean> getSchools(String keywords){
        return navigationDao.getSchools(keywords);
    }

    public List<Banner> getBanners(long schoolId){
        return navigationDao.getBanners(schoolId);
    }

    public Integer editDefaultBanner(long schoolId, Integer newBanner) {
        return navigationDao.editDefaultBanner(schoolId,newBanner);
    }

    public Integer delBanner(long bannerId) {
        return navigationDao.delBanner(bannerId);
    }

    public Integer delChannel(String channelId) {
        return navigationDao.delChannel(channelId);
    }
}
