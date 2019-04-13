package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.ParentDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    public RestRecord getParentList(String userId, String id) {
        return parentDao.getParentList(userId, id);
    }

    public RestRecord getApplyList(String id) {
        return parentDao.getApplyList(id);
    }

    public RestRecord applyMain(String userId, Map map){
        if(map.get("targetUserId") == null || map.get("studentUserId") == null ){
            return new RestRecord( 431, String.format( WebMessageConstants.SCE_PORTAL_MSG_431, "必要" ) );
        }
        return parentDao.applyMain(userId, map);
    }

    public RestRecord auditApplyMain(String userId, Map map){
        if(map.get("applyUserId") == null || map.get("studentUserId") == null || map.get("audit") == null ){
            return new RestRecord( 431, String.format( WebMessageConstants.SCE_PORTAL_MSG_431, "必要" ) );
        }
        return parentDao.auditApplyMain(userId, map);
    }

    public RestRecord getMainPhone(String userId, String studentLoginName){
        return parentDao.getMainPhone(userId, studentLoginName);
    }
}
