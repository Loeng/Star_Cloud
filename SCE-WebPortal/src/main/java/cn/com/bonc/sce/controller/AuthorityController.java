package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.Banner;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AuthorityService;
import cn.com.bonc.sce.service.BannerService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Slf4j
@RestController
@Api( value = "机构", tags = "机构"  )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RequestMapping( "/authoritys" )
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;

    /**
     * 获取机构
     *
     * @return 获取机构
     */
    @ApiOperation( value = "获取机构", notes = "获取机构", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping
    @ResponseBody
    public RestRecord getAll() {
        return authorityService.getAll();
    }
}
