package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.bean.NavigationBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.mapper.NavigationMapper;
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

    public List<NavigationBean> getNavListByChannel (Integer channelId){
        return navigationMapper.getNavListByChannel(channelId);
    }

    public int addNav(String columnName,String columnUrl, Integer channelId){
        return navigationMapper.addNav(columnName,columnUrl,channelId);
    }

    public int editNav(String columnName,String columnUrl, Integer columnId){
        return navigationMapper.editNav(columnName,columnUrl,columnId);
    }

    public List<SchoolBean> getSchools (String keywords){
        return navigationMapper.getSchools(keywords);
    }
}
