package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.StudentDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Charles on 2019/3/28.
 */
@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public RestRecord getParents(String id) {
        return studentDao.getParents(id);
    }

    public RestRecord getApplyList(String id) {
        return studentDao.getApplyList(id);
    }

    public RestRecord audit(String json) {
        return studentDao.audit(json);
    }

    public RestRecord getStudentBasicData(String USER_ID) {
        return studentDao.getStudentBasicData(USER_ID);
    }
}
