package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.model.ExcelToUser;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author BTW
 * @version 0.1
 * @since 2018/12/22 16:56
 */
@FeignClient( "sce-data-access" )
public interface FileUploadDao {

    /**
     * 文件上传保存信息存入数据库
     *
     * @param fileType        上传文件类型
     * @param fileStorePath   文件物理存储位置
     * @param fileMappingPath 文件映射地址
     * @return
     */
    @RequestMapping( value = "/file-upload", method = RequestMethod.POST )
    RestRecord saveUploadFile( @RequestParam( "fileType" ) String fileType,
                               @RequestParam( "fileStorePath" ) String fileStorePath,
                               @RequestParam( "fileMappingPath" ) String fileMappingPath );


    /**
     * 学校人员管理批量导入
     * 文件上传用户信息保存至user表
     *
     * @param list     用户数据
     * @param userType 用户类型
     * @return
     */
    @RequestMapping( value = "/file-upload/upload-user-info", method = RequestMethod.POST )
    RestRecord uploadUserInfo( @RequestBody List< ExcelToUser > list, @RequestParam( "userType" ) String userType, @RequestParam( "currentUserId" ) String currentUserId );

    /**
     * 后台用户管理-批量导入用户信息
     * 文件上传用户信息保存至user表
     *
     * @param list     用户数据
     * @param userType 用户类型
     * @return
     */
    @RequestMapping( value = "/user-info-upload/upload-user-info", method = RequestMethod.POST )
    RestRecord uploadUserInfoByAdmin( @RequestBody List< ExcelToUser > list, @RequestParam( "userType" ) String userType, @RequestParam( "currentUserId" ) String currentUserId );

    /**
     * 根据id获取文件储存路径
     *
     * @param resourceId
     * @return
     */
    @RequestMapping( value = "/file-upload/getFileResource", method = RequestMethod.GET )
    RestRecord fileUploadDao( @RequestParam( "resourceId" ) Integer resourceId );
}
