package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2018/12/25 15:47
 * @Description:
 */
@FeignClient( "sce-data-access" )
public interface AppDownloadDao {

    /**
     * 获取用户下载列表
     * @param userId
     * @return
     */
    @RequestMapping( value = "/app-download", method = RequestMethod.POST )
    RestRecord getUserAppDownloadList(
            @RequestParam( "userId" ) String userId,
            @RequestParam( "pageSize" ) Integer pageSize,
            @RequestParam( "pageNumber" ) Integer pageNumber );


}
