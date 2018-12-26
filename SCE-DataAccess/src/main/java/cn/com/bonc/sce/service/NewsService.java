package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.NewsDao;
import cn.com.bonc.sce.entity.News;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
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
@Service
@Transactional( rollbackFor = Exception.class )
public class NewsService {

    @Autowired
    private NewsDao newsDao;

    /**
     * 新增教育新闻
     *
     * @param news 新闻
     * @return 添加新闻是否成功
     */
    public RestRecord insertNews( News news ) {
        news.setId( null );
        news.setColumnId( 1 );
        news.setIsDelete( 1 );
        return new RestRecord( 200, newsDao.save( news ) );
    }

    /**
     * 删除教育新闻
     *
     * @param list 新闻Id列表
     * @return 删除新闻是否成功
     */
    public RestRecord deleteNewsByIdList( String list ) {
        String[] idArr = list.split( "," );
        int total = 0;
        for ( String idStr : idArr ) {
            total += newsDao.updateDeleteStatusById( Integer.parseInt( idStr ) );
        }
        return new RestRecord( 200, total );
    }


    /**
     * 更改教育新闻
     *
     * @param news 新闻
     * @return 更新新闻是否成功
     */
    public RestRecord updateNews( News news ) {
        news.setIsDelete( 1 );
        return new RestRecord( 200, newsDao.save( news ) );
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
    public RestRecord getNewsList( String auditStatus, String content, String startDate, String endDate, Integer pageNum, Integer pageSize ) {
        Pageable pageable = PageRequest.of( pageNum, pageSize );
        Page< News > page;
        if ( StringUtils.isEmpty( content ) ) {
            content = "%%";
        } else {
            content = "%" + content + "%";
        }
        if ( StringUtils.isEmpty( startDate ) ) {
            page = newsDao.findByIsDeleteAndContentLikeAndColumnIdAndContentStatus( 1, content, 1, auditStatus, pageable );
        } else {
            page = newsDao.findByIsDeleteAndContentLikeAndColumnIdAndContentStatusAndUpdateTimeBetween( 1, content, 1, auditStatus, new Date( Long.parseLong( startDate ) ), new Date( Long.parseLong( endDate ) ), pageable );
        }
        List< News > list = page.getContent();
        for ( News news : list ) {
            if ( news.getUser() == null ) {
                continue;
            }
            news.setUserName( news.getUser().getUserName() );
            news.setUser( null );
            if ( news.getPic() == null ) {
                continue;
            }
            news.setPicUrl( news.getPic().getFileMappingPath() );
            news.setPic( null );
        }
        Map< String, Object > info = new HashMap<>();
        info.put( "total", page.getTotalElements() );
        info.put( "info", list );
        return new RestRecord( 200, info );
    }

    /**
     * 查询新闻详情接口
     *
     * @param newsId 新闻id
     * @return 分页后的新闻列表
     */
    public RestRecord getNews( Integer newsId ) {
        News news = newsDao.findByIdAndIsDelete( newsId, 1 );
        if ( news.getUser() != null ) {
            news.setUserName( news.getUser().getUserName() );
            news.setUser( null );
        }
        return new RestRecord( 200, news );
    }
}
