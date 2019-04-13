package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by Charles on 2019/3/28.
 */
@Repository
@FeignClient( value = "sce-data-mybatis" )
public interface ParentDao {

    @RequestMapping( value = "/parent/getStudentList", method = RequestMethod.GET )
    RestRecord getStudentList(@RequestParam("id") String id);

    @RequestMapping( value = "/parent/bindStudent", method = RequestMethod.POST)
    RestRecord bindStudent(@RequestBody String json);

    @RequestMapping( value = "/parent/unbind", method = RequestMethod.DELETE)
    RestRecord unbind(@RequestParam("parentId")String parentId, @RequestParam("studentId")String studentId);

    @RequestMapping( value = "/parent/getParentList", method = RequestMethod.GET )
    RestRecord getParentList(@RequestParam("userId") String userId, @RequestParam("id") String id);

    @RequestMapping( value = "/parent/getApplyList", method = RequestMethod.GET )
    RestRecord getApplyList(@RequestParam("id") String id);

    @RequestMapping( value = "/parent/applyMain", method = RequestMethod.POST )
    RestRecord applyMain(@RequestParam("userId") String userId,
                         @RequestBody Map map);

    @RequestMapping( value = "/parent/auditApplyMain", method = RequestMethod.PUT )
    RestRecord auditApplyMain(@RequestParam("userId") String userId,
                              @RequestBody Map map);

    @RequestMapping( value = "/parent/getMainPhone", method = RequestMethod.GET )
    RestRecord getMainPhone(@RequestParam("userId") String userId,
                            @RequestParam("studentLoginName") String studentLoginName);
}
