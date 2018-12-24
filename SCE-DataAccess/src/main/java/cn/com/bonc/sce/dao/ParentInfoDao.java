package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.ParentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 家长信息表
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Repository
@Transactional
public interface ParentInfoDao extends JpaRepository< ParentInfo, Integer > {
    @Override
    ParentInfo save( ParentInfo user );
}