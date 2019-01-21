package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.AccountDao;
import cn.com.bonc.sce.dao.UserPasswordDao;
import cn.com.bonc.sce.entity.Account;
import cn.com.bonc.sce.entity.FileResourceEntity;
import cn.com.bonc.sce.entity.StudentParentRel;
import cn.com.bonc.sce.entity.UserPassword;
import cn.com.bonc.sce.model.ExcelToUser;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.repository.ExportUserRepository;
import cn.com.bonc.sce.repository.FileResourceRepository;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IDUtil;
import cn.com.bonc.sce.utils.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    public static final String STUDENT_PRE = "1";

    public static final String TEACHER_PRE = "2";

    public static final String PARENT_CODE = "2";

    public static final String CERTIFICATE_TYPE = "1";

    @Autowired
    public FileUploadApiController( FileResourceRepository fileResourceRepository, UserPasswordDao userPasswordDao ,ExportUserRepository exportUserRepository) {
        this.fileResourceRepository = fileResourceRepository;
        this.userPasswordDao = userPasswordDao;
        this.exportUserRepository = exportUserRepository;
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
    public RestRecord uploadParseExcel( @RequestBody List< ExcelToUser > list, @RequestParam( "userType" ) String userType ) {


        if ( TEACHER_PRE.equals( userType ) ) {
            String pre = "js_";
            for ( ExcelToUser excelToUser : list ) {
                UserPassword userPassword = new UserPassword();
                String secret = Secret.generateSecret();
                String loginName = IDUtil.createID( pre );
                String userId = UUID.getUUID();
                userPassword.setIsDelete( 1 );
                userPassword.setPassword( "star123!" );
                userPassword.setUserId( userId );
                fileResourceRepository.savaAllUserInfo( userId, excelToUser.getUserName(), excelToUser.getGender(),
                        loginName, userType, excelToUser.getMailAddress(), CERTIFICATE_TYPE, excelToUser.getCertificateNumber(),
                        excelToUser.getPhoneNumber(), excelToUser.getAddress(), excelToUser.getOrganizationId(), secret );
                userPasswordDao.save( userPassword );
            }
        } else if ( STUDENT_PRE.equals( userType ) ) {
            String pre = "xs_";
            for ( ExcelToUser excelToUser : list ) {
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
                        excelToUser.getPhoneNumber(), excelToUser.getAddress(), excelToUser.getOrganizationId(), secret );
                userPasswordDao.save( userPassword );

                //生成家长用户，并关联学生
                UserPassword parentPassword = new UserPassword();
                String secretParent = Secret.generateSecret();
                String loginParentName = IDUtil.createID( "zj_" );
                String parentId = UUID.getUUID();
                parentPassword.setIsDelete( 1 );
                parentPassword.setPassword( "star123!" );
                parentPassword.setUserId( parentId );
                fileResourceRepository.savaAllUserInfo( parentId, "", "",
                        loginParentName, PARENT_CODE, "", "", "",
                        "", "", "", secretParent );
                userPasswordDao.save( parentPassword );

                //建立学生家长关系
                StudentParentRel studentParentRel = new StudentParentRel();
                studentParentRel.setIsMain( 1 );
                studentParentRel.setParentUserId( parentId );
                studentParentRel.setStudentUserId( studentId );
                exportUserRepository.save( studentParentRel);

            }
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
