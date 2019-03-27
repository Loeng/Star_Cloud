package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.AgentBean;
import cn.com.bonc.sce.bean.SchoolBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/4.
 */
public interface AgencyMapper {
    int editActivity(@Param( "id" ) long id,@Param( "isActivate" ) Integer isActivate);

    int editInfo(@Param("id") long id,@Param("agentName") String agentName,
                 @Param("province") String province,@Param("city")String city, @Param("area")String area);

    List<SchoolBean> getSchools(@Param("id") long id);

    int delSchoolRel(@Param("agentId") long agentId, @Param("schoolId")long schoolId);

    List<AgentBean> getAgents(@Param("agentName")String agentName, @Param("grade")String grade,@Param("agentArea") String agentArea);

    int saveAgent( AgentBean agentBean);

    int delAgentUser(@Param("id") String id);

    AgentBean getInfo(@Param("id") long id);

}
