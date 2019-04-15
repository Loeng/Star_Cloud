package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.bean.UserBean;
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

    public String testCertificate(String loginName) {
        return userMapper.testCertificate(loginName);
    }

    public String getIdByPhone(String phone) {
        return userMapper.getIdByPhone(phone);
    }

    public List<Map> getTeachers(long organizationId,String userName, String loginName, String gender, String position, String accountStatus) {
        System.out.println("------------------------------");
        System.out.println("------------------------------");
        System.out.println(accountStatus);
        System.out.println("------------------------------");
        System.out.println("------------------------------");
        return userMapper.getTeachers(organizationId,userName,loginName,gender,position,accountStatus);
    }

    public int delTeacher(String id) {
        return userMapper.delTeacher(id);
    }

    public Map getTeacherInfo(String id) {
        return userMapper.getTeacherInfo(id);
    }

    public int editUser(String user_id, Integer certificate_type, String certificate_number, String user_name, String gender, String phone_number,
                        String mail_address, String birthdate, String nationCode, String nationality) {
        return userMapper.editUser(user_id,certificate_type,certificate_number,user_name,gender,phone_number,mail_address,birthdate, nationCode, nationality);
    }

    public int editTeacher(String user_id, String academic_qualification, String work_number, String school_time, String teach_time, String job_code, Integer teach_range) {
        return userMapper.editTeacher( user_id,academic_qualification,work_number,school_time,teach_time,job_code,teach_range);
    }

    public int addUser(Long user_id, Integer certificate_type, String certificate_number, String user_name, String gender, String phone_number, String organization_id, String mail_address, String birthdate) {
        return userMapper.addUser(user_id,certificate_type,certificate_number,user_name,gender,phone_number,organization_id,mail_address,birthdate);
    }

    public int addTeacher(Long user_id,String nation_code, String nationlity, String academic_qualification, String work_number, String school_time, String teach_time, String job_code, Integer teach_range) {
        return userMapper.addTeacher(user_id,nation_code,nationlity,academic_qualification,work_number,school_time,teach_time,job_code,teach_range);
    }


    public int transInto(Long id, String user_id, String apply_user_id, String origin_school_id, String target_school_id, String tea_work_number, Date entrance_year, String tea_position,String tea_range) {
        return userMapper.transInto(id,user_id,apply_user_id,origin_school_id,target_school_id,tea_work_number,entrance_year,tea_position,tea_range);
    }

    public List<Map> getTransferTeachers(Integer getType, long organizationId, String userName, String loginName, String gender, String position, Integer accountStatus) {
        return userMapper.getTransferTeachers(getType,organizationId,userName,loginName,gender,position,accountStatus);
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

    public int editTeacherPracticeInfo(String user_id,String teach_certification, Date teach_time, Date school_time, String job_profession,String teach_range, String work_number) {
        return userMapper.editTeacherPracticeInfo( user_id,teach_certification,teach_time,school_time,job_profession,teach_range,work_number);
    }

    public Map selectParentInfoByCertificationNumber(String certificationNumber){
        return userMapper.selectParentInfoByCertificationNumber(certificationNumber);
    }

    public Map selectStudentInfoByCertificationNumber(String certificationNumber){
        return userMapper.selectStudentInfoByCertificationNumber(certificationNumber);
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

    public String selectUserIdByCertification(String parentCertificateNumber, String parentCertificateType){
        Map map = selectUserIdAndOrganizationId(parentCertificateNumber, parentCertificateType);
        return map.get("USER_ID").toString();
    }

    public Map selectUserIdAndOrganizationId(String parentCertificateNumber, String parentCertificateType){
        return userMapper.selectUserIdAndOrganizationId(parentCertificateNumber, parentCertificateType);
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

    public void updateOrganizationIdByTransferId(String transferId){
        userMapper.updateOrganizationIdByTransferId(transferId);
    }

    @Transactional( propagation = Propagation.REQUIRED )
    public void updateStudent(String transferId){
        Map map = userMapper.selectStudentInfoByTransferId(transferId);
        userMapper.updateStudent(map);
    }
}
