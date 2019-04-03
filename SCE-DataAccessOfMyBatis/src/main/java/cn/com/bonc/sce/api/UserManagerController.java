package cn.com.bonc.sce.api;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.AgentBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.bean.UserBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.UserService;
import cn.com.bonc.sce.tool.IDUtil;
import cn.com.bonc.sce.tool.IdWorker;
import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    private IdWorker idWorker;

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

    @Transactional
    @ApiOperation(value = "用户注册", notes="用户注册", httpMethod = "POST")
    @PostMapping("/register")
    @ResponseBody
    public RestRecord register(@RequestBody @ApiParam( example = "{\"loginName\": \"测试张\",\"secret\": \"123456\",\"phoneNumber\": \"12345678901\",\"userType\": 1,\"userName\": \"测试张\"}" ) String json ){
        Map map = (Map) JSONUtils.parse(json);
        String loginName = (String) map.get("loginName");
        String password = (String) map.get("password");
        String phoneNumber = (String) map.get("phoneNumber");
        Integer userType = (Integer) map.get("userType");
        String userName = (String) map.get("userName");

        UserBean user = new UserBean();
        Long userId = idWorker.nextId();
        user.setLoginName( loginName );
        user.setUserType( userType );
        user.setPhoneNumber(phoneNumber);
        user.setUserName(userName);
        user.setIsDelete( 1 );
        user.setUserId(userId);
        user.setAccountStatus(0);

        if (userService.isExist(loginName) > 0){
            return new RestRecord(1022,MessageConstants.SCE_MSG_1022);
        }

        AccountBean account = new AccountBean();
        long accountId = idWorker.nextId();
        account.setId( accountId );
        account.setPassword( password );
        account.setIsDelete( 1 );
        account.setUserId(userId);

        if ( userService.saveUser( user )==1 && userService.saveAccount(account)==1){
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200,1 );
        } else {
            return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
        }
    }

    @ApiOperation(value = "通过用户名获取电话号码", notes="忘记密码时的身份认证", httpMethod = "GET")
    @GetMapping("/getPhone")
    @ResponseBody
    public RestRecord getPhone(@RequestParam( "loginName" ) String loginName){
        Map map = userService.getPhone(loginName);
        if (map!=null){
            return new RestRecord(200,MessageConstants.SCE_MSG_0200,map);
        } else {
            return new RestRecord(406,MessageConstants.SCE_MSG_406);
        }
    }

    @ApiOperation(value = "通过用户名修改密码", notes="重设密码", httpMethod = "PUT")
    @PutMapping("/updatePwdByName")
    @ResponseBody
    public RestRecord updatePwdByName(@RequestParam( "loginName" ) String loginName,
                                     @RequestParam("password") String password){
        int count = userService.updatePwdByName(loginName,password);
        if (count==1){
            return new RestRecord(200,MessageConstants.SCE_MSG_0200,1);
        } else {
            return new RestRecord(407,MessageConstants.SCE_MSG_407);
        }
    }

    @ApiOperation(value = "通过用户名和输入的身份证信息在后台验证", notes="验证身份证信息是否正确", httpMethod = "GET")
    @GetMapping("/testCertificate")
    @ResponseBody
    public RestRecord testCertificate(@RequestParam( "loginName" ) String loginName,
                                      @RequestParam("certificate") String certificate){
        String tempCer = userService.testCertificate(loginName);
        if (tempCer.equals(certificate)){
            return new RestRecord(200,MessageConstants.SCE_MSG_0200);
        } else {
            return new RestRecord(1010,"身份证验证失败");
        }
    }
}
