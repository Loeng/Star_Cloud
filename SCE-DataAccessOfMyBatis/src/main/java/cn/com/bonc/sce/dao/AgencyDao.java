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

    public int editActivity(Integer id,Integer isActivate) {
        return agencyMapper.editActivity(id,isActivate);
    }

    public int editInfo(Integer id,String agentName,String province,String city, String area){
        return agencyMapper.editInfo(id,agentName,province,city,area);
    }

    public List<SchoolBean> getSchools(Integer id){
        return agencyMapper.getSchools(id);
    }

    public int delSchoolRel(Integer agentId, Integer schoolId) {
        return agencyMapper.delSchoolRel(agentId,schoolId);
    }

    public List<AgentBean> getAgents(String agentName, Integer isActivate) {
        return agencyMapper.getAgents(agentName,isActivate);
    }
}
