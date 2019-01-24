package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.Authority;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AuthorityService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 添加authority
     *
     * @param authority 信息
     * @return 是否添加成功
     */
    @ApiOperation( value = "添加authority", notes = "添加authority", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertAuthority( @RequestBody @ApiParam( name = "authority", value = "authority信息", required = true ) Authority authority ) {
        return authorityService.insertAuthority( authority );
    }

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
    public RestRecord getAll(@RequestParam( value = "pageNum", required = false, defaultValue = "1"  ) @ApiParam( name = "pageNum", value = "页码")Integer pageNum,
                             @RequestParam( value = "pageSize", required = false, defaultValue = "10"  ) @ApiParam( name = "pageSize", value = "数量")Integer pageSize) {
        return authorityService.getAll(pageNum,pageSize);
    }
}
