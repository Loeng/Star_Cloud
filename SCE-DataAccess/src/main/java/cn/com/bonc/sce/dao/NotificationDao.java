package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 新闻
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Repository
@Transactional
public interface NotificationDao extends JpaRepository< Notification, Integer > {

    @Override
    Notification save( Notification banner );

    @Modifying
    @Query( "UPDATE Notification n SET n.isDelete=1 WHERE n.id=?1" )
    Integer updateDeleteStatusById( Integer id );

    Notification findByIdAndIsDelete( Integer newsId, Integer isDelete );

    Page< Notification > findByIsDeleteAndContentTypeAndContentStatusAndUpdateTimeBetween( Integer isDelete,
                                                                                           Integer contentType,
                                                                                           String contentStatus,
                                                                                           String createTimeFrom,
                                                                                           String createTimeTo,
                                                                                           Pageable pageable);
}
