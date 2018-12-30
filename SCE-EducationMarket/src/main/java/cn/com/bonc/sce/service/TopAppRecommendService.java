package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.TopAppRecommendDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TopAppRecommendService {

    private TopAppRecommendDao topAppDao;

    @Autowired
    public TopAppRecommendService( TopAppRecommendDao topAppRecommendDao ) {
        this.topAppDao = topAppRecommendDao;
    }

    public RestRecord addTopRecommendAppList( List< String > appIdList, String userId ) {
        return topAppDao.addTopRecommendAppList( appIdList, userId );
    }

    public RestRecord selectTopRecommendAppList( Integer pageNum, Integer pageSize ) {
        // 查询应用表中重点推荐状态为true的应用
        return topAppDao.selectTopRecommendAppList( pageNum, pageSize );
    }

    public RestRecord addTopRecommendApp( String userId, String appId ) {
        return topAppDao.addTopRecommendApp( userId, appId );
    }

    public RestRecord cancelTopRecommendApp( String userId, String appId ) {
        return topAppDao.cancelTopRecommendApp( userId, appId );
    }

    public RestRecord selectTopAppList( Integer pageNum, Integer pageSize, String userId ) {
        return topAppDao.selectTopAppList( pageNum, pageSize, userId );
    }



}
