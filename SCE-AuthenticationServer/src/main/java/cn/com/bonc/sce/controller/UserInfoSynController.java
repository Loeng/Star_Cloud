package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.Payloads;
import cn.com.bonc.sce.rest.RestRecord;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  author wf
 *  date 2019/03/28
 *  用户数据同步接口
 */
@RestController
@RequestMapping( "/user-info-service" )
@Slf4j
public class UserInfoSynController {


    @GetMapping( "/user" )
    public RestRecord user( @Payloads Claims claims ){


        return null;
    }

}
