package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.utils.Run;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @ClassName RealNameAuthenticationController
 * @Description 实名认证
 * @Author YQ
 * @Date 2019/4/25 19:53
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping( "/realNameAuthentication" )
public class RealNameAuthenticationController {


    @ApiOperation(value = "实名认证", notes="实名认证", httpMethod = "GET")
    @GetMapping("/authentication")
    @ResponseBody
    public RestRecord authentication(@RequestParam( value = "realName",required = true) String realName,
                              @RequestParam( value = "idCard" ,required = true) String idCard){
        try {
            return new RestRecord(200,WebMessageConstants.SCE_PORTAL_MSG_200,new Run().idCardValt(realName,idCard));
        } catch (IOException e) {
            e.printStackTrace();
            return new RestRecord(780,WebMessageConstants.SCE_PORTAL_MSG_780);
        }
    }

}
