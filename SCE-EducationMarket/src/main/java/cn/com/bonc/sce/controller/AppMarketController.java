package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  author wf
 *  date 2019/03/29
 */
@RestController
@RequestMapping("/app-market")
public class AppMarketController {

    /**
     *  userId 测试一下
     * @param userId 用户id
     * @return RestRecord
     */
    @GetMapping( "/getIp" )
    public RestRecord getIp( @CurrentUserId String userId ){
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

}
