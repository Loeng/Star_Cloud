package cn.com.bonc.sce.mapper;


import cn.com.bonc.sce.bean.NewsBean;
import cn.com.bonc.sce.bean.NewsParamBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author BTW
 */
public interface NewsMapper {

    /**
     * 新增新闻信息
     *
     * @param newsBean
     * @return
     */
    int insertNewsInfo( NewsBean newsBean );

    /**
     * 修改新闻信息
     *
     * @param newsBean
     * @return
     */
    int updateNewsInfo( NewsBean newsBean );

    /**
     * 删除新闻信息
     *
     * @param idList
     * @param userId
     * @return
     */
    int deleteNewsInfo( @Param( "idList" ) List< Long > idList,
                        @Param( "userId" ) String userId );

    /**
     * 取消头条信息
     *
     * @param idList
     * @param userId
     * @return
     */
    int deleteTopNewsInfo( @Param( "idList" ) List< Long > idList,
                           @Param( "userId" ) String userId );

    /**
     * 添加头条信息
     *
     * @param idList
     * @param userId
     * @return
     */
    int addTopNewsInfo( @Param( "idList" ) List< Long > idList,
                        @Param( "userId" ) String userId );


    /**
     * * 新闻审核
     *
     * @param contentStatus 1：通过审核 2：不通过审核
     * @param userId
     * @param rejectOpinion
     * @param contentId
     * @return
     */
    int auditNewsInfo( @Param( "contentStatus" ) String contentStatus,
                       @Param( "userId" ) String userId,
                       @Param( "rejectOpinion" ) String rejectOpinion,
                       @Param( "contentId" ) Long contentId );

    /**
     * 更新新闻发布状态
     *
     * @param isPublish
     * @param userId
     * @param contentId
     * @return
     */
    int updateNewsPublishStatus( @Param( "isPublish" ) Integer isPublish,
                                 @Param( "userId" ) String userId,
                                 @Param( "contentId" ) Long contentId );

    /**
     * 通过新闻Id查询新闻详情
     *
     * @param contentId
     * @return
     */
    Map< String, Object > selectNewsDetailById( @Param( "contentId" ) Long contentId );


    /**
     * 查询title是否重复
     *
     * @param contentTitle
     * @return
     */
    Map< String, Object > getNumByTitle( @Param( "contentTitle" ) String contentTitle );

    /**
     * 获取点击排行
     *
     * @return
     */
    List< Map >  getHitVolumeRank();

    /**
     * 获取最新发布排行
     *
     * @return
     */
    List< Map >  getNewestNewsRank();

    /**
     * 查询头条新闻列表
     *
     * @return
     */
    List< Map > selectTopNewsList();

    /**
     * 更新头条新闻排序信息
     *
     * @param topOrder
     * @param contentId
     * @param userId
     * @return
     */
    int updateTopNewOrder( @Param( "topOrder" ) Integer topOrder,
                           @Param( "contentId" ) Long contentId,
                           @Param( "userId" ) String userId );

    /**
     * 更新新闻排序信息
     *
     * @param showOrder
     * @param contentId
     * @param userId
     * @return
     */
    int updateNormalNewOrder( @Param( "showOrder" ) Integer showOrder,
                           @Param( "contentId" ) Long contentId,
                           @Param( "userId" ) String userId );

    /**
     * 查询头条新闻数量
     *
     * @return
     */
    @Select( "SELECT COUNT(*) FROM STARCLOUDPORTAL.SCE_COMMON_COLUMN_CONTENT WHERE IS_DELETE = 1 AND CONTENT_STATUS = '1' AND IS_PUBLISH = 1 AND IS_TOP = 1" )
    int selectTopNewsCount();

    /**
     * 查询新闻栏列表（按照新闻排序）
     *
     * @return
     */
    List<NewsParamBean> selectNewsList(NewsParamBean newsBean);

    /**
     * 查询头条新闻列表（按照头条排序）
     * @param newsBean
     * @return
     */
    List<NewsParamBean> fetchTopNewsList(NewsParamBean newsBean);

    /**
     * 查询新闻栏列表（后台管理用）
     *
     * @return
     */
    List<NewsParamBean> selectBackendNewsList(NewsParamBean newsBean);


    /**
     * 查询非头条列表（后台管理用）
     *
     * @return
     */
    List<NewsParamBean> selectNotTopList(NewsParamBean newsBean);


    /**
     * 查询头条新闻数量
     *
     * @return
     */
    @Select( "SELECT T.NEWS_CONTENT CONTENT FROM STARCLOUDPORTAL.SCE_COMMON_COLUMN_CONTENT T WHERE CONTENT_ID = #{contentId}" )
    String selectNewsContent(@Param("contentId") String contentId);


}
