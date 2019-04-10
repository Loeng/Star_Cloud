package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.bean.NewsBean;
import cn.com.bonc.sce.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public int auditNewsInfo( String contentStatus, String userId, String rejectOpinion, Long contentId ) {
        return newsMapper.auditNewsInfo( contentStatus, userId, rejectOpinion, contentId );
    }

    public int updateNewsPublishStatus( Integer isPublish, String userId, Long contentId){
        return newsMapper.updateNewsPublishStatus( isPublish, userId, contentId );
    }
}
