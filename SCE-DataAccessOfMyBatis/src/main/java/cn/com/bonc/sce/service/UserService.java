package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.*;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.UserDao;
import cn.com.bonc.sce.exception.ImportUserFailedException;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IDUtil;
import cn.com.bonc.sce.tool.IdWorker;
import cn.com.bonc.sce.tool.UserPropertiesUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Charles on 2019/3/6.
 */
@Service
@Slf4j
public class UserService {

    private static final String DEFAULT_PASSWORD = "star123!";
    private static final String STUDENT = "该学生%s";
    private static final String PARENT = "该家长%s";

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    public int saveUser(UserBean userBean) {
        return userDao.saveUser(userBean);
    }

    public int saveAccount(AccountBean account) {
        return userDao.saveAccount(account);
    }

    public int delUser(String id) {
        return userDao.delUser(id);
    }

    public int delPassword(String id){
        return userDao.delPassword(id);
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

    public List<Map> getTeachers(long organizationId,String userName, String loginName, String gender, String position, String accountStatus) {
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

    public int editTeacher(String user_id, String academic_qualification, String work_number, String school_time, String teach_time, String position, Integer teach_range) {
        return userDao.editTeacher( user_id,academic_qualification,work_number,school_time,teach_time,position,teach_range);
    }

    public int addUser(String user_id, Integer certificate_type, String certificate_number, String user_name,
                       String gender, String phone_number, String organization_id, String mail_address, String birthdate,
                       String nationality, String nationCode, String secret, String userType, String loginName) {
        return userDao.addUser(user_id,certificate_type,certificate_number,user_name,gender,phone_number,organization_id,
                mail_address,birthdate, nationality, nationCode, secret, userType, loginName);
    }

    public int addTeacher(String user_id, String academic_qualification, String work_number, String school_time, String teach_time, String position, Integer teach_range) {
        return userDao.addTeacher(user_id,academic_qualification,work_number,school_time,teach_time,position,teach_range);
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
        System.out.println(list);
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

    @Transactional( propagation = Propagation.REQUIRED )
    public RestRecord addStudent(Map map, String userId){
        //学生身份证验证
        String studentCertificateType = map.get("studentCertificateType").toString();
        String studentCertificateNumber = map.get("studentCertificateNumber").toString();
        String parentCertificateType = map.get("parentCertificateType").toString();
        String parentCertificateNumber = map.get("parentCertificateNumber").toString();
        if(studentCertificateType.equals("1")){
            if(!UserPropertiesUtil.checkCertificateNumber(studentCertificateNumber)){
                log.info("学生身份证验证未通过");
                return new RestRecord( 432, String.format(STUDENT, "身份证填写不正确") );
            }
        }
        if(userDao.selectCountByCertificateNumber(studentCertificateType, studentCertificateNumber) > 0){
            log.info("学生证件已被使用");
            return new RestRecord( 432, String.format(STUDENT, "证件已被使用") );
        }
        String organizationId = userDao.getOrganizationIdByUserId(userId);
        if(organizationId == null){
            return new RestRecord( 432, "当前账户任何学校相关信息，无法添加" );
        }
        String bindType = map.get("bindType").toString();
        String parentId = null;
        if(studentCertificateType.equals(parentCertificateType) && studentCertificateNumber.equals(parentCertificateNumber)){
            return new RestRecord( 432, "学生和家长的证件不能相同" );
        }
        if(bindType.equals("1")){
            //新建家长
            if(parentCertificateType.equals("1")){
                if(!UserPropertiesUtil.checkCertificateNumber(parentCertificateNumber)){
                    log.info("家长身份证验证未通过");
                    return new RestRecord( 432, String.format(PARENT, "身份证填写不正确") );
                }
            }
            //验证手机号
            if(!UserPropertiesUtil.checkPhone(map.get("parentPhoneNumber").toString())){
                log.info("家长手机号验证未通过");
                return new RestRecord( 432, String.format(PARENT, "手机号填写不正确") );
            }
            String parentMailAddress = null;
            try{
                parentMailAddress = map.get("parentMailAddress").toString();
            }catch (NullPointerException e){}

            if( parentMailAddress != null && !UserPropertiesUtil.checkMail(parentMailAddress)){
                log.info("家长邮箱验证未通过");
                return new RestRecord( 432, String.format(PARENT, "邮箱填写不正确") );
            }
            //1.存入家长密码表
            String parentSecret = Secret.ES256GenerateSecret();
            String parentLoginName = IDUtil.createID( "zj_" );
            parentId = UUID.randomUUID().toString().replace( "-", "" ).toLowerCase();
            userDao.saveUserPassword(idWorker.nextId(), parentId, DEFAULT_PASSWORD);

            //2.存入家长用户表
            map.put("parentId", parentId);
            map.put("parentSecret", parentSecret);
            map.put("parentLoginName", parentLoginName);
            map.put("userType", 5);
            userDao.saveUserOfParent(map);

        }else if(bindType.equals("2")){
            //绑定现有家长
            if(parentCertificateType.equals("1")){
                if(!UserPropertiesUtil.checkCertificateNumber(parentCertificateNumber)){
                    log.info("家长身份证验证未通过");
                    return new RestRecord( 432, String.format(PARENT, "身份证填写不正确") );
                }
                if(userDao.selectCountByCertificateNumber(parentCertificateType, parentCertificateNumber) < 1){
                    log.info("家长证件不存在");
                    return new RestRecord( 432, "暂无该家长信息" );
                }
            }
            parentId = userDao.selectUserIdByCertification(map.get("parentCertificateNumber").toString(), map.get("parentCertificateType").toString(),"5");
        }
        //1.存入学生密码
        String studentSecret = Secret.ES256GenerateSecret();
        String studentLoginName = IDUtil.createID( "xs_" );
        String studentId = UUID.randomUUID().toString().replace( "-", "" ).toLowerCase();
        userDao.saveUserPassword(idWorker.nextId(), studentId, DEFAULT_PASSWORD);

        //2.存入学生用户表
        map.put("studentId", studentId);
        map.put("studentLoginName", studentLoginName);
        map.put("studentSecret", studentSecret);
        map.put("organizationId", organizationId);
        map.put("userType", 1);
        userDao.saveUserOfStudent(map);

        //3.存入学生表
        userDao.saveStudent(map);

        //4.存入家长学生关系表
        userDao.saveParentStudentRel(idWorker.nextId(), parentId, studentId, 1, map.get("relationship").toString());
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    /**
     *  外校转入学生
     * @param map 数据
     * @param currentUserId 用户ID
     * @return RestRecord
     */
    public RestRecord transferInStudent(Map map, String currentUserId){
        String studentCertificateType = map.get("studentCertificateType").toString();
        String studentCertificateNumber = map.get("studentCertificateNumber").toString();
        Map userInfo = userDao.selectUserIdAndOrganizationId(studentCertificateNumber, studentCertificateType, "1");
        if(userInfo == null){
            return new RestRecord( 434, String.format(WebMessageConstants.SCE_PORTAL_MSG_434, "") );
        }
        String organizationId = userDao.getOrganizationIdByUserId(currentUserId);
        if(organizationId == null){
            return new RestRecord( 432, "当前账户任何学校相关信息，无法添加" );
        }
        if(organizationId.equals(userInfo.get("ORGANIZATION_ID").toString())){
            return new RestRecord( 434, "该学生已是本校学生，无需申请转入" );
        }
        map.put("id", idWorker.nextId());
        map.put("userId", userInfo.get("USER_ID"));
        map.put("userType", 1);
        map.put("originSchoolId", userInfo.get("ORGANIZATION_ID"));
        map.put("targetSchoolId", organizationId);
        map.put("applyUserId", currentUserId);

        //查询该学生是否已申请转入
        int count = userDao.selectTransfer(userInfo.get("USER_ID").toString(), organizationId);
        if(count > 0){
            return new RestRecord( 434, String.format(WebMessageConstants.SCE_PORTAL_MSG_435, "已提交转入申请"));
        }
        userDao.addTransfer(map);
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    public String selectUserIdByLoginName(String loginName){
        return userDao.selectUserIdByLoginName(loginName);
    }


    public int editTeacherPracticeInfo(String user_id,String teach_certification, Date teach_time, Date school_time, String job_profession,Integer teach_range, String work_number) {
        return userDao.editTeacherPracticeInfo( user_id,teach_certification,teach_time,school_time,job_profession,teach_range,work_number);
    }

    public RestRecord getParentInfo(String certificationType, String certificationNumber, String userType){
        Map map = null;
        if(userType.equals("1")){
            map = userDao.selectStudentInfoByCertificationNumber(certificationType, certificationNumber);
        }else if(userType.equals("5")){
            map = userDao.selectParentInfoByCertificationNumber(certificationType, certificationNumber);
        }else if(userType.equals("2")){
            map = userDao.selectTeacherInfoByCertificationNumber(certificationType, certificationNumber);
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, map);
    }

    public RestRecord getTransferStudent(String userName, String loginName, String studentNumber, String gender, String grade, String applyStatus,
                                         String transferType, String pageNum, String pageSize, String userId){
        String organizationId = userDao.getOrganizationIdByUserId(userId);
        try {
            PageHelper.startPage( Integer.parseInt( pageNum ), Integer.parseInt( pageSize ) );
        } catch ( NumberFormatException e ) {
            log.warn( "不支持的分页参数 -> pageNum:{},pageSize:{}", pageNum, pageSize );
            return new RestRecord( 433, WebMessageConstants.SCE_PORTAL_MSG_433 );
        }
        List list = null;
        if(transferType.equals("1")){
            list = userDao.selectTransferInStudent(userName, loginName, studentNumber, gender, grade, applyStatus, organizationId);
        }else if(transferType.equals("2")){
            list = userDao.selectTransferOutStudent(userName, loginName, studentNumber, gender, grade, organizationId);
        }
        long total = list == null ? 0L : ((Page) list).getTotal();
        RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, list );
        restRecord.setTotal( total );
        return restRecord;
    }

    public RestRecord repealApply(String currentUserId, String transferId){
        long id;
        try{
            id = Long.parseLong(transferId);
        }catch (NumberFormatException e){
            return new RestRecord( 422, WebMessageConstants.SCE_PORTAL_MSG_422 );
        }
        String organizationId = userDao.getOrganizationIdByUserId(currentUserId);
        int count = userDao.deleteTransferApply(id, organizationId);
        if(count > 0 ){
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        }
        return new RestRecord( 436, WebMessageConstants.SCE_PORTAL_MSG_436 );
    }

    public RestRecord reCall(String transferId){
        userDao.reCall(transferId);
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    public RestRecord getTransferOut(String transferId){
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, userDao.selectTransferInfo(transferId) );
    }

    @Transactional( isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class )
    public RestRecord auditTransfer(String userId, Map map){
        String organizationId = userDao.getOrganizationIdByUserId(userId);
        map.put("organizationId", organizationId);
        List<String> ids = (List<String>)map.get("ids");
        for( String id : ids){
            map.put("id", id);
            int count = userDao.auditTransfer(map);
            if(count < 1){
                return new RestRecord( 436, WebMessageConstants.SCE_PORTAL_MSG_436 );
            }
            if(map.get("applyStatus").toString().equals("1")){
                //转移学生的学校
                userDao.updateOrganizationIdByTransferId(id);
                //修改学生的学生表
                userDao.updateStudent(id);
            }
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );

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

    public void addPassword(long id, String userId, String password){
        userDao.saveUserPassword(id, userId, password);
    }

    public int checkUser(Integer certificateType, String certificateNumber, String phoneNumber){
        return userDao.checkUser(certificateType, certificateNumber, phoneNumber);
    }

    public RestRecord getTransferTeacherInfo(String transferId){
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, userDao.getTransferTeacherInfo(transferId));
    }

    public Map getUserId(String certificateType, String certificateNumber){
        return userDao.selectTeacherInfoByCertificationNumber(certificateType, certificateNumber);
    }

    @Transactional( isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class )
    public RestRecord auditTeacher(String userId, Map map){
        String organizationId = userDao.getOrganizationIdByUserId(userId);
        map.put("organizationId", organizationId);
        List<String> ids = (List<String>) map.get("ids");
        for(String id : ids){
            map.put("id", id);
            int count = userDao.auditTransfer(map);
            if(count < 1){
                return new RestRecord( 436, WebMessageConstants.SCE_PORTAL_MSG_436 );
            }
            if(map.get("applyStatus").toString().equals("1")){
                //转移教师的学校
                userDao.updateOrganizationIdByTransferId(id);
                //修改教师的教师表
                userDao.updateTeacher(map.get("id").toString());
            }
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );

    }

    public int selectCountByCertificateNumber(String certificateType, String certificateNumber){
        return userDao.selectCountByCertificateNumber(certificateType, certificateNumber);
    }

    public int selectCountByPhoneNumber(String phoneNumber){
        return userDao.selectCountByPhoneNumber(phoneNumber);
    }

    public int selectCountByMailAddress(String mailAddress){
        return userDao.selectCountByMailAddress(mailAddress);
    }

    public String getOrganizationIdByUserId(String userId){
        return userDao.getOrganizationIdByUserId(userId);
    }

    public int selectTransfer(String userId, String organizationId){
        return userDao.selectTransfer(userId, organizationId);
    }

    public Map selectUserIdAndOrganizationId(String certificateNumber, String certificateType, String userType){
        return userDao.selectUserIdAndOrganizationId(certificateNumber, certificateType, userType);
    }

    public RestRecord reCallTeacher(Map map){
        String transferId = map.get("transferId").toString();
        userDao.reCall(transferId);
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    @Transactional( rollbackFor = Exception.class )
    public RestRecord updateAudit(String auditUserId,Map map ){

        if(map!=null) {
            String userId = map.get("userId").toString();
            Integer userType = Integer.parseInt(map.get("userType").toString());
            Integer auditStatus = Integer.parseInt(map.get("auditStatus").toString());
            Long entryid = Long.parseLong(map.get("entityId").toString());
            if (userType == 2 && auditStatus == 1) {
                userDao.updateInfoTeacher(userId, entryid);
            } else if (userType == 7 && auditStatus == 1) {
                userDao.updateInfoInstitution(userId, entryid);
            } else if (userType == 6 && auditStatus == 1) {
                userDao.updateInfoAgent(userId, entryid);
            }
            String rejectOpinion = "";
            if(map.get("rejectOpinion")!=null){
                rejectOpinion = map.get("rejectOpinion").toString();
            }

            userDao.updateAudit(auditUserId, userId, auditStatus, rejectOpinion);

            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        }else {
            return new RestRecord( 421, WebMessageConstants.SCE_PORTAL_MSG_421 );
        }

    }

    public UserAuditBean getAudit(String userId){
        return userDao.getAudit(userId);
    }
}
