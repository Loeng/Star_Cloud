package cn.com.bonc.sce.dao.history;

import cn.com.bonc.sce.entity.history.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/27 20:12
 */
@Repository
public interface LoginHistoryDao extends JpaRepository< LoginHistory, Integer > {
}
