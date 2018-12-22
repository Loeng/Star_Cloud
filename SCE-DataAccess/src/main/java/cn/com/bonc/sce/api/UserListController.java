package cn.com.bonc.sce.api;

import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户列表-查询api
 *
 * @author jc_D
 * @description
 * @date 2018/12/22
 **/
@Slf4j
@RestController
@RequestMapping( "/user-list" )
public class UserListController {

    /**
     * 全部用户
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping( "/all/{pageNum}/{pageSize}" )
    public RestRecord getAllUserInfo( @PathVariable( "pageNum" ) String pageNum,
                                      @PathVariable( "pageSize" ) String pageSize ) {

        return null;
    }

    /**
     * 根据角色id查询用户信息
     *
     * @param roleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping( "/role/{roleId}/{pageNum}/{pageSize}" )
    public RestRecord getUserInfoByRole( @PathVariable String roleId,
                                         @PathVariable( "pageNum" ) String pageNum,
                                         @PathVariable( "pageSize" ) String pageSize ) {

        return null;
    }


    /**
     * 根据条件查询
     *
     * @param conditionMap
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping( "/condition/{pageNum}/{pageSize}" )
    public RestRecord getUserInfoByCondition( @RequestBody Map conditionMap,
                                              @PathVariable( "pageNum" ) String pageNum,
                                              @PathVariable( "pageSize" ) String pageSize ) {

        return null;
    }
}
