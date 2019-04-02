package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/28.
 */
public interface StudentMapper {
    List<Map> getParents(@Param("id") String id);

    List<Map> getApplyList(@Param("id") String id);

    int audit(@Param("id") String id, @Param("applyResult") Integer applyResult, @Param("rejectReason") String rejectReason);

    int addRelation(@Param("id") long id, @Param("applyUserId") String applyUserId, @Param("bindUserId") String bindUserId, @Param("relationship") String relationship);
}
