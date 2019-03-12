package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.UserOperationMybatisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Api(value = "用户信息增删改相关接口", tags = "用户相关接口")
@ApiResponses({@ApiResponse(code = 500, message = "服务器内部错误", response = RestRecord.class)})
@RestController
@RequestMapping("/user-info-mybatis")
public class UserOprationMybatisController {

    @Autowired
    private UserOperationMybatisService userOperationMybatisService;

    @PutMapping("/updateUserInfo")
    @ResponseBody
    public RestRecord updateUserInfoById(@RequestBody Map<String, Object> userInfo) {
        return userOperationMybatisService.updateUserInfoById(userInfo);
    }

    @PutMapping("/updatePassword")
    @ResponseBody
    public RestRecord updatePassword(@RequestBody Map<String, Object> info) {
        return userOperationMybatisService.updatePassword(info);
    }

}
