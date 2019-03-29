package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.ParentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/28.
 */
@Repository
public class ParentDao {

    @Autowired
    private ParentMapper parentMapper;

    public List<Map> getStudentList(String id) {
        return parentMapper.getStudentList(id);
    }

    public int bindStudent(Long id, String applyUserId, String targetUserId, Integer applyType, String bindUserId, String relationship) {
        return parentMapper.bindStudent(id,applyUserId,targetUserId,applyType,bindUserId,relationship);
    }

    public int unbind(String parentId, String studentId) {
        return parentMapper.unbind(parentId,studentId);
    }

    public List<Map> getParentList(String id) {
        return parentMapper.getParentList(id);
    }

    public List<Map> getApplyList(String id) {
        return parentMapper.getApplyList(id);
    }
}
