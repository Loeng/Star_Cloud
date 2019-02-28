package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Charles on 2019/2/26.
 *
 * 用户登录权限DAO
 */
@FeignClient( value = "sce-data-mybatis" )
@Repository
public interface LoginPermissionDao {

    @RequestMapping( value = "/change-userInfo/changePermission", method = RequestMethod.PUT )
    RestRecord updateLoginPermission (@RequestParam( "userId" ) String userId,
                                      @RequestParam( "loginPermissionStatus" ) Integer loginPermissionStatus);

}
