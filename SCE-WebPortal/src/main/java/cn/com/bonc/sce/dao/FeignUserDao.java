package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 7:54
 */
@FeignClient("sce-data-access")
public interface FeignUserDao {

    /**
     * 根据用户 id 获取用户详细信息
     * @param userId 用户id
     */
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public User getUserById( @PathVariable("userId") String userId );
}
