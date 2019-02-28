package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.LoginPermissionService;
import io.swagger.annotations.ApiOperation;
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
    private LoginPermissionService loginPermissionService;

    @ApiOperation(value = "用户登录权限控制", notes="通过接收当前登录权限状态，切换登录权限", httpMethod = "PUT")
    @PutMapping("/changePermission")
    @ResponseBody
    public RestRecord changePermission(
            @RequestParam ( "userId" ) String userId,
            @RequestParam( "loginPermissionStatus" ) Integer loginPermissionStatus ) {
        return loginPermissionService.updateLoginPermission(userId,loginPermissionStatus);
    }
}
