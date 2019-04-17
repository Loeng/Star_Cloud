package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.NewsInfoDao;
import cn.com.bonc.sce.model.NewsModel;
import cn.com.bonc.sce.model.NewsParamModel;
import cn.com.bonc.sce.model.NewsStatusModel;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BTW
 */
@Slf4j
@Service
public class NewsInfoService {

    @Autowired
    private NewsInfoDao newsInfoDao;

    public RestRecord insertNewsInfo( NewsModel newsModel ) {
        return newsInfoDao.insertNewsInfo( newsModel );
    }

    public RestRecord updateNewsInfo( NewsModel newsModel ) {
        return newsInfoDao.updateNewsInfo( newsModel );
    }

    public RestRecord deleteNewsInfo( List< Long > idList, String userId ) {
        return newsInfoDao.deleteNewsInfo( idList, userId );
    }

    public RestRecord deleteTopNewsInfo( List< Long > idList, String userId ) {
        return newsInfoDao.deleteTopNewsInfo( idList, userId );
    }

    public RestRecord addTopNewsInfo( Map map ) {
        return newsInfoDao.addTopNewsInfo( map );
    }

    public RestRecord auditNewsInfo( String contentStatus, String userId, String rejectOpinion, Long contentId ) {
        return newsInfoDao.auditNewsInfo( contentStatus, userId, rejectOpinion, contentId );
    }

    public RestRecord updateNewsPublishStatus( Integer isPublish, String userId, Long contentId ) {
        return newsInfoDao.updateNewsPublishStatus( isPublish, userId, contentId );
    }

    public RestRecord selectNewsById(Long contentId) {
        return newsInfoDao.selectNewsById( contentId );
    }

    public RestRecord getAllNews( Integer pageSize, Integer pageNum, NewsParamModel newsModel) {

        return newsInfoDao.selectNewsList(pageSize, pageNum,newsModel);
    }

    public RestRecord getTopNewsWithOrder( Integer pageSize, Integer pageNum, NewsParamModel newsModel) {

        return newsInfoDao.getTopNewsListWithOrder(pageSize, pageNum,newsModel);
    }

    public RestRecord getBackendNewsList( Integer pageSize, Integer pageNum, NewsParamModel newsModel) {

        return newsInfoDao.selectBackendNewsList(pageSize, pageNum,newsModel);
    }

    /**
     * 获取非头条列表
     * @param pageSize
     * @param pageNum
     * @param newsModel
     * @return
     */
    public RestRecord getNotTopList( Integer pageSize, Integer pageNum, NewsParamModel newsModel) {

        return newsInfoDao.selectNotTopList(pageSize, pageNum,newsModel);
    }

    /**
     * 获取点击量排行列表
     * @param pageSize
     * @param pageNum
     * @return
     */
    public RestRecord selectVolumeRankList( Integer pageSize, Integer pageNum) {

        return newsInfoDao.selectVolumeRankList(pageSize, pageNum);
    }


    /**
     * 最新发布列表
     * @param pageSize
     * @param pageNum
     * @return
     */
    public RestRecord selectNewestList( Integer pageSize, Integer pageNum) {

        return newsInfoDao.selectNewestList(pageSize, pageNum);
    }

    public RestRecord selectTopNewsList(){
        return newsInfoDao.selectTopNewsList();
    }

    public RestRecord updateTopNewsInfo( List<NewsStatusModel> newsList, String userId ){
        Map<String,Object> newsInfoMap = new HashMap<>();
        newsInfoMap.put( "newsList",newsList );
        newsInfoMap.put( "userId",userId );
        return newsInfoDao.updateTopNewsInfo( newsInfoMap );
    }
}
