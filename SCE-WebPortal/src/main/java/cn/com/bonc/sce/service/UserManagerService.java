package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.UserManagerDao;
import cn.com.bonc.sce.model.InfoTeacherModel;
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

    public RestRecord register(String json) {
        return userManagerDao.register(json);
    }

    public RestRecord getPhone(String loginName) {
        return userManagerDao.getPhone(loginName);
    }

    public RestRecord updatePwdByName(String loginName, String password) {
        return userManagerDao.updatePwdByName(loginName,password);
    }

    public RestRecord testCertificcate(String loginName, String certificate) {
        return userManagerDao.testCertificate(loginName,certificate);
    }

    public RestRecord getTeachers(long organizationId, String userName, String loginName, String gender, String position, String accountStatus, Integer pageNum, Integer pageSize) {
        return userManagerDao.getTeachers(organizationId,userName,loginName,gender,position,accountStatus,pageNum,pageSize);
    }

    public RestRecord delTeacher(String id) {
        return userManagerDao.delTeacher(id);
    }

    public RestRecord getTeacherInfo(String id) {
        return userManagerDao.getTeacherInfo(id);
    }

    public RestRecord editTeacherInfo(String json) {
        return userManagerDao.editTeacherInfo(json);
    }

    public RestRecord addTeacher(String json) {
        return userManagerDao.addTeacher(json);
    }

    public RestRecord getTransferTeachers(Integer getType, long organizationId, String userName, String loginName, String gender, String position, Integer accountStatus, Integer pageNum, Integer pageSize) {
        return userManagerDao.getTransferTeachers(getType,organizationId,userName,loginName,gender,position,accountStatus,pageNum,pageSize);
    }

    public RestRecord getStudents(String userName, String loginName, String studentNumber, String gender, String grade, String accountStatus,
                                  String userId, String pageNum, String pageSize){
        return userManagerDao.getStudents(userName, loginName, studentNumber, gender, grade, accountStatus, userId, pageNum, pageSize);
    }

    public RestRecord getStudentInfo(String userId){
        return userManagerDao.getStudentInfo(userId);
    }

    public RestRecord editStudent(Map map){
        return userManagerDao.editStudent(map);
    }

    public RestRecord delStudent(String userId){
        return userManagerDao.delStudent(userId);
    }

    public RestRecord addStudent(Map map, String userId){
        //必需字段验证
        if(map.get("transferType") == null || map.get("studentCertificateType") == null || map.get("studentCertificateNumber") == null || map.get("entranceYear") == null ||
                map.get("grade") == null || map.get("classNumber") == null || map.get("seatNumber") == null || map.get("studentNumber") == null ){
            return new RestRecord( 431, String.format(WebMessageConstants.SCE_PORTAL_MSG_431, "必需") );
        }
        RestRecord restRecord = null;
        if( map.get("studentUserName") == null || map.get("studentBirthDate") == null || map.get("studentNationality") == null || map.get("studentVolk") == null ||
                map.get("parentCertificateType") == null || map.get("parentCertificateNumber") == null || map.get("relationship") == null ){
            return new RestRecord( 431, String.format(WebMessageConstants.SCE_PORTAL_MSG_431, "必需") );
        }

        String bindType;
        try{
            bindType = map.get("bindType").toString();
        }catch (NullPointerException e){
            return new RestRecord( 431, String.format(WebMessageConstants.SCE_PORTAL_MSG_431, "必需") );
        }
        if(bindType.equals("1")){
            if(map.get("parentUserName") == null || map.get("parentNationality") == null || map.get("parentVolk") == null ||
                    map.get("parentPhoneNumber") == null ){
                return new RestRecord( 431, String.format(WebMessageConstants.SCE_PORTAL_MSG_431, "必需") );
            }
        }
        restRecord = userManagerDao.addStudent(map, userId);

        return restRecord;
    }


    public RestRecord editTeacherPracticeInfo(InfoTeacherModel model){
        return userManagerDao.editTeacherPracticeInfo(model);
    }

    public RestRecord getTeacherInfoById(String userId){
        return userManagerDao.getTeacherInfoById(userId);
    }

    public RestRecord addTeacherInfo(InfoTeacherModel model) {
        return userManagerDao.addTeacherInfo(model);
    }

    public RestRecord getUserById(String userId){
        return userManagerDao.getUserById(userId);
    }
}
