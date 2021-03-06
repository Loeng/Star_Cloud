package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.FileUploadDao;
import cn.com.bonc.sce.dao.UserDao;
import cn.com.bonc.sce.exception.ImportUserFailedException;
import cn.com.bonc.sce.model.ExcelToUser;
import cn.com.bonc.sce.model.User;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.FileUploadUtil;
import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author BTW
 * @version 0.1
 * @since 2018/12/22 14:46
 */
@Slf4j
@Service
public class FileUploadService {

    @Value( "${fileUpload.image}" )
    String picFileStorePath;

    @Value( "${fileUpload.soft}" )
    String softFileStorePath;

    @Value( "${fileUpload.document}" )
    String documentFileStorePath;

    @Value( "${fileUpload.war}" )
    String warFileStorePath;

    @Value( "${fileUpload.mapping.image}" )
    String picFileMappingPath;

    @Value( "${fileUpload.mapping.soft}" )
    String softFileMappingPath;

    @Value( "${fileUpload.mapping.document}" )
    String documentFileMappingPath;

    @Value( "${fileUpload.mapping.war}" )
    String warFileMappingPath;

    private FileUploadDao fileUploadDao;

    private UserDao userDao;


    @Autowired
    public FileUploadService( FileUploadDao fileUploadDao, UserDao userDao ) {
        this.fileUploadDao = fileUploadDao;
        this.userDao = userDao;
    }

    public RestRecord uploadMultipart( MultipartFile multipartFile, String fileType ) {
        try {
            byte[] bytes = multipartFile.getBytes();
            String originFileName = multipartFile.getOriginalFilename();
            String suffixName = FileUploadUtil.getFileSuffix( originFileName );
            String prefixName = FileUploadUtil.getFilePrefix( originFileName );
            String date = new SimpleDateFormat( "yyyyMMddHHmmss" ).format( new Date() );
            String saveFileName = prefixName + date + suffixName;

            String fileStorePath;
            String fileMappingPath;
            String fileSavePath;
            switch ( fileType ) {
                case "pic":
                    fileStorePath = picFileStorePath + File.separator + saveFileName;
                    fileMappingPath = picFileMappingPath + File.separator + saveFileName;
                    fileSavePath = picFileStorePath;
                    break;

                case "soft":
                    fileStorePath = softFileStorePath + File.separator + saveFileName;
                    fileMappingPath = softFileMappingPath + File.separator + saveFileName;
                    fileSavePath = softFileStorePath;
                    break;

                case "document":
                    fileStorePath = documentFileStorePath + File.separator + saveFileName;
                    fileMappingPath = documentFileMappingPath + File.separator + saveFileName;
                    fileSavePath = documentFileStorePath;
                    break;

                case "war":
                    fileStorePath = warFileStorePath + File.separator + saveFileName;
                    fileMappingPath = warFileMappingPath + File.separator + saveFileName;
                    fileSavePath = warFileStorePath;
                    break;

                default:
                    return new RestRecord( 500, WebMessageConstants.SCE_PORTAL_MSG_452 );
            }

            boolean status = FileUploadUtil.uploadFile( bytes, saveFileName, fileSavePath );

            if ( status ) {
                RestRecord restRecord = fileUploadDao.saveUploadFile( fileType, fileStorePath, fileMappingPath );
                return restRecord;
            } else {
                return new RestRecord( 560, WebMessageConstants.SCE_PORTAL_MSG_500 );
            }
        } catch ( IOException e ) {
            return new RestRecord( 500, WebMessageConstants.SCE_PORTAL_MSG_451 );
        }
    }

    public RestRecord uploadUserInfo( List< ExcelToUser > list, String userType, String currentUserId ) throws ImportUserFailedException {
        //根据currentUserId判断是否是管理员
        User user = userDao.getUserById( currentUserId );
        //userType为0是后台管理员
        if ( user.getUserType() == 0 ) {
            return fileUploadDao.uploadUserInfoByAdmin( list, userType, currentUserId );
        } else {
            return fileUploadDao.uploadUserInfo( list, userType, currentUserId );
        }


    }

    public RestRecord uploadMultipartAll( MultipartFile[] multipartFileAll, String fileType ) {
        Object[] result = new Object[ multipartFileAll.length ];
        boolean flag = false;
        for ( int i = 0; i < multipartFileAll.length; i++ ) {
            MultipartFile multipartFile = multipartFileAll[ i ];
            try {
                byte[] bytes = multipartFile.getBytes();
                String originFileName = multipartFile.getOriginalFilename();
                String suffixName = FileUploadUtil.getFileSuffix( originFileName );
                String saveFileName = UUID.randomUUID().toString().replace( "-", "" ) + suffixName;

                String fileStorePath;
                String fileMappingPath;
                String fileSavePath;
                switch ( fileType ) {
                    case "pic":
                        fileStorePath = picFileStorePath + File.separator + saveFileName;
                        fileMappingPath = picFileMappingPath + File.separator + saveFileName;
                        fileSavePath = picFileStorePath;
                        break;

                    case "soft":
                        fileStorePath = softFileStorePath + File.separator + saveFileName;
                        fileMappingPath = softFileMappingPath + File.separator + saveFileName;
                        fileSavePath = softFileStorePath;
                        break;

                    case "document":
                        fileStorePath = documentFileStorePath + File.separator + saveFileName;
                        fileMappingPath = documentFileMappingPath + File.separator + saveFileName;
                        fileSavePath = documentFileStorePath;
                        break;

                    case "war":
                        fileStorePath = warFileStorePath + File.separator + saveFileName;
                        fileMappingPath = warFileMappingPath + File.separator + saveFileName;
                        fileSavePath = warFileStorePath;
                        break;

                    default:
                        result[ i ] = WebMessageConstants.SCE_PORTAL_MSG_452;
                        continue;
                }

                boolean status = FileUploadUtil.uploadFile( bytes, saveFileName, fileSavePath );

                if ( status ) {
                    flag = true;
                    RestRecord restRecord = fileUploadDao.saveUploadFile( fileType, fileStorePath, fileMappingPath );
                    result[ i ] = restRecord.getMsg() == null ? restRecord.getData() : restRecord.getMsg();
                } else {
                    result[ i ] = WebMessageConstants.SCE_PORTAL_MSG_500;
                }
            } catch ( IOException e ) {
                result[ i ] = WebMessageConstants.SCE_PORTAL_MSG_501;
            }
        }
        return new RestRecord( flag ? 200 : 500, result );
    }

    public RestRecord getFileResourceById( Integer resourceId ) {
        return fileUploadDao.fileUploadDao( resourceId );
    }
}
