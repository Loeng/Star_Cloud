package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.LoginPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Charles on 2019/2/26.
 *
 * 用户登录权限DAO
 */

@Repository
public class LoginPermissionDao {

    @Autowired( required = false )
    private LoginPermissionMapper loginPermissionMapper;

    public Integer updateLoginPermission (String userId, Integer loginPermissionStatus){
        return loginPermissionMapper.updateLoginPermission(userId,loginPermissionStatus);
    }
}
