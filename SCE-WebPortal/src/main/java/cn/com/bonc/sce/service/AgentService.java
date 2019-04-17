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

    public RestRecord addAgent( AgentModel agent,String userId,Integer roleId ) {
        return agentDao.addAgent( agent,userId,roleId );
    }

    public RestRecord updateAgentById(AgentModel agent ) {
        return agentDao.updateAgentById(agent );
    }

    public RestRecord getAgentById( String id ) {
        return agentDao.getAgentById( id );
    }

    public RestRecord updateAgent(AgentModel agent){
        return agentDao.updateAgent(agent);
    }

    public RestRecord getAllAgentUserInfo( Integer pageNum, Integer pageSize ) {
        return agentDao.getAllAgentUserInfo( pageNum, pageSize );
    }

    public RestRecord getAgentInfo( String AGENT_NAME,String PROPERTY,Integer AUDIT_STATUS,Integer pageNum ,Integer pageSize ) {
        return agentDao.getAgentInfo(AGENT_NAME,PROPERTY,AUDIT_STATUS, pageNum, pageSize );
    }

}
