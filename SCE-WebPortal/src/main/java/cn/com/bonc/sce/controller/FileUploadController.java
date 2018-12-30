package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.ExcelToUser;
import cn.com.bonc.sce.model.UploadFileModel;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.FileUploadService;
import cn.com.bonc.sce.tool.ParseExcel;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 通用文件上传接口
 *
 * @author BTW
 * @version 0.1
 * @since 2018/12/22 14:36
 */
@Slf4j
@Api( value = "通用文件上传接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping( "/file-upload" )
public class FileUploadController {

    private FileUploadService fileUploadService;
    private MultipartFile multipartFile;
    private String fileType;

    @Autowired
    public FileUploadController( FileUploadService fileUploadService ) {
        this.fileUploadService = fileUploadService;
    }

    /**
     * @param multipartFile 上传文件
     * @param fileType      上传文件类型
     * @return 返回对应文件在资源表中ID
     */
    @ApiOperation( value = "文件上传通用接口", notes = "文件上传通用接口", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping( "" )
    @ResponseBody
    public RestRecord uploadPicture( @RequestParam( "file" ) @ApiParam( name = "file", value = "上传文件", required = true ) MultipartFile multipartFile,
                                     @RequestParam( "fileType" ) @ApiParam( name = "fileType", value = "文件类型", allowableValues = "pic,soft,document" ) String fileType ) {
        if ( multipartFile == null || multipartFile.isEmpty() ) {
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_450 );
        }
        return fileUploadService.uploadMultipart( multipartFile, fileType );
    }

    /**
     * 批量上传Excel解析用户数据
     *
     * @return 返回成功与否
     */
    @ApiOperation( value = "文件上传解析Excel", notes = "文件上传解析Excel", httpMethod = "POST" )

    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping( "/upload-user-info" )
    @ResponseBody
    public RestRecord uploadParseExcel( @ModelAttribute UploadFileModel uploadFileModel ) {
        if ( uploadFileModel.getMultipartFile() == null || uploadFileModel.getMultipartFile().isEmpty()
                || uploadFileModel.getFileType().isEmpty() || uploadFileModel.getUserType().isEmpty() ) {
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_450 );
        }
        try {
            List< ExcelToUser > list = ParseExcel.importExcel( uploadFileModel.getMultipartFile(), 1, 1, ExcelToUser.class );

            log.info( "解析Excel成功" );

            fileUploadService.uploadUserInfo( list, uploadFileModel.getUserType() );

            log.info( "上传用户成功" );
        }catch ( Exception e ){
            return new RestRecord( 423, WebMessageConstants.SCE_PORTAL_MSG_423 );
        }

        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    /**
     * @param multipartFileAll 上传文件
     * @param fileType         上传文件类型
     * @return 返回对应文件在资源表中ID
     */
    @ApiOperation( value = "多文件上传通用接口", notes = "多文件上传通用接口", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping( "/all" )
    @ResponseBody
    public RestRecord uploadPictureAll( @RequestParam( "file" ) @ApiParam( name = "file", value = "上传文件", required = true ) MultipartFile[] multipartFileAll,
                                        @RequestParam( "fileType" ) @ApiParam( name = "filType", value = "文件类型", allowableValues = "pic,soft,document" ) String fileType ) {
        if ( multipartFileAll == null || multipartFileAll.length == 0 ) {
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_450 );
        }
        return fileUploadService.uploadMultipartAll( multipartFileAll, fileType );
    }

}
