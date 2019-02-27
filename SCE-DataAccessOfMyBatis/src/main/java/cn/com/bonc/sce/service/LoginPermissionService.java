package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.LoginPermissionDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Charles on 2019/2/26.
 */
@Service
@Slf4j
public class LoginPermissionService {

    @Autowired
    private LoginPermissionDao loginPermissionDao;

    public int updateLoginPermission(String userId, Integer loginPermissionStatus ) {
        return loginPermissionDao.updateLoginPermission(userId,loginPermissionStatus);
    }
}
