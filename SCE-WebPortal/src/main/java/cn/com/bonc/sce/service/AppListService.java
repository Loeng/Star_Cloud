package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppListDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return appListDao.getAppListInfo(appClassId,keyword);
    }
}
