package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.NewsInfoDao;
import cn.com.bonc.sce.model.NewsModel;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public RestRecord auditNewsInfo( String contentStatus, String userId, String rejectOpinion, Long contentId ) {
        return newsInfoDao.auditNewsInfo( contentStatus, userId, rejectOpinion, contentId );
    }

    public RestRecord updateNewsPublishStatus( Integer isPublish, String userId, Long contentId ) {
        return newsInfoDao.updateNewsPublishStatus( isPublish, userId, contentId );
    }

    public RestRecord selectNewsById(Long contentId) {
        return newsInfoDao.selectNewsById( contentId );
    }
}
