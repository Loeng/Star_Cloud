package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.FileResourceDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/4/18.
 */
@Service
@Slf4j
public class FileResourceService {

    @Autowired
    private FileResourceDao fileResourceDao;

    public String getFileStorePath( Integer resourceId ) {
        return fileResourceDao.getFileStorePath( resourceId );
    }
}
