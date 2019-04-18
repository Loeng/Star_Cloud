package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.ParentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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

    public Integer selectApplyResult(String currentUserId, String studentId, Integer isMain, String parentId, Integer currentUserIdIsMain){
        return parentMapper.selectApplyResult(currentUserId, studentId, isMain, parentId, currentUserIdIsMain);
    }

    public Integer selectIsMain(String userId, String id){
        return parentMapper.selectIsMain(userId, id);
    }

    public List<Map> getApplyList(String id) {
        return parentMapper.getApplyList(id);
    }

    public int addApplyMain( Map map ){
        return parentMapper.addApplyMain( map );
    }

    public BigDecimal selectApply(Map map){
        return parentMapper.selectApply(map);
    }

    public int updateApplyMain(Map map){
        return parentMapper.updateApplyMain(map);
    }

    public String selectApplyMain(Map map){
        return parentMapper.selectApplyMain(map);
    }

    public int auditApplyMain(Map map){
        return parentMapper.auditApplyMain(map);
    }

    public int delStudentParentRel(Map map){
        return parentMapper.delStudentParentRel(map);
    }

    public int updateStudentParentRel(Map map){
        return parentMapper.updateStudentParentRel(map);
    }

    public String getMainPhone(String studentLoginName){
        return parentMapper.getMainPhone(studentLoginName);
    }

    public BigDecimal selectUserTypeByUserId(String userId){
        return parentMapper.selectUserTypeByUserId(userId);
    }

    public void addStudentParentRel(long id, String parentUserId, String studentUserId, Integer isMain, String relationship){
        parentMapper.addStudentParentRel(id, parentUserId, studentUserId, isMain, relationship);
    }

    public void delAudit(String applyUserId, String targetUserId){
        parentMapper.delAudit(applyUserId, targetUserId);
    }
}
