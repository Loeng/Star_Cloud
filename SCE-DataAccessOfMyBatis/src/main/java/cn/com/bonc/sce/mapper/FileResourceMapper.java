package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author BTW
 */
@Repository
public interface FileResourceMapper {

    /**
     * 获取文件存储路径
     * @param resourceId
     * @return
     */
    @Select( "SELECT FILE_STORE_PATH FROM STARCLOUDPORTAL.SCE_FILE_RESOURCE WHERE RESOURCE_ID = #{resourceId}" )
    String getFileStorePath(@Param( "resourceId" ) Integer resourceId);
}
