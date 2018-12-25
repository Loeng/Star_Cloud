package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author BTW
 * @version 0.1
 * @since 2018/12/22 16:56
 */
@FeignClient( "sce-data-access" )
public interface FileUploadDao {

    /**
     * 文件上传保存信息存入数据库
     * @param fileType 上传文件类型
     * @param fileStorePath 文件物理存储位置
     * @param fileMappingPath 文件映射地址
     * @return
     */
    @RequestMapping( value = "/file-upload", method = RequestMethod.POST )
    RestRecord saveUploadFile( @RequestParam( "fileType" ) String fileType,
                               @RequestParam( "fileStorePath") String fileStorePath,
                               @RequestParam( "fileMappingPath" ) String fileMappingPath );
}
