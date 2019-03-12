package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.UserManagerDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Charles on 2019/3/8.
 */
@Service
@Slf4j
public class UserManagerService {

    @Autowired
    private UserManagerDao userManagerDao;

    public RestRecord delUser(String id) {
        return userManagerDao.delUser(id);
    }

    public RestRecord resetPwd(String id) {
        return userManagerDao.resetPwd(id);
    }

    public RestRecord editLogin(String id, Integer loginPermissionStatus) {
        return userManagerDao.editLogin(id,loginPermissionStatus);
    }
}
