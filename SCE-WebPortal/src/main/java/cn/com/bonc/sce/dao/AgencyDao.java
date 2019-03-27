package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Charles on 2019/3/4.
 */
@Repository
@FeignClient( value = "sce-data-mybatis" )
public interface AgencyDao {

    @RequestMapping( value = "/agent/editActivity", method = RequestMethod.PUT )
    RestRecord editActivity (@RequestParam("id") long id,@RequestParam("isActivate") Integer isActivate);

    @RequestMapping( value = "/agent/editInfo", method = RequestMethod.PUT )
    RestRecord editInfo(@RequestBody String json);

    @RequestMapping( value = "/agent/getSchools/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord getSchools(@RequestParam("id") long id,
                          @PathVariable(value = "pageNum") Integer pageNum,
                          @PathVariable(value = "pageSize") Integer pageSize);

    @RequestMapping( value = "/agent/delSchoolRel", method = RequestMethod.DELETE )
    RestRecord delSchoolRel(@RequestParam("agentId") long agentId,@RequestParam("schoolId") long schoolId);

    @RequestMapping( value = "/agent/getAgents/{pageNum}/{pageSize}", method = RequestMethod.GET )
    RestRecord getAgents(@RequestParam("agentName") String agentName,
                         //@RequestParam("isActivate") Integer isActivate,
                         @RequestParam ( value = "grade",required = false) String grade,
                         @RequestParam ( value = "agentArea",required = false) String agentArea,
                         @PathVariable(value = "pageNum") Integer pageNum,
                         @PathVariable(value = "pageSize") Integer pageSize);

    @RequestMapping( value = "/agent/insertInfo", method = RequestMethod.POST )
    RestRecord insertInfo(String json);

    @RequestMapping( value = "/agent/getInfo", method = RequestMethod.POST)
    RestRecord getInfo(@RequestParam(value = "id") long id);
}
