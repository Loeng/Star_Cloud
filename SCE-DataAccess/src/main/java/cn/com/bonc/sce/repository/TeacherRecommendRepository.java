package cn.com.bonc.sce.repository;

import cn.com.bonc.sce.entity.TeacherRecommend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Repository
@Transactional( rollbackFor = Exception.class )
public interface TeacherRecommendRepository extends JpaRepository< TeacherRecommend, Long >, JpaSpecificationExecutor< TeacherRecommend > {

    /**
     * 查询教师推荐应用列表
     *
     * @param userId    教师Id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageable  分页参数
     * @return
     */

    @Query( nativeQuery = true,
            value = "SELECT * FROM STARCLOUDMARKET.TEACHER_RECOMMEND_APP_VIEW TR " +
                    "WHERE \n" +
                    "TR.user_id = :userId \n" +
                    "AND TR.START_TIME >= to_date ( :startTime , 'yyyy-mm-dd hh24:mi:ss' ) \n" +
                    "AND TR.END_TIME <= to_date ( :endTime , 'yyyy-mm-dd hh24:mi:ss' )  ",
            countQuery = "SELECT COUNT(*) FROM STARCLOUDMARKET.TEACHER_RECOMMEND_APP_VIEW TR " +
                    "WHERE \n" +
                    "TR.user_id = :userId \n" +
                    "AND TR.START_TIME >= to_date ( :startTime , 'yyyy-mm-dd hh24:mi:ss' ) \n" +
                    "AND TR.END_TIME <= to_date ( :endTime , 'yyyy-mm-dd hh24:mi:ss' )  " )
    Page< List< Map< String, Object > > > findAllByUserIdAndTime(
            @Param( "userId" ) String userId,
            @Param( "startTime" ) String startTime,
            @Param( "endTime" ) String endTime,
            Pageable pageable );

    /**
     * 删除教师推荐应用
     *
     * @param userId    用户Id(此处为教师Id)
     * @param appIdList 应用Id列表
     * @return 影响条数
     */
    @Modifying
    @Query( "UPDATE TeacherRecommend SET IS_DELETE=0 WHERE USER_ID=:userId AND APP_ID in (:appIdList)" )
    int deleteTeacherRecommendByAppIdList(
            @Param( "userId" ) String userId,
            @Param( "appIdList" ) List< String > appIdList );

    /**
     * 查询教师推荐应用列表
     *
     * @param userId   用户Id
     * @param pageable 分页参数
     * @return
     */
    @Query( nativeQuery = true, value = "SELECT * FROM ( SELECT DISTINCT( INFO.APP_ID ), INFO.RECOMMEND_START_TIME, INFO.RECOMMEND_END_TIME, INFO.APP_NAME, INFO.APP_ICON, INFO.APP_NOTES, INFO.APP_SOURCE, INFO.APP_LINK,\n" +
            "\tDECODE( MAO.USER_ID, :userId, '1', '0' ) IS_OPEN,\n" +
            "\tDECODE( UAC.USER_ID, :userId, '1', '0' ) IS_COLLECTION \n" +
            "FROM ( SELECT TRA.APP_ID, TRA.RECOMMEND_START_TIME, TRA.RECOMMEND_END_TIME, MAI.APP_NAME, MAI.APP_ICON, MAI.APP_NOTES, MAI.APP_SOURCE, MAI.APP_LINK \n" +
            "FROM STARCLOUDMARKET.SCE_TEACHER_RECOMMEND_APP TRA LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_INFO MAI ON TRA.APP_ID = MAI.APP_ID  AND TRA.IS_DELETE = 1  ) INFO LEFT JOIN STARCLOUDMARKET.SCE_MARKET_APP_OPEN MAO ON MAO.APP_ID = INFO.APP_ID  \tAND MAO.IS_DELETE = 1  AND MAO.USER_ID = :userId \n" +
            "\tLEFT JOIN STARCLOUDMARKET.SCE_USER_APP_COLLECTION UAC ON INFO.APP_ID = UAC.APP_ID  AND UAC.USER_ID = :userId ) TEST\n" +
            "\tWHERE   TEST.RECOMMEND_END_TIME >=SYSDATE AND TEST.RECOMMEND_START_TIME <=SYSDATE " )
    Page< List< Map< String, Object > > > getTeacherRecommendList( @Param( "userId" ) String userId, Pageable pageable );

    /**
     * 教师点击推荐按钮。
     * 第一次则新增记录
     * 非第一次，如果收藏则取消，如果以未收藏则收藏
     *
     * @param userId 用户Id
     * @param appId  应用Id
     * @return
     */
     @Modifying
    @Query( nativeQuery = true, value =
            "MERGE INTO \"STARCLOUDMARKET\".\"SCE_TEACHER_RECOMMEND_APP\" t USING DUAL ON ( t.APP_ID = :appId AND t.USER_ID = :userId )  \n" +
                    "WHEN NOT matched THEN INSERT ( t.APP_ID, t.USER_ID, t.IS_DELETE , t.UPDATE_TIME) VALUES ( :appId, :userId, '1', SYSDATE ) \n" +
                    "WHEN matched THEN UPDATE  SET t.IS_DELETE = ABS( t.IS_DELETE - 1 ), t.UPDATE_TIME=SYSDATE" )
    int updateIsCommend( @Param( "userId" ) String userId, @Param( "appId" ) String appId );
}