package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.FileResourceDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2019/4/18.
 */
@Slf4j
@Service
public class FileResourceService {

    @Autowired
    FileResourceDao fileResourceDao;

    public Map< String, Object > getFileStorePath(Integer resourceId){
        return fileResourceDao.getFileStorePath(resourceId);
    }
}
