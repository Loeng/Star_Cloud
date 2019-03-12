package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppListDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 应用列表
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Slf4j
@Service
@Transactional( rollbackFor = Exception.class )
public class AppListService {
    @Autowired
    private AppListDao appListDao;

    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    public RestRecord getAppListInfo() {
        return new RestRecord( 200, appListDao.findByIsDelete( 0 ) );
    }

    /**
     * 获取所有应用分类数据
     *
     * @param classId 应用分类信息
     * @return 应用分类数据list
     */
    public RestRecord getAppListInfo( Integer classId ) {
        return new RestRecord( 200, appListDao.findByCategoryIdAndIsDelete( classId, 0 ) );
    }

    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    public RestRecord getAppListInfo( String keyword ) {
        return new RestRecord( 200, appListDao.findByAppNameLikeAndIsDelete( "%" + keyword + "%", 0 ) );
    }

    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    public RestRecord getAppListInfo( Integer classId, String keyword ) {
        return new RestRecord( 200, appListDao.findByCategoryIdAndAppNameLikeAndIsDelete( classId, "%" + keyword + "%", 0 ) );
    }
}

