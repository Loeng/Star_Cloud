package cn.com.bonc.sce.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/27 21:02
 */
@FeignClient( "sce-data-access" )
public interface LoginHistoryDaoClient {
    @RequestMapping( value = "/login-his/{userId}/login-his", method = RequestMethod.POST )
    public boolean insertLoginRecord( @PathVariable( "userId" ) String userId, @RequestBody String loginIp );
}
