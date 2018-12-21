package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.HotAppDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HotAppService {

    private HotAppDao hotAppDao;

    @Autowired
    public HotAppService( HotAppDao hotAppDao ) {
        this.hotAppDao = hotAppDao;
    }

    public RestRecord addHotRecommendAppList( List< String > appIdList, String userId ) {
        return hotAppDao.addHotRecommendAppList( appIdList, userId );
    }

    public RestRecord selectHotRecommendAppList( Integer pageNum, Integer pageSize ) {
        // 查询应用表中热门推荐状态为true的应用
        return hotAppDao.selectHotRecommendAppList( pageNum, pageSize );
    }


}
