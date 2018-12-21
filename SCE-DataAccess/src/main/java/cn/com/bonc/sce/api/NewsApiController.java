package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.NewsDao;
import cn.com.bonc.sce.model.message.News;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    private NewsDao newsDao;

    /**
     * 新增教育新闻
     *
     * @param news 新闻
     * @return 添加新闻是否成功
     */
    @PostMapping
    @ResponseBody
    public RestRecord insertNews( News news ) {
        try {
            return new RestRecord( 200, newsDao.save( news ) );
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
            String[] idArr = list.split( "," );
            int total = 0;
            for(String idStr:idArr){
                total+=newsDao.updateDeleteStatusById( Integer.parseInt( idStr ));
            }
            return new RestRecord( 200, total );
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
    public RestRecord updateNews( News news ) {
        try {
            return new RestRecord( 200, newsDao.save( news ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 407, MessageConstants.SCE_MSG_407, e );
        }
    }

    /**
     * 查询新闻列表接口
     *
     * @param auditStatus 新闻审核状态
     * @param startDate   查询起始日期
     * @param endDate     查询结束日期
     * @param pageNum     分页页码
     * @param pageSize    分页每页条数
     * @return 分页后的新闻列表
     */
    @GetMapping( "/{auditStatus}/{startDate}/{endDate}/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getNewsList( @PathVariable( "auditStatus" ) String auditStatus,
                                   @PathVariable( "startDate" ) String startDate,
                                   @PathVariable( "endDate" ) String endDate,
                                   @PathVariable( "pageNum" ) Integer pageNum,
                                   @PathVariable( "pageSize" ) Integer pageSize ) {
        try {
            Pageable pageable = PageRequest.of( pageNum, pageSize );
            Page< News > info = newsDao.findByIsDeleteAndContentStatusAndUpdateTimeBetween( 0,auditStatus,startDate,endDate, pageable );
            return new RestRecord( 200, info );
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
            return new RestRecord( 200, newsDao.findByIdAndIsDelete( newsId, 0 ) );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }
}
