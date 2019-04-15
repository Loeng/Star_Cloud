package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.bean.TeacherInfoBean;
import cn.com.bonc.sce.bean.UserBean;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.UserDao;
import cn.com.bonc.sce.rest.RestRecord;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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

    public int editUser(String user_id, Integer certificate_type, String certificate_number, String user_name, String gender,
                        String phone_number, String mail_address, String birthdate, String nationCode, String nationality) {
        return userDao.editUser(user_id,certificate_type,certificate_number,user_name,gender,phone_number,mail_address,birthdate,nationCode, nationality);
    }

    public int editTeacher(String user_id, String academic_qualification, String work_number, String school_time, String teach_time, String job_code, Integer teach_range) {
        return userDao.editTeacher( user_id,academic_qualification,work_number,school_time,teach_time,job_code,teach_range);
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

    public RestRecord getStudents(String userName, String loginName, String studentNumber, String gender, String grade, String accountStatus,
                                  String userId, String pageNum, String pageSize){
        String organizationId = userDao.getOrganizationIdByUserId( userId );
        try {
            PageHelper.startPage( Integer.parseInt( pageNum ), Integer.parseInt( pageSize ) );
        } catch ( NumberFormatException e ) {
            log.warn( "不支持的分页参数 -> pageNum:{},pageSize:{}", pageNum, pageSize );
            return new RestRecord( 433, WebMessageConstants.SCE_PORTAL_MSG_433 );
        }
        List list = userDao.getStudents( userName, loginName, studentNumber, gender, grade, accountStatus, organizationId );
        long total = list == null ? 0L : ((Page) list).getTotal();
        RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, list );
        restRecord.setTotal( total );
        return restRecord;
    }

    public RestRecord getStudentInfo( String userId ){
        Map map = userDao.getStudentInfo(userId);
        if(map == null){
            return new RestRecord( 434, String.format( WebMessageConstants.SCE_PORTAL_MSG_434, "" ) );
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, map );
    }

    @Transactional( rollbackFor = Exception.class )
    public RestRecord editStudent(Map map){
        int count1 =  userDao.editStudentOfUser(map);
        int count2 =  userDao.editStudentOfStudent(map);
        if(count1 >0 && count2 > 0 ){
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        }else {
            return new RestRecord( 434, String.format( WebMessageConstants.SCE_PORTAL_MSG_434, "修改学生失败，" ) );
        }
    }

    @Transactional( rollbackFor = Exception.class )
    public RestRecord delStudent(String userId){
        int count1 = userDao.delStudentOfStudent(userId);
        int count2 = userDao.delStudentOfUser(userId);
        if(count1 >0 && count2 > 0 ){
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        }else {
            return new RestRecord( 434, String.format( WebMessageConstants.SCE_PORTAL_MSG_434, "删除学生失败，" ) );
        }
    }

    @Transactional( isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class )
    public RestRecord addStudent(Map map, String userId){
        String organizationId = userDao.getOrganizationIdByUserId(userId);
        //插入学生到学生表


        return null;
    }

    public String selectUserIdByLoginName(String loginName){
        return userDao.selectUserIdByLoginName(loginName);
    }


    public int editTeacherPracticeInfo(String user_id,String teach_certification, Date teach_time, Date school_time, String job_profession,Integer teach_range, String work_number) {
        return userDao.editTeacherPracticeInfo( user_id,teach_certification,teach_time,school_time,job_profession,teach_range,work_number);
    }

    public TeacherInfoBean getTeacherInfoById(String userId){
        return userDao.getTeacherInfoById(userId);
    }

    public int addTeacherInfo(String user_id,String teach_certification, Date teach_time, Date school_time, String job_profession,Integer teach_range, String work_number,Integer is_delete) {
        return userDao.addTeacherInfo( user_id,teach_certification,teach_time,school_time,job_profession,teach_range,work_number,is_delete);
    }

    public UserBean getUserById(String userId){
        return userDao.getUserById(userId);
    }
}
