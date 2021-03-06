package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.NewsModel;
import cn.com.bonc.sce.model.NewsParamModel;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author BTW
 */
@Repository
@FeignClient( "sce-data-mybatis" )
public interface NewsInfoDao {

    /**
     * 新增教育新闻
     *
     * @param newsModel 新闻
     * @return 添加新闻是否成功
     */
    @RequestMapping( value = "/news-info/new-info", method = RequestMethod.POST )
    RestRecord insertNewsInfo( NewsModel newsModel );

    /**
     * 更新教育新闻
     *
     * @param newsModel 新闻
     * @return 更新新闻是否成功
     */
    @RequestMapping( value = "/news-info/updated-info", method = RequestMethod.POST )
    RestRecord updateNewsInfo( NewsModel newsModel );

    /**
     * 删除新闻
     *
     * @param idList
     * @param userId
     * @return
     */
    @RequestMapping( value = "/news-info/{userId}", method = RequestMethod.DELETE )
    RestRecord deleteNewsInfo( @RequestBody List< Long > idList,
                               @PathVariable( "userId" ) String userId );

    /**
     * 删除头条新闻
     *
     * @param idList
     * @param userId
     * @return
     */
    @RequestMapping( value = "/news-info/top-news/{userId}", method = RequestMethod.DELETE )
    RestRecord deleteTopNewsInfo( @RequestBody List< Long > idList,
                                  @PathVariable( "userId" ) String userId );

    /**
     * 新闻审核
     *
     * @param contentStatus
     * @param userId
     * @param rejectOpinion
     * @param contentId
     * @return
     */
    @RequestMapping( value = "/news-info/audit-info", method = RequestMethod.POST )
    RestRecord auditNewsInfo( @RequestParam( "contentStatus" ) String contentStatus,
                              @RequestParam( "userId" ) String userId,
                              @RequestParam( "rejectOpinion" ) String rejectOpinion,
                              @RequestParam( "contentId" ) Long contentId );

    /**
     * 更新新闻发布状态
     *
     * @param isPublish
     * @param userId
     * @param contentId
     * @return
     */
    @RequestMapping( value = "/news-info/publish-info", method = RequestMethod.POST )
    RestRecord updateNewsPublishStatus( @RequestParam( "isPublish" ) Integer isPublish,
                                        @RequestParam( "userId" ) String userId,
                                        @RequestParam( "contentId" ) Long contentId );

    /**
     * 根据ID查询新闻
     *
     * @param contentId
     * @return
     */
    @RequestMapping( value = "news-info/one-news-info", method = RequestMethod.GET )
    RestRecord selectNewsById( @RequestParam( "contentId" ) Long contentId );


    /**
     * 获取新闻列表
     * @return
     */
    @RequestMapping( value = "/news-info/select-front-list", method = RequestMethod.POST )
    RestRecord selectNewsList(@RequestParam( "pageSize" ) Integer pageSize,
                              @RequestParam( "pageNum" ) Integer pageNum,
                              NewsParamModel newsModel);

    /**
     * 获取头条新闻列表（按照头条排序）
     * @return
     */
    @RequestMapping( value = "/news-info/select-top-list", method = RequestMethod.POST )
    RestRecord getTopNewsListWithOrder(@RequestParam( "pageSize" ) Integer pageSize,
                                       @RequestParam( "pageNum" ) Integer pageNum,
                                       NewsParamModel newsModel);

    /**
     * 获取新闻列表
     * @return
     */
    @RequestMapping( value = "/news-info/select-back-list", method = RequestMethod.POST )
    RestRecord selectBackendNewsList(@RequestParam( "pageSize" ) Integer pageSize,
                              @RequestParam( "pageNum" ) Integer pageNum,
                              NewsParamModel newsModel);


    /**
     * 获取非头条列表
     * @return
     */
    @RequestMapping( value = "/news-info/select-notTop-list", method = RequestMethod.POST )
    RestRecord selectNotTopList(@RequestParam( "pageSize" ) Integer pageSize,
                                     @RequestParam( "pageNum" ) Integer pageNum,
                                     NewsParamModel newsModel);
    /**
     * 获取点击量列表
     * @return
     */
    @RequestMapping( value = "/news-info/select-hit-rank", method = RequestMethod.POST )
    RestRecord selectVolumeRankList(@RequestParam( "pageSize" ) Integer pageSize,
                                     @RequestParam( "pageNum" ) Integer pageNum);

    /**
     * 获取最新列表
     * @return
     */
    @RequestMapping( value = "/news-info/select-newest-rank", method = RequestMethod.POST )
    RestRecord selectNewestList(@RequestParam( "pageSize" ) Integer pageSize,
                                    @RequestParam( "pageNum" ) Integer pageNum);

    /**
     * 查询头条新闻列表
     *
     * @return
     */
    @RequestMapping( value = "news-info/top-manage-list", method = RequestMethod.GET )
    RestRecord selectTopNewsList();

    /**
     * 更新头条新闻排序信息
     *
     * @return
     */
    @RequestMapping( value = "news-info/top-order-change", method = RequestMethod.POST )
    RestRecord updateTopNewsInfo( @RequestBody Map newsInfoMap );

    /**
     * 添加头条新闻
     *
     * @param map
     * @return
     */
    @RequestMapping( value = "news-info/new-top-news", method = RequestMethod.POST)
    RestRecord addTopNewsInfo(@RequestBody Map map);
}
