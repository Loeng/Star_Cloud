package cn.com.bonc.sce.api;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.UserService;
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
    private UserService userService;

    @ApiOperation(value = "删除用户", notes="通过用户id，删除用户", httpMethod = "DELETE")
    @DeleteMapping("/delUser")
    @ResponseBody
    public RestRecord delUser(@RequestParam( "id" ) String id){
        int count = userService.delUser(id);
        if (count==1){
            return new RestRecord(200, MessageConstants.SCE_MSG_0200,1);
        } else {
            return new RestRecord(408,MessageConstants.SCE_MSG_408);
        }
    }

    @ApiOperation(value = "密码重置", notes="重置密码为初始密码", httpMethod = "PUT")
    @PutMapping("/resetPwd")
    @ResponseBody
    public RestRecord resetPwd(@RequestParam( "id" ) String id){
        int count = userService.resetPwd(id,"star123!");
        if (count==1){
            return new RestRecord(200,MessageConstants.SCE_MSG_0200,1);
        } else {
            return new RestRecord(408,MessageConstants.SCE_MSG_407);
        }
    }

    @ApiOperation(value = "登录权限控制", notes="修改登录权限", httpMethod = "PUT")
    @PutMapping("/editLogin")
    @ResponseBody
    public RestRecord editLogin(@RequestParam( "id" ) String id,
                                @RequestParam( "loginPermissionStatus" ) Integer loginPermissionStatus){

        int newStatus = (loginPermissionStatus==0) ? 1 : 0;
        int count = userService.updateLoginPermission(id,newStatus);
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 ,count);
        } else {
            return new RestRecord( 407, MessageConstants.SCE_MSG_407 );
        }
    }

    @GetMapping("/teacher-list/{pageNum}/{pageSize}")
    public RestRecord findTeacherList(@CurrentUserId String userId,
                                      @PathVariable(value = "pageNum") String pageNum,
                                      @PathVariable(value = "pageSize") String pageSize){
        return userService.findTeacherList(userId, pageNum, pageSize);
    }

    @GetMapping("/teacher/{userName}")
    public RestRecord findTeacher(@CurrentUserId String userId, @PathVariable String userName){
        return userService.findTeacher();
    }
}
