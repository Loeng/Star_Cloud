package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/28.
 */
public interface ParentMapper {

    List<Map> getStudentList(@Param("id") String id);

    int bindStudent(@Param("id") Long id, @Param("applyUserId") String applyUserId,
                    @Param("targetUserId")String targetUserId, @Param("applyType")Integer applyType,
                    @Param("bindUserId")String bindUserId, @Param("relationship")String relationship);

    int unbind(@Param("parentId") String parentId, @Param("studentId") String studentId);

    List<Map> getParentList(@Param("userId") String userId, @Param("id") String id, @Param("isMain") int isMain);

    Integer selectIsMain(@Param("userId") String userId, @Param("id") String id);

    List<Map> getApplyList(@Param("id") String id);

    int addApplyMain( Map map );

    BigDecimal selectApply(Map map);

    int updateApplyMain(Map map);

    String selectApplyMain(Map map);

    int auditApplyMain(Map map);

    int delStudentParentRel(Map map);

    int updateStudentParentRel(Map map);

    String getMainPhone(@Param("studentLoginName") String studentLoginName);

    BigDecimal selectUserTypeByUserId(@Param("userId") String userId);

    void addStudentParentRel(@Param("id") long id, @Param("parentUserId") String parentUserId,
                             @Param("studentUserId") String studentUserId,@Param("isMain") Integer isMain,@Param("relationship") String relationship);

    void delAudit(@Param("applyUserId") String applyUserId, @Param("targetUserId") String targetUserId );
}
