package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 总消息
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Repository
@Transactional
public interface MessageDao extends JpaRepository<Message, Integer> {

    @Override
    Message save( Message message);

    @Modifying
    @Query( "UPDATE Message m SET m.isDelete=1 WHERE m.id=?1" )
    Integer updateDeleteStatusById( Integer id );

    @Query( value="SELECT CREATE_TIME FROM SCE_COMMON_USER_INFO WHERE USER_ID=?1 AND rownum<=1 ORDER BY CREATE_TIME DESC",nativeQuery=true )
    String getNewestTimeByUserId( String userId );

    List<Message> findByTargetIdAndCreateTimeGreaterThanAndIsDelete( String targetId, String createTime,Integer isDelete );
}
