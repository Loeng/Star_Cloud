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
     * 查询头条新闻数量
     *
     * @return
     */
    @Select( "SELECT COUNT(*) FROM STARCLOUDPORTAL.SCE_COMMON_COLUMN_CONTENT WHERE IS_DELETE = 1 AND CONTENT_STATUS = '1' AND IS_PUBLISH = 1 AND IS_TOP = 1" )
    int selectTopNewsCount();

    /**
     * 查询新闻栏目类型列表
     *
     * @return
     */
    List<NewsParamBean> selectNewsList(NewsParamBean newsBean);

}
