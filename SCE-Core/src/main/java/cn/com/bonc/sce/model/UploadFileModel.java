package cn.com.bonc.sce.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2018/12/30 11:53
 * @Description:
 */
public class UploadFileModel {

    MultipartFile multipartFile;

    String fileType;

    String userType;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile( MultipartFile multipartFile ) {
        this.multipartFile = multipartFile;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType( String fileType ) {
        this.fileType = fileType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType( String userType ) {
        this.userType = userType;
    }
}
