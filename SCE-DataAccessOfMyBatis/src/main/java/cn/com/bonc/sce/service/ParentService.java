package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.ParentDao;
import cn.com.bonc.sce.rest.RestRecord;
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
public class ParentService {

    @Autowired
    private ParentDao parentDao;

    public List<Map> getStudentList(String id) {
        return parentDao.getStudentList(id);
    }

    public int bindStudent(Long id, String applyUserId, String targetUserId, Integer applyType, String bindUserId, String relationship) {
        return parentDao.bindStudent(id,applyUserId,targetUserId,applyType,bindUserId,relationship);
    }

    public int unbind(String parentId, String studentId) {
        return parentDao.unbind(parentId,studentId);
    }

    public List<Map> getParentList(String id) {
        return parentDao.getParentList(id);
    }

    public List<Map> getApplyList(String id) {
        return parentDao.getApplyList(id);
    }
}
