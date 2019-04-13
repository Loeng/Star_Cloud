package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.ParentDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    @Autowired
    private IdWorker idWorker;

    public List<Map> getStudentList(String id) {
        return parentDao.getStudentList(id);
    }

    public int bindStudent(Long id, String applyUserId, String targetUserId, Integer applyType, String bindUserId, String relationship) {
        return parentDao.bindStudent(id,applyUserId,targetUserId,applyType,bindUserId,relationship);
    }

    public int unbind(String parentId, String studentId) {
        return parentDao.unbind(parentId,studentId);
    }

    public List<Map> getParentList(String userId, String id) {
        Integer isMain = parentDao.selectIsMain(userId, id);
        if(isMain == null){
            return null;
        }
        return parentDao.getParentList(userId, id, isMain);
    }

    public List<Map> getApplyList(String id) {
        return parentDao.getApplyList(id);
    }

    @Transactional( isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class )
    public RestRecord applyMain(String userId, Map map){
        map.put("userId", userId);
        String applyResult = parentDao.selectApplyMain(map);
        if(applyResult != null){
            return new RestRecord( 435, String.format( WebMessageConstants.SCE_PORTAL_MSG_435, "已提交申请" ));
        }
        BigDecimal id = parentDao.selectApply(map);
        if(id == null){
            map.put("id", idWorker.nextId());
            parentDao.addApplyMain( map );
        }else {
            parentDao.updateApplyMain(map);
        }

        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    @Transactional
    public RestRecord auditApplyMain(String userId, Map map){
        map.put("userId", userId);
        parentDao.auditApplyMain(map);
        if(map.get("audit").toString().equals("1")){
            //变更学生的监护人
            parentDao.delStudentParentRel(map);
            parentDao.updateStudentParentRel(map);
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    public RestRecord getMainPhone(String userId, String studentLoginName){
        BigDecimal userType = parentDao.selectUserTypeByUserId(userId);
        if(userType.intValue() != 5){
            return new RestRecord( 436, WebMessageConstants.SCE_PORTAL_MSG_436 );
        }
        String phone = parentDao.getMainPhone(studentLoginName);
        if( phone == null){
            return new RestRecord( 153, WebMessageConstants.SCE_WEB_MSG_153 );
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, phone );
    }

    public void addStudentParentRel(long id, String parentUserId, String studentUserId, Integer isMain, String relationship){
        parentDao.addStudentParentRel(id, parentUserId, studentUserId, isMain, relationship);
    }

    public void delAudit(String applyUserId, String targetUserId){
        parentDao.delAudit(applyUserId, targetUserId);
    }

}
