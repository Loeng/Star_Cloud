package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.News;
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
public interface NewsDao extends JpaRepository< News, Integer > {
    @Override
    News save( News banner );

    @Modifying
    @Query( "UPDATE News n SET n.isDelete=1 WHERE n.id=?1" )
    Integer updateDeleteStatusById( Integer id );

    News findByIdAndIsDelete( Integer newsId, Integer isDelete );

    Page< News > findByIsDeleteAndContentStatusAndUpdateTimeBetween( Integer isDelete,
                                                                     String contentStatus,
                                                                     String createTimeFrom,
                                                                     String createTimeTo,
                                                                     Pageable pageable);
}
