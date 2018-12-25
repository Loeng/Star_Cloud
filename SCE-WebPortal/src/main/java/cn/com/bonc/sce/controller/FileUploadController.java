package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.FileUploadService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 通用文件上传接口
 * @author BTW
 * @version 0.1
 * @since 2018/12/22 14:36
 */
@Slf4j
@Api( value = "通用文件上传接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping("/file-upload")
public class FileUploadController {

    private FileUploadService fileUploadService;

    @Autowired
    public FileUploadController ( FileUploadService fileUploadService ) {
        this.fileUploadService = fileUploadService;
    }

    /**
     * @param multipartFile 上传文件
     * @param fileType 上传文件类型
     * @return 返回对应文件在资源表中ID
     */
    @ApiOperation( value = "文件上传通用接口", notes = "文件上传通用接口", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "file", value = "上传文件", dataType = "file", paramType = "query", required = true ) ,
            @ApiImplicitParam( name = "fileType", dataType = "String", value = "上传文件类型( 图片:pic  软件应用：soft 软件说明文档：document )", paramType = "query", required = true, allowableValues = "pic,soft,document" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping( "" )
    @ResponseBody
    public RestRecord uploadPicture( @RequestParam( "file" ) MultipartFile multipartFile,
                                     @RequestParam( "fileType" ) String fileType ) {
        if ( multipartFile == null || multipartFile.isEmpty() ) {
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_450 );
        }
        return fileUploadService.uploadMultipart( multipartFile, fileType );
    }
}
