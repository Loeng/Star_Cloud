package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.UserMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 用户消息
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Repository
public interface UserMessageDao extends JpaRepository<UserMessage, Integer> {

    @Modifying
    @Query( "UPDATE UserMessage um SET um.isDelete=0 WHERE um.id=?1" )
    Integer updateDeleteStatusById( Integer id );

    @Modifying
    @Query( "UPDATE UserMessage um SET um.isDelete=0 WHERE um.messageId=?1" )
    Integer updateDeleteStatusByMessageId( Integer messageId );

    @Modifying
    @Query( "UPDATE UserMessage um SET um.isRead=1 WHERE um.id=?1" )
    Integer updateIsReadById( Integer id );

    Page<UserMessage> findByUserIdAndIsDelete( String userId, Integer isDelete, Pageable pageable);
}
