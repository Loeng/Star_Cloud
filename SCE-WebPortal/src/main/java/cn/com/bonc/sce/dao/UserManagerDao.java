package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Charles on 2019/3/8.
 */
@Repository
@FeignClient( value = "sce-data-mybatis" )
public interface UserManagerDao {

    @RequestMapping( value = "/userManager/delUser", method = RequestMethod.DELETE )
    RestRecord delUser(@RequestParam("id")String id);

    @RequestMapping( value = "/userManager/resetPwd", method = RequestMethod.PUT )
    RestRecord resetPwd(@RequestParam("id") String id);

    @RequestMapping( value = "/userManager/editLogin", method = RequestMethod.PUT )
    RestRecord editLogin(@RequestParam("id")String id, @RequestParam("loginPermissionStatus")Integer loginPermissionStatus);
}
