package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Repository
@FeignClient( "sce-data-access" )
public interface AppMarketDao {

    /**
     * 根据用户 id 获取用户详细信息
     *
     * @param userId 用户id
     * @return 用户的详细数据
     */
    @RequestMapping( value = "/users/{userId}", method = RequestMethod.GET )
    public User getUserById(@PathVariable( "userId" ) String userId );
}
