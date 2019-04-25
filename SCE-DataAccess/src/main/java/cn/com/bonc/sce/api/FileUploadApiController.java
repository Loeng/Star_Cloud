package cn.com.bonc.sce.api;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;

import cn.com.bonc.sce.dao.UserPasswordDao;

import cn.com.bonc.sce.entity.FileResourceEntity;
import cn.com.bonc.sce.entity.StudentParentRel;
import cn.com.bonc.sce.entity.UserPassword;
import cn.com.bonc.sce.exception.ImportUserFailedException;
import cn.com.bonc.sce.model.ExcelToUser;
import cn.com.bonc.sce.entity.Message;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.repository.ExportUserRepository;
import cn.com.bonc.sce.repository.FileResourceRepository;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.MessageService;
import cn.com.bonc.sce.tool.IDUtil;
import cn.com.bonc.sce.tool.IdWorker;
import cn.com.bonc.sce.tool.UserPropertiesUtil;
import cn.com.bonc.sce.utils.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文件上传存储信息保存
 *
 * @author BTW
 */
@Slf4j
@RestController
@RequestMapping( "/file-upload" )
public class FileUploadApiController {

    private FileResourceRepository fileResourceRepository;

    private UserPasswordDao userPasswordDao;

    private ExportUserRepository exportUserRepository;

    private MessageService messageService;

    @Autowired
    private IdWorker idWorker;

    public static final String STUDENT_PRE = "1";

    public static final String TEACHER_PRE = "2";

    public static final String PARENT_CODE = "5";

    public static final String CERTIFICATE_TYPE = "1";

    private static final int EXCEL_NUMBER = 4;

    private static final String certificateNumberPrompt = "请检查%s身份证信息填写是否正确";

    private static final String certificateNumberExist = "%s证件号已被使用";

    private static final String phonePrompt = "%s手机号填写不正确";

    private static final String phonePromptExist = "%s手机号已被使用";

    private static final String emailPrompt = "%s邮箱填写不正确";

    private static final String emailPromptExit = "%s邮箱已被使用";

    private static final String success = "共添加%s条数据";

    @Autowired
    public FileUploadApiController( FileResourceRepository fileResourceRepository, UserPasswordDao userPasswordDao ,ExportUserRepository exportUserRepository,MessageService messageService) {
        this.fileResourceRepository = fileResourceRepository;
        this.userPasswordDao = userPasswordDao;
        this.exportUserRepository = exportUserRepository;
        this.messageService=messageService;
    }

    @PostMapping
    @ResponseBody
    public RestRecord addAppTypeInfo( @RequestParam( "fileType" ) String fileType,
                                      @RequestParam( "fileMappingPath" ) String fileMappingPath,
                                      @RequestParam( "fileStorePath" ) String fileStorePath ) {
        FileResourceEntity fileResourceEntity = new FileResourceEntity();
        fileResourceEntity.setFileType( fileType );
        fileResourceEntity.setFileStorePath( fileStorePath );
        fileResourceEntity.setFileMappingPath( fileMappingPath );
        FileResourceEntity saveEntity = fileResourceRepository.save( fileResourceEntity );
        if ( saveEntity != null ) {
            return new RestRecord( 200, saveEntity.getResourceId() );
        } else {
            return new RestRecord( 200, MessageConstants.SCE_MSG_409 );
        }
    }


    /**
     * 批量上传Excel解析用户数据
     * READ_UNCOMMITTED 解决同一个表导入时所有身份证都不能相同，但是会出现脏读的情况，个人觉得并不影响使用。
     * @return 返回是否成功
     */
    @PostMapping( "/upload-user-info" )
    @ResponseBody
    @Transactional( isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class )
    public RestRecord uploadParseExcel(@RequestBody List< ExcelToUser > list, @RequestParam( "userType" ) String userType, @CurrentUserId String currentUserId) throws ImportUserFailedException{

        //通过用户id查询所属学校id
        String organizationId = fileResourceRepository.selectOrganizationId( currentUserId );
        Integer auth = fileResourceRepository.selectAuth(currentUserId);
        if(auth == null || auth == 0){
            throw new ImportUserFailedException( WebMessageConstants.SCE_PORTAL_MSG_436 );
        }
        ExcelToUser excelToUser;
        int i = 0;
        int count = 0;
        try {
            if ( TEACHER_PRE.equals( userType ) ) {
                if( organizationId == null ){
                    throw new ImportUserFailedException( "当前账户任何学校相关信息，无法导入" );
                }
                String pre = "js_";
                for ( ; i < list.size(); i++ ) {
                    excelToUser = list.get(i);
                    if(excelToUser.getUserName() == null && excelToUser.getGender() == null && excelToUser.getWorkNumber() == null && excelToUser.getCertificateNumber() == null && excelToUser.getPhoneNumber() == null &&
                            excelToUser.getNationLity() == null && excelToUser.getNationCode() == null && excelToUser.getTeachTime() == null &&
                            excelToUser.getAcademicQualification() == null && excelToUser.getMailAddress() == null && excelToUser.getPosition() == null && excelToUser.getSchoolTime() == null &&
                            excelToUser.getTeachRange() == null && excelToUser.getCertificateType() == null && excelToUser.getIsAdministrators() == null){
                        continue;
                    }
                    if(excelToUser.getUserName() == null || excelToUser.getGender() == null || excelToUser.getWorkNumber() == null || excelToUser.getCertificateNumber() == null || excelToUser.getPhoneNumber() == null ||
                            excelToUser.getNationLity() == null || excelToUser.getNationCode() == null || excelToUser.getTeachTime() == null ||
                            excelToUser.getAcademicQualification() == null || excelToUser.getMailAddress() == null || excelToUser.getPosition() == null || excelToUser.getSchoolTime() == null ||
                            excelToUser.getTeachRange() == null || excelToUser.getCertificateType() == null || excelToUser.getIsAdministrators() == null){
                        log.info("缺少必填项");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, "缺少必填项", i + EXCEL_NUMBER ));
                    }
                    if( excelToUser.getCertificateType().equals("1") && !UserPropertiesUtil.checkCertificateNumber(excelToUser.getCertificateNumber())){
                        log.info("教师身份证验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( certificateNumberPrompt, "教师" ), i + EXCEL_NUMBER ));
                    }
                    if(!UserPropertiesUtil.checkPhone(excelToUser.getPhoneNumber())){
                        log.info("教师手机号验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( phonePrompt, "教师" ), i + EXCEL_NUMBER ));
                    }
                    if(!UserPropertiesUtil.checkMail(excelToUser.getMailAddress())){
                        log.info("教师邮箱验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( emailPrompt, "教师" ), i + EXCEL_NUMBER ));
                    }
                    if( fileResourceRepository.selectUserCount(excelToUser.getCertificateNumber()) > 0){
                        log.info("教师证件号已被使用");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( certificateNumberExist, "教师" ), i + EXCEL_NUMBER ));
                    }
                    if( fileResourceRepository.selectPhoneNumber(excelToUser.getPhoneNumber()) > 0){
                        log.info("教师手机号已被使用");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( phonePromptExist, "教师" ), i + EXCEL_NUMBER ));
                    }
                    if( fileResourceRepository.selectMailAddress(excelToUser.getMailAddress()) > 0){
                        log.info("教师邮箱已被使用");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( emailPromptExit, "教师" ), i + EXCEL_NUMBER ));
                    }

                    UserPassword userPassword = new UserPassword();
                    String secret = Secret.ES256GenerateSecret();
                    String loginName = IDUtil.createID( pre );
                    String userId = UUID.getUUID();
                    userPassword.setIsDelete( 1 );
                    userPassword.setPassword( "star123!" );
                    userPassword.setUserId( userId );
                    if(excelToUser.getCertificateType().equals("1")){
                        fileResourceRepository.savaAllUserInfo( userId, excelToUser.getUserName(), excelToUser.getGender(),
                                loginName, Integer.parseInt(userType), excelToUser.getMailAddress(), Integer.parseInt(excelToUser.getCertificateType()), excelToUser.getCertificateNumber(),
                                excelToUser.getPhoneNumber(), Long.parseLong(organizationId),
                                UserPropertiesUtil.getBirthDateByCer(excelToUser.getCertificateNumber()), secret, excelToUser.getNationLity(), excelToUser.getNationCode(),
                                Integer.parseInt(excelToUser.getIsAdministrators()));
                    }else {
                        fileResourceRepository.savaAllUserInfoWithNotBirthDate( userId, excelToUser.getUserName(), excelToUser.getGender(),
                                loginName, Integer.parseInt(userType), excelToUser.getMailAddress(), Integer.parseInt(excelToUser.getCertificateType()), excelToUser.getCertificateNumber(),
                                excelToUser.getPhoneNumber(), Long.parseLong(organizationId),
                                secret, excelToUser.getNationLity(), excelToUser.getNationCode(),
                                Integer.parseInt(excelToUser.getIsAdministrators()));
                    }

                    userPasswordDao.save( userPassword );

                    String schoolTime = excelToUser.getSchoolTime();
                    String teachTime = excelToUser.getTeachTime();
                    Date schoolDate;
                    Date teachDate;
                    if ( schoolTime.length() > 5 ) {
                        schoolDate = new Date( schoolTime );
                    } else {
                        schoolDate = UserPropertiesUtil.getDateByExcelDate( schoolTime );
                    }
                    if ( teachTime.length() > 5 ) {
                        teachDate = new Date( teachTime );
                    } else {
                        teachDate = UserPropertiesUtil.getDateByExcelDate( teachTime );
                    }

                    //插入数据到教师表
                    Integer teachRange = UserPropertiesUtil.changeTeachRange(excelToUser.getTeachRange());
                    fileResourceRepository.saveTeacher( userId, Long.parseLong(organizationId), excelToUser.getPosition(),
                            excelToUser.getWorkNumber(), schoolDate,
                            teachDate, excelToUser.getAcademicQualification(), teachRange );
                    count++;
                }
            } else if ( STUDENT_PRE.equals( userType ) ) {
                if( organizationId == null ){
                    throw new ImportUserFailedException( "当前学校无学校相关信息，无法导入" );
                }
                String pre = "xs_";
                for ( ; i < list.size(); i++ ) {
                    excelToUser = list.get(i);
                    //必填项过滤
                    if(excelToUser.getUserName() == null && excelToUser.getCertificateNumber() == null && excelToUser.getParentFamify() == null && excelToUser.getParentCertificationNumber() == null
                            && excelToUser.getGrade() == null && excelToUser.getClassNumber() == null && excelToUser.getCertificateType() == null && excelToUser.getParentCertificateType() == null &&
                            excelToUser.getGender() == null && excelToUser.getStudentNumber() == null && excelToUser.getNationLity() == null && excelToUser.getNationCode() == null &&
                            excelToUser.getEntranceYear() == null && excelToUser.getPhoneNumber() == null && excelToUser.getParentName() == null && excelToUser.getParentGender() == null &&
                            excelToUser.getParentNationLity() == null && excelToUser.getParentNationCode() == null && excelToUser.getParentTelephone() == null &&
                            excelToUser.getParentEmail() == null){
                        continue;
                    }
                    if(excelToUser.getUserName() == null || excelToUser.getCertificateNumber() == null || excelToUser.getParentFamify() == null || excelToUser.getParentCertificationNumber() == null
                    || excelToUser.getGrade() == null || excelToUser.getClassNumber() == null || excelToUser.getCertificateType() == null || excelToUser.getParentCertificateType() == null ||
                    excelToUser.getGender() == null || excelToUser.getStudentNumber() == null || excelToUser.getNationLity() == null || excelToUser.getNationCode() == null ||
                    excelToUser.getEntranceYear() == null){
                        log.info("缺少必填项");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, "缺少必填项", i + EXCEL_NUMBER ));
                    }
                    if( excelToUser.getCertificateType().equals("1") && !UserPropertiesUtil.checkCertificateNumber(excelToUser.getCertificateNumber())){
                        log.info("学生身份证验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( certificateNumberPrompt, "学生" ), i + EXCEL_NUMBER ));
                    }
                    if( excelToUser.getPhoneNumber() != null && !UserPropertiesUtil.checkPhone(excelToUser.getPhoneNumber())){
                        log.info("学生手机号验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( phonePrompt, "学生" ), i + EXCEL_NUMBER ));
                    }
                    if( excelToUser.getMailAddress() != null && !UserPropertiesUtil.checkMail(excelToUser.getMailAddress())){
                        log.info("学生邮箱验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( emailPrompt, "学生" ), i + EXCEL_NUMBER ));
                    }
                    if( fileResourceRepository.selectUserCount(excelToUser.getCertificateNumber()) > 0){
                        log.info("学生身份证已被使用");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( certificateNumberExist, "学生" ), i + EXCEL_NUMBER ));
                    }
                    if( excelToUser.getParentCertificateType().equals("1") && !UserPropertiesUtil.checkCertificateNumber(excelToUser.getParentCertificationNumber())){
                        log.info("家长身份证验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( certificateNumberPrompt, "家长" ), i + EXCEL_NUMBER ));
                    }
                    if( excelToUser.getParentTelephone() != null && !UserPropertiesUtil.checkPhone(excelToUser.getParentTelephone())){
                        log.info("家长手机号验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( phonePrompt, "家长" ), i + EXCEL_NUMBER ));
                    }
                    if( excelToUser.getParentEmail() != null && !UserPropertiesUtil.checkMail(excelToUser.getParentEmail())){
                        log.info("家长邮箱验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( emailPrompt, "家长" ), i + EXCEL_NUMBER ));
                    }
                    if(excelToUser.getParentCertificationNumber().equals(excelToUser.getCertificateNumber())){
                        log.info("家长和学生的身份证不能相同");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, "家长和学生的身份证不能相同", i + EXCEL_NUMBER ));
                    }
                    if( excelToUser.getPhoneNumber() != null && fileResourceRepository.selectPhoneNumber(excelToUser.getPhoneNumber()) > 0){
                        log.info("学生手机号已被使用");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( phonePromptExist, "学生" ), i + EXCEL_NUMBER ));
                    }
                    if( excelToUser.getParentTelephone() != null && fileResourceRepository.selectPhoneNumber(excelToUser.getParentTelephone()) > 0){
                        log.info("家长手机号已被使用");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( phonePromptExist, "家长" ), i + EXCEL_NUMBER ));
                    }
                    if( excelToUser.getParentEmail() != null && fileResourceRepository.selectMailAddress(excelToUser.getParentEmail()) > 0){
                        log.info("家长邮箱已被使用");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( emailPromptExit, "家长" ), i + EXCEL_NUMBER ));
                    }
                    String parentId = fileResourceRepository.selectUserIdByCertificateNumber(excelToUser.getParentCertificationNumber());

                    //生成学生用户，并自动生成家长用户关联学生
                    UserPassword userPassword = new UserPassword();
                    String secret = Secret.ES256GenerateSecret();
                    String loginName = IDUtil.createID( pre );
                    String studentId = UUID.getUUID();
                    userPassword.setIsDelete( 1 );
                    userPassword.setPassword( "star123!" );
                    userPassword.setUserId( studentId );
                    if(excelToUser.getCertificateType().equals("1")){
                        fileResourceRepository.savaAllUserInfo( studentId, excelToUser.getUserName(), excelToUser.getGender(),
                                loginName, Integer.parseInt(userType), excelToUser.getMailAddress(),Integer.parseInt(excelToUser.getCertificateType()) , excelToUser.getCertificateNumber(),
                                excelToUser.getPhoneNumber(),Long.parseLong(organizationId),
                                UserPropertiesUtil.getBirthDateByCer(excelToUser.getCertificateNumber()), secret, excelToUser.getNationLity(), excelToUser.getNationCode(),0);
                    }else {
                        fileResourceRepository.savaAllUserInfoWithNotBirthDate( studentId, excelToUser.getUserName(), excelToUser.getGender(),
                                loginName, Integer.parseInt(userType), excelToUser.getMailAddress(),Integer.parseInt(excelToUser.getCertificateType()) , excelToUser.getCertificateNumber(),
                                excelToUser.getPhoneNumber(),Long.parseLong(organizationId),
                                secret, excelToUser.getNationLity(), excelToUser.getNationCode(),0);
                    }

                    userPasswordDao.save( userPassword );

                    String loginParentName;
                    if(parentId == null){
                        //生成家长用户，并关联学生；新建家长时，要求家长相关的必填项填写完整
                        UserPassword parentPassword = new UserPassword();
                        String secretParent = Secret.ES256GenerateSecret();
                        loginParentName = IDUtil.createID( "zj_" );
                        parentId = UUID.getUUID();
                        parentPassword.setIsDelete( 1 );
                        parentPassword.setPassword( "star123!" );
                        parentPassword.setUserId( parentId );
                        //新增家长时的必填项过滤
                        if(excelToUser.getParentName() == null || excelToUser.getParentGender() == null || excelToUser.getParentNationLity() == null ||
                            excelToUser.getParentNationCode() == null || excelToUser.getParentTelephone() == null || excelToUser.getParentEmail() == null){
                            log.info("缺少必填项");
                            throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, "缺少必填项", i + EXCEL_NUMBER ));
                        }
                        if(excelToUser.getParentCertificateType().equals("1")){
                            fileResourceRepository.saveParent(parentId, excelToUser.getParentName(), excelToUser.getParentGender(), loginParentName, PARENT_CODE, excelToUser.getParentEmail(),
                                    excelToUser.getParentCertificateType(), excelToUser.getParentCertificationNumber(), excelToUser.getParentTelephone(),
                                    UserPropertiesUtil.getBirthDateByCer(excelToUser.getParentCertificationNumber()), secretParent, excelToUser.getParentNationLity(), excelToUser.getParentNationCode() );
                        }else {
                            fileResourceRepository.saveParentWithoutBirthDate(parentId, excelToUser.getParentName(), excelToUser.getParentGender(), loginParentName, PARENT_CODE, excelToUser.getParentEmail(),
                                    excelToUser.getParentCertificateType(), excelToUser.getParentCertificationNumber(), excelToUser.getParentTelephone(),
                                    secretParent, excelToUser.getParentNationLity(), excelToUser.getParentNationCode() );
                        }

                        userPasswordDao.save( parentPassword );

                        //向学生账号发送一条消息告知家长账号
                        Message message = new Message();
                        message.setContent( "恭喜您，申请主家长账号分配成功！账号："+loginParentName +" 密码：star123!");
                        message.setSourceId("0");
                        message.setTargetId( studentId );
                        messageService.insertMessage( message );
                    }else if(fileResourceRepository.selectUserCountByCertificate(excelToUser.getStudentCertificationNumber(), "5") < 1){
                        log.info("填写的家长证件为非家长角色，不能绑定家长");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, "填写的家长证件为非家长角色，不能绑定家长", i + EXCEL_NUMBER ));
                    }


                    //建立学生家长关系
                    if(excelToUser.getParentFamify() == null){
                        log.info("家长学生关系未填写");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, "家长学生关系未填写", i + EXCEL_NUMBER ));
                    }
                    StudentParentRel studentParentRel = new StudentParentRel();
                    studentParentRel.setRelationShip( excelToUser.getParentFamify() );
                    studentParentRel.setIsMain( 1 );
                    studentParentRel.setParentUserId( parentId );
                    studentParentRel.setStudentUserId( studentId );
                    exportUserRepository.save( studentParentRel);

                    Date entranceYear;
                    if ( excelToUser.getEntranceYear().length() > 5 ) {
                        entranceYear = new Date( excelToUser.getEntranceYear() );
                    } else {
                        entranceYear = UserPropertiesUtil.getDateByExcelDate( excelToUser.getEntranceYear() );
                    }

                    //将学生数据插入到学生表中
                    fileResourceRepository.saveStudent(studentId, excelToUser.getClassNumber(), excelToUser.getGrade(), excelToUser.getStudentNumber(),entranceYear);

                    count++;
                }
            }else if( PARENT_CODE.equals( userType )){
                String pre = "jz_";
                for ( ; i < list.size(); i++ ) {
                    excelToUser = list.get(i);
                    if(excelToUser.getUserName() == null && excelToUser.getGender() == null && excelToUser.getCertificateNumber() == null && excelToUser.getPhoneNumber() == null && excelToUser.getCertificateType() == null &&
                        excelToUser.getNationLity() == null && excelToUser.getNationCode() == null && excelToUser.getMailAddress() == null && excelToUser.getParentFamify() == null && excelToUser.getStudentCertificationNumber() == null &&
                        excelToUser.getStudentCertificationType() == null){
                        continue;
                    }
                    if(excelToUser.getUserName() == null || excelToUser.getGender() == null || excelToUser.getCertificateNumber() == null || excelToUser.getPhoneNumber() == null || excelToUser.getCertificateType() == null){
                        log.info("缺少必填项");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, "缺少必填项", i + EXCEL_NUMBER ));
                    }
                    if(excelToUser.getCertificateType().equals("1") && !UserPropertiesUtil.checkCertificateNumber(excelToUser.getCertificateNumber())){
                        log.info("家长身份证验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( certificateNumberPrompt, "家长" ), i + EXCEL_NUMBER ));
                    }
                    if(!UserPropertiesUtil.checkPhone(excelToUser.getPhoneNumber())){
                        log.info("家长手机号验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( phonePrompt, "家长" ), i + EXCEL_NUMBER ));
                    }
                    if( excelToUser.getMailAddress() !=null && !UserPropertiesUtil.checkMail(excelToUser.getMailAddress())){
                        log.info("家长邮箱验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( emailPrompt, "家长" ), i + EXCEL_NUMBER ));
                    }
                    if ( fileResourceRepository.selectUserCount( excelToUser.getCertificateNumber() ) > 0) {
                        log.info( "家长证件已被使用" );
                        throw new ImportUserFailedException( String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( "%s证件已被使用", "家长" ), i + EXCEL_NUMBER ) );
                    }
                    if( fileResourceRepository.selectPhoneNumber(excelToUser.getPhoneNumber()) > 0){
                        log.info("家长手机号已被使用");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( phonePromptExist, "家长" ), i + EXCEL_NUMBER ));
                    }
                    if( excelToUser.getMailAddress() != null && fileResourceRepository.selectMailAddress(excelToUser.getMailAddress()) > 0){
                        log.info("家长邮箱已被使用");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, String.format( emailPromptExit, "家长" ), i + EXCEL_NUMBER ));
                    }

                    //创建家长数据，准备添加数据
                    UserPassword userPassword = new UserPassword();
                    String secret = Secret.ES256GenerateSecret();
                    String loginName = IDUtil.createID( pre );
                    String parentId = UUID.getUUID();
                    userPassword.setIsDelete( 1 );
                    userPassword.setPassword( "star123!" );
                    userPassword.setUserId( parentId );
                    //查询学生id 并生成家长对应的  家长-学生关系
                    if ( excelToUser.getStudentCertificationNumber() != null && excelToUser.getStudentCertificationType() != null) {
                        if ( excelToUser.getParentFamify() == null ) {
                            log.info( "缺少家长学生关系" );
                            throw new ImportUserFailedException( String.format( WebMessageConstants.SCE_PORTAL_MSG_432, "缺少家长学生关系", i + EXCEL_NUMBER ) );
                        }
                        String studentId = fileResourceRepository.selectUserIdByCertificateNumber( excelToUser.getStudentCertificationNumber() );
                        if(studentId == null){
                            log.info( "暂无该学生信息，无法绑定" );
                            throw new ImportUserFailedException( String.format( WebMessageConstants.SCE_PORTAL_MSG_432, "暂无该学生信息，无法绑定", i + EXCEL_NUMBER ) );
                        }else if(fileResourceRepository.selectUserCountByCertificate(excelToUser.getStudentCertificationNumber(), "1") < 1){
                            log.info("填写的学生证件为非学生角色，不能绑定学生");
                            throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, "填写的学生证件为非学生角色，不能绑定学生", i + EXCEL_NUMBER ));
                        }
                        StudentParentRel studentParentRel = new StudentParentRel();
                        studentParentRel.setRelationShip( excelToUser.getParentFamify() );
                        studentParentRel.setIsMain( 0 );
                        studentParentRel.setParentUserId( parentId );
                        studentParentRel.setStudentUserId( studentId );
                        exportUserRepository.save( studentParentRel );
                    }

                    //插入家长用户
                    if(excelToUser.getCertificateType().equals("1")){
                        fileResourceRepository.saveParent( parentId, excelToUser.getUserName(), excelToUser.getGender(), loginName, PARENT_CODE,
                                excelToUser.getMailAddress(), excelToUser.getCertificateType(), excelToUser.getCertificateNumber(), excelToUser.getPhoneNumber(),
                                UserPropertiesUtil.getBirthDateByCer(excelToUser.getCertificateNumber()), secret, excelToUser.getNationLity(), excelToUser.getNationCode() );
                    }else {
                        fileResourceRepository.saveParentWithoutBirthDate( parentId, excelToUser.getUserName(), excelToUser.getGender(), loginName, PARENT_CODE,
                                excelToUser.getMailAddress(), excelToUser.getCertificateType(), excelToUser.getCertificateNumber(), excelToUser.getPhoneNumber(),
                                secret, excelToUser.getNationLity(), excelToUser.getNationCode() );
                    }

                    userPasswordDao.save( userPassword );
                    count++;
                }
            }
        }catch (ImportUserFailedException e){
            e.printStackTrace();
            throw  new ImportUserFailedException(e.getMsg());
        }catch (Exception e){
            e.printStackTrace();
            return new RestRecord(423, String.format(WebMessageConstants.SCE_PORTAL_MSG_432, "添加数据失败", i + EXCEL_NUMBER));
        }
        return new RestRecord( 200, String.format( success, count ), list.size() );

    }

    /**
     * @Author : lyy
     * @Desc : 根据id获取文件储存路径
     * @Date : 16:54 2018/12/27
     */
    @GetMapping( "/getFileResource" )
    @ResponseBody
    public RestRecord getFileResourceById( @RequestParam( "resourceId" ) Integer resourceId ) {
        Map< String, Object > fileResource = fileResourceRepository.getFileStorePathById( resourceId );
        return new RestRecord( 200, fileResource );
    }

}
