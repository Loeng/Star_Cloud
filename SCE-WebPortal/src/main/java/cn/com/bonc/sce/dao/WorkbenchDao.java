package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "sce-data-mybatis")
@Repository
public interface WorkbenchDao {

    @RequestMapping(value = "/workbench/deleteAddress/{ID}", method = RequestMethod.PUT)
    RestRecord deleteAddress(@PathVariable("ID") Integer ID);

    @RequestMapping(value = "/workbench/getAddress/{USER_ID}", method = RequestMethod.GET)
    RestRecord getAddress(@PathVariable("USER_ID") String USER_ID);

    @RequestMapping(value = "/workbench/updateAddress", method = RequestMethod.PUT)
    RestRecord updateAddress(@RequestBody Map<String, Object> addressInfo);

    @RequestMapping(value = "/workbench/addAddress", method = RequestMethod.POST)
    RestRecord addAddress(@RequestBody Map<String, Object> addressInfo);

    @RequestMapping(value = "/workbench/getStudentBinding/{USER_ID}", method = RequestMethod.GET)
    RestRecord getStudentBinding(@PathVariable("USER_ID") String USER_ID);

    @RequestMapping(value = "/workbench/deleteStudentBinding/{ID}", method = RequestMethod.DELETE)
    RestRecord deleteStudentBinding(@PathVariable("ID") Integer ID);

    @RequestMapping(value = "/workbench/addStudentBinding", method = RequestMethod.POST)
    RestRecord addStudentBinding(@RequestBody Map<String, Object> info);

    @RequestMapping(value = "/workbench/getOrganization/{USER_ID}/{LOGIN_NAME}/{USER_NAME}/{GENDER}/{pageNum}/{pageSize}", method = RequestMethod.GET)
    RestRecord getOrganization(@PathVariable("USER_ID") String USER_ID,
                               @PathVariable("LOGIN_NAME") String LOGIN_NAME,
                               @PathVariable("USER_NAME") String USER_NAME,
                               @PathVariable("GENDER") String GENDER,
                               @PathVariable(value = "pageNum") Integer pageNum,
                               @PathVariable(value = "pageSize") Integer pageSize);

    @RequestMapping(value = "/workbench/addOrganization", method = RequestMethod.POST)
    RestRecord addOrganization(@RequestBody Map<String, Object> info);

    @RequestMapping(value = "/workbench/updateOrganization", method = RequestMethod.PUT)
    RestRecord updateOrganization(@RequestBody Map<String, Object> info);

    @RequestMapping(value = "/workbench/deleteOrganization/{USER_ID}", method = RequestMethod.DELETE)
    RestRecord deleteOrganization(@PathVariable("USER_ID") String USER_ID);

}
