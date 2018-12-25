package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yinming on 2018/12/25.
 */
@FeignClient( "sce-data-access" )
public interface AppOpenDao {
    /**
     * 获取用户开通列表
     * @param userId
     * @return
     */
    @RequestMapping( value = "/app-open", method = RequestMethod.GET )
    RestRecord getUserAppOpenList(
            @RequestParam( "userId" ) String userId );
}
