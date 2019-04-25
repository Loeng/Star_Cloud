package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.bean.*;
import cn.com.bonc.sce.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/6.
 */
@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public int saveUser(UserBean userBean) {
        return userMapper.saveUser(userBean);
    }

    public int saveAccount(AccountBean account) {
        return userMapper.saveAccount(account);
    }

    public int delUser(String id) {
        return userMapper.delUser(id);
    }

    public int resetPwd(String id,String pwd) {
        return userMapper.resetPwd(id,pwd);
    }

    public int updateLoginPermission(String id, int newStatus) {
        return userMapper.updateLoginPermission(id,newStatus);
    }

    public List<SchoolBean> getSchools4edu(long id) {
        return userMapper.getSchools4edu(id);
    }

    public int delSchools4edu(long id, long institutionId) {
        return userMapper.delSchools4edu(id,institutionId);
    }

    public List<Map> getInstitutions(String id, String institutionName, String loginPermissionStatus) {
        return userMapper.getInstitutions(id,institutionName,loginPermissionStatus);
    }

    public int isExist(String loginName) {
        return userMapper.isExist(loginName);
    }

    public Map getPhone(String loginName) {
        return userMapper.getPhone(loginName);
    }

    public int updatePwdByName(String loginName, String password) {
        return userMapper.updatePwdByName(loginName,password);
    }

    public int updateAccountStatusByName(String loginName, int accountStatus) {
        return userMapper.updateAccountStatusByName(loginName,accountStatus);
    }

    public String testCertificate(String loginName) {
        return userMapper.testCertificate(loginName);
    }

    public String getIdByPhone(String phone) {
        return userMapper.getIdByPhone(phone);
    }

    public List<Map> getTeachers(long organizationId,String userName, String loginName, String gender, String position, String accountStatus) {
        return userMapper.getTeachers(organizationId,userName,loginName,gender,position,accountStatus);
    }

    public int delTeacher(String id) {
        return userMapper.delTeacher(id);
    }

    public Map getTeacherInfo(String id) {
        return userMapper.getTeacherInfo(id);
    }

    public int editUser(String user_id, Integer certificate_type, String certificate_number, String user_name, String gender, String phone_number,
                        String mail_address, String birthdate, String nationCode, String nationality, Integer ISADMINISTRATORS) {
        return userMapper.editUser(user_id,certificate_type,certificate_number,user_name,gender,phone_number,mail_address,birthdate, nationCode, nationality, ISADMINISTRATORS);
    }

    public int editTeacher(String user_id, String academic_qualification, String work_number, String school_time, String teach_time, String position, Integer teach_range) {
        return userMapper.editTeacher( user_id,academic_qualification,work_number,school_time,teach_time,position,teach_range);
    }

    public int addUser(String user_id, Integer certificate_type, String certificate_number, String user_name, String gender,
                       String phone_number, String organization_id, String mail_address, String birthdate, String nationality,
                       String nationCode, String secret, String userType, String loginName, Integer ISADMINISTRATORS) {
        return userMapper.addUser(user_id,certificate_type,certificate_number,user_name,gender,phone_number,organization_id,mail_address,
                birthdate, nationality, nationCode, secret, userType, loginName, ISADMINISTRATORS);
    }

    public int addTeacher(String user_id, String academic_qualification, String work_number, String school_time, String teach_time, String position, Integer teach_range) {
        return userMapper.addTeacher(user_id,academic_qualification,work_number,school_time,teach_time,position,teach_range);
    }


    public int transInto(Long id, String user_id, String apply_user_id, String origin_school_id, String target_school_id, String tea_work_number, Date entrance_year, String tea_position,String tea_range, Integer ISADMINISTRATORS) {
        return userMapper.transInto(id,user_id,apply_user_id,origin_school_id,target_school_id,tea_work_number,entrance_year,tea_position,tea_range, ISADMINISTRATORS);
    }

    public List<Map> getTransferTeachers(Integer getType, long organizationId, String userName, String loginName, String gender, String position, Integer accountStatus, String applyStatus) {
        return userMapper.getTransferTeachers(getType,organizationId,userName,loginName,gender,position,accountStatus, applyStatus);
    }

    public List< Map > getStudents(String userName, String loginName, String studentNumber, String gender, String grade,
                                   String accountStatus, String organizationId){
        return userMapper.getStudents( userName, loginName, studentNumber, gender, grade, accountStatus, organizationId );
    }

    public String getOrganizationIdByUserId( String userId ){
        return userMapper.getOrganizationIdByUserId( userId );
    }

    public Map getStudentInfo(String userId){
        return userMapper.getStudentInfo(userId);
    }

    public int editStudentOfStudent(Map map){
        return userMapper.editStudentOfStudent(map);
    }

    public int editStudentOfUser(Map map){
        return userMapper.editStudentOfUser(map);
    }

    public int delStudentOfStudent(String userId){
        return userMapper.delStudentOfStudent(userId);
    }

    public int delStudentOfUser(String userId){
        return userMapper.delStudentOfUser(userId);
    }

    public String selectUserIdByLoginName(String loginName){
        return userMapper.selectUserIdByLoginName(loginName);
    }

    public int editTeacherPracticeInfo(String user_id,String teach_certification, Date teach_time, Date school_time, String job_profession,Integer teach_range, String work_number) {
        return userMapper.editTeacherPracticeInfo( user_id,teach_certification,teach_time,school_time,job_profession,teach_range,work_number);
    }

    public Map selectParentInfoByCertificationNumber(String certificationType, String certificationNumber){
        return userMapper.selectParentInfoByCertificationNumber(certificationType, certificationNumber);
    }

    public Map selectStudentInfoByCertificationNumber(String certificationType, String certificationNumber){
        return userMapper.selectStudentInfoByCertificationNumber(certificationType, certificationNumber);
    }

    public Map selectTeacherInfoByCertificationNumber(String certificationType, String certificationNumber){
        return userMapper.selectTeacherInfoByCertificationNumber(certificationType, certificationNumber);
    }

    public void saveUserPassword(long id, String userId, String password){
        userMapper.insertUserPassword(id, userId, password);
    }

    public void saveUserOfStudent(Map map){
        userMapper.saveUserOfStudent(map);
    }

    public void saveStudent(Map map){
        userMapper.saveStudent(map);
    }

    public void saveUserOfParent(Map map){
        userMapper.saveUserOfParent(map);
    }

    public void saveParentStudentRel(long id, String parentUserId, String studentUserId, int isMain, String relationship){
        userMapper.saveParentStudentRel(id, parentUserId, studentUserId, isMain, relationship);
    }

    public String selectUserIdByCertification(String parentCertificateNumber, String parentCertificateType, String userType){
        Map map = selectUserIdAndOrganizationId(parentCertificateNumber, parentCertificateType, userType);
        return map.get("USER_ID").toString();
    }

    public Map selectUserIdAndOrganizationId(String certificateNumber, String certificateType, String userType){
        return userMapper.selectUserIdAndOrganizationId(certificateNumber, certificateType, userType);
    }

    public void addTransfer(Map map){
        userMapper.addTransfer(map);
    }

    public List<Map> selectTransferInStudent(String userName, String loginName, String studentNumber, String gender, String grade, String applyStatus, String organizationId){
        return userMapper.selectTransferInStudent(userName, loginName, studentNumber, gender, grade, applyStatus, organizationId);
    }

    public List<Map> selectTransferOutStudent(String userName, String loginName, String studentNumber, String gender, String grade, String organizationId){
        return userMapper.selectTransferOutStudent(userName, loginName, studentNumber, gender, grade, organizationId);
    }

    public int deleteTransferApply(long id, String organizationId){
       return userMapper.deleteTransferApply(id, organizationId);
    }

    public int selectTransfer(String userId, String organizationId){
        return userMapper.selectTransfer(userId, organizationId);
    }

    public void reCall(String transferId){
        userMapper.reCall(transferId);
    }

    public Map selectTransferInfo(String transferId){
        return userMapper.selectTransferInfo(transferId);
    }

    public int auditTransfer(Map map){
        return userMapper.auditTransfer(map);
    }

    @Transactional( propagation = Propagation.REQUIRED )
    public void updateOrganizationIdByTransferId(String transferId){
        Map map = userMapper.selectTransferInfoByTransferId(transferId);
        Integer TEA_ISADMINISTRATORS = null;
        try{
            TEA_ISADMINISTRATORS = Integer.parseInt(map.get("TEA_ISADMINISTRATORS").toString());
        }catch (Exception e){}
        Long ORGANIZATION_ID = null;
        try{
            ORGANIZATION_ID = Long.parseLong(map.get("ORGANIZATION_ID").toString());
        }catch (NullPointerException e){}
        userMapper.updateOrganizationId(ORGANIZATION_ID, map.get("USER_ID").toString(), TEA_ISADMINISTRATORS);
    }

    @Transactional( propagation = Propagation.REQUIRED )
    public void updateStudent(String transferId){
        Map map = userMapper.selectStudentInfoByTransferId(transferId);
        userMapper.updateStudent(map);
    }

    public TeacherInfoBean getTeacherInfoById(String userId){
        return userMapper.getTeacherInfoById(userId);
    }

    public int addTeacherInfo(String user_id,String teach_certification, Date teach_time, Date school_time, String job_profession,Integer teach_range, String work_number,Integer is_delete) {
        return userMapper.addTeacherInfo( user_id,teach_certification,teach_time,school_time,job_profession,teach_range,work_number,is_delete);
    }

    public UserBean getUserById(String userId){
        return userMapper.getUserById(userId);
    }

    public int delPassword(String id){
        return userMapper.delPassword(id);
    }

    public int updateOrganizationIdByUserId(Long organizationId,String userId){
        return userMapper.updateOrganizationId(organizationId,userId, null);
    }

    public int saveUserAudit(UserAuditBean userAudit) {
        return userMapper.saveUserAudit(userAudit);
    }

    public UserAuditBean findByUserAuditEntityId(Long entityId){
        return userMapper.findByUserAuditEntityId(entityId);
    }

    public int updateUserAuditById(UserAuditBean userAudit){
        return userMapper.updateUserAuditById(userAudit);
    }

    public int checkUser(Integer certificateType, String certificateNumber, String phoneNumber){
        return userMapper.checkUser(certificateType, certificateNumber, phoneNumber);
    }

    public Map getTransferTeacherInfo(String transferId){
        return userMapper.getTransferTeacherInfo(transferId);
    }

    @Transactional( propagation = Propagation.REQUIRED )
    public void updateTeacher(String id){
        Map map = userMapper.selectTeacherInfoByTransferId(id);
        userMapper.updateTeacher(map);
    }

    public int selectCountByCertificateNumber(String certificateType, String certificateNumber, String userType){
        return userMapper.selectCountByCertificateNumber(certificateType, certificateNumber, userType);
    }

    public int selectCountByPhoneNumber(String phoneNumber){
        return userMapper.selectCountByPhoneNumber(phoneNumber);
    }

    public int selectCountByMailAddress(String mailAddress){
        return userMapper.selectCountByMailAddress(mailAddress);
    }

    public Integer selectIsAdministortars(String userId){
        return userMapper.selectIsAdministortars(userId);
    }

    public void updateAudit(String auditUserId,String userId,Integer auditStatus,String rejectOpinion){
        userMapper.updateAudit(auditUserId,userId,auditStatus,rejectOpinion);
    }

    public UserAuditBean getAudit(String userId){
        return userMapper.getAudit(userId);
    }

    public void updateIsadministrators(String userId){
        userMapper.updateIsadministrators(userId);
    }

    public int selectAuthStatus(String userId){
        return userMapper.selectAuthStatus(userId);
    }

    public int authentication(String USER_NAME,
                              String CERTIFICATE_NUMBER,
                              String IS_IDCARD_VALID,
                              String USER_ID){
        return userMapper.authentication(USER_NAME,CERTIFICATE_NUMBER,IS_IDCARD_VALID, USER_ID);
    }

    public Map idCardIsExit(String CERTIFICATE_NUMBER){
        return userMapper.idCardIsExit(CERTIFICATE_NUMBER);
    }
}
