package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
