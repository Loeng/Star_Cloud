package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.UserBean;
import cn.com.bonc.sce.mapper.WorkbenchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class WorkbenchDao {

    @Autowired(required = false)
    private WorkbenchMapper workbenchMapper;

    public int deleteAddress(Integer ID) {
        return workbenchMapper.deleteAddress(ID);
    }

    public List<Map> getAddress(String USER_ID) {
        return workbenchMapper.getAddress(USER_ID);
    }

    public int defaultTotal(String USER_ID) {
        return workbenchMapper.defaultTotal(USER_ID);
    }

    public int setDefault(String USER_ID) {
        return workbenchMapper.setDefault(USER_ID);
    }

    public int updateAddress(Map<String, Object> addressInfo) {
        return workbenchMapper.updateAddress(addressInfo);
    }

    public int addAddress(Map<String, Object> addressInfo) {
        return workbenchMapper.addAddress(addressInfo);
    }

    public List<Map> getStudentBinding(String USER_ID) {
        return workbenchMapper.getStudentBinding(USER_ID);
    }

    public int deleteStudentBinding(Integer ID) {
        return workbenchMapper.deleteStudentBinding(ID);
    }

    public String queryStudentUserId(Map<String, Object> info) {
        return workbenchMapper.queryStudentUserId(info);
    }

    public int queryIsBinding(String PARENT_USER_ID, String STU_USER_ID) {
        return workbenchMapper.queryIsBinding(PARENT_USER_ID, STU_USER_ID);
    }

    public int addStudentBinding(String PARENT_USER_ID, String STU_USER_ID) {
        return workbenchMapper.addStudentBinding(PARENT_USER_ID, STU_USER_ID);
    }

    public List<Map> getOrganization(String USER_ID, String LOGIN_NAME, String USER_NAME) {
        return workbenchMapper.getOrganization(USER_ID, LOGIN_NAME, USER_NAME);
    }

    public String queryUserId(Map<String, Object> info) {
        return workbenchMapper.queryUserId(info);
    }

    public int queryUserType(Map<String, Object> info) {
        return workbenchMapper.queryUserType(info);
    }

    public long queryOrganizationId(Map<String, Object> info) {
        return workbenchMapper.queryOrganizationId(info);
    }

    public int addOrganization(UserBean userBean) {
        return workbenchMapper.addOrganization(userBean);
    }

    public int saveAccount(AccountBean account) {
        return workbenchMapper.saveAccount(account);
    }

}
