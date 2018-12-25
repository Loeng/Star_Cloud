package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.NewsDao;
import cn.com.bonc.sce.model.News;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 新闻增删改
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Slf4j
@Service
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
        return newsDao.insertNews( news );
    }

    /**
     * 删除教育新闻
     *
     * @param list 新闻Id列表
     * @return 删除新闻是否成功
     */
    public RestRecord deleteNewsByIdList( String list ) {
        return newsDao.deleteNewsByIdList( list );
    }


    /**
     * 更改教育新闻
     *
     * @param news 新闻
     * @return 更新新闻是否成功
     */
    public RestRecord updateNews( News news ) {
        return newsDao.updateNews( news );
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
    public RestRecord getNewsList( String auditStatus,String startDate,String endDate,Integer pageNum,Integer pageSize) {
        if( StringUtils.isEmpty(startDate)){
            return newsDao.getNewsList( auditStatus,  pageNum, pageSize );
        }
        return newsDao.getNewsList( auditStatus, startDate, endDate, pageNum, pageSize );
    }

    /**
     * 查询新闻详情接口
     *
     * @param newsId 新闻id
     * @return 分页后的新闻列表
     */
    public RestRecord getNews( Integer newsId ) {
        return newsDao.getNews( newsId );
    }
}
