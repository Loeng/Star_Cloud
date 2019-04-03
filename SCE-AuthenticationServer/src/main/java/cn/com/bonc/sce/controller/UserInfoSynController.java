package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.Payloads;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *  author wf
 *  date 2019/03/28
 *  用户数据同步接口
 */
@RestController
@RequestMapping( "/user-info-service" )
@Slf4j
@SessionAttributes(types = Claims.class)
public class UserInfoSynController {

    @Autowired
    UserService userService;

    /**
     * 通过appId、appToken同步所有用户数据
     * @param request 请求
     * @return RestRecord
     */
    @GetMapping( "/user-rest" )
    public RestRecord user( HttpServletRequest request ){
        return userService.getUser( request );
    }

    @GetMapping( "/user-jwt" )
    public RestRecord user(@Payloads List<Map> users){
        return userService.getUserJWT( users );
    }

    @GetMapping( "/user-detailed" )
    public RestRecord userDetailed(@Payloads List<Map> claims){
        return userService.getUserDetailed( claims );
    }

}
