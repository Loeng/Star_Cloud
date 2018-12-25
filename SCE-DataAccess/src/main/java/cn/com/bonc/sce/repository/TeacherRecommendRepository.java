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
}