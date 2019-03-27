package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/8.
 */
@Repository
@FeignClient( value = "sce-data-mybatis" )
public interface UserManagerDao {

    @RequestMapping( value = "/userManager/delUser", method = RequestMethod.DELETE )
    RestRecord delUser(@RequestParam("id")String id);

    @RequestMapping( value = "/userManager/resetPwd", method = RequestMethod.PUT )
    RestRecord resetPwd(@RequestParam("id") String id);

    @RequestMapping( value = "/userManager/editLogin", method = RequestMethod.PUT )
    RestRecord editLogin(@RequestParam("id")String id, @RequestParam("loginPermissionStatus")Integer loginPermissionStatus);

    @RequestMapping( value = "/userManager/getSchools4edu/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord getSchools4edu(@RequestParam("id") long id, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize);

    @RequestMapping( value = "/userManager/delSchools4edu", method = RequestMethod.DELETE )
    RestRecord delSchools4edu(@RequestParam("id") long id, @RequestParam("institutionId") long institutionId);

    @RequestMapping( value = "/userManager/getInstitutionList/{pageNum}/{pageSize}", method = RequestMethod.POST )
    RestRecord getInstitutionList(@RequestBody String json,@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize);

    @RequestMapping( value = "/userManager/getInstitutions", method = RequestMethod.GET )
    List<Map> getInstitutions(@RequestParam("id")String id, @RequestParam("institutionName")String institutionName, @RequestParam("loginPermissionStatus") String loginPermissionStatus);

    @RequestMapping( value = "/userManager/register", method = RequestMethod.POST )
    RestRecord register(@RequestBody String json);

    @RequestMapping( value = "/userManager/getPhone", method = RequestMethod.GET )
    RestRecord getPhone(@RequestParam("loginName") String loginName);

    @RequestMapping( value = "/userManager/updatePwdByName", method = RequestMethod.PUT )
    RestRecord updatePwdByName(@RequestParam("loginName") String loginName, @RequestParam("password") String password);
}
