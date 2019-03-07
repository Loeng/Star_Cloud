package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.AgentBean;
import cn.com.bonc.sce.bean.SchoolBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Charles on 2019/3/4.
 */
public interface AgencyMapper {
    int editActivity(@Param( "id" ) Integer id,@Param( "isActivate" ) Integer isActivate);

    int editInfo(@Param("id") Integer id,@Param("agentName") String agentName,
                 @Param("province") String province,@Param("city")String city, @Param("area")String area);

    List<SchoolBean> getSchools(@Param("id") Integer id);

    int delSchoolRel(@Param("agentId") Integer agentId, @Param("schoolId")Integer schoolId);

    List<AgentBean> getAgents(@Param("agentName")String agentName, @Param("grade")String grade,@Param("agentArea") String agentArea);

    int saveAgent( AgentBean agentBean);
}
