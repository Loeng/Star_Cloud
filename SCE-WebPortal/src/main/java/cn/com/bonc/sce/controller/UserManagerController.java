package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.rest.RestRecord;

import cn.com.bonc.sce.service.UserManagerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Charles on 2019/3/8.
 */
@Slf4j
@RestController
@RequestMapping( "/userManager" )
public class UserManagerController {

    @Autowired
    private UserManagerService userManagerService;

    @ApiOperation(value = "删除用户", notes="通过用户id，删除用户", httpMethod = "DELETE")
    @DeleteMapping("/delUser")
    @ResponseBody
    public RestRecord delUser(@RequestParam( "id" ) String id){
        return userManagerService.delUser(id);
    }

    @ApiOperation(value = "密码重置", notes="重置密码为初始密码", httpMethod = "PUT")
    @PutMapping("/resetPwd")
    @ResponseBody
    public RestRecord resetPwd(@RequestParam( "id" ) String id){
        return userManagerService.resetPwd(id);
    }

    @ApiOperation(value = "登录权限控制", notes="修改登录权限", httpMethod = "PUT")
    @PutMapping("/editLogin")
    @ResponseBody
    public RestRecord editLogin(@RequestParam( "id" ) String id,
                                @RequestParam( "loginPermissionStatus" ) Integer loginPermissionStatus){
        return userManagerService.editLogin(id,loginPermissionStatus);
    }

    @GetMapping( "/teacher-list/{pageNum}/{pageSize}" )
    public RestRecord findTeacherList( @CurrentUserId String userId, @PathVariable String pageNum, @PathVariable String pageSize ) {
        return userManagerService.findTeacherList( userId, pageNum, pageSize );
    }

    @GetMapping( "/teacher/{userName}" )
    public RestRecord findTeacher( @CurrentUserId String userId, @PathVariable String userName ) {
        return userManagerService.findTeacher( userId, userName );
    }

}
