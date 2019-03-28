package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.Param;

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
}
