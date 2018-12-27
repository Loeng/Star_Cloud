package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.entity.FileResourceEntity;
import cn.com.bonc.sce.model.ExcelToUser;
import cn.com.bonc.sce.repository.FileResourceRepository;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.utils.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传存储信息保存
 * @author BTW
 */
@Slf4j
@RestController
@RequestMapping( "/file-upload" )
public class FileUploadApiController {

    private FileResourceRepository fileResourceRepository;


    @Autowired
    public FileUploadApiController ( FileResourceRepository fileResourceRepository ) {
        this.fileResourceRepository = fileResourceRepository;
    }

    @PostMapping
    @ResponseBody
    public RestRecord addAppTypeInfo( @RequestParam( "fileType" ) String fileType,
                                      @RequestParam( "fileMappingPath" ) String fileMappingPath,
                                      @RequestParam( "fileStorePath") String fileStorePath ) {
        FileResourceEntity fileResourceEntity = new FileResourceEntity(  );
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
    @PostMapping("/upload-user-info")
    @ResponseBody
    public RestRecord uploadParseExcel(@RequestBody List<ExcelToUser> list) {

        //batchExportUser.save( list );

        for ( ExcelToUser excelToUser:list ) {
            fileResourceRepository.savaAllUserInfo(UUID.getUUID(), excelToUser.getUserName(), excelToUser.getGender(),
                    excelToUser.getLoginName(), excelToUser.getUserType(), excelToUser.getMailAddress()
                    , excelToUser.getCertificateType(), excelToUser.getCertificateNumber(), excelToUser.getPhoneNumber()
                    ,excelToUser.getAddress()
                );
        }
        return  new RestRecord( 0,"上传成功");

    }

    /**
     *@Author : lyy
     *@Desc : 根据id获取文件储存路径
     *@Date : 16:54 2018/12/27
     */
    @PostMapping("/getFileResource")
    @ResponseBody
    public RestRecord getFileResourceById(@RequestParam("resourceId") Integer resourceId) {
        Map<String,Object>  fileResource = fileResourceRepository.getFileResourceById(resourceId);
        return  new RestRecord( 200, fileResource );
    }

}
