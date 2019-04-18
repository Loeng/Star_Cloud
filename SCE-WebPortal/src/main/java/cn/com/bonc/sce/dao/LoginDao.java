package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.Account;
import cn.com.bonc.sce.model.SSOAuthentication;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 账号安全信息相关
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Repository
@FeignClient( "sce-authentication-server" )
public interface LoginDao {

    /**
     * 登陆获取ticket
     *
     * @param ssoAuthentication
     * @return
     */
    @RequestMapping( value = "/authentication", method = RequestMethod.POST )
    public RestRecord getTicket( @RequestBody SSOAuthentication ssoAuthentication );
}
