package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.AccountSecurity;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Repository
@FeignClient( "sce-data-access" )
public interface AccountSecurityDao {
    /*@RequestMapping( value = "/updateAccount/getUserAccountPhone/{userAccount}", method = RequestMethod.GET )
    public RestRecord getUserAccountPhone( String userAccount);*/
    /**
     * 获取所有advise数据
     *
     * @return advise数据list
     */
    @RequestMapping( value = "/updateAccount", method = RequestMethod.POST )
    public RestRecord updateAccount(AccountSecurity accountSecurity);
}
