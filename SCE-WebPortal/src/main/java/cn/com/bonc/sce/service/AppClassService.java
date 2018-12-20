package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppClassDao;
import cn.com.bonc.sce.model.appListAndClass.AppClass;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 应用分类
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */

@Slf4j
@Service
public class AppClassService {

    @Autowired
    private AppClassDao appClassDao;

    /**
     * 添加应用分类
     *
     * @param appClass 信息
     * @return 是否添加成功
     */
    public RestRecord insertAppClass( AppClass appClass ) {
        return appClassDao.insertAppClass( appClass );
    }

    /**
     * 通过id删除应用分类
     *
     * @param classId id
     * @return 删除是否成功
     */
    public RestRecord deleteAppClassById( Integer classId ) {
        return appClassDao.deleteAppClassById( classId );
    }

    /**
     * 更新应用分类
     *
     * @param appClass 应用分类信息
     * @return appClass
     */
    public RestRecord updateAppClassInfo( AppClass appClass ) {
        return appClassDao.updateAppClassInfo( appClass );
    }

    /**
     * 获取所有应用分类数据
     *
     * @return 应用分类数据list
     */
    public RestRecord getAllAppClassInfo() {
        return appClassDao.getAllAppClassInfo();
    }

}
