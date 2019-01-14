package cn.com.bonc.sce.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/21 10:45
 */
@Slf4j
@RequestMapping( "/test" )
@Controller
public class TestController {

    @GetMapping( "" )
    @ResponseBody
    public String test( String info ) {
        return "it works well";
    }

    @GetMapping( value = "redirect" )
    public void redirect( HttpServletRequest httpServletRequest, HttpServletResponse response ) throws IOException {
        response.sendRedirect( "https://" + httpServletRequest.getRequestURL() + "?ticket=akb48" );
    }
}
