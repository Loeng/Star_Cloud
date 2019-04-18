package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.FileResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by Administrator on 2019/4/18.
 */
@Repository
public interface FileResourceDao extends JpaRepository <FileResourceEntity,Integer>{

    @Query( value = "SELECT FILE_STORE_PATH AS \"fileStorePath\" FROM STARCLOUDPORTAL.SCE_FILE_RESOURCE WHERE RESOURCE_ID = ?1", nativeQuery = true )
    Map< String, Object > getFileStorePath(Integer resourceId);
}
