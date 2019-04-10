package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.NewsRecordBean;

/**
 * @author BTW
 */
public interface NewsRecordMapper {

    /**
     * 插入新闻浏览记录
     *
     * @param newsRecordBean
     * @return
     */
    int insertNewsRecord( NewsRecordBean newsRecordBean);
}
