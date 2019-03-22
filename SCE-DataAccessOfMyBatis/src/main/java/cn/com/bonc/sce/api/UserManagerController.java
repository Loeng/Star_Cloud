package cn.com.bonc.sce.api;

import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.UserService;
import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/8.
 */
@Slf4j
@RestController
@RequestMapping( "/userManager" )
public class UserManagerController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "删除用户", notes="通过用户id，删除用户", httpMethod = "DELETE")
    @DeleteMapping("/delUser")
    @ResponseBody
    public RestRecord delUser(@RequestParam( "id" ) String id){
        int count = userService.delUser(id);
        if (count==1){
            return new RestRecord(200, MessageConstants.SCE_MSG_0200,1);
        } else {
            return new RestRecord(408,MessageConstants.SCE_MSG_408);
        }
    }

    @ApiOperation(value = "密码重置", notes="重置密码为初始密码", httpMethod = "PUT")
    @PutMapping("/resetPwd")
    @ResponseBody
    public RestRecord resetPwd(@RequestParam( "id" ) String id){
        int count = userService.resetPwd(id,"star123!");
        if (count==1){
            return new RestRecord(200,MessageConstants.SCE_MSG_0200,1);
        } else {
            return new RestRecord(408,MessageConstants.SCE_MSG_407);
        }
    }

    @ApiOperation(value = "登录权限控制", notes="修改登录权限", httpMethod = "PUT")
    @PutMapping("/editLogin")
    @ResponseBody
    public RestRecord editLogin(@RequestParam( "id" ) String id,
                                @RequestParam( "loginPermissionStatus" ) Integer loginPermissionStatus){

        int newStatus = (loginPermissionStatus==0) ? 1 : 0;
        int count = userService.updateLoginPermission(id,newStatus);
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 ,count);
        } else {
            return new RestRecord( 407, MessageConstants.SCE_MSG_407 );
        }
    }

    @ApiOperation(value = "教育机构下学校列表查询", notes="通过教育机构id查询学校列表", httpMethod = "GET")
    @GetMapping("/getSchools4edu/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getSchools4edu(@RequestParam( "id" ) long id,
                                     @PathVariable (value = "pageNum")Integer pageNum,
                                     @PathVariable (value = "pageSize") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<SchoolBean> schoolList = userService.getSchools4edu(id);
        PageInfo pageInfo = new PageInfo(schoolList);
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200,pageInfo );
    }

    @ApiOperation(value = "教育机构下学校删除", notes="通过教育机构id和学校id删除学校", httpMethod = "DELETE")
    @DeleteMapping("/delSchools4edu")
    @ResponseBody
    public RestRecord delSchools4edu(@RequestParam( "id" ) long id,
                                     @RequestParam("institutionId") long institutionId){
        int count = userService.delSchools4edu(id,institutionId);
        if (count==1){
            return new RestRecord(200, MessageConstants.SCE_MSG_0200,1);
        } else {
            return new RestRecord(408,MessageConstants.SCE_MSG_408);
        }
    }

    @ApiOperation(value = "获取教育机构下列表", notes="获取教育机构下列表", httpMethod = "POST")
    @PostMapping("/getInstitutionList/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getInstitutionList(@RequestBody String json,
                                         @PathVariable (value = "pageNum")Integer pageNum,
                                         @PathVariable (value = "pageSize") Integer pageSize){
        Map map = (Map) JSONUtils.parse(json);
        String id = (String) map.get("id");
        String institutionName = (String) map.get("institutionName");
        String loginPermissionStatus = (String) map.get("loginPermissionStatus");

        PageHelper.startPage(pageNum, pageSize);
        List<Map> list = userService.getInstitutions(id,institutionName,loginPermissionStatus);
        PageInfo pageInfo = new PageInfo(list);
        return new RestRecord( 200, MessageConstants.SCE_MSG_0200,pageInfo );
    }

    @ApiOperation(value = "获取教育机构下列表", notes="获取教育机构下列表", httpMethod = "GET")
    @GetMapping("/getInstitutions")
    @ResponseBody
    public List<Map> getInstitutions(@RequestParam("id") String id,
                                     @RequestParam("institutionName") String institutionName,
                                     @RequestParam("loginPermissionStatus") String loginPermissionStatus){
        List<Map> list = userService.getInstitutions(id,institutionName,loginPermissionStatus);
        return list;
    }
}
