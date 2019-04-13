package cn.com.bonc.sce.api;

import cn.com.bonc.sce.bean.NewsBean;
import cn.com.bonc.sce.bean.NewsParamBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.NewsDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.NewsService;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author BTW
 */
@Slf4j
@RestController
@RequestMapping( "/news-info" )
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsDao newsDao;

    /**
     * 新增教育新闻
     *
     * @param newsBean 新闻
     * @return 添加新闻是否成功
     */
    @Transactional
    @PostMapping( "/new-info" )
    @ResponseBody
    public RestRecord insertNewsInfo( @RequestBody NewsBean newsBean ) {
        try {
            int count = newsService.insertNewsInfo( newsBean );
            if ( count == 1 ) {
                return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
            } else {
                return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
            }
        } catch ( Exception e ) {
            log.error( "insert newsInfo fail {}", e );
            return new RestRecord( 423, MessageConstants.SCE_MSG_409, e );
        }
    }

    /**
     * 修改教育新闻
     *
     * @param newsBean 新闻
     * @return 修改新闻是否成功
     */
    @PostMapping( "/updated-info" )
    @ResponseBody
    public RestRecord updateNewsInfo( @RequestBody NewsBean newsBean ) {
        int count = newsDao.updateNewsInfo( newsBean );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 409, MessageConstants.SCE_MSG_407 );
        }
    }

    @Transactional
    @DeleteMapping( "/{userId}" )
    public RestRecord deleteNewsInfo( @RequestBody List< Long > idList,
                                      @PathVariable( "userId" ) String userId ) {
        try {
            newsDao.deleteNewsInfo( idList, userId );
        } catch ( Exception e ) {
            log.error( "delete newsInfo fail {}", e );
            return new RestRecord( 423, WebMessageConstants.SCE_PORTAL_MSG_422, e );
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    @Transactional
    @DeleteMapping( "/top-news/{userId}" )
    public RestRecord deleteTopNewsInfo( @RequestBody List< Long > idList,
                                         @PathVariable( "userId" ) String userId ) {
        try {
            newsDao.deleteTopNewsInfo( idList, userId );
        } catch ( Exception e ) {
            log.error( "delete topNewsInfo fail {}", e );
            return new RestRecord( 423, WebMessageConstants.SCE_PORTAL_MSG_422, e );
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    @Transactional
    @PostMapping( "/new-top-news" )
    @ResponseBody
    public RestRecord addTopNewsInfo( @RequestBody Map map ) {
        List< Long > idList = ( List< Long > ) map.get( "idList" );
        String userId = String.valueOf( map.get( "userId" ) );
        int count = newsDao.selectTopNewsCount();
        int listCount = idList.size();
        if( (count + listCount) > 8 ) {
            return new RestRecord( 423, WebMessageConstants.SCE_PORTAL_MSG_490 );
        }
        try {
            newsDao.addTopNewsInfo( idList, userId );
        } catch ( Exception e ) {
            log.error( "add topNewsInfo fail {}", e );
            return new RestRecord( 423, WebMessageConstants.SCE_PORTAL_MSG_423, e );
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    @PostMapping( "/audit-info" )
    @ResponseBody
    public RestRecord auditNewsInfo( @RequestParam( "contentStatus" ) String contentStatus,
                                     @RequestParam( "userId" ) String userId,
                                     @RequestParam( "rejectOpinion" ) String rejectOpinion,
                                     @RequestParam( "contentId" ) Long contentId ) {
        if ( StrUtil.isBlank( rejectOpinion ) ) {
            rejectOpinion = "";
        }
        int count = newsDao.auditNewsInfo( contentStatus, userId, rejectOpinion, contentId );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 409, MessageConstants.SCE_MSG_407 );
        }
    }

    @PostMapping( "/publish-info" )
    @ResponseBody
    public RestRecord updateNewsPublishStatus( @RequestParam( "isPublish" ) Integer isPublish,
                                               @RequestParam( "userId" ) String userId,
                                               @RequestParam( "contentId" ) Long contentId ) {
        int count = newsDao.updateNewsPublishStatus( isPublish, userId, contentId );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 409, MessageConstants.SCE_MSG_407 );
        }
    }

    @GetMapping( "/one-news-info" )
    @ResponseBody
    public RestRecord selectNewsById( @RequestParam( "contentId" ) Long contentId ) {
        Map newsInfo = newsDao.selectNewsDetailById( contentId );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, newsInfo );
    }

    @GetMapping( "/top-manage-list" )
    @ResponseBody
    public RestRecord selectTopNewsList() {
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, newsDao.selectTopNewsList() );
    }

    @PostMapping( "/top-order-change" )
    @ResponseBody
    public RestRecord updateNewsInfo( @RequestBody Map newsInfoMap ) {
        List< Map > newsBeanList = ( List< Map > ) newsInfoMap.get( "newsList" );
        String userId = ( String ) newsInfoMap.get( "userId" );
        int count = newsService.updateTopNewsOrder( newsBeanList, userId );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 409, MessageConstants.SCE_MSG_407 );
        }
    }

    @PostMapping("/select-front-list")
    @ResponseBody
    public RestRecord getNewsList( @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize,
                                   @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
                                   @RequestBody NewsParamBean newsBean){
        PageHelper.startPage( pageNum, pageSize );
        List<NewsParamBean> list = newsDao.selectNewsList(newsBean);
        PageInfo pageInfo = new PageInfo( list );
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, pageInfo );
    }

    @PostMapping("/select-top-list")
    @ResponseBody
    public RestRecord getNewsTopList( @RequestParam( value = "pageSize", defaultValue = "6" ) Integer pageSize,
                                   @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
                                   @RequestBody NewsParamBean newsBean){
        PageHelper.startPage( pageNum, pageSize );
        List<NewsParamBean> list = newsDao.fetchTopNewsOrderList(newsBean);
        PageInfo pageInfo = new PageInfo( list );
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, pageInfo );
    }

    @PostMapping("/select-back-list")
    @ResponseBody
    public RestRecord getBackNewsList( @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize,
                                   @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum,
                                   @RequestBody NewsParamBean newsBean){
        PageHelper.startPage( pageNum, pageSize );
        List<NewsParamBean> list = newsDao.selectBackendNewsList(newsBean);
        PageInfo pageInfo = new PageInfo( list );
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200, pageInfo );
    }


    @PostMapping( "/select-hit-rank" )
    @ResponseBody
    public RestRecord selectHitVolumeRank( @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize,
                                           @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum ) {
        PageHelper.startPage( pageNum, pageSize );
        List<Map> list = newsDao.getHitVolumeRank();
        PageInfo pageInfo = new PageInfo( list );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, pageInfo );
    }

    @PostMapping( "/select-newest-rank" )
    @ResponseBody
    public RestRecord selectNewestRank( @RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize,
                                           @RequestParam( value = "pageNum", defaultValue = "1" ) Integer pageNum ) {
        PageHelper.startPage( pageNum, pageSize );
        List<Map> list = newsDao.getNewestNewsRank();
        PageInfo pageInfo = new PageInfo( list );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, pageInfo );
    }
}
