package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户列表-查询
 */
@Repository
@FeignClient( "sce-data-access" )
public interface UserListDao {
    /**
     * 查询所有用户信息
     */
    @RequestMapping( value = "/user-list/all", method = RequestMethod.GET )
    RestRecord getAllUserInfo( @PathVariable( "pageNum" ) String pageNum,
                               @PathVariable( "pageSize" ) String pageSize );

    /**
     * 根据角色id查询用户信息
     */
    @RequestMapping( value = "/user-list/role/{roleId}/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord getUserInfoByRole( @PathVariable( "roleId" ) String roleId,
                                  @PathVariable( "pageNum" ) String pageNum,
                                  @PathVariable( "pageSize" ) String pageSize );


    /**
     * 根据条件查询
     *
     */
    @RequestMapping( value = "/user-list/condition/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord getUserInfoByCondition( @RequestBody Map conditionMap,
                                       @PathVariable( "pageNum" ) String pageNum,
                                       @PathVariable( "pageSize" ) String pageSize );

}
