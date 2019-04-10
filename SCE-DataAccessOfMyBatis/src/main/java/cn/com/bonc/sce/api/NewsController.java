package cn.com.bonc.sce.api;

import cn.com.bonc.sce.bean.NewsBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.NewsDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.NewsService;
import cn.com.bonc.sce.tool.IdWorker;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

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
    @PostMapping("/new-info")
    @ResponseBody
    public RestRecord insertNewsInfo( @RequestBody NewsBean newsBean){
        try{
            int count = newsService.insertNewsInfo( newsBean );
            if ( count == 1 ) {
                return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
            } else {
                return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
            }
        } catch ( Exception e ) {
            log.error( "delete newsInfo fail {}", e );
            return new RestRecord( 423, MessageConstants.SCE_MSG_409, e );
        }
    }

    /**
     * 修改教育新闻
     *
     * @param newsBean 新闻
     * @return 修改新闻是否成功
     */
    @PostMapping("/updated-info")
    @ResponseBody
    public RestRecord updateNewsInfo( @RequestBody NewsBean newsBean){
        int count = newsDao.updateNewsInfo( newsBean );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 409, MessageConstants.SCE_MSG_407 );
        }
    }

    @Transactional
    @DeleteMapping( "/{userId}" )
    public RestRecord deleteApps( @RequestBody List< Long > idList,
                                  @PathVariable( "userId" ) String userId ) {
        try {
            newsDao.deleteNewsInfo( idList, userId );
        } catch ( Exception e ) {
            log.error( "delete newsInfo fail {}", e );
            return new RestRecord( 423, WebMessageConstants.SCE_PORTAL_MSG_422, e );
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    @PostMapping( "/audit-info" )
    @ResponseBody
    public RestRecord auditNewsInfo(@RequestParam( "contentStatus" ) String contentStatus,
                                    @RequestParam( "userId" ) String userId,
                                    @RequestParam( "rejectOpinion" ) String rejectOpinion,
                                    @RequestParam("contentId") Long contentId){
        if( StrUtil.isBlank( rejectOpinion )) {
            rejectOpinion = "";
        }
        int count = newsDao.auditNewsInfo( contentStatus, userId, rejectOpinion, contentId );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 409, MessageConstants.SCE_MSG_407 );
        }
    }
}
