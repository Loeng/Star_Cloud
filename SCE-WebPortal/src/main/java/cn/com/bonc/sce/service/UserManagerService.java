package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.UserManagerDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public RestRecord getSchools4edu(long id, Integer pageNum, Integer pageSize) {
        return userManagerDao.getSchools4edu(id,pageNum,pageSize);
    }

    public RestRecord delSchools4edu(long id, long institutionId) {
        return userManagerDao.delSchools4edu(id,institutionId);
    }

    public RestRecord getInstitutionList(String json,Integer pageNum, Integer pageSize) {
        return userManagerDao.getInstitutionList(json,pageNum,pageSize);
    }

    public List<Map> getInstitutions(String id, String institutionName, String loginPermissionStatus) {
        return userManagerDao.getInstitutions(id,institutionName,loginPermissionStatus);
    }
}
