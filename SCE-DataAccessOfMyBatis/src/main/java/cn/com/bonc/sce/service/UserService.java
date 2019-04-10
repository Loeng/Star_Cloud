package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.bean.UserBean;
import cn.com.bonc.sce.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Map getPhone(String loginName) {
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

    public List<Map> getTeachers(long organizationId,String userName, String loginName, String gender, String position, Integer accountStatus) {
        return userDao.getTeachers(organizationId,userName,loginName,gender,position,accountStatus);
    }

    public int delTeacher(String id) {
        return userDao.delTeacher(id);
    }

    public Map getTeacherInfo(String id) {
        return userDao.getTeacherInfo(id);
    }

    public int editUser(String user_id, Integer certificate_type, String certificate_number, String user_name, String gender, String phone_number, String mail_address, String birthdate) {
        return userDao.editUser(user_id,certificate_type,certificate_number,user_name,gender,phone_number,mail_address,birthdate);
    }

    public int editTeacher(String user_id,String nation_code, String nationlity, String academic_qualification, String work_number, String school_time, String teach_time, String job_code, Integer teach_range) {
        return userDao.editTeacher( user_id,nation_code,nationlity,academic_qualification,work_number,school_time,teach_time,job_code,teach_range);
    }

    public int addUser(Long user_id, Integer certificate_type, String certificate_number, String user_name, String gender, String phone_number, String organization_id, String mail_address, String birthdate) {
        return userDao.addUser(user_id,certificate_type,certificate_number,user_name,gender,phone_number,organization_id,mail_address,birthdate);
    }

    public int addTeacher(Long user_id,String nation_code, String nationlity, String academic_qualification, String work_number, String school_time, String teach_time, String job_code, Integer teach_range) {
        return userDao.addTeacher(user_id,nation_code,nationlity,academic_qualification,work_number,school_time,teach_time,job_code,teach_range);
    }


    public int transInto(Long id, String user_id, String apply_user_id, String origin_school_id, String target_school_id, String tea_work_number, Date entrance_year, String tea_position,String tea_range) {
        return userDao.transInto(id,user_id,apply_user_id,origin_school_id,target_school_id,tea_work_number,entrance_year,tea_position,tea_range);
    }

    public List<Map> getTransferTeachers(Integer getType, long organizationId, String userName, String loginName, String gender, String position, Integer accountStatus) {
        return userDao.getTransferTeachers(getType,organizationId,userName,loginName,gender,position,accountStatus);
    }
}
