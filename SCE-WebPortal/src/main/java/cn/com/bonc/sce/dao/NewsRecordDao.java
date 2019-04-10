package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.NewsRecordModel;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author BTW
 */
@Repository
@FeignClient( "sce-data-mybatis" )
public interface NewsRecordDao {

    /**
     * 添加新闻浏览记录
     *
     * @param newsRecordModel
     * @return
     */
    @RequestMapping( value = "/news-record/new-record", method = RequestMethod.POST )
    RestRecord insertNewsRecord( NewsRecordModel newsRecordModel);
}
