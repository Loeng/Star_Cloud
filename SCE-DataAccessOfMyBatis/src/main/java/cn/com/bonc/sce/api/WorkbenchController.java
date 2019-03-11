package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.WorkbenchDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/workbench")
public class WorkbenchController {

    @Autowired
    private WorkbenchDao workbenchDao;

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
        if(isBinding != 0){
            return new RestRecord("学生不能被同一个账号重复绑定", 0);
        }
        return new RestRecord(200, workbenchDao.addStudentBinding(info.get("USER_ID").toString(),STU_USER_ID));
    }

}
