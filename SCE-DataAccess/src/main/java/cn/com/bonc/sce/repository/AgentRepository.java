package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author BTW
 */
public interface AgentRepository extends JpaRepository< Agent, String > {

    /**
     * 新增/更新代理实体信息
     *
     * @param s
     * @param <S>
     * @return
     */
    @Override
    < S extends Agent > S save( S s );

    /**
     * 查询所有实体及相关用户信息
     *
     * @param pageable
     * @return
     */
    @Query(value="SELECT A.AGENT_NAME,A.AGENT_AREA,A.GRADE,A.ESTABLISHING_TIME,A.JURIDICAL_PERSON,A.AGENT_REGISTATION_ID,A.AGENT_INTRODUCTION,A.AGENT_ADDRESS,A.AGENT_TAX_NUM,A.AGENT_WEBSITE,A.ID," +
            "B.USER_ID,B.LOGIN_NAME,B.USER_NAME,B.GENDER,B.USER_TYPE,B.MAIL_ADDRESS,\n" +
            "       B.PHONE_NUMBER,B.ADDRESS,B.IS_FIRST_LOGIN,B.LOGIN_PERMISSION_STATUS,B.CREATE_TIME \n" +
            " FROM STARCLOUDPORTAL.SCE_ENTITY_AGENT A \n" +
            " LEFT JOIN STARCLOUDPORTAL.SCE_COMMON_USER B ON A.ID=B.ORGANIZATION_ID AND A.IS_DELETE=1 AND B.IS_DELETE=1 ORDER BY B.CREATE_TIME DESC",
    countQuery = "SELECT COUNT(*) " +
            " FROM STARCLOUDPORTAL.SCE_ENTITY_AGENT A \n" +
            " LEFT JOIN STARCLOUDPORTAL.SCE_COMMON_USER B ON A.ID=B.ORGANIZATION_ID AND A.IS_DELETE=1 AND B.IS_DELETE=1",
    nativeQuery = true)
    Page<Map<String,Object> > getAllAgentUserInfo( Pageable pageable);
}
