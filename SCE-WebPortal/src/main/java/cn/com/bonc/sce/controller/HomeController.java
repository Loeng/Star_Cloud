package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 7:56
 */
@Slf4j
@RestController
@RequestMapping( "/index" )
public class HomeController {
    @RequestMapping( "hello" )

    public String hello( @CurrentUserId String user) {
        System.out.println(user);
        return  null;
    }
}
