package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.StudentDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/28.
 */
@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public List<Map> getParents(String id) {
        return studentDao.getParents(id);
    }

    public List<Map> getApplyList(String id) {
        return studentDao.getApplyList(id);
    }

    public int audit(String id, Integer applyResult, String rejectReason) {
        return studentDao.audit(id,applyResult,rejectReason);
    }


    public int addRelation(long id, String applyUserId, String bindUserId, String relationship) {
        return studentDao.addRelation(id,applyUserId,bindUserId,relationship);
    }

    public Map getStudentBasicData(String USER_ID) {
        return studentDao.getStudentBasicData(USER_ID);
    }

}
