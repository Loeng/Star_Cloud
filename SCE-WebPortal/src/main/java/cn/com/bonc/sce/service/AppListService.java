package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppListDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 应用列表
 *
 * @author wzm
 * @version 0.1
 * @since 2018/17/12 12:00
 */
@Slf4j
@Service
public class AppListService {

    @Autowired
    private AppListDao appListDao;

    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    public RestRecord getAppListInfo( Integer appClassId,String keyword) {
        if( !StringUtils.isEmpty( appClassId )){
            if(!StringUtils.isEmpty( keyword ))
                return appListDao.getAppListInfo(appClassId,keyword);
            else
                return appListDao.getAppListInfo(appClassId);
        }else{
            if(!StringUtils.isEmpty( keyword ))
                return appListDao.getAppListInfo(keyword);
            else
                return appListDao.getAppListInfo();
        }
    }
}
