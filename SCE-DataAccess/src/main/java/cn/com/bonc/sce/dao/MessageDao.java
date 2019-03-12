package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * 总消息
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Repository
public interface MessageDao extends JpaRepository< Message, Integer > {
    @Override
    Message save( Message message );

    @Modifying
    @Query( "UPDATE Message m SET m.isDelete=0 WHERE m.id=?1" )
    Integer updateDeleteStatusById( Integer id );

    @Query( value = "SELECT CREATE_TIME FROM (SELECT CREATE_TIME FROM STARCLOUDPORTAL.SCE_COMMON_USER_INFO WHERE USER_ID=?1 ORDER BY CREATE_TIME DESC ) WHERE ROWNUM<=1", nativeQuery = true )
    Timestamp getNewestTimeByUserId( String userId );


    @Query( value = "SELECT CREATE_TIME FROM SCE_COMMON_USER WHERE USER_ID=?1", nativeQuery = true )
    Timestamp getCreateTimeByUserId( String userId );

    List< Message > findByTypeInAndCreateTimeAfterAndIsDeleteOrTargetIdAndCreateTimeAfterAndIsDelete( List type, Timestamp createTime, Integer isDelete, String targetId, Timestamp createTime2, Integer isDelete2 );

    List< Message > findByTypeInAndIsDeleteOrTargetIdAndIsDelete( List type, Integer isDelete, String targetId, Integer isDelete2 );
}
