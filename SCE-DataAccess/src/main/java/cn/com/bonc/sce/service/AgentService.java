package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.AgentDao;
import cn.com.bonc.sce.dao.UserInfoRepository;
import cn.com.bonc.sce.entity.Agent;
import cn.com.bonc.sce.entity.UserAudit;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2019/4/17.
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AgentService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private AgentDao agentDao;

    public RestRecord addAgent(Agent agent, String userId, Integer roleId) {
        agent.setIsDelete(1L);
        Long id = idWorker.nextId();
        agent.setId(id);
        agentDao.save(agent);
        userInfoRepository.updateOrganizationIdByUserId(agent.getId(), userId);
        UserAudit userAudit = new UserAudit();
        userAudit.setUserId(userId);
        userAudit.setUserType(roleId);
        userAudit.setEntityId(agent.getId());
        userAudit.setAuditStatus(0);
        userInfoRepository.save(userAudit);
        return new RestRecord(200, MessageConstants.SCE_MSG_0200);
    }

    public RestRecord updateAgentById(Agent agent) {
        int status = 0;
        Long agentId = agent.getId() == null ? 0 : agent.getId();
        String agentAddress = agent.getAgentAddress() == null ? "" : agent.getAgentAddress();
        String postcode = agent.getPostcode() == null ? "" : agent.getPostcode();
        String phone = agent.getPhone() == null ? "" : agent.getPhone();
        String agentEmail = agent.getAgentEmail() == null ? "" : agent.getAgentEmail();
        String agentWebsite = agent.getAgentWebsite() == null ? "" : agent.getAgentWebsite();
        status = agentDao.updateAgentById(agentId, agentAddress, postcode, phone, agentEmail, agentWebsite);
        return new RestRecord(200, MessageConstants.SCE_MSG_0200, status);
    }

    public Agent getAgentById(Long id) {
        return agentDao.findById(id);
    }

    public RestRecord updateAgent(Agent agent) {
        agent.setIsDelete(1L);
        agentDao.save(agent);
        UserAudit audit = userInfoRepository.findByEntityId(agent.getId());
        UserAudit userAudit = new UserAudit();
        userAudit.setId(audit.getId());
        userAudit.setUserId(audit.getUserId());
        userAudit.setUserType(audit.getUserType());
        userAudit.setEntityId(agent.getId());
        userAudit.setAuditStatus(0);
        userInfoRepository.save(userAudit);
        return new RestRecord(200, MessageConstants.SCE_MSG_0200);
    }

    public RestRecord addActingSchool(String AGENT_ID, String SCHOOL_ID) {
        userInfoRepository.addActingSchool(idWorker.nextId(),AGENT_ID, SCHOOL_ID);
        return new RestRecord(200, MessageConstants.SCE_MSG_0200);
    }

    public RestRecord deleteActingSchool(String AGENT_ID, String SCHOOL_ID) {
        userInfoRepository.deleteActingSchool(AGENT_ID, SCHOOL_ID);
        return new RestRecord(200, MessageConstants.SCE_MSG_0200);
    }

}
