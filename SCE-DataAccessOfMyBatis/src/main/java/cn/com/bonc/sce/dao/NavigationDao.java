package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.bean.NavigationBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.mapper.NavigationMapper;
import cn.com.bonc.sce.model.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Charles on 2019/2/27.
 */
@Repository
public class NavigationDao {

    @Autowired( required = false )
    private NavigationMapper navigationMapper;

    public List<NavigationBean> getChannel (Integer channelType){
        return navigationMapper.getChannel(channelType);
    }

    public int addChannel(String channelName,String channelUrl,long channelId,int channelType,int isDelete){
        return navigationMapper.addChannel(channelName,channelUrl,channelId,channelType,isDelete);
    }

    public int editChannel(String channelName,String channelUrl, String channelId){
        return navigationMapper.editChannel(channelName,channelUrl,channelId);
    }

    public List<SchoolBean> getSchools (String keywords){
        return navigationMapper.getSchools(keywords);
    }

    public List<Banner> getBanners (long schoolId){
        return navigationMapper.getBanners(schoolId);
    }

    public Integer editDefaultBanner (long schoolId, Integer newBanner){
        return navigationMapper.editDefaultBanner(schoolId,newBanner);
    }

    public Integer delBanner (long bannerId){
        return navigationMapper.delBanner(bannerId);
    }

    public Integer delChannel(String channelId) {
        return navigationMapper.delChannel(channelId);
    }
}
