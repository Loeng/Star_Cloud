package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.School;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 学校
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Repository
@FeignClient( "sce-data-access" )
public interface SchoolDao {

    /**
     * 添加school
     *
     * @param school 信息
     * @return 是否添加成功
     */
    @RequestMapping( value = "/schools", method = RequestMethod.POST )
    public RestRecord insertSchool(School school);

    /**
     * 获取学校
     *
     * @return 获取学校
     */
    @RequestMapping( value = "/schools", method = RequestMethod.GET )
    public RestRecord getAll(@RequestParam( "pageNum" ) Integer pageNum,
                             @RequestParam( "pageSize" ) Integer pageSize);

    @RequestMapping( value = "/schools", method = RequestMethod.POST )
    RestRecord saveSchool( Map map );

    /**
     * 添加school
     *
     * @param school 信息
     * @return 是否添加成功
     */
    @RequestMapping( value = "/schools/addSchool/{roleId}", method = RequestMethod.POST )
    RestRecord addSchool(@RequestBody School school,@RequestParam( "userId" ) String userId,@PathVariable( "roleId" ) Integer roleId);

    @RequestMapping(value = "/schools/{id}", method = RequestMethod.GET)
    RestRecord getSchoolById(@PathVariable("id") Integer id);

    @RequestMapping( value = "/schools/updateSchoolById", method = RequestMethod.PUT )
    RestRecord updateSchoolById(@RequestBody @ApiParam( "学校信息对象" ) School school);

    @RequestMapping( value = "/schools/updateSchoolInfo", method = RequestMethod.PUT )
    RestRecord updateSchoolInfo(@RequestBody @ApiParam( "学校信息对象" ) School school,@ApiParam(name = "userId", value = "用户ID", required = true) @RequestParam( "userId" ) String userId,@ApiParam(name = "roleId", value = "角色类型", required = true) @RequestParam( "roleId" ) Integer roleId);


}
