package cn.com.bonc.sce.api;

import cn.com.bonc.sce.mapper.LiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2019/2/12 16:19
 * @Description:
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    LiveMapper liveMapper;

    @RequestMapping( value = "/get", method = RequestMethod.GET )
    public List< Map > selectAllInfo() {
        return liveMapper.getAllInfo();
    }
}
