package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.WorkbenchDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@Service
public class WorkbenchService {

    @Autowired
    private WorkbenchDao workbenchDao;

    public RestRecord deleteAddress(Integer ID) {
        return workbenchDao.deleteAddress(ID);
    }

    public RestRecord getAddress(String USER_ID) {
        return workbenchDao.getAddress(USER_ID);
    }

    public RestRecord updateAddress(Map<String, Object> addressInfo) {
        return workbenchDao.updateAddress(addressInfo);
    }

    public RestRecord addAddress(Map<String, Object> addressInfo) {
        return workbenchDao.addAddress(addressInfo);
    }

    public RestRecord getStudentBinding(String USER_ID) {
        return workbenchDao.getStudentBinding(USER_ID);
    }

    public RestRecord deleteStudentBinding(Integer ID) {
        return workbenchDao.deleteStudentBinding(ID);
    }

    public RestRecord addStudentBinding(Map<String, Object> info) {
        return workbenchDao.addStudentBinding(info);
    }

    public RestRecord getOrganization(String USER_ID, String LOGIN_NAME, String USER_NAME,String GENDER, Integer pageNum, Integer pageSize) {
        return workbenchDao.getOrganization(USER_ID, LOGIN_NAME, USER_NAME,GENDER, pageNum, pageSize);
    }

    public RestRecord addOrganization(Map<String, Object> info) {
        return workbenchDao.addOrganization(info);
    }

    public RestRecord updateOrganization(Map<String, Object> info) {
        return workbenchDao.updateOrganization(info);
    }

    public RestRecord deleteOrganization(String USER_ID) {
        return workbenchDao.deleteOrganization(USER_ID);
    }

}
