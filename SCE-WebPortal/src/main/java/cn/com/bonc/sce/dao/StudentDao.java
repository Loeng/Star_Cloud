package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Charles on 2019/3/28.
 */
@Repository
@FeignClient( value = "sce-data-mybatis" )
public interface StudentDao {

    @RequestMapping( value = "/student/getParents", method = RequestMethod.GET )
    RestRecord getParents(@RequestParam("id")String id);

    @RequestMapping( value = "/student/getApplyList", method = RequestMethod.GET )
    RestRecord getApplyList(@RequestParam("id")String id);

    @RequestMapping( value = "/student/audit", method = RequestMethod.POST )
    RestRecord audit(@RequestBody String json);

    @RequestMapping( value = "/student/getStudentBasicData/{USER_ID}", method = RequestMethod.GET )
    RestRecord getStudentBasicData(@PathVariable("USER_ID") String USER_ID);
}
