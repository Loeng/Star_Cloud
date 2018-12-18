package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.app.AppInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jc_D
 * @description
 * @date 2018/12/16
 **/
@Slf4j
@Repository
public class HotAppDao {

    public List< AppInfoEntity > selectHotRecommendAppList() {
        return null;
    }

    public boolean addHotRecommendAppList() {
        return true;
    }

    public boolean deleteHotRecommendApp() {
        return true;
    }
}
