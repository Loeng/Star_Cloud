package cn.com.bonc.sce.api;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.UserBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.WorkbenchDao;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/workbench")
public class WorkbenchController {

    @Autowired
    private WorkbenchDao workbenchDao;

    @Autowired
    private IdWorker idWorker;

    @PutMapping("/deleteAddress/{ID}")
    @ResponseBody
    public RestRecord deleteAddress(@PathVariable("ID") Integer ID) {
        return new RestRecord(200, workbenchDao.deleteAddress(ID));
    }

    @GetMapping("/getAddress/{USER_ID}")
    @ResponseBody
    public RestRecord getAddress(@PathVariable("USER_ID") String USER_ID) {
        return new RestRecord(200, workbenchDao.getAddress(USER_ID));
    }

    @PutMapping("/updateAddress")
    @ResponseBody
    public RestRecord updateAddress(@RequestBody Map<String, Object> addressInfo) {
        //将地址设为默认，先判断是否要设置，再判断是否已经有默认的地址，如果已经有，将地址设为不是默认
        if (Integer.parseInt(addressInfo.get("IS_DEFAULT").toString()) == 1 && workbenchDao.defaultTotal(addressInfo.get("USER_ID").toString()) != 0) {
            workbenchDao.setDefault(addressInfo.get("USER_ID").toString());
        }
        return new RestRecord(200, workbenchDao.updateAddress(addressInfo));
    }

    @PostMapping("/addAddress")
    @ResponseBody
    public RestRecord addAddress(@RequestBody Map<String, Object> addressInfo) {
        //将地址设为默认，先判断是否要设置，再判断是否已经有默认的地址，如果已经有，将地址设为不是默认
        if (Integer.parseInt(addressInfo.get("IS_DEFAULT").toString()) == 1 && workbenchDao.defaultTotal(addressInfo.get("USER_ID").toString()) != 0) {
            workbenchDao.setDefault(addressInfo.get("USER_ID").toString());
        }
        return new RestRecord(200, workbenchDao.addAddress(addressInfo));
    }

    @GetMapping("/getStudentBinding/{USER_ID}")
    @ResponseBody
    public RestRecord getStudentBinding(@PathVariable("USER_ID") String USER_ID) {
        return new RestRecord(200, workbenchDao.getStudentBinding(USER_ID));
    }

    @DeleteMapping("/deleteStudentBinding/{ID}")
    @ResponseBody
    public RestRecord deleteStudentBinding(@PathVariable("ID") Integer ID) {
        return new RestRecord(200, workbenchDao.deleteStudentBinding(ID));
    }

    @PostMapping("/addStudentBinding")
    @ResponseBody
    public RestRecord addStudentBinding(@RequestBody Map<String, Object> info) {
        //查询学生信息是否存在
        String STU_USER_ID = workbenchDao.queryStudentUserId(info);
        if (STU_USER_ID == null) {
            return new RestRecord("学生不存在或者信息有误", 0);
        }
        //查询学生是否已经被绑定
        int isBinding = workbenchDao.queryIsBinding(info.get("USER_ID").toString(), STU_USER_ID);
        if (isBinding != 0) {
            return new RestRecord("学生不能被同一个账号重复绑定", 0);
        }
        return new RestRecord(200, workbenchDao.addStudentBinding(info.get("USER_ID").toString(), STU_USER_ID));
    }

    @GetMapping("/getOrganization/{USER_ID}/{LOGIN_NAME}/{USER_NAME}/{GENDER}/{pageNum}/{pageSize}")
    @ResponseBody
    public RestRecord getOrganization(@PathVariable("USER_ID") String USER_ID,
                                      @PathVariable("LOGIN_NAME") String LOGIN_NAME,
                                      @PathVariable("USER_NAME") String USER_NAME,
                                      @PathVariable("GENDER") String GENDER,
                                      @PathVariable(value = "pageNum") Integer pageNum,
                                      @PathVariable(value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List schoolList = workbenchDao.getOrganization(USER_ID, LOGIN_NAME, USER_NAME ,GENDER);
        PageInfo pageInfo = new PageInfo(schoolList);
        return new RestRecord(200, MessageConstants.SCE_MSG_0200, pageInfo);
    }

    @PostMapping("/addOrganization")
    @ResponseBody
    @Transactional
    public RestRecord addOrganization(@RequestBody Map<String, Object> info) {
        String USER_ID = workbenchDao.queryUserId(info);
        if(USER_ID != null ){
            return new RestRecord("账号已经存在", 0);
        }
        UserBean user = new UserBean();
        long userId = idWorker.nextId();
        String secret = Secret.generateSecret();

        int userType = workbenchDao.queryUserType(info);
        long organizationId = workbenchDao.queryOrganizationId(info);

        user.setUserId( userId );
        user.setSecret( secret );
        user.setUserType( userType);//查询操作人员的usertype，创建的用户和他一致
        user.setOrganizationId(organizationId);
        user.setUserName(info.get("USER_NAME").toString());
        user.setPhoneNumber(info.get("PHONE_NUMBER").toString());
        user.setLoginName(info.get("LOGIN_NAME").toString());
        user.setCertificateNumber(info.get("CERTIFICATE_NUMBER").toString());
        if(info.containsKey("GENDER")) {
            user.setGender(info.get("GENDER").toString());
        }

        if(userType == 6)//当代理商管理员添加代理商账号得时候保存省市县
        {
            info.put("userId",userId);
            info.put("organizationId",organizationId);
            workbenchDao.addAgentInfo(info);
        }

        //workbenchDao.addOrganization(user);
       // AccountBean account = new AccountBean( idWorker.nextId(), info.get( "PASSWORD" ).toString(), 1, userId );

        return new RestRecord(200, workbenchDao.addOrganization(user));
    }

    @PutMapping("/updateOrganization")
    @ResponseBody
    @Transactional
    public RestRecord updateOrganization(@RequestBody Map<String, Object> info) {
        int userType = workbenchDao.queryUserType(info);
        if(userType == 6){
            workbenchDao.updateAgentAddress(info);
        }
        return new RestRecord(200, workbenchDao.updateOrganization(info));
    }


    @DeleteMapping("/deleteOrganization/{USER_ID}")
    @ResponseBody
    public RestRecord deleteOrganization(@PathVariable("USER_ID") String USER_ID) {
        return new RestRecord(200, workbenchDao.deleteOrganization(USER_ID));
    }

}
