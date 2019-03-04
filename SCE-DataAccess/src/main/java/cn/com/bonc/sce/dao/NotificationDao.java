package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 新闻
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Repository
public interface NotificationDao extends JpaRepository< Notification, Integer > {
    @Override
    Notification save( Notification banner );

    @Modifying
    @Query( "UPDATE Notification n SET n.isDelete=0 WHERE n.id=?1" )
    Integer updateDeleteStatusById( Integer id );

    @Query( nativeQuery = true,
            value =
                    "SELECT USER_ID FROM STARCLOUDPORTAL.SCE_COMMON_USER scu " +
                            "WHERE scu.ORGANIZATION_ID IN (" +
                            "SELECT TO_CHAR(id) FROM STARCLOUDPORTAL.SCE_ENTITY_INSTITUTION WHERE PROVINCE= :province) " )
    List< Object > selectUserIdByAddress( @Param( "province" ) String province );

    @Query( nativeQuery = true,
            value =
                    "SELECT USER_ID FROM STARCLOUDPORTAL.SCE_COMMON_USER scu " +
                            "WHERE scu.ORGANIZATION_ID IN (" +
                            "SELECT TO_CHAR(id) FROM STARCLOUDPORTAL.SCE_ENTITY_INSTITUTION WHERE PROVINCE= :province AND CITY=:city ) " )
    List< Object > selectUserIdByAddress( @Param( "province" ) String province, @Param( "city" ) String city );

    @Query( nativeQuery = true,
            value =
                    "SELECT USER_ID FROM STARCLOUDPORTAL.SCE_COMMON_USER scu " +
                            "WHERE scu.ORGANIZATION_ID IN (" +
                            "SELECT TO_CHAR(id) FROM STARCLOUDPORTAL.SCE_ENTITY_INSTITUTION WHERE PROVINCE= :province AND CITY=:city AND DISTRICT =:district) " )
    List< Object > selectUserIdByAddress( @Param( "province" ) String province, @Param( "city" ) String city, @Param( "district" ) String district );

    Notification findByIdAndIsDelete( Integer newsId, Integer isDelete );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnId( Integer isDelete,
                                                                  String content,
                                                                  Integer columnId,
                                                                  Pageable pageable );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnIdAndUpdateTimeBetween( Integer isDelete,
                                                                                      String content,
                                                                                      Integer columnId,
                                                                                      Date createTimeFrom,
                                                                                      Date createTimeTo,
                                                                                      Pageable pageable );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnIdAndContentType( Integer isDelete,
                                                                                String content,
                                                                                Integer columnId,
                                                                                Integer type,
                                                                                Pageable pageable );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnIdAndContentTypeAndUpdateTimeBetween( Integer isDelete,
                                                                                                    String content,
                                                                                                    Integer columnId,
                                                                                                    Integer contentType,
                                                                                                    Date createTimeFrom,
                                                                                                    Date createTimeTo,
                                                                                                    Pageable pageable );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnIdAndContentStatus( Integer isDelete,
                                                                                  String content,
                                                                                  Integer columnId,
                                                                                  String contentStatus,
                                                                                  Pageable pageable );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnIdAndContentStatusAndUpdateTimeBetween( Integer isDelete,
                                                                                                      String content,
                                                                                                      Integer columnId,
                                                                                                      String contentStatus,
                                                                                                      Date createTimeFrom,
                                                                                                      Date createTimeTo,
                                                                                                      Pageable pageable );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnIdAndContentTypeAndContentStatus( Integer isDelete,
                                                                                                String content,
                                                                                                Integer columnId,
                                                                                                Integer type,
                                                                                                String contentStatus,
                                                                                                Pageable pageable );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnIdAndContentTypeAndContentStatusAndUpdateTimeBetween( Integer isDelete,
                                                                                                                    String content,
                                                                                                                    Integer columnId,
                                                                                                                    Integer contentType,
                                                                                                                    String contentStatus,
                                                                                                                    Date createTimeFrom,
                                                                                                                    Date createTimeTo,
                                                                                                                    Pageable pageable );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnIdAndCreateUserIdIn( Integer isDelete,
                                                                                   String content,
                                                                                   Integer columnId,
                                                                                   List< Object > userIdList,
                                                                                   Pageable pageable );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnIdAndUpdateTimeBetweenAndCreateUserIdIn( Integer isDelete,
                                                                                                       String content,
                                                                                                       Integer columnId,
                                                                                                       Date createTimeFrom,
                                                                                                       Date createTimeTo,
                                                                                                       List< Object > userIdList,
                                                                                                       Pageable pageable );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnIdAndContentTypeAndCreateUserIdIn( Integer isDelete,
                                                                                                 String content,
                                                                                                 Integer columnId,
                                                                                                 Integer type,
                                                                                                 List< Object > userIdList,
                                                                                                 Pageable pageable );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnIdAndContentTypeAndUpdateTimeBetweenAndCreateUserIdIn( Integer isDelete,
                                                                                                                     String content,
                                                                                                                     Integer columnId,
                                                                                                                     Integer contentType,
                                                                                                                     Date createTimeFrom,
                                                                                                                     Date createTimeTo,
                                                                                                                     List< Object > userIdList,
                                                                                                                     Pageable pageable );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnIdAndContentStatusAndCreateUserIdIn( Integer isDelete,
                                                                                                   String content,
                                                                                                   Integer columnId,
                                                                                                   String contentStatus,
                                                                                                   List< Object > userIdList,
                                                                                                   Pageable pageable );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnIdAndContentStatusAndUpdateTimeBetweenAndCreateUserIdIn( Integer isDelete,
                                                                                                                       String content,
                                                                                                                       Integer columnId,
                                                                                                                       String contentStatus,
                                                                                                                       Date createTimeFrom,
                                                                                                                       Date createTimeTo,
                                                                                                                       List< Object > userIdList,
                                                                                                                       Pageable pageable );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnIdAndContentTypeAndContentStatusAndCreateUserIdIn( Integer isDelete,
                                                                                                                 String content,
                                                                                                                 Integer columnId,
                                                                                                                 Integer type,
                                                                                                                 String contentStatus,
                                                                                                                 List< Object > userIdList,
                                                                                                                 Pageable pageable );

    Page< Notification > findByIsDeleteAndContentLikeAndColumnIdAndContentTypeAndContentStatusAndUpdateTimeBetweenAndCreateUserIdIn( Integer isDelete,
                                                                                                                                     String content,
                                                                                                                                     Integer columnId,
                                                                                                                                     Integer contentType,
                                                                                                                                     String contentStatus,
                                                                                                                                     Date createTimeFrom,
                                                                                                                                     Date createTimeTo,
                                                                                                                                     List< Object > userIdList,
                                                                                                                                     Pageable pageable );
}
