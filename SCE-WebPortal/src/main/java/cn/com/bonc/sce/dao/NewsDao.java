package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.News;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

/**
 * 新闻增删改相关接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Repository
@FeignClient( "sce-data-access" )
public interface NewsDao {

    /**
     * 新增教育新闻
     *
     * @param news 新闻
     * @return 添加新闻是否成功
     */
    @RequestMapping( value = "/news", method = RequestMethod.POST )
    public RestRecord insertNews( News news );

    /**
     * 删除教育新闻
     *
     * @param list 新闻Id列表
     * @return 删除新闻是否成功
     */
    @RequestMapping( value = "/news/{list}", method = RequestMethod.DELETE )
    public RestRecord deleteNewsByIdList( @PathVariable( "list" ) String list );

    /**
     * 更改教育新闻
     *
     * @param news 新闻
     * @return 更新新闻是否成功
     */
    @RequestMapping( value = "/news", method = RequestMethod.PUT )
    public RestRecord updateNews( News news );

    /**
     * 查询新闻列表接口
     *
     * @param auditStatus 新闻审核状态
     * @param content     内容
     * @param startDate   查询起始日期
     * @param endDate     查询结束日期
     * @param pageNum     分页页码
     * @param pageSize    分页每页条数
     * @return 分页后的新闻列表
     */
    @RequestMapping( value = "/news/list/{auditStatus}", method = RequestMethod.GET )
    public RestRecord getNewsList( @PathVariable( "auditStatus" ) String auditStatus,
                                   @RequestParam( "content" ) String content,
                                   @RequestParam( "startDate" ) String startDate,
                                   @RequestParam( "endDate" ) String endDate,
                                   @RequestParam( "pageNum" ) Integer pageNum,
                                   @RequestParam( "pageSize" ) Integer pageSize);

    @RequestMapping( value = "/news/list/{auditStatus}", method = RequestMethod.GET )
    public RestRecord getNewsList( @PathVariable( "auditStatus" ) String auditStatus,
                                   @RequestParam( "content" ) String content,
                                   @RequestParam( "pageNum" ) Integer pageNum,
                                   @RequestParam( "pageSize" ) Integer pageSize);

    /**
     * 查询新闻详情接口
     *
     * @param newsId 新闻id
     * @return 分页后的新闻列表
     */
    @RequestMapping( value = "/news/{newsId}", method = RequestMethod.GET )
    public RestRecord getNews( @PathVariable( "newsId" ) Integer newsId );
}
