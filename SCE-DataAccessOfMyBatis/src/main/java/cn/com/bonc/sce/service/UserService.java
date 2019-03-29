package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.bean.UserBean;
import cn.com.bonc.sce.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/6.
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDao userDao;

    public int saveUser(UserBean userBean) {
        return userDao.saveUser(userBean);
    }

    public int saveAccount(AccountBean account) {
        return userDao.saveAccount(account);
    }

    public int delUser(String id) {
        return userDao.delUser(id);
    }

    public int resetPwd(String id,String pwd) {
        return userDao.resetPwd(id,pwd);
    }

    public int updateLoginPermission(String id, int newStatus) {
        return userDao.updateLoginPermission(id,newStatus);
    }

    public List<SchoolBean> getSchools4edu(long id) {
        return userDao.getSchools4edu(id);
    }

    public int delSchools4edu(long id, long institutionId) {
        return userDao.delSchools4edu(id,institutionId);
    }

    public List<Map> getInstitutions(String id, String institutionName, String loginPermissionStatus) {
        return userDao.getInstitutions(id,institutionName,loginPermissionStatus);
    }

    public int isExist(String loginName) {
        return userDao.isExist(loginName);
    }

    public String getPhone(String loginName) {
        return userDao.getPhone(loginName);
    }

    public int updatePwdByName(String loginName, String password) {
        return userDao.updatePwdByName(loginName,password);
    }

    public String testCertificate(String loginName) {
        return userDao.testCertificate(loginName);
    }

    public String getIdByPhone(String phone){
        return userDao.getIdByPhone(phone);
    }

}
