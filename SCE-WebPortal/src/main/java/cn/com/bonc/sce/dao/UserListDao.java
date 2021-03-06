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
     * 根据角色id查询用户信息
     */
    @RequestMapping( value = "/user-list/role/{roleId}/{pageNum}/{pageSize}", method = RequestMethod.POST )
    RestRecord getUserInfoByRole( @PathVariable( "roleId" ) Integer roleId,
                                  @PathVariable( "pageNum" ) Integer pageNum,
                                  @PathVariable( "pageSize" ) Integer pageSize,
                                  @RequestBody (required = false) Map<String,Object> condition);


    @RequestMapping( value = "/user-list/number", method = RequestMethod.GET )
    RestRecord getUserNumberInfo();

    /**
     * 根据条件查询
     */
    @RequestMapping( value = "/user-list/condition/{pageNum}/{pageSize}", method = RequestMethod.POST )
    RestRecord getUserInfoByCondition( @RequestBody Map conditionMap,
                                       @PathVariable( "pageNum" ) Integer pageNum,
                                       @PathVariable( "pageSize" ) Integer pageSize );

}
