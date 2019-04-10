package cn.com.bonc.sce.mapper;


import cn.com.bonc.sce.bean.NewsBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     *
     * * 新闻审核
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
}
