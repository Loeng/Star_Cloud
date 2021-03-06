package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.bean.NewsBean;
import cn.com.bonc.sce.bean.NewsParamBean;
import cn.com.bonc.sce.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author BTW
 */
@Repository
public class NewsDao {

    @Autowired
    private NewsMapper newsMapper;

    public int insertNewsInfo( NewsBean newsBean ) {
        return newsMapper.insertNewsInfo( newsBean );
    }

    public int updateNewsInfo( NewsBean newsBean ) {
        return newsMapper.updateNewsInfo( newsBean );
    }

    public int deleteNewsInfo( List< Long > idList, String userId ) {
        return newsMapper.deleteNewsInfo( idList, userId );
    }

    public int deleteTopNewsInfo( List< Long > idList, String userId ) {
        return newsMapper.deleteTopNewsInfo( idList, userId );
    }

    public int addTopNewsInfo( List< Long > idList, String userId ) {
        return newsMapper.addTopNewsInfo( idList, userId );
    }

    public int auditNewsInfo( String contentStatus, String userId, String rejectOpinion, Long contentId ) {
        return newsMapper.auditNewsInfo( contentStatus, userId, rejectOpinion, contentId );
    }

    public int updateNewsPublishStatus( Integer isPublish, String userId, Long contentId){
        return newsMapper.updateNewsPublishStatus( isPublish, userId, contentId );
    }

    public Map selectNewsDetailById( Long contentId ) {
        return newsMapper.selectNewsDetailById( contentId );
    }
    public String selectNewsContent(Long contentId){return newsMapper.selectNewsContent( contentId.toString() );}

    public List<NewsParamBean> selectNewsList(NewsParamBean newsBean) {
        return newsMapper.selectNewsList(newsBean);
    }

    public List<NewsParamBean> fetchTopNewsOrderList(NewsParamBean newsBean) {
        return newsMapper.fetchTopNewsList(newsBean);
    }
    public List<NewsParamBean> selectBackendNewsList(NewsParamBean newsBean) {
        return newsMapper.selectBackendNewsList(newsBean);
    }

    /**
     * 获取非头条列表
     * @param newsBean
     * @return
     */
    public List<NewsParamBean> selectNotTopList(NewsParamBean newsBean) {
        return newsMapper.selectNotTopList(newsBean);
    }

    /**
     * 查询title是否重复
     * @param contentTitle
     * @return
     */
    public Map getNumByTitle( String contentTitle,String contentId ) {
        return newsMapper.getNumByTitle( contentTitle,contentId );
    }

    public List selectTopNewsList(){
        return newsMapper.selectTopNewsList();
    }

    public int updateTopNewsOrder(Integer topOrder, Long contentId, String userId){
        return newsMapper.updateTopNewOrder( topOrder, contentId, userId );
    }
    public int updateNormalNewsOrder(Integer showOrder, Long contentId, String userId){
        return newsMapper.updateNormalNewOrder( showOrder, contentId, userId );
    }

    public int selectTopNewsCount(){
        return newsMapper.selectTopNewsCount();
    }

    /**
     * 获取点击排行榜
     * @return
     */
    public List<Map> getHitVolumeRank() {
        return newsMapper.getHitVolumeRank();
    }
    /**
     * 获取最新发布排行榜
     * @return
     */
    public List< Map >  getNewestNewsRank() {
        return newsMapper.getHitVolumeRank();
    }
}
