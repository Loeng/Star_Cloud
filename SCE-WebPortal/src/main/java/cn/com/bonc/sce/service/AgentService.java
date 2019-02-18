package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AgentDao;
import cn.com.bonc.sce.model.AgentModel;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {
    private AgentDao agentDao;

    @Autowired
    public AgentService( AgentDao agentDao ) {
        this.agentDao = agentDao;
    }

    public RestRecord saveAgentInfo( AgentModel agentModel ) {
        return agentDao.saveAgentInfo( agentModel );
    }

    public RestRecord getAllAgentUserInfo( Integer pageNum, Integer pageSize ) {
        return agentDao.getAllAgentUserInfo( pageNum, pageSize );
    }

}
