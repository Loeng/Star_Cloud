package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppClassDao;
import cn.com.bonc.sce.entity.AppClass;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 应用分类
 *
 * @author wzm
 * @version 0.1
 * @since 2018/17/12 12:00
 */
@Slf4j
@Service
@Transactional( rollbackFor = Exception.class )
public class AppClassService {
    private AppClassDao appClassDao;

    @Autowired
    public AppClassService( AppClassDao appClassDao ) {
        this.appClassDao = appClassDao;
    }

    /**
     * 添加应用分类
     *
     * @param appClass 信息
     * @return 是否添加成功
     */
    public RestRecord insertAppClass( AppClass appClass ) {
        return new RestRecord( 200, appClassDao.save( appClass ) );
    }

    /**
     * 通过id删除应用分类
     *
     * @param classId id
     * @return 删除是否成功
     */
    public RestRecord deleteAppClassById( Integer classId ) {
        appClassDao.updateDeleteStatusById( classId );
        return new RestRecord( 200 );
    }

    /**
     * 更新应用分类
     *
     * @param appClass 应用分类信息
     * @return appClass
     */
    public RestRecord updateAppClassInfo( AppClass appClass ) {
        return new RestRecord( 200, appClassDao.save( appClass ) );
    }

    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    public RestRecord getAllAppClassInfo() {
        return new RestRecord( 200, appClassDao.findByIsDelete( 1 ) );
    }
}

