package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.entity.Authority;
import cn.com.bonc.sce.entity.user.User;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 机构
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Slf4j
@RestController
@RequestMapping( "/authoritys" )
public class AuthorityApiController {
    @Autowired
    private AuthorityService authorityService;


    /**
     * 添加authority
     *
     * @param authority 信息
     * @return 是否添加成功
     */
    @PostMapping( "" )
    @ResponseBody
    public RestRecord insertAuthority( @RequestBody Authority authority ) {
        try {
            return authorityService.insertAuthority( authority );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 409, MessageConstants.SCE_MSG_409, e );
        }
    }

    /**
     * 获取机构
     *
     * @return 获取机构
     */
    @GetMapping( "" )
    @ResponseBody
    public RestRecord getAll(@RequestParam( value = "pageNum", required = false, defaultValue = "0" ) Integer pageNum,
                             @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize) {
        try {
            return authorityService.getAll(pageNum, pageSize);
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }

    /**
     * 获取机构
     *
     * @return 获取机构
     */
    @GetMapping( "/user" )
    @ResponseBody
    public RestRecord getUser(@RequestParam( value = "pageNum", required = false, defaultValue = "0" ) Integer pageNum,
                             @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize) {
        try {
            return authorityService.getUser(pageNum, pageSize);
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }
}
