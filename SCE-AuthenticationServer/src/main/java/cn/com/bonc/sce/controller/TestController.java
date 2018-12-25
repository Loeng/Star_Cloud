package cn.com.bonc.sce.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/21 10:45
 */
@Slf4j
@RequestMapping( "/test" )
@RestController
public class TestController {

    @GetMapping( "" )
    @ResponseBody
    public String test( String info ) {
        return "it works well";
    }
}
