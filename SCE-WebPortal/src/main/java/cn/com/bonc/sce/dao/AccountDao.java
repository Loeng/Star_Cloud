package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.Account;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 账号安全信息相关
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Repository
@FeignClient( "sce-data-access" )
public interface AccountDao {
    /*@RequestMapping( value = "/updateAccount/getUserAccountPhone/{userAccount}", method = RequestMethod.GET )
    public RestRecord getUserAccountPhone( String userAccount);*/
    /**
     * 修改account
     *
     * @param account account
     * @return advise数据list
     */
    @RequestMapping( value = "/accountSecurity", method = RequestMethod.PUT )
    public RestRecord updateAccount( Account account);
}
