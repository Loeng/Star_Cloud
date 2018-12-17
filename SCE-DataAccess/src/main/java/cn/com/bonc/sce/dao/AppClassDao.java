package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.AppClass;
import cn.com.bonc.sce.model.Banner;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppClassDao {

    public boolean insertAppClass(AppClass appClass) {
        return true;
    }

    public Integer deleteAppClassById(Integer appClassId) {
        return null;
    }

    public Integer updateAppClassInfo(AppClass appClass) {
        return null;
    }

    public List<AppClass > getAllAppClassInfo() {
        return null;
    }
}
