package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.UserModel;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 管理员
 * @Auther: tlz
 * @Date: 2018/12/22 10:11
 * @Description:
 */
@FeignClient( value = "sce-data-access" )
@Repository
public interface UserOperationDao {

    /**
     * 添加用户
     *
     * @param roleId
     * @param userInfo
     * @return 是否添加成功
     */
    @RequestMapping( value = "/user-info/addUser/{roleId}", method = RequestMethod.POST )
    RestRecord addUserInfo( @PathVariable( "roleId" ) Integer roleId, @RequestBody Map< String, Object > userInfo );


    /**
     * 更新用户
     *
     * @param userInfo
     * @return 是否更新成功
     */
    @RequestMapping( value = "/user-info/updateUserInfo", method = RequestMethod.PUT )
    RestRecord updateUserInfoById( @RequestBody Map< String, Object > userInfo, @RequestParam( "userId" ) String userId );


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
     * @param userName
     * @return 用户信息
     */
    @RequestMapping( value = "/user-info", method = RequestMethod.GET )
    RestRecord selectUserInfoByName( @RequestParam( "userName" ) String userName );


    @RequestMapping( value = "/user-info/insert", method = RequestMethod.POST )
    RestRecord insertUser( @RequestBody UserModel userModel );
}
