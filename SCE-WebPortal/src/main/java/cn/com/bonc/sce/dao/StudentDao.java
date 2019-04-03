package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
}
