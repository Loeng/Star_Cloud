package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 新闻
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Repository
public interface NewsDao extends JpaRepository< News, Integer > {
    @Override
    News save( News banner );

    @Modifying
    @Query( "UPDATE News n SET n.isDelete=0 WHERE n.id=?1" )
    Integer updateDeleteStatusById( Integer id );

    News findByIdAndIsDelete( Integer newsId, Integer isDelete );

    Page< News > findByIsDeleteAndContentLikeAndColumnId( Integer isDelete,
                                                          String content,
                                                          Integer columnId,
                                                          Pageable pageable );

    Page< News > findByIsDeleteAndContentLikeAndColumnIdAndUpdateTimeBetween( Integer isDelete,
                                                                              String content,
                                                                              Integer columnId,
                                                                              Date createTimeFrom,
                                                                              Date createTimeTo,
                                                                              Pageable pageable );

    Page< News > findByIsDeleteAndContentLikeAndColumnIdAndContentStatus( Integer isDelete,
                                                                          String content,
                                                                          Integer columnId,
                                                                          String contentStatus,
                                                                          Pageable pageable );

    Page< News > findByIsDeleteAndContentLikeAndColumnIdAndContentStatusAndUpdateTimeBetween( Integer isDelete,
                                                                                              String content,
                                                                                              Integer columnId,
                                                                                              String contentStatus,
                                                                                              Date createTimeFrom,
                                                                                              Date createTimeTo,
                                                                                              Pageable pageable );
}
