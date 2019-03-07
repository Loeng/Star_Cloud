package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.bean.AgentBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.mapper.AgencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Charles on 2019/3/4.
 */
@Repository
public class AgencyDao {

    @Autowired
    private AgencyMapper agencyMapper;

    public int editActivity(long id,Integer isActivate) {
        return agencyMapper.editActivity(id,isActivate);
    }

    public int editInfo(long id,String agentName,String province,String city, String area){
        return agencyMapper.editInfo(id,agentName,province,city,area);
    }

    public List<SchoolBean> getSchools(long id){
        return agencyMapper.getSchools(id);
    }

    public int delSchoolRel(long agentId, long schoolId) {
        return agencyMapper.delSchoolRel(agentId,schoolId);
    }

    public List<AgentBean> getAgents(String agentName, String grade,String agentArea) {
        return agencyMapper.getAgents(agentName,grade,agentArea);
    }

    public int saveAgent(AgentBean agentBean) {
        return agencyMapper.saveAgent(agentBean);
    }

    public int delAgentUser(long id) {
        return agencyMapper.delAgentUser(id);
    }
}
