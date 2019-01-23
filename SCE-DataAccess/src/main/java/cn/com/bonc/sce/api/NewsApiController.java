package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.entity.News;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 新闻增删改
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Slf4j
@RestController
@RequestMapping( "/news" )
public class NewsApiController {

    @Autowired
    private NewsService newsService;

    /**
     * 新增教育新闻
     *
     * @param news 新闻
     * @return 添加新闻是否成功
     */
    @PostMapping
    @ResponseBody
    public RestRecord insertNews( @RequestBody News news ) {
        try {
            return newsService.insertNews( news );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 409, MessageConstants.SCE_MSG_409, e );
        }
    }

    /**
     * 删除教育新闻
     *
     * @param list 新闻Id列表
     * @return 删除新闻是否成功
     */
    @DeleteMapping( "/{list}" )
    @ResponseBody
    public RestRecord deleteNewsByIdList( @PathVariable( "list" ) String list ) {
        try {
            return newsService.deleteNewsByIdList( list );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 408, MessageConstants.SCE_MSG_408, e );
        }
    }


    /**
     * 更改教育新闻
     *
     * @param news 新闻
     * @return 更新新闻是否成功
     */
    @PutMapping
    @ResponseBody
    public RestRecord updateNews( @RequestBody News news ) {
        try {
            return newsService.updateNews( news );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 407, MessageConstants.SCE_MSG_407, e );
        }
    }

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
    @GetMapping( "/list/{auditStatus}" )
    @ResponseBody
    public RestRecord getNewsList( @PathVariable( "auditStatus" ) String auditStatus,
                                   @RequestParam( value = "content", required = false ) String content,
                                   @RequestParam( value = "startDate", required = false ) String startDate,
                                   @RequestParam( value = "endDate", required = false ) String endDate,
                                   @RequestParam( value = "pageNum", required = false, defaultValue = "0" ) Integer pageNum,
                                   @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize ) {
        try {

            RestRecord rr = newsService.getNewsList( auditStatus, content, startDate, endDate, pageNum, pageSize );
            List< News > list = (List< News >)((Map< String, Object > )rr.getData()).get( "info" );
            for ( News news : list ) {
                if ( news.getUser() == null ) {
                    continue;
                }
                news.setUserName( news.getUser().getUserName() );
                news.setUser( null );
                if ( news.getPic() == null ) {
                    continue;
                }
                news.setPicUrl( news.getPic().getFileStorePath() );
                news.setPic( null );
            }
            return rr;
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }

    /**
     * 查询新闻详情接口
     *
     * @param newsId 新闻id
     * @return 分页后的新闻列表
     */
    @GetMapping( "/{newsId}" )
    @ResponseBody
    public RestRecord getNews( @PathVariable( "newsId" ) Integer newsId ) {
        try {
            News news = newsService.getNews( newsId );
            if ( news.getUser() != null ) {
                news.setUserName( news.getUser().getUserName() );
                news.setUser( null );
            }
            return new RestRecord( 200, news );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }
}
