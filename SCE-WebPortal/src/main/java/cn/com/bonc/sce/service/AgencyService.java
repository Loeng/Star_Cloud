package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AgencyDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Charles on 2019/3/4.
 */
@Service
@Slf4j
public class AgencyService {

    @Autowired
    private AgencyDao agencyDao;

    public RestRecord editActivity(Integer id,Integer isActivate) {
        return agencyDao.editActivity(id,isActivate);
    }

    public RestRecord editInfo(String json){
        return agencyDao.editInfo(json);
    }

    public RestRecord getSchools(Integer id,Integer pageNum, Integer pageSize){
        return agencyDao.getSchools(id,pageNum,pageSize);
    }

    public RestRecord delSchoolRel(Integer agentId,Integer schoolId){
        return agencyDao.delSchoolRel(agentId,schoolId);
    }

    public RestRecord getAgents(String agentName,Integer isActivate,Integer pageNum, Integer pageSize){
        return agencyDao.getAgents(agentName,isActivate,pageNum,pageSize);
    }

    public RestRecord insertInfo(String json){
        return agencyDao.insertInfo(json);
    }
}
