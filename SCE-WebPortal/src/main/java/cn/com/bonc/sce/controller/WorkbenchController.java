package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.WorkbenchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Api(value = "工作台相关接口", tags = "工作台相关接口")
@ApiResponses({@ApiResponse(code = 500, message = "服务器内部错误", response = RestRecord.class)})
@RestController
@RequestMapping("/user-info-mybatis")
public class WorkbenchController {
    @Autowired
    private WorkbenchService workbenchService;

    /**
     * 删除地址
     * @param ID
     * @return
     */
    @PutMapping("/deleteAddress/{ID}")
    @ResponseBody
    public RestRecord deleteAddress(@PathVariable( "ID" ) Integer ID) {
        return workbenchService.deleteAddress(ID);
    }

    /**
     * 获取账号下工作台-地址管理的地址
     * @param USER_ID
     * @return
     */
    @GetMapping("/getAddress/{USER_ID}")
    @ResponseBody
    public RestRecord getAddress(@PathVariable( "USER_ID" ) String USER_ID) {
        return workbenchService.getAddress(USER_ID);
    }


    /**
     * 更新地址
     * @param addressInfo
     * @return
     */
    @PutMapping("/updateAddress")
    @ResponseBody
    public RestRecord updateAddress(@RequestBody Map<String, Object> addressInfo) {
        return workbenchService.updateAddress(addressInfo);
    }

    /**
     * 添加地址
     * @param addressInfo
     * @return
     */
    @PostMapping("/addAddress")
    @ResponseBody
    public RestRecord addAddress(@RequestBody Map<String, Object> addressInfo) {
        return workbenchService.addAddress(addressInfo);
    }

    /**
     * 获取个人中心-学生绑定列表
     * @param USER_ID
     * @return
     */
    @GetMapping("/getStudentBinding/{USER_ID}")
    @ResponseBody
    public RestRecord getStudentBinding(@PathVariable( "USER_ID" ) String USER_ID){
        return workbenchService.getStudentBinding(USER_ID);
    }

    /**
     * 删除学生绑定
     * @param ID
     * @return
     */
    @DeleteMapping("/deleteStudentBinding/{ID}")
    @ResponseBody
    public RestRecord deleteStudentBinding(@PathVariable( "ID" ) Integer ID){
        return workbenchService.deleteStudentBinding(ID);
    }

    @PostMapping("/addStudentBinding")
    @ResponseBody
    public RestRecord addStudentBinding(@RequestBody Map<String, Object> info){
        return workbenchService.addStudentBinding(info);
    }

}
