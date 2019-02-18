package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.AppInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 应用推荐-重点推荐应用接口-dao
 *
 * @author jc_D
 * @description
 * @date 2018/12/16
 **/
@Slf4j
@Repository
public class TopAppDao {

    public List< AppInfoEntity > selectTopRecommendAppList() {
        return null;
    }

    public boolean addTopRecommendAppList() {
        return true;
    }

}
