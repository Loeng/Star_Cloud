package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 机构
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Repository
public interface AuthorityDao extends JpaRepository< Authority, Integer > {
    Page< Authority > findByIsDelete( Integer isDelete ,Pageable pageable);
}
