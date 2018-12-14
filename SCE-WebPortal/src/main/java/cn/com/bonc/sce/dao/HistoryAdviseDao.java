package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.HistoryAdvise;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@FeignClient( "sce-data-access" )
public interface HistoryAdviseDao {

    /**
     * 添加historyAdvise
     *
     * @param historyAdvise 信息
     * @return 是否添加成功
     */
    @RequestMapping( value = "/historyAdvises", method = RequestMethod.POST )
    public RestRecord insertHistoryAdvise( HistoryAdvise historyAdvise );

    /**
     * 通过id删除historyAdvise
     *
     * @param historyAdviseId  id
     * @return 删除是否成功
     */
    @RequestMapping( value = "/historyAdvises/{historyAdviseId}", method = RequestMethod.DELETE )
    public RestRecord deleteHistoryAdviseById( @PathVariable( "historyAdviseId" ) String historyAdviseId );

    /**
     * 更新historyAdvise
     *
     * @param historyAdvise historyAdvise信息
     * @return historyAdvise
     */
    @RequestMapping( value = "/historyAdvises", method = RequestMethod.PUT )
    public RestRecord updateHistoryAdviseInfo( HistoryAdvise historyAdvise );

    /**
     * 修改url
     *
     * @param historyAdviseId   historyAdviseId
     * @param url 待修改的url
     * @return 跟新是否成功
     */
    @RequestMapping( value = "/historyAdvises/{historyAdviseId}", method = RequestMethod.PATCH )
    public RestRecord updateHistoryAdviseUrl( @PathVariable( "historyAdviseId" ) String historyAdviseId, @RequestParam( "url" ) String url );

    /**
     * 获取historyAdvise数据
     *
     * @param historyAdviseId historyAdviseId
     * @return historyAdvise数据
     */
    @RequestMapping( value = "/historyAdvises/{historyAdviseId}", method = RequestMethod.GET )
    public RestRecord getHistoryAdviseById( @PathVariable( "historyAdviseId" ) String historyAdviseId );

    /**
     * 获取所有historyAdvise数据
     *
     * @return historyAdvise数据list
     */
    @RequestMapping( value = "/historyAdvises", method = RequestMethod.GET )
    public RestRecord getAllHistoryAdvisesInfo();
}
