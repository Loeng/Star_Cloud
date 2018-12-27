package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.FileResourceEntity;
import cn.com.bonc.sce.model.ExcelToUser;
import org.mapstruct.ObjectFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author BTW
 */
@Transactional(rollbackFor = Exception.class)
public interface FileResourceRepository extends JpaRepository< FileResourceEntity, String > {

    /**
     * 文件存储信息保存
     * @param s
     * @param <S>
     * @return
     */
    @Override
    < S extends FileResourceEntity > S save( S s );



    /**
     * 插入用户啊
     * @param
     * @return
     */
    @Modifying
    @Query( nativeQuery = true, value = "insert  into  STARCLOUDPORTAL.SCE_COMMON_USER(USER_ID,USER_NAME,GENDER,LOGIN_NAME,USER_TYPE," +
            "MAIL_ADDRESS,CERTIFICATE_TYPE,CERTIFICATE_NUMBER,PHONE_NUMBER,ADDRESS ) VALUES " +
            "(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)  " )
    int savaAllUserInfo( String id,String userName, String gender ,String loginName
                        ,String userType,String mailAddress,String certificateType
                        , String certificateNumber,String phoneNumber,String address
                      );

    /**
     *@Desc: 根据文件id查询文件存储地址
     *@Param: resourceId
     *@return: String
     *@Author: lyy
     *@date: 2018/12/27
     */
    @Query(value = "SELECT FILE_STORE_PATH FROM STARCLOUDPORTAL.SCE_FILE_RESOURCE where RESOURCE_ID =?1"
            ,nativeQuery = true)
    Map<String,Object> getFileResourceById(Integer resourceId);

}
