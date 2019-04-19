package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/28.
 */
@Repository
public class StudentDao {
    @Autowired
    private StudentMapper studentMapper;

    public List<Map> getParents(String id) {
        return studentMapper.getParents(id);
    }

    public List<Map> getApplyList(String id) {
        return studentMapper.getApplyList(id);
    }

    public int audit(String id, Integer applyResult, String rejectReason) {
        return studentMapper.audit(id,applyResult,rejectReason);
    }

    public int addRelation(long id, String applyUserId, String bindUserId, String relationship) {
        return studentMapper.addRelation(id,applyUserId,bindUserId,relationship);
    }

    public Map getStudentBasicData(String USER_ID) {
        return studentMapper.getStudentBasicData(USER_ID);
    }
}
