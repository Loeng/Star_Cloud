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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
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

    private static final String certificateNumberPrompt = "请检查身份证信息填写是否正确";

    private static final String certificateNumberExist = "身份证已被使用";

    private static final String phonePrompt = "手机号填写不正确";

    private static final String emailPrompt = "邮箱填写不正确";

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
     *
     * @return 返回是否成功
     */
    @PostMapping( "/upload-user-info" )
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public RestRecord uploadParseExcel(@RequestBody List< ExcelToUser > list, @RequestParam( "userType" ) String userType, @CurrentUserId String currentUserId) throws ImportUserFailedException{

        //通过用户id查询所属学校id
        BigDecimal orgazizationId = fileResourceRepository.selectOrganizationId(currentUserId);

        ExcelToUser excelToUser;
        int i = -1;
        try {
            if ( TEACHER_PRE.equals( userType ) ) {
                String pre = "js_";
                for ( i = 0; i < list.size(); i++ ) {
                    excelToUser = list.get(i);
                    if(excelToUser.getUserName() == null){
                        continue;
                    }
                    if(!UserPropertiesUtil.checkCertificateNumber(excelToUser.getCertificateNumber())){
                        log.info("身份证验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, certificateNumberPrompt, i+3 ));
                    }
                    if(!UserPropertiesUtil.checkPhone(excelToUser.getPhoneNumber())){
                        log.info("手机号验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, phonePrompt, i+3 ));
                    }
                    if(!UserPropertiesUtil.checkMail(excelToUser.getMailAddress())){
                        log.info("邮箱验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, emailPrompt, i+3 ));
                    }
                    if(fileResourceRepository.selectUserCount(excelToUser.getCertificateNumber()) != null){
                        log.info("身份证已被使用");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, certificateNumberExist, i+3 ));
                    }

                    UserPassword userPassword = new UserPassword();
                    String secret = Secret.generateSecret();
                    String loginName = IDUtil.createID( pre );
                    String userId = UUID.getUUID();
                    userPassword.setIsDelete( 1 );
                    userPassword.setPassword( "star123!" );
                    userPassword.setUserId( userId );
                    fileResourceRepository.savaAllUserInfo( userId, excelToUser.getUserName(), excelToUser.getGender(),
                            loginName, userType, excelToUser.getMailAddress(), CERTIFICATE_TYPE, excelToUser.getCertificateNumber(),
                            excelToUser.getPhoneNumber(), excelToUser.getAddress(), orgazizationId,
                            UserPropertiesUtil.getBirthDateByCer(excelToUser.getCertificateNumber()), secret );
                    userPasswordDao.save( userPassword );

                    //插入数据到教师表
                    fileResourceRepository.saveTeacher(userId, orgazizationId.toString(), excelToUser.getPosition(), excelToUser.getSubject(), excelToUser.getSchoolAge(),
                            excelToUser.getNameSpell(), excelToUser.getNationCode(), excelToUser.getPoliticsStatus(), excelToUser.getWorkNumber(), excelToUser.getMarriAgeCode(),
                            excelToUser.getBirthPlace(), excelToUser.getAccountAreaCode(), UserPropertiesUtil.getDateByExcelDate(excelToUser.getSchoolTime()));

                }
            } else if ( STUDENT_PRE.equals( userType ) ) {
                String pre = "xs_";
                for (i = 0; i < list.size(); i++ ) {
                    excelToUser = list.get(i);
                    if(excelToUser.getUserName() == null){
                        continue;
                    }
                    if(!UserPropertiesUtil.checkCertificateNumber(excelToUser.getCertificateNumber())){
                        log.info("身份证验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, certificateNumberPrompt, i+3 ));
                    }
                    if(!UserPropertiesUtil.checkPhone(excelToUser.getPhoneNumber())){
                        log.info("手机号验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, phonePrompt, i+3 ));
                    }
                    if(!UserPropertiesUtil.checkMail(excelToUser.getMailAddress())){
                        log.info("邮箱验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, emailPrompt, i+3 ));
                    }
                    if(fileResourceRepository.selectUserCount(excelToUser.getCertificateNumber()) != null){
                        log.info("身份证已被使用");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, certificateNumberExist, i+3 ));
                    }

                    //生成学生用户，并自动生成家长用户关联学生
                    UserPassword userPassword = new UserPassword();
                    String secret = Secret.generateSecret();
                    String loginName = IDUtil.createID( pre );
                    String studentId = UUID.getUUID();
                    userPassword.setIsDelete( 1 );
                    userPassword.setPassword( "star123!" );
                    userPassword.setUserId( studentId );
                    fileResourceRepository.savaAllUserInfo( studentId, excelToUser.getUserName(), excelToUser.getGender(),
                            loginName, userType, excelToUser.getMailAddress(), CERTIFICATE_TYPE, excelToUser.getCertificateNumber(),
                            excelToUser.getPhoneNumber(), excelToUser.getAddress(), orgazizationId,
                            UserPropertiesUtil.getBirthDateByCer(excelToUser.getCertificateNumber()), secret );
                    userPasswordDao.save( userPassword );

                    //生成家长用户，并关联学生
                    UserPassword parentPassword = new UserPassword();
                    String secretParent = Secret.generateSecret();
                    String loginParentName = IDUtil.createID( "zj_" );
                    String parentId = UUID.getUUID();
                    parentPassword.setIsDelete( 1 );
                    parentPassword.setPassword( "star123!" );
                    parentPassword.setUserId( parentId );
                    fileResourceRepository.saveParentOfUser(parentId, loginParentName, PARENT_CODE, secretParent);
                    userPasswordDao.save( parentPassword );

                    //建立学生家长关系
                    StudentParentRel studentParentRel = new StudentParentRel();
                    studentParentRel.setIsMain( 1 );
                    studentParentRel.setParentUserId( parentId );
                    studentParentRel.setStudentUserId( studentId );
                    exportUserRepository.save( studentParentRel);

                    //将学生数据插入到学生表中
                    fileResourceRepository.saveStudent(studentId, excelToUser.getClassNumber(), excelToUser.getGrade(), excelToUser.getStudentNumber(),
                            excelToUser.getHealthCode(), excelToUser.getNationLity(), excelToUser.getMarriAgeCode(), excelToUser.getAccountAreaCode(),
                            excelToUser.getLoginName(), excelToUser.getBirthPlace(), excelToUser.getNativePlace(), excelToUser.getNationCode(),
                            excelToUser.getPoliticsStatus(), excelToUser.getAccountClassCode(), UserPropertiesUtil.getDateByExcelDate(excelToUser.getEntranceYear()),
                            excelToUser.getLearnSpecialty());

                    //向学生账号发送一条消息告知家长账号
                    Message message = new Message();
                    message.setContent( "恭喜您，申请主家长账号分配成功！账号："+loginParentName +" 密码：star123!");
                    message.setSourceId("0");
                    message.setTargetId(studentId );
                    messageService.insertMessage(  message);
                }
            }else if( PARENT_CODE.equals( userType )){
                String pre = "jz_";
                for (i = 0; i < list.size(); i++ ) {
                    excelToUser = list.get(i);
                    if(excelToUser.getUserName() == null){
                        continue;
                    }
                    if(!UserPropertiesUtil.checkCertificateNumber(excelToUser.getCertificateNumber())){
                        log.info("家长身份证验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, certificateNumberPrompt, i+3 ));
                    }
                    if(!UserPropertiesUtil.checkCertificateNumber(excelToUser.getStudentCertificationNumber())){
                        log.info("学生身份证验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, certificateNumberPrompt, i+3 ));
                    }
                    if(!UserPropertiesUtil.checkPhone(excelToUser.getPhoneNumber())){
                        log.info("手机号验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, phonePrompt, i+3 ));
                    }
                    if(!UserPropertiesUtil.checkMail(excelToUser.getMailAddress())){
                        log.info("邮箱验证未通过");
                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, emailPrompt, i+3 ));
                    }
//                    if(excelToUser.getCertificateNumber().equals(excelToUser.getStudentCertificationNumber()) && fileResourceRepository.selectUserCount(excelToUser.getCertificateNumber()) > 0){
//                        log.info("身份证已被使用");
//                        throw new ImportUserFailedException(String.format( WebMessageConstants.SCE_PORTAL_MSG_432, certificateNumberExist, i+3 ));
//                    }

                    //插入家长用户
                    UserPassword userPassword = new UserPassword();
                    String secret = Secret.generateSecret();
                    String loginName = IDUtil.createID( pre );
                    String parentId = UUID.getUUID();
                    userPassword.setIsDelete( 1 );
                    userPassword.setPassword( "star123!" );
                    userPassword.setUserId( parentId );
                    fileResourceRepository.saveParent(parentId, excelToUser.getUserName(), excelToUser.getGender(), loginName, PARENT_CODE,
                            excelToUser.getMailAddress(), CERTIFICATE_TYPE, excelToUser.getCertificateNumber(), excelToUser.getPhoneNumber(),
                            excelToUser.getAddress(), UserPropertiesUtil.getBirthDateByCer(excelToUser.getCertificateNumber()), secret);
                    userPasswordDao.save( userPassword );

                    //插入数据到家长表
                    fileResourceRepository.saveParent(parentId, excelToUser.getFimilyRole());

                    //查询学生id 并生成家长对应的  家长-学生关系
                    String studentId = fileResourceRepository.selectUserCount(excelToUser.getStudentCertificationNumber());
                    fileResourceRepository.saveStudentParentRel(idWorker.nextId(), parentId, studentId);

                }
            }
        }catch (ImportUserFailedException e){
            e.printStackTrace();
            throw  new ImportUserFailedException(e.getMsg());
        }catch (Exception e){
            e.printStackTrace();
            return new RestRecord(423, String.format(WebMessageConstants.SCE_PORTAL_MSG_432, "添加数据失败", i+3));
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, list.size() );

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
