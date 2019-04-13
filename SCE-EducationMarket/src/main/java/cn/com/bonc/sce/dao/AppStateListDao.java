package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@FeignClient( "sce-data-mybatis" )
public interface AppStateListDao {

    @RequestMapping( value = "/app-state/{state}", method = RequestMethod.GET )
    RestRecord getStateList( @PathVariable( "state" ) Integer state,
                             @RequestParam( value = "auditStatus", required = false ) String auditStatus,
                             @RequestParam( value = "typeId", required = false, defaultValue = "0" ) Integer typeId,
                             @RequestParam( value = "keyword", required = false ) String keyword,
                             @RequestParam( value = "orderBy", required = false ) String orderBy,
                             @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                             @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize,
                             @RequestParam( value = "userId" ) String userId );

}
