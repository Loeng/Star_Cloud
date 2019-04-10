package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.FileResourceMapper;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author BTW
 */
@Repository
public class FileResourceDao {

    @Autowired
    private FileResourceMapper fileResourceMapper;

    public String getFileStorePath( Integer resourceId ) {
        return fileResourceMapper.getFileStorePath( resourceId );
    }
}
