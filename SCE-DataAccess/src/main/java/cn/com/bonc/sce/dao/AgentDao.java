package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2019/4/17.
 */
@Repository
public interface AgentDao extends JpaRepository<Agent, Integer > {

    @Override
    Agent save( Agent agent);

    @Modifying
    @Query(value = "UPDATE STARCLOUDPORTAL.SCE_ENTITY_AGENT SET AGENT_ADDRESS = ?2,POSTCODE = ?3,PHONE = ?4,AGENT_EMAIL = ?5,AGENT_WEBSITE = ?6 WHERE ID = ?1 ", nativeQuery = true)
    int updateAgentById(Long id,String agentAddress,String postcode,String phone,String agentEmail,String agentWebsite);

    Agent findById(Long id);

    @Query( value = "SELECT COUNT(*) FROM STARCLOUDPORTAL.SCE_ENTITY_AGENT A LEFT JOIN  STARCLOUDPORTAL.SCE_USER_AUDIT T ON A.ID=T.ENTITY_ID" +
            " WHERE T.AUDIT_STATUS = 1 AND A.ID=?1 ", nativeQuery = true )
    Integer getAgentAuditCountById(String ID);
}
