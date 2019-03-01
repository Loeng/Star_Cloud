package cn.com.bonc.sce.api;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.LoginPermissionDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.LoginPermissionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Charles on 2019/2/26.
 */
@Slf4j
@RestController
@RequestMapping( "/change-userInfo" )
public class LoginPermissionController {

    @Autowired
    private LoginPermissionDao loginPermissionDao;

    @ApiOperation(value = "用户登录权限控制", notes="通过接收当前登录权限状态，切换登录权限", httpMethod = "PUT")
    @PutMapping("/changePermission")
    @ResponseBody
    public RestRecord changePermission(
            @RequestParam ( "userId" ) String userId,
            @RequestParam( "loginPermissionStatus" ) Integer loginPermissionStatus ) {
        int newStatus = (loginPermissionStatus==0) ? 1 : 0;
        int count = loginPermissionDao.updateLoginPermission(userId,newStatus);
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 407, MessageConstants.SCE_MSG_407 );
        }
    }
}
