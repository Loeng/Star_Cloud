package cn.com.bonc.sce.api;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.dao.UserInfoMybatisDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user-info-mybatis")
public class UserOperationMybatisController {

    @Autowired
    private UserInfoMybatisDao userInfoMybatisDao;

    @PutMapping("/updateUserInfo")
    @ResponseBody
    public RestRecord updateUserInfo(@RequestBody Map<String, Object> userInfo) {
        return new RestRecord(200, userInfoMybatisDao.updateUserInfo(userInfo));
    }

    @PutMapping("/updatePassword")
    @ResponseBody
    public RestRecord updatePassword(@RequestBody Map<String, Object> userInfo) {
        //旧密码状态置为已删除
        userInfoMybatisDao.updatePassword(userInfo);
        //生成新密码
        return new RestRecord(200, userInfoMybatisDao.addPassword(userInfo));
    }

}
