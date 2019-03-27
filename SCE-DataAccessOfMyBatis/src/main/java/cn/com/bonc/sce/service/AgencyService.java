package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.AgentBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.dao.AgencyDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/4.
 */
@Service
@Slf4j
public class AgencyService {

    @Autowired
    private AgencyDao agencyDao;

    public int editActivity(long id, Integer isActivate){
        return agencyDao.editActivity(id,isActivate);
    }

    public int editInfo(long id,String agentName, String province, String city, String area){
        return agencyDao.editInfo(id,agentName,province,city,area);
    }

    public List<SchoolBean> getSchools(long id){
        return agencyDao.getSchools(id);
    }

    public int delSchoolRel(long agentId, long schoolId) {
        return agencyDao.delSchoolRel(agentId,schoolId);
    }

    public List<AgentBean> getAgents(String agentName,String grade,String agentArea){
        return agencyDao.getAgents(agentName,grade,agentArea);
    }

    public int saveAgent(AgentBean agentBean) {
        return agencyDao.saveAgent(agentBean);
    }

    public int delAgentUser(String id) {
        return agencyDao.delAgentUser(id);
    }

    public AgentBean getInfo(long id) {
        return agencyDao.getInfo(id);
    }
}
