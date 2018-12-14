package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.Advise;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@FeignClient( "sce-data-access" )
public interface AdviseDao {

    /**
     * 添加advise
     *
     * @param advise 信息
     * @return 是否添加成功
     */
    @RequestMapping( value = "/advises", method = RequestMethod.POST )
    public RestRecord insertAdvise( Advise advise );

    /**
     * 通过id删除advise
     *
     * @param adviseId  id
     * @return 删除是否成功
     */
    @RequestMapping( value = "/advises/{adviseId}", method = RequestMethod.DELETE )
    public RestRecord deleteAdviseById( @PathVariable( "adviseId" ) String adviseId );

    /**
     * 更新advise
     *
     * @param advise advise信息
     * @return advise
     */
    @RequestMapping( value = "/advises", method = RequestMethod.PUT )
    public RestRecord updateAdviseInfo( Advise advise );

    /**
     * 修改url
     *
     * @param adviseId   adviseId
     * @param url 待修改的url
     * @return 跟新是否成功
     */
    @RequestMapping( value = "/advises/{adviseId}", method = RequestMethod.PATCH )
    public RestRecord updateAdviseUrl( @PathVariable( "adviseId" ) String adviseId, @RequestParam( "url" ) String url );

    /**
     * 获取advise数据
     *
     * @param adviseId adviseId
     * @return advise数据
     */
    @RequestMapping( value = "/advises/{adviseId}", method = RequestMethod.GET )
    public RestRecord getAdviseById( @PathVariable( "adviseId" ) String adviseId );

    /**
     * 获取所有advise数据
     *
     * @return advise数据list
     */
    @RequestMapping( value = "/advises", method = RequestMethod.GET )
    public RestRecord getAllAdvisesInfo();
}
