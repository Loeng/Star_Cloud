package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.ParentDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Charles on 2019/3/28.
 */
@Service
@Slf4j
public class ParentService {

    @Autowired
    private ParentDao parentDao;

    public RestRecord getStudentList(String id) {
        return parentDao.getStudentList(id);
    }

    public RestRecord bindStudent(String json) {
        return parentDao.bindStudent(json);
    }

    public RestRecord unbind(String parentId, String studentId) {
        return parentDao.unbind(parentId,studentId);
    }
}
