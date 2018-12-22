package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;;
import java.util.Map;

/**
 * @author 管理员
 * @Auther: tlz
 * @Date: 2018/12/22 10:11
 * @Description:
 */
@FeignClient( value = "sce-data-access" )
public interface UserOperationDao {

    /**
     * 添加用户
     *
     * @param userInfo
     * @return 是否添加成功
     */
    @RequestMapping( value = "/user-info", method = RequestMethod.POST )
    RestRecord addUserInfo(
            @RequestBody Map< String, Object > userInfo );


    /**
     * 更新用户
     *
     * @param userInfo
     * @return 是否更新成功
     */
    @RequestMapping( value = "/user-info", method = RequestMethod.PUT )
    RestRecord updateUserInfoById( @RequestBody Map< String, Object > userInfo );


    /**
     * 删除用户
     *
     * @param userId
     * @return 是否删除成功
     */
    @RequestMapping( value = "/user-info", method = RequestMethod.DELETE )
    RestRecord deleteUserInfoById( @RequestParam( "userId" ) String userId );


    /**
     * 查询用户
     *
     * @param userId
     * @return 用户信息
     */
    @RequestMapping( value = "/user-info", method = RequestMethod.GET )
    RestRecord selectUserInfoById( @RequestParam( "userId" ) String userId );


}
