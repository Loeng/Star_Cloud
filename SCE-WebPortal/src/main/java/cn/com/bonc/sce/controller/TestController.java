package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.Payloads;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/29 19:53
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping( "/payloads" )
    @ResponseBody
    public Object test( @Payloads Map< String, String > payloads ) {

        return payloads;
    }

}
