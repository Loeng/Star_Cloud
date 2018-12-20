package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.model.message.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户消息
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Repository
@Transactional
public interface UserMessageDao extends JpaRepository<UserMessage, Integer> {

    @Modifying
    @Query( "UPDATE UserMessage um SET um.isDelete=1 WHERE um.id=?1" )
    Integer updateDeleteStatusById( Integer id );

    @Modifying
    @Query( "UPDATE UserMessage um SET um.isDelete=1 WHERE um.messageId=?1" )
    Integer updateDeleteStatusByMessageId( Integer messageId );

    @Modifying
    @Query( "UPDATE UserMessage um SET um.isRead=1 WHERE um.id=?1" )
    Integer updateIsReadById( Integer id );

    List<UserMessage> findByUserIdAndIsDelete(String userId,Integer isDelete);
}
